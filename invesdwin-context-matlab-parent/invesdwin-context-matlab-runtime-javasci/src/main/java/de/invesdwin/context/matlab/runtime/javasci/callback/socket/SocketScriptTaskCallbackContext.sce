global globalSocketScriptTaskCallbackContextUuid;
globalSocketScriptTaskCallbackContextUuid = socketScriptTaskCallbackContextUuid;
global globalSocketScriptTaskCallbackServerHost;
globalSocketScriptTaskCallbackServerHost = socketScriptTaskCallbackServerHost;
global globalSocketScriptTaskCallbackServerPort;
 globalSocketScriptTaskCallbackServerPort = socketScriptTaskCallbackServerPort;
global globalSocketScriptTaskCallbackConnected;
globalSocketScriptTaskCallbackConnected = %F;

function B = callback_cellfun(func,A)
    for i = 1 : size(A,'r')
        B(i) = func(A(i))
    end
endfunction

function callback_createSocket()
	global globalSocketScriptTaskCallbackSocket;
	global globalSocketScriptTaskCallbackServerHost;
	global globalSocketScriptTaskCallbackServerPort;
	global globalSocketScriptTaskCallbackContextUuid;
	global globalSocketScriptTaskCallbackConnected;
    globalSocketScriptTaskCallbackSocket = tcpclient(globalSocketScriptTaskCallbackServerHost, globalSocketScriptTaskCallbackServerPort);
    writeline(globalSocketScriptTaskCallbackSocket, globalSocketScriptTaskCallbackContextUuid);
    globalSocketScriptTaskCallbackConnected = %T;
endfunction

function result = callback_invokeSocket(parameters)
	global globalSocketScriptTaskCallbackSocket;
	dims = callback_cellfun(size, parameters);
    writeline(globalSocketScriptTaskCallbackSocket, strcat(toJSON(dims), ';', toJSON(parameters)));
    returnExpression = readline(globalSocketScriptTaskCallbackSocket);
    result = eval(returnExpression);
endfunction

function result = callback(varargin)
	global globalSocketScriptTaskCallbackConnected;
    if ~globalSocketScriptTaskCallbackConnected
    	global globalSocketScriptTaskCallbackContextUuid;
        if length(globalSocketScriptTaskCallbackContextUuid) != 0
            callback_createSocket();
        else
            error('IScriptTaskCallback not available');
        end
    end
    result = callback_invokeSocket(varargin);
endfunction

