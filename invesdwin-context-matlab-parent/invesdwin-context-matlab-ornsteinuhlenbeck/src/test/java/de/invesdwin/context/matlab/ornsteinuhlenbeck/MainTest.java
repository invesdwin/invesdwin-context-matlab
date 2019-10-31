package de.invesdwin.context.matlab.ornsteinuhlenbeck;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.concurrent.NotThreadSafe;

import org.junit.Test;
import org.springframework.core.io.ClassPathResource;

import de.invesdwin.context.ContextProperties;
import de.invesdwin.context.PlatformInitializerProperties;
import de.invesdwin.context.matlab.runtime.contract.ProvidedScriptTaskRunnerMatlab;
import de.invesdwin.context.matlab.runtime.javaoctave.JavaOctaveScriptTaskRunnerMatlab;
import de.invesdwin.util.assertions.Assertions;
import de.invesdwin.util.lang.Files;
import de.invesdwin.util.math.decimal.Decimal;

@NotThreadSafe
public class MainTest {

    static {
        PlatformInitializerProperties.setAllowed(false);
    }

    @Test
    public void test() throws IOException {
        final String providedInstanceProperty = "-D" + ProvidedScriptTaskRunnerMatlab.PROVIDED_INSTANCE_KEY + "="
                + JavaOctaveScriptTaskRunnerMatlab.class.getName();
        final String inputFile = new ClassPathResource(MainTest.class.getSimpleName() + "_input.csv", MainTest.class)
                .getFile()
                .getAbsolutePath();
        final String outputFile = new File(ContextProperties.TEMP_DIRECTORY,
                MainTest.class.getSimpleName() + "_output.csv").getAbsolutePath();
        Main.main(new String[] { providedInstanceProperty, "-i", inputFile, "-o", outputFile });
        final List<String> optimalFStrs = Files.readLines(new File(outputFile), Charset.defaultCharset());
        final List<Decimal> optimalFs = new ArrayList<Decimal>();
        for (final String optimalFStr : optimalFStrs) {
            optimalFs.add(new Decimal(optimalFStr).round(3));
        }
        Assertions.assertThat(optimalFs).isEqualTo(Arrays.asList(new Decimal("0.052"), new Decimal("0.213")));
    }

}
