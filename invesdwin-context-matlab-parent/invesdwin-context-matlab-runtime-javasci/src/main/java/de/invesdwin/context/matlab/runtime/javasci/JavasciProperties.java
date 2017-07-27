package de.invesdwin.context.matlab.runtime.javasci;

import java.io.File;

import javax.annotation.concurrent.Immutable;

import de.invesdwin.context.system.properties.SystemProperties;

@Immutable
public final class JavasciProperties {

    public static final File JAVASCI_LIBRARY_PATH;
    public static final File SCILAB_PATH;

    static {
        final SystemProperties systemProperties = new SystemProperties(JavasciProperties.class);
        if (systemProperties.containsValue("JAVASCI_LIBRARY_PATH")) {
            JAVASCI_LIBRARY_PATH = systemProperties.getFile("JAVASCI_LIBRARY_PATH");
        } else {
            JAVASCI_LIBRARY_PATH = null;
        }
        if (systemProperties.containsValue("SCILAB_PATH")) {
            SCILAB_PATH = systemProperties.getFile("SCILAB_PATH");
        } else {
            SCILAB_PATH = null;
        }
    }

    private JavasciProperties() {}

}
