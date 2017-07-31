disp('getLong')
if exists('getLong') then
	error('getLong already defined!')
end
getLong = putLong
disp(typeof(getLong))
disp(getLong)
if ~type(getLong)==1 then
	error('getLong not int64!')
end

disp('getLongVector')
if exists('getLongVector') then
	error('getLongVector already defined!')
end
getLongVector = putLongVector
disp(typeof(getLongVector))
disp(getLongVector)
if ~type(getLongVector)==1 then
	error('getLongVector not int64!')
end

disp('getLongVectorAsList')
if exists('getLongVectorAsList') then
	error('getLongVectorAsList already defined!')
end
getLongVectorAsList = putLongVectorAsList
disp(typeof(getLongVectorAsList))
disp(getLongVectorAsList)
if ~type(getLongVectorAsList)==1 then
	error('getLongVectorAsList not int64!')
end

disp('getLongMatrix')
if exists('getLongMatrix') then
	error('getLongMatrix already defined!')
end
getLongMatrix = putLongMatrix
disp(typeof(getLongMatrix))
disp(getLongMatrix)
if ~type(getLongMatrix)==1 then
	error('getLongMatrix not int64!')
end

disp('getLongMatrixAsList')
if exists('getLongMatrixAsList') then
	error('getLongMatrixAsList already defined!')
end
getLongMatrixAsList = putLongMatrixAsList
disp(typeof(getLongMatrixAsList))
disp(getLongMatrixAsList)
if ~type(getLongMatrixAsList)==1 then
	error('getLongMatrixAsList not int64!')
end
