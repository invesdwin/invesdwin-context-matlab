package de.invesdwin.context.matlab.runtime.matlabconsolectl;

import javax.annotation.concurrent.Immutable;

import de.invesdwin.context.system.properties.SystemProperties;

@Immutable
public final class MatlabConsoleCtlProperties {

    public static final String MATLAB_COMMAND;

    static {
        final SystemProperties systemProperties = new SystemProperties(MatlabConsoleCtlProperties.class);
        if (systemProperties.containsValue("MATLAB_COMMAND")) {
            MATLAB_COMMAND = systemProperties.getString("MATLAB_COMMAND");
        } else {
            MATLAB_COMMAND = null;
        }
    }

    private MatlabConsoleCtlProperties() {}

}
