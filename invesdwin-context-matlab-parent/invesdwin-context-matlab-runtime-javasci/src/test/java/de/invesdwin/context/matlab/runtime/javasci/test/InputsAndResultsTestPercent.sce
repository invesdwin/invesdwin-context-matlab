disp('getPercent')
if exists('getPercent') then
	error('getPercent already defined!')
end
getPercent = putPercent
disp(typeof(getPercent))
disp(getPercent)
if ~isa(getPercent, 'double') then
	error('getPercent not double!')
end

disp('getPercentVector')
if exists('getPercentVector') then
	error('getPercentVector already defined!')
end
getPercentVector = putPercentVector
disp(typeof(getPercentVector))
disp(getPercentVector)
if ~isa(getPercentVector, 'double') then
	error('getPercentVector not double!')
end

disp('getPercentVectorAsList')
if exists('getPercentVectorAsList') then
	error('getPercentVectorAsList already defined!')
end
getPercentVectorAsList = putPercentVectorAsList
disp(typeof(getPercentVectorAsList))
disp(getPercentVectorAsList)
if ~isa(getPercentVectorAsList, 'double') then
	error('getPercentVectorAsList not double!')
end

disp('getPercentMatrix')
if exists('getPercentMatrix') then
	error('getPercentMatrix already defined!')
end
getPercentMatrix = putPercentMatrix
disp(typeof(getPercentMatrix))
disp(getPercentMatrix)
if ~isa(getPercentMatrix, 'double') then
	error('getPercentMatrix not double!')
end

disp('getPercentMatrixAsList')
if exists('getPercentMatrixAsList') then
	error('getPercentMatrixAsList already defined!')
end
getPercentMatrixAsList = putPercentMatrixAsList
disp(typeof(getPercentMatrixAsList))
disp(getPercentMatrixAsList)
if ~isa(getPercentMatrixAsList, 'double') then
	error('getPercentMatrixAsList not double!')
end
