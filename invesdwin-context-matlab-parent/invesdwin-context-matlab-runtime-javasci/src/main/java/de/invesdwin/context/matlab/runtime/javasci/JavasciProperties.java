package de.invesdwin.context.matlab.runtime.javasci;

import java.io.File;

import javax.annotation.concurrent.Immutable;

import de.invesdwin.context.system.properties.SystemProperties;

@Immutable
public final class JavasciProperties {

    public static final File JAVASCI_LIBRARY_PATH;

    static {
        final SystemProperties systemProperties = new SystemProperties(JavasciProperties.class);
        if (systemProperties.containsValue("JAVASCI_LIBRARY_PATH")) {
            JAVASCI_LIBRARY_PATH = systemProperties.getFile("JAVASCI_LIBRARY_PATH");
        } else {
            JAVASCI_LIBRARY_PATH = null;
        }
    }

    private JavasciProperties() {}

}
