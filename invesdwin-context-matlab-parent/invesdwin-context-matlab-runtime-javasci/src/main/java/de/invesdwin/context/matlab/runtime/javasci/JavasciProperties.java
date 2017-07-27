package de.invesdwin.context.matlab.runtime.javasci;

import java.util.Collections;
import java.util.List;

import javax.annotation.concurrent.Immutable;

import de.invesdwin.context.system.properties.SystemProperties;

@Immutable
public final class JavasciProperties {

    public static final List<String> JAVASCI_LIBRARY_PATHS;
    public static final String SCILAB_PATH;

    static {
        final SystemProperties systemProperties = new SystemProperties(JavasciProperties.class);
        if (systemProperties.containsValue("JAVASCI_LIBRARY_PATHS")) {
            JAVASCI_LIBRARY_PATHS = systemProperties.getList("JAVASCI_LIBRARY_PATHS");
        } else {
            JAVASCI_LIBRARY_PATHS = Collections.emptyList();
        }
        if (systemProperties.containsValue("SCILAB_PATH")) {
            SCILAB_PATH = systemProperties.getString("SCILAB_PATH");
        } else {
            SCILAB_PATH = null;
        }
    }

    private JavasciProperties() {}

}
