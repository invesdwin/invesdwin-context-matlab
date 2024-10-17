global globalSocketScriptTaskCallbackContextRequestPartFile;
globalSocketScriptTaskCallbackContextRequestPartFile = socketScriptTaskCallbackContextRequestPartFile;
global globalSocketScriptTaskCallbackContextRequestFile;
globalSocketScriptTaskCallbackContextRequestFile = socketScriptTaskCallbackContextRequestFile;
global globalSocketScriptTaskCallbackContextResponseFile;
globalSocketScriptTaskCallbackContextResponseFile = socketScriptTaskCallbackContextResponseFile;

function result = callback_dims(parameters)
    for i = 1 : length(parameters)
    	try
		    result(i) = size(parameters(i));
		catch
			try
				result(i) = length(parameters(i));
			catch
		    	result(i) = 0;
		    end
		end
    end
endfunction

function result = callback(varargin)
	global globalSocketScriptTaskCallbackContextRequestPartFile;
	global globalSocketScriptTaskCallbackContextRequestFile;
	global globalSocketScriptTaskCallbackContextResponseFile;
    if length(globalSocketScriptTaskCallbackContextRequestPartFile) == 0 || length(globalSocketScriptTaskCallbackContextRequestFile) == 0 || length(globalSocketScriptTaskCallbackContextResponseFile) == 0
        error('IScriptTaskCallback not available');
    end
    dims = callback_dims(varargin);
	message = strcat([toJSON(dims), ';', toJSON(varargin)]);
	requestFd = mopen(globalSocketScriptTaskCallbackContextRequestPartFile, "wt");
    mputstr(message, requestFd);
    mclose(requestFd);
    movefile(globalSocketScriptTaskCallbackContextRequestPartFile, globalSocketScriptTaskCallbackContextRequestFile);
    while ~isfile(globalSocketScriptTaskCallbackContextResponseFile)
    	sleep(1);
    end
    responseLength = fileinfo(globalSocketScriptTaskCallbackContextResponseFile)(1);
    retry = %T;
    while retry
    	try
	    	responseFd = mopen(globalSocketScriptTaskCallbackContextResponseFile, "rt");
		    returnExpression = mgetstr(responseLength, responseFd);
		    mclose(responseFd);
		    mdelete(globalSocketScriptTaskCallbackContextResponseFile);
		    result = evstr(returnExpression);
		    retry = %F;
		catch
			//windows file lock might still be active
			sleep(1);
		end
    end
endfunction

