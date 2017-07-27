disp('getBoolean')
if exists('getBoolean') then
	error('getBoolean already defined!')
end
getBoolean = putBoolean
disp(class(getBoolean))
disp(getBoolean)
if ~isa(getBoolean, 'logical') then
	error('getBoolean not logical!')
end

disp('getBooleanVector')
if exists('getBooleanVector') then
	error('getBooleanVector already defined!')
end
getBooleanVector = putBooleanVector
disp(class(getBooleanVector))
disp(getBooleanVector)
if ~isa(getBooleanVector, 'logical') then
	error('getBooleanVector not logical!')
end

disp('getBooleanVectorAsList')
if exists('getBooleanVectorAsList') then
	error('getBooleanVectorAsList already defined!')
end
getBooleanVectorAsList = putBooleanVectorAsList
disp(class(getBooleanVectorAsList))
disp(getBooleanVectorAsList)
if ~isa(getBooleanVectorAsList, 'logical') then
	error('getBooleanVectorAsList not logical!')
end

disp('getBooleanMatrix')
if exists('getBooleanMatrix') then
	error('getBooleanMatrix already defined!')
end
getBooleanMatrix = putBooleanMatrix
disp(class(getBooleanMatrix))
disp(getBooleanMatrix)
if ~isa(getBooleanMatrix, 'logical') then
	error('getBooleanMatrix not logical!')
end

disp('getBooleanMatrixAsList')
if exists('getBooleanMatrixAsList') then
	error('getBooleanMatrixAsList already defined!')
end
getBooleanMatrixAsList = putBooleanMatrixAsList
disp(class(getBooleanMatrixAsList))
disp(getBooleanMatrixAsList)
if ~isa(getBooleanMatrixAsList, 'logical') then
	error('getBooleanMatrixAsList not logical!')
end
