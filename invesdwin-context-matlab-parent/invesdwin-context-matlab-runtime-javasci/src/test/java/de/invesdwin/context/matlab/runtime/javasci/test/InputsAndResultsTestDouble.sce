disp('getDouble')
if exists('getDouble') then
	error('getDouble already defined!')
end
getDouble = putDouble
disp(typeof(getDouble))
disp(getDouble)
if ~type(getDouble)==1 then
	error('getDouble not double!')
end

disp('getDoubleVector')
if exists('getDoubleVector') then
	error('getDoubleVector already defined!')
end
getDoubleVector = putDoubleVector
disp(typeof(getDoubleVector))
disp(getDoubleVector)
if ~type(getDoubleVector)==1 then
	error('getDoubleVector not double!')
end

disp('getDoubleVectorAsList')
if exists('getDoubleVectorAsList') then
	error('getDoubleVectorAsList already defined!')
end
getDoubleVectorAsList = putDoubleVectorAsList
disp(typeof(getDoubleVectorAsList))
disp(getDoubleVectorAsList)
if ~type(getDoubleVectorAsList)==1 then
	error('getDoubleVectorAsList not double!')
end

disp('getDoubleMatrix')
if exists('getDoubleMatrix') then
	error('getDoubleMatrix already defined!')
end
getDoubleMatrix = putDoubleMatrix
disp(typeof(getDoubleMatrix))
disp(getDoubleMatrix)
if ~type(getDoubleMatrix)==1 then
	error('getDoubleMatrix not double!')
end

disp('getDoubleMatrixAsList')
if exists('getDoubleMatrixAsList') then
	error('getDoubleMatrixAsList already defined!')
end
getDoubleMatrixAsList = putDoubleMatrixAsList
disp(typeof(getDoubleMatrixAsList))
disp(getDoubleMatrixAsList)
if ~type(getDoubleMatrixAsList)==1 then
	error('getDoubleMatrixAsList not double!')
end
