disp('getFloat')
if exist('getFloat')
	error('getFloat already defined!')
end
getFloat = putFloat
disp(class(getFloat))
disp(getFloat)
if ~isa(getFloat, 'double')
	error('getFloat not double!')
end

disp('getFloatVector')
if exist('getFloatVector')
	error('getFloatVector already defined!')
end
getFloatVector = putFloatVector
disp(class(getFloatVector))
disp(getFloatVector)
if ~isa(getFloatVector, 'double')
	error('getFloatVector not double!')
end

disp('getFloatVectorAsList')
if exist('getFloatVectorAsList')
	error('getFloatVectorAsList already defined!')
end
getFloatVectorAsList = putFloatVectorAsList
disp(class(getFloatVectorAsList))
disp(getFloatVectorAsList)
if ~isa(getFloatVectorAsList, 'double')
	error('getFloatVectorAsList not double!')
end

disp('getFloatMatrix')
if exist('getFloatMatrix')
	error('getFloatMatrix already defined!')
end
getFloatMatrix = putFloatMatrix
disp(class(getFloatMatrix))
disp(getFloatMatrix)
if ~isa(getFloatMatrix, 'double')
	error('getFloatMatrix not double!')
end

disp('getFloatMatrixAsList')
if exist('getFloatMatrixAsList')
	error('getFloatMatrixAsList already defined!')
end
getFloatMatrixAsList = putFloatMatrixAsList
disp(class(getFloatMatrixAsList))
disp(getFloatMatrixAsList)
if ~isa(getFloatMatrixAsList, 'double')
	error('getFloatMatrixAsList not double!')
end
