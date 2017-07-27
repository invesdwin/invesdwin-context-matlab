disp('getFloat')
if exists('getFloat') then
	error('getFloat already defined!')
end
getFloat = putFloat
disp(typeof(getFloat))
disp(getFloat)
if ~isa(getFloat, 'single') then
	error('getFloat not single!')
end

disp('getFloatVector')
if exists('getFloatVector') then
	error('getFloatVector already defined!')
end
getFloatVector = putFloatVector
disp(typeof(getFloatVector))
disp(getFloatVector)
if ~isa(getFloatVector, 'single') then
	error('getFloatVector not single!')
end

disp('getFloatVectorAsList')
if exists('getFloatVectorAsList') then
	error('getFloatVectorAsList already defined!')
end
getFloatVectorAsList = putFloatVectorAsList
disp(typeof(getFloatVectorAsList))
disp(getFloatVectorAsList)
if ~isa(getFloatVectorAsList, 'single') then
	error('getFloatVectorAsList not single!')
end

disp('getFloatMatrix')
if exists('getFloatMatrix') then
	error('getFloatMatrix already defined!')
end
getFloatMatrix = putFloatMatrix
disp(typeof(getFloatMatrix))
disp(getFloatMatrix)
if ~isa(getFloatMatrix, 'single') then
	error('getFloatMatrix not single!')
end

disp('getFloatMatrixAsList')
if exists('getFloatMatrixAsList') then
	error('getFloatMatrixAsList already defined!')
end
getFloatMatrixAsList = putFloatMatrixAsList
disp(typeof(getFloatMatrixAsList))
disp(getFloatMatrixAsList)
if ~isa(getFloatMatrixAsList, 'single') then
	error('getFloatMatrixAsList not single!')
end
