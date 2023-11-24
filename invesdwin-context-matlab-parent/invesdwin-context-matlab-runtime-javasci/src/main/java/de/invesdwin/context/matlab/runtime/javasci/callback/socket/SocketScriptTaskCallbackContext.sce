//might have to install this via the Scilab GUI on windows
atomsInstall("socket_toolbox")

global globalSocketScriptTaskCallbackContextUuid;
globalSocketScriptTaskCallbackContextUuid = socketScriptTaskCallbackContextUuid;
global globalSocketScriptTaskCallbackServerHost;
globalSocketScriptTaskCallbackServerHost = socketScriptTaskCallbackServerHost;
global globalSocketScriptTaskCallbackServerPort;
globalSocketScriptTaskCallbackServerPort = socketScriptTaskCallbackServerPort;
global globalSocketScriptTaskCallbackConnected;
globalSocketScriptTaskCallbackConnected = %F;

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

function callback_createSocket()
	global globalSocketScriptTaskCallbackSocket;
	global globalSocketScriptTaskCallbackServerHost;
	global globalSocketScriptTaskCallbackServerPort;
	global globalSocketScriptTaskCallbackContextUuid;
	global globalSocketScriptTaskCallbackConnected;
    globalSocketScriptTaskCallbackSocket = 1;
    //Caused by: org.scilab.modules.javasci.JavasciException$ScilabErrorException: A Scilab error occurred: Scilab 'TCL_EvalStr' Funktion in -nogui oder -nwni Modus deaktiviert.
    SOCKET_open(globalSocketScriptTaskCallbackSocket, globalSocketScriptTaskCallbackServerHost, globalSocketScriptTaskCallbackServerPort);
    SOCKET_write(globalSocketScriptTaskCallbackSocket, globalSocketScriptTaskCallbackContextUuid);
    SOCKET_pause(1);
    globalSocketScriptTaskCallbackConnected = %T;
endfunction

function result = callback_invokeSocket(parameters)
	global globalSocketScriptTaskCallbackSocket;
	disp(parameters)
	dims = callback_dims(parameters);
	disp(dims)
	message = strcat([toJSON(dims), ';', toJSON(parameters)]);
	message = strsubst(message, '""', '__QUOTE__');
	message = strsubst(message, ' ', '__SPACE__');
    SOCKET_write(globalSocketScriptTaskCallbackSocket, globalSocketScriptTaskCallbackContextUuid);
    returnExpression = SOCKET_read(globalSocketScriptTaskCallbackSocket);
    result = evstr(returnExpression);
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

