global globalSocketScriptTaskCallbackContextRequestFile;
globalSocketScriptTaskCallbackContextRequestFile = socketScriptTaskCallbackContextRequestFile;
global globalSocketScriptTaskCallbackContextResponseFile;
globalSocketScriptTaskCallbackContextResponseFile = socketScriptTaskCallbackContextResponseFile;
global globalSocketScriptTaskCallbackContextRequestDoneFile;
globalSocketScriptTaskCallbackContextRequestDoneFile = socketScriptTaskCallbackContextRequestDoneFile;
global globalSocketScriptTaskCallbackContextResponseDoneFile;
globalSocketScriptTaskCallbackContextResponseDoneFile = socketScriptTaskCallbackContextResponseDoneFile;

if length(globalSocketScriptTaskCallbackContextRequestDoneFile) == 0 || length(globalSocketScriptTaskCallbackContextResponseDoneFile) == 0 || length(globalSocketScriptTaskCallbackContextRequestFile) == 0 || length(globalSocketScriptTaskCallbackContextResponseFile) == 0
    error('IScriptTaskCallback not available');
end

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
	global globalSocketScriptTaskCallbackContextRequestFile;
	global globalSocketScriptTaskCallbackContextResponseFile;
	global globalSocketScriptTaskCallbackContextRequestDoneFile;
	global globalSocketScriptTaskCallbackContextResponseDoneFile;
    dims = callback_dims(varargin);
	message = strcat([toJSON(dims), ';', toJSON(varargin)]);
	requestFd = mopen(globalSocketScriptTaskCallbackContextRequestFile, "wt");
    mputstr(message, requestFd);
    mclose(requestFd);
    requestDoneFd = mopen(globalSocketScriptTaskCallbackContextRequestDoneFile, "wt");
    mputstr('', requestDoneFd);
    mclose(requestDoneFd);
    while ~isfile(globalSocketScriptTaskCallbackContextResponseDoneFile)
    	sleep(1);
    end
    responseLength = fileinfo(globalSocketScriptTaskCallbackContextResponseFile)(1); 
    responseFd = mopen(globalSocketScriptTaskCallbackContextResponseFile, "rt");
    returnExpression = mgetstr(responseLength, responseFd);
    mclose(responseFd);
    mdelete(globalSocketScriptTaskCallbackContextResponseFile);
    mdelete(globalSocketScriptTaskCallbackContextResponseDoneFile);
    result = evstr(returnExpression);
endfunction

