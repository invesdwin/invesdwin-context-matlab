function installed = callback_packageInstalled(package)
	[user_packages, system_packages] = pkg ("list", "instrument-control")
	if length(user_packages) == 0 && length(system_packages) == 0
		installed = false
	else	
		installed = true
	end
end

if !callback_packageInstalled("instrument-control")
    pkg install -forge instrument-control
end
pkg load instrument-control

if !callback_packageInstalled("io")
	pkg install -forge io
end
pkg load io

global globalSocketScriptTaskCallbackContextUuid = socketScriptTaskCallbackContextUuid
global globalSocketScriptTaskCallbackServerHost = socketScriptTaskCallbackServerHost
global globalSocketScriptTaskCallbackServerPort = socketScriptTaskCallbackServerPort

function callback_createSocket()
	global globalSocketScriptTaskCallbackSocket
	global globalSocketScriptTaskCallbackServerHost
	global globalSocketScriptTaskCallbackServerPort
	global globalSocketScriptTaskCallbackContextUuid
    globalSocketScriptTaskCallbackSocket = tcpclient(globalSocketScriptTaskCallbackServerHost, globalSocketScriptTaskCallbackServerPort)
    writeline(globalSocketScriptTaskCallbackSocket, globalSocketScriptTaskCallbackContextUuid)
end

function result = callback_invokeSocket(parameters)
	global globalSocketScriptTaskCallbackSocket
    dims = arrayfun(@(x) size(x), parameters, "UniformOutput", false)
    disp("1")
    jsonDims = toJSON(dims)
    disp("1a")
    disp(parameters)
    jsonParameters = toJSON(parameters)
    disp("1b")
    message = strcat(jsonDims, ';', jsonParameters)
    disp("2")
    writeline(globalSocketScriptTaskCallbackSocket, message)
    disp("3")
    returnExpression = readline(globalSocketScriptTaskCallbackSocket)
    disp("4")
    result = eval(returnExpression)
end

function result = callback(varargin)
	global globalSocketScriptTaskCallbackContext
	disp("context")
	disp(globalSocketScriptTaskCallbackContext)
	disp(length(globalSocketScriptTaskCallbackContext))
    if length(globalSocketScriptTaskCallbackContext) == 0
    	global globalSocketScriptTaskCallbackContextUuid
        if globalSocketScriptTaskCallbackContextUuid != 0
        	disp("create")
            callback_createSocket()
        else
            error('IScriptTaskCallback not available')
        end
    end
    disp("invoke")
    result = callback_invokeSocket(varargin)
end

