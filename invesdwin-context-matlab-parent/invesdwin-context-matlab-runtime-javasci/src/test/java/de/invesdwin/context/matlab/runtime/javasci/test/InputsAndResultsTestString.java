package de.invesdwin.context.matlab.runtime.javasci.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.concurrent.NotThreadSafe;

import org.springframework.core.io.ClassPathResource;

import de.invesdwin.context.integration.script.IScriptTaskEngine;
import de.invesdwin.context.integration.script.IScriptTaskInputs;
import de.invesdwin.context.integration.script.IScriptTaskResults;
import de.invesdwin.context.matlab.runtime.contract.AScriptTaskMatlab;
import de.invesdwin.context.matlab.runtime.contract.IScriptTaskRunnerMatlab;
import de.invesdwin.util.assertions.Assertions;
import de.invesdwin.util.lang.Objects;

@NotThreadSafe
public class InputsAndResultsTestString {

    private final IScriptTaskRunnerMatlab runner;

    public InputsAndResultsTestString(final IScriptTaskRunnerMatlab runner) {
        this.runner = runner;
    }

    public void testString() {
        //putString
        final String putString = "asdf";
        final String putStringWithNull = null;

        //putStringVector
        final String[] putStringVector = new String[3];
        for (int i = 0; i < putStringVector.length; i++) {
            putStringVector[i] = i + "-" + i;
        }
        final String[] putStringVectorWithNull = Objects.deepClone(putStringVector);
        putStringVectorWithNull[1] = null;

        //putStringVectorAsList
        final List<String> putStringVectorAsList = Arrays.asList(putStringVector);
        final List<String> putStringVectorAsListWN = Objects.deepClone(putStringVectorAsList);
        putStringVectorAsListWN.set(1, null);

        //putStringMatrix
        final String[][] putStringMatrix = new String[4][];
        for (int i = 0; i < putStringMatrix.length; i++) {
            final String[] vector = new String[3];
            for (int j = 0; j < vector.length; j++) {
                vector[j] = i + "" + j + "-" + i + "" + j;
            }
            putStringMatrix[i] = vector;
        }
        final String[][] putStringMatrixWithNull = Objects.deepClone(putStringMatrix);
        for (int i = 0; i < putStringMatrixWithNull[0].length; i++) {
            putStringMatrixWithNull[i][i] = null;
        }

        //putStringMatrixAsList
        final List<List<String>> putStringMatrixAsList = new ArrayList<List<String>>(putStringMatrix.length);
        for (final String[] vector : putStringMatrix) {
            putStringMatrixAsList.add(Arrays.asList(vector));
        }
        final List<List<String>> putStringMatrixAsListWN = Objects.deepClone(putStringMatrixAsList);
        for (int i = 0; i < putStringMatrixAsListWN.get(0).size(); i++) {
            putStringMatrixAsListWN.get(i).set(i, null);
        }

        new AScriptTaskMatlab<Void>() {

            @Override
            public void populateInputs(final IScriptTaskInputs inputs) {
                inputs.putString("putString", putString);
                inputs.putString("putStringWithNull", putStringWithNull);

                inputs.putStringVector("putStringVector", putStringVector);
                inputs.putStringVector("putStringVectorWithNull", putStringVectorWithNull);

                inputs.putStringVectorAsList("putStringVectorAsList", putStringVectorAsList);
                inputs.putStringVectorAsList("putStringVectorAsListWN", putStringVectorAsListWN);

                inputs.putStringMatrix("putStringMatrix", putStringMatrix);
                inputs.putStringMatrix("putStringMatrixWithNull", putStringMatrixWithNull);

                inputs.putStringMatrixAsList("putStringMatrixAsList", putStringMatrixAsList);
                inputs.putStringMatrixAsList("putStringMatrixAsListWN", putStringMatrixAsListWN);
            }

            @Override
            public void executeScript(final IScriptTaskEngine engine) {
                engine.eval(new ClassPathResource(InputsAndResultsTestString.class.getSimpleName() + ".sce",
                        InputsAndResultsTestString.class));
            }

            @Override
            public Void extractResults(final IScriptTaskResults results) {
                //getString
                final String getString = results.getString("getString");
                Assertions.assertThat(putString).isEqualTo(getString);
                final String getStringWithNull = results.getString("getStringWithNull");
                Assertions.assertThat(putStringWithNull).isEqualTo(getStringWithNull);

                //getStringVector
                final String[] getStringVector = results.getStringVector("getStringVector");
                Assertions.assertThat(putStringVector).isEqualTo(getStringVector);
                final String[] getStringVectorWithNull = results.getStringVector("getStringVectorWithNull");
                Assertions.assertThat(putStringVectorWithNull).isEqualTo(getStringVectorWithNull);

                //getStringVectorAsList
                final List<String> getStringVectorAsList = results.getStringVectorAsList("getStringVectorAsList");
                Assertions.assertThat(putStringVectorAsList).isEqualTo(getStringVectorAsList);
                final List<String> getStringVectorAsListWN = results.getStringVectorAsList("getStringVectorAsListWN");
                Assertions.assertThat(putStringVectorAsListWN).isEqualTo(getStringVectorAsListWN);

                //getStringMatrix
                final String[][] getStringMatrix = results.getStringMatrix("getStringMatrix");
                Assertions.assertThat(putStringMatrix).isEqualTo(getStringMatrix);
                final String[][] getStringMatrixWithNull = results.getStringMatrix("getStringMatrixWithNull");
                Assertions.assertThat(putStringMatrixWithNull).isEqualTo(getStringMatrixWithNull);

                //getStringMatrixAsList
                final List<List<String>> getStringMatrixAsList = results.getStringMatrixAsList("getStringMatrixAsList");
                Assertions.assertThat(putStringMatrixAsList).isEqualTo(getStringMatrixAsList);
                final List<List<String>> getStringMatrixAsListWN = results
                        .getStringMatrixAsList("getStringMatrixAsListWN");
                Assertions.assertThat(putStringMatrixAsListWN).isEqualTo(getStringMatrixAsListWN);
                return null;
            }
        }.run(runner);
    }

}
