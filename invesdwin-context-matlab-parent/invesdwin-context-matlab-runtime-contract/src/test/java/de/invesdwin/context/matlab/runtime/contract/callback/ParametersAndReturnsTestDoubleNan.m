disp('getDouble')
if exist('getDouble')
	error('getDouble already defined!')
end
getDouble = callback('getDouble')
disp(class(getDouble))
disp(getDouble)
if ~isa(getDouble, 'double')
	error('getDouble not double!')
end
if ~isnan(getDouble)
	error('getDouble not NaN!')
end
callback('setDouble',getDouble)

disp('getDoubleVector')
if exist('getDoubleVector')
	error('getDoubleVector already defined!')
end
getDoubleVector = callback('getDoubleVector')
disp(class(getDoubleVector))
disp(getDoubleVector)
if ~isa(getDoubleVector, 'double')
	error('getDoubleVector not double!')
end
if ~isnan(getDoubleVector(2))
	error('getDoubleVector[1] not NaN!')
end
callback('setDoubleVector',getDoubleVector)

disp('getDoubleVectorAsList')
if exist('getDoubleVectorAsList')
	error('getDoubleVectorAsList already defined!')
end
getDoubleVectorAsList = callback('getDoubleVectorAsList')
disp(class(getDoubleVectorAsList))
disp(getDoubleVectorAsList)
if ~isa(getDoubleVectorAsList, 'double')
	error('getDoubleVectorAsList not double!')
end
if ~isnan(getDoubleVectorAsList(2))
	error('getDoubleVectorAsList[1] not NaN!')
end
callback('setDoubleVectorAsList',getDoubleVectorAsList)

disp('getDoubleMatrix')
if exist('getDoubleMatrix')
	error('getDoubleMatrix already defined!')
end
getDoubleMatrix = callback('getDoubleMatrix')
disp(class(getDoubleMatrix))
disp(getDoubleMatrix)
if ~isa(getDoubleMatrix, 'double')
	error('getDoubleMatrix not double!')
end
if ~isnan(getDoubleMatrix(1,1))
	error('getDoubleMatrix[0][0] not NaN!')
end
if ~isnan(getDoubleMatrix(2,2))
	error('getDoubleMatrix[1][1] not NaN!')
end
if ~isnan(getDoubleMatrix(3,3))
	error('getDoubleMatrix[2][2] not NaN!')
end
callback('setDoubleMatrix',getDoubleMatrix)

disp('getDoubleMatrixAsList')
if exist('getDoubleMatrixAsList')
	error('getDoubleMatrixAsList already defined!')
end
getDoubleMatrixAsList = callback('getDoubleMatrixAsList')
disp(class(getDoubleMatrixAsList))
disp(getDoubleMatrixAsList)
if ~isa(getDoubleMatrixAsList, 'double')
	error('getDoubleMatrixAsList not double!')
end
if ~isnan(getDoubleMatrixAsList(1,1))
	error('getDoubleMatrixAsList[0][0] not NaN!')
end
if ~isnan(getDoubleMatrixAsList(2,2))
	error('getDoubleMatrixAsList[1][1] not NaN!')
end
if ~isnan(getDoubleMatrixAsList(3,3))
	error('getDoubleMatrixAsList[2][2] not NaN!')
end
callback('setDoubleMatrixAsList',getDoubleMatrixAsList)