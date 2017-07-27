disp('getBoolean')
if exists('getBoolean') then
	error('getBoolean already defined!')
end
getBoolean = putBoolean
disp(typeof(getBoolean))
disp(getBoolean)
if ~isa(getBoolean, 'logical') then
	error('getBoolean not logical!')
end

disp('getBooleanVector')
if exists('getBooleanVector') then
	error('getBooleanVector already defined!')
end
getBooleanVector = putBooleanVector
disp(typeof(getBooleanVector))
disp(getBooleanVector)
if ~isa(getBooleanVector, 'logical') then
	error('getBooleanVector not logical!')
end

disp('getBooleanVectorAsList')
if exists('getBooleanVectorAsList') then
	error('getBooleanVectorAsList already defined!')
end
getBooleanVectorAsList = putBooleanVectorAsList
disp(typeof(getBooleanVectorAsList))
disp(getBooleanVectorAsList)
if ~isa(getBooleanVectorAsList, 'logical') then
	error('getBooleanVectorAsList not logical!')
end

disp('getBooleanMatrix')
if exists('getBooleanMatrix') then
	error('getBooleanMatrix already defined!')
end
getBooleanMatrix = putBooleanMatrix
disp(typeof(getBooleanMatrix))
disp(getBooleanMatrix)
if ~isa(getBooleanMatrix, 'logical') then
	error('getBooleanMatrix not logical!')
end

disp('getBooleanMatrixAsList')
if exists('getBooleanMatrixAsList') then
	error('getBooleanMatrixAsList already defined!')
end
getBooleanMatrixAsList = putBooleanMatrixAsList
disp(typeof(getBooleanMatrixAsList))
disp(getBooleanMatrixAsList)
if ~isa(getBooleanMatrixAsList, 'logical') then
	error('getBooleanMatrixAsList not logical!')
end
