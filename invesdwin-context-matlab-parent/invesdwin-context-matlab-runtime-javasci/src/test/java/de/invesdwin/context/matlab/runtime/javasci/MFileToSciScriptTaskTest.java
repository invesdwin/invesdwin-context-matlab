package de.invesdwin.context.matlab.runtime.javasci;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.springframework.core.io.Resource;

import de.invesdwin.context.ContextProperties;
import de.invesdwin.context.beans.init.PreMergedContext;
import de.invesdwin.context.test.ATest;

@NotThreadSafe
public class MFileToSciScriptTaskTest extends ATest {

    @Test
    public void testConversion() throws IOException {
        final String pattern = "classpath*:" + de.invesdwin.context.matlab.runtime.contract.InputsAndResultsTests.class
                .getPackage().getName().replace(".", "/") + "/**/*.m";
        final Resource[] mFiles = PreMergedContext.getInstance().getResources(pattern);
        for (final Resource mFile : mFiles) {
            final String sciStr = new MFileToSciScriptTask(mFile.getInputStream()).run();
            FileUtils.writeStringToFile(
                    new File(ContextProperties.getCacheDirectory(), mFile.getFilename().replace(".m", ".sce")), sciStr,
                    Charset.defaultCharset());
        }
    }

}
