global globalSocketScriptTaskCallbackContextRequestFile;
globalSocketScriptTaskCallbackContextRequestFile = globalSocketScriptTaskCallbackContextRequestFile;
global globalSocketScriptTaskCallbackContextResponseFile;
globalSocketScriptTaskCallbackContextResponseFile = globalSocketScriptTaskCallbackContextResponseFile;

function B = callback_dims(A)
    for i = 1 : length(A)
    	if ismatrix(A(i)) && typeof(A(i)) ~= 'string'
    		disp(A(i))
        	B(i) = size(A(i));
        else
        	B(i) = %nan;
        end
    end
endfunction

function result = callback(varargin)
	global globalSocketScriptTaskCallbackContextRequestFile;
	global globalSocketScriptTaskCallbackContextResponseFile;
    if length(globalSocketScriptTaskCallbackContextRequestFile) == 0 || length(globalSocketScriptTaskCallbackContextResponseFile) == 0
        error('IScriptTaskCallback not available');
    end
    dims = callback_dims(varargin);
	message = strcat([toJSON(dims), ';', toJSON(varargin)]);
	requestFd = mopen(globalSocketScriptTaskCallbackContextRequestFile, "wt");
    mputstr(message, requestFd);
    mclose(requestFd);
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

