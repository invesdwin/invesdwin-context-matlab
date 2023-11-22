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

global globalSocketScriptTaskCallbackContextUuid = socketScriptTaskCallbackContextUuid;
global globalSocketScriptTaskCallbackServerHost = socketScriptTaskCallbackServerHost;
global globalSocketScriptTaskCallbackServerPort = socketScriptTaskCallbackServerPort;
global globalSocketScriptTaskCallbackConnected = false;

function callback_createSocket()
	global globalSocketScriptTaskCallbackSocket;
	global globalSocketScriptTaskCallbackServerHost;
	global globalSocketScriptTaskCallbackServerPort;
	global globalSocketScriptTaskCallbackContextUuid;
	global globalSocketScriptTaskCallbackConnected;
    globalSocketScriptTaskCallbackSocket = tcpclient(globalSocketScriptTaskCallbackServerHost, globalSocketScriptTaskCallbackServerPort);
    writeline(globalSocketScriptTaskCallbackSocket, globalSocketScriptTaskCallbackContextUuid);
    globalSocketScriptTaskCallbackConnected = true;
end

function result = callback_invokeSocket(parameters)
	global globalSocketScriptTaskCallbackSocket;
    writeline(globalSocketScriptTaskCallbackSocket, strcat(toJSON(cellfun(@(x) size(x), parameters, "UniformOutput", false)), ';', toJSON(parameters)));
    returnExpression = readline(globalSocketScriptTaskCallbackSocket);
    result = eval(returnExpression);
end

function result = callback(varargin)
	global globalSocketScriptTaskCallbackConnected;
    if !globalSocketScriptTaskCallbackConnected
    	global globalSocketScriptTaskCallbackContextUuid;
        if length(globalSocketScriptTaskCallbackContextUuid) != 0
            callback_createSocket();
        else
            error('IScriptTaskCallback not available');
        end
    end
    result = callback_invokeSocket(varargin);
end

