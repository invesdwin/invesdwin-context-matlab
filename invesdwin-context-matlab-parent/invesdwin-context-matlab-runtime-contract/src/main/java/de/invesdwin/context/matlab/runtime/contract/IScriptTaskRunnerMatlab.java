package de.invesdwin.context.matlab.runtime.contract;

import de.invesdwin.context.log.Log;

public interface IScriptTaskRunnerMatlab {

    String CLEANUP_SCRIPT = "rm(list=ls(all=TRUE))";

    Log LOG = new Log(IScriptTaskRunnerMatlab.class);

    <T> T run(AScriptTaskMatlab<T> scriptTask);

}
