disp('getLong')
if exist('getLong')
	error('getLong already defined!')
end
getLong = putLong
disp(class(getLong))
disp(getLong)
if ~isa(getLong, 'double')
	error('getLong not double!')
end

disp('getLongVector')
if exist('getLongVector')
	error('getLongVector already defined!')
end
getLongVector = putLongVector
disp(class(getLongVector))
disp(getLongVector)
if ~isa(getLongVector, 'double')
	error('getLongVector not double!')
end

disp('getLongVectorAsList')
if exist('getLongVectorAsList')
	error('getLongVectorAsList already defined!')
end
getLongVectorAsList = putLongVectorAsList
disp(class(getLongVectorAsList))
disp(getLongVectorAsList)
if ~isa(getLongVectorAsList, 'double')
	error('getLongVectorAsList not double!')
end

disp('getLongMatrix')
if exist('getLongMatrix')
	error('getLongMatrix already defined!')
end
getLongMatrix = putLongMatrix
disp(class(getLongMatrix))
disp(getLongMatrix)
if ~isa(getLongMatrix, 'double')
	error('getLongMatrix not double!')
end

disp('getLongMatrixAsList')
if exist('getLongMatrixAsList')
	error('getLongMatrixAsList already defined!')
end
getLongMatrixAsList = putLongMatrixAsList
disp(class(getLongMatrixAsList))
disp(getLongMatrixAsList)
if ~isa(getLongMatrixAsList, 'double')
	error('getLongMatrixAsList not double!')
end
