disp('getLong')
if exists('getLong') then
	error('getLong already defined!')
end
getLong = putLong
disp(class(getLong))
disp(getLong)
if ~isa(getLong, 'int64') then
	error('getLong not int64!')
end

disp('getLongVector')
if exists('getLongVector') then
	error('getLongVector already defined!')
end
getLongVector = putLongVector
disp(class(getLongVector))
disp(getLongVector)
if ~isa(getLongVector, 'int64') then
	error('getLongVector not int64!')
end

disp('getLongVectorAsList')
if exists('getLongVectorAsList') then
	error('getLongVectorAsList already defined!')
end
getLongVectorAsList = putLongVectorAsList
disp(class(getLongVectorAsList))
disp(getLongVectorAsList)
if ~isa(getLongVectorAsList, 'int64') then
	error('getLongVectorAsList not int64!')
end

disp('getLongMatrix')
if exists('getLongMatrix') then
	error('getLongMatrix already defined!')
end
getLongMatrix = putLongMatrix
disp(class(getLongMatrix))
disp(getLongMatrix)
if ~isa(getLongMatrix, 'int64') then
	error('getLongMatrix not int64!')
end

disp('getLongMatrixAsList')
if exists('getLongMatrixAsList') then
	error('getLongMatrixAsList already defined!')
end
getLongMatrixAsList = putLongMatrixAsList
disp(class(getLongMatrixAsList))
disp(getLongMatrixAsList)
if ~isa(getLongMatrixAsList, 'int64') then
	error('getLongMatrixAsList not int64!')
end
