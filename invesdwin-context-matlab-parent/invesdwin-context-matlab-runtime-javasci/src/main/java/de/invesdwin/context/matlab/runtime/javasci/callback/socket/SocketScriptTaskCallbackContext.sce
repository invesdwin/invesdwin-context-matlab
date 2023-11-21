function callback_createSocket()
	global socketScriptTaskCallbackSocket
	global socketScriptTaskCallbackServerHost
	global socketScriptTaskCallbackServerPort
	global socketScriptTaskCallbackContextUuid
    socketScriptTaskCallbackSocket = tcpclient(socketScriptTaskCallbackServerHost, socketScriptTaskCallbackServerPort)
    writeline(socketScriptTaskCallbackSocket, socketScriptTaskCallbackContextUuid)
end

function result = callback_invokeSocket(methodName, parameters)
	global socketScriptTaskCallbackSocket
    dims = arrayfun(@(x) size(x), parameters)
    writeline(socketScriptTaskCallbackSocket, strcat({'methodName '}, ';', jsonencode(dims), ';', jsonencode(parameters)))
    returnExpression = readline(socketScriptTaskCallbackSocket)
    result = eval(returnExpression)
end

function result = callback(methodName, varargin)
	global socketScriptTaskCallbackContext
    if !exist('socketScriptTaskCallbackContext')
    	global socketScriptTaskCallbackContextUuid
        if exist('socketScriptTaskCallbackContextUuid')
            callback_createSocket()
        else
            error('IScriptTaskCallback not available')
        end
    end
    result = callback_invokeSocket(methodName, varargin)
end
