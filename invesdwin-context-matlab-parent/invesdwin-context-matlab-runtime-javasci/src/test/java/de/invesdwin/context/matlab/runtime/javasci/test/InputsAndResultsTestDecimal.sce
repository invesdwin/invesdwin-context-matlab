disp('getDecimal')
if exists('getDecimal') then
	error('getDecimal already defined!')
end
getDecimal = putDecimal
disp(typeof(getDecimal))
disp(getDecimal)
if ~type(getDecimal)==1 then
	error('getDecimal not double!')
end

disp('getDecimalVector')
if exists('getDecimalVector') then
	error('getDecimalVector already defined!')
end
getDecimalVector = putDecimalVector
disp(typeof(getDecimalVector))
disp(getDecimalVector)
if ~type(getDecimalVector)==1 then
	error('getDecimalVector not double!')
end

disp('getDecimalVectorAsList')
if exists('getDecimalVectorAsList') then
	error('getDecimalVectorAsList already defined!')
end
getDecimalVectorAsList = putDecimalVectorAsList
disp(typeof(getDecimalVectorAsList))
disp(getDecimalVectorAsList)
if ~type(getDecimalVectorAsList)==1 then
	error('getDecimalVectorAsList not double!')
end

disp('getDecimalMatrix')
if exists('getDecimalMatrix') then
	error('getDecimalMatrix already defined!')
end
getDecimalMatrix = putDecimalMatrix
disp(typeof(getDecimalMatrix))
disp(getDecimalMatrix)
if ~type(getDecimalMatrix)==1 then
	error('getDecimalMatrix not double!')
end

disp('getDecimalMatrixAsList')
if exists('getDecimalMatrixAsList') then
	error('getDecimalMatrixAsList already defined!')
end
getDecimalMatrixAsList = putDecimalMatrixAsList
disp(typeof(getDecimalMatrixAsList))
disp(getDecimalMatrixAsList)
if ~type(getDecimalMatrixAsList)==1 then
	error('getDecimalMatrixAsList not double!')
end
