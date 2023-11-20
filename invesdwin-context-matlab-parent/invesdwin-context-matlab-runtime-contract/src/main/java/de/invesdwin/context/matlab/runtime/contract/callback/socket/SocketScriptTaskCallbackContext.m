function callback_createSocket()
	global socketScriptTaskCallbackSocket
    socketScriptTaskCallbackSocket = tcpclient(socketScriptTaskCallbackServerHost, socketScriptTaskCallbackServerPort)
    writeline(socketScriptTaskCallbackSocket, socketScriptTaskCallbackContextUuid)
end

function result = callback_invokeSocket(methodName, parameters)
    dims = arrayfun(@(x) size(x), parameters)
    writeline(socketScriptTaskCallbackSocket, strcat({'methodName '}, ';', jsonencode(dims), ';', jsonencode(parameters)))
    returnExpression = readline(socketScriptTaskCallbackSocket)
    result = eval(returnExpression)
end

function result = callback(methodName, varargin)
    if !exist('socketScriptTaskCallbackContext')
        if exist('socketScriptTaskCallbackContextUuid')
            callback_createSocket()
        else
            stop('IScriptTaskCallback not available')
        end
    end
    result = callback_invokeSocket(methodName, varargin)
end
