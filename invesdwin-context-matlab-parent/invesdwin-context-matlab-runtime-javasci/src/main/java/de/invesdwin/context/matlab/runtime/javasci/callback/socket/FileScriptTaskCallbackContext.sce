global globalSocketScriptTaskCallbackContextRequestPartFile;
globalSocketScriptTaskCallbackContextRequestPartFile = socketScriptTaskCallbackContextRequestPartFile;
global globalSocketScriptTaskCallbackContextRequestFile;
globalSocketScriptTaskCallbackContextRequestFile = socketScriptTaskCallbackContextRequestFile;
global globalSocketScriptTaskCallbackContextResponseFile;
globalSocketScriptTaskCallbackContextResponseFile = socketScriptTaskCallbackContextResponseFile;

function B = callback_dims(A)
    for i = 1 : length(A)
    	try
		    B(i) = size(A(i));
		catch
		    B(i) = "";
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
    //jackson does not support nan values: https://github.com/FasterXML/jackson-databind/issues/1818
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
    responseFd = mopen(globalSocketScriptTaskCallbackContextResponseFile, "rt");
    returnExpression = mgetstr(responseLength, responseFd);
    mclose(responseFd);
    mdelete(globalSocketScriptTaskCallbackContextResponseFile);
    result = evstr(returnExpression);
endfunction

