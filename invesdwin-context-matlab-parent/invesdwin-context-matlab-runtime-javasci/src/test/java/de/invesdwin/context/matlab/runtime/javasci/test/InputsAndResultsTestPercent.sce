disp('getPercent')
if exists('getPercent') then
	error('getPercent already defined!')
end
getPercent = putPercent
disp(typeof(getPercent))
disp(getPercent)
if ~type(getPercent)==1 then
	error('getPercent not double!')
end

disp('getPercentVector')
if exists('getPercentVector') then
	error('getPercentVector already defined!')
end
getPercentVector = putPercentVector
disp(typeof(getPercentVector))
disp(getPercentVector)
if ~type(getPercentVector)==1 then
	error('getPercentVector not double!')
end

disp('getPercentVectorAsList')
if exists('getPercentVectorAsList') then
	error('getPercentVectorAsList already defined!')
end
getPercentVectorAsList = putPercentVectorAsList
disp(typeof(getPercentVectorAsList))
disp(getPercentVectorAsList)
if ~type(getPercentVectorAsList)==1 then
	error('getPercentVectorAsList not double!')
end

disp('getPercentMatrix')
if exists('getPercentMatrix') then
	error('getPercentMatrix already defined!')
end
getPercentMatrix = putPercentMatrix
disp(typeof(getPercentMatrix))
disp(getPercentMatrix)
if ~type(getPercentMatrix)==1 then
	error('getPercentMatrix not double!')
end

disp('getPercentMatrixAsList')
if exists('getPercentMatrixAsList') then
	error('getPercentMatrixAsList already defined!')
end
getPercentMatrixAsList = putPercentMatrixAsList
disp(typeof(getPercentMatrixAsList))
disp(getPercentMatrixAsList)
if ~type(getPercentMatrixAsList)==1 then
	error('getPercentMatrixAsList not double!')
end
