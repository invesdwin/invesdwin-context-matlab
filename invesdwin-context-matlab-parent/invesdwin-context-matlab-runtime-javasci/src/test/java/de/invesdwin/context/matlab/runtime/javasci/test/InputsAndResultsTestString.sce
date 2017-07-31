disp('getString')
if exists('getString') then
	error('getString already defined!')
end
getString = putString
disp(typeof(getString))
disp(getString)
if ~type(getString)==10 then
	error('getString not char!')
end

disp('getStringWithNull')
if exists('getStringWithNull') then
	error('getStringWithNull already defined!')
end
getStringWithNull = putStringWithNull
disp(typeof(getStringWithNull))
disp(getStringWithNull)
if ~type(getStringWithNull)==1 then
	error('getStringWithNull not double!')
end
if ~isnan(getStringWithNull) then
	error('getStringWithNull not nan!')
end

disp('getStringVector')
if exists('getStringVector') then
	error('getStringVector already defined!')
end
getStringVector = putStringVector
disp(typeof(getStringVector))
disp(getStringVector)
if ~type(getStringVector)==10 then
	error('getStringVector not cell!')
end


disp('getStringVectorWithNull')
if exists('getStringVectorWithNull') then
	error('getStringVectorWithNull already defined!')
end
getStringVectorWithNull = putStringVectorWithNull
disp(typeof(getStringVectorWithNull))
disp(getStringVectorWithNull)
if ~type(getStringVectorWithNull)==10 then
	error('getStringVectorWithNull not cell!')
end
if ~isempty(getStringVectorWithNull(2)) then
	error('getStringVectorWithNull(2) not empty!')
end

disp('getStringVectorAsList')
if exists('getStringVectorAsList') then
	error('getStringVectorAsList already defined!')
end
getStringVectorAsList = putStringVectorAsList
disp(typeof(getStringVectorAsList))
disp(getStringVectorAsList)
if ~type(getStringVectorAsList)==10 then
	error('getStringVectorAsList not cell!')
end

disp('getStringVectorAsListWN')
if exists('getStringVectorAsListWN') then
	error('getStringVectorAsListWN already defined!')
end
getStringVectorAsListWN = putStringVectorAsListWN
disp(typeof(getStringVectorAsListWN))
disp(getStringVectorAsListWN)
if ~type(getStringVectorAsListWN)==10 then
	error('getStringVectorAsListWN not cell!')
end
if ~isempty(getStringVectorAsListWN(2)) then
	error('getStringVectorAsListWN(2) not empty!')
end

disp('getStringMatrix')
if exists('getStringMatrix') then
	error('getStringMatrix already defined!')
end
getStringMatrix = putStringMatrix
disp(typeof(getStringMatrix))
disp(getStringMatrix)
if ~type(getStringMatrix)==10 then
	error('getStringMatrix not cell!')
end


disp('getStringMatrixWithNull')
if exists('getStringMatrixWithNull') then
	error('getStringMatrixWithNull already defined!')
end
getStringMatrixWithNull = putStringMatrixWithNull
disp(typeof(getStringMatrixWithNull))
disp(getStringMatrixWithNull)
if ~type(getStringMatrixWithNull)==10 then
	error('getStringMatrixWithNull not cell!')
end
if ~isempty(getStringMatrixWithNull(1,1)) then
	error('getStringMatrixWithNull(1,1) not empty!')
end
if ~isempty(getStringMatrixWithNull(2,2)) then
	error('getStringMatrixWithNull(2,2) not empty!')
end
if ~isempty(getStringMatrixWithNull(3,3)) then
	error('getStringMatrixWithNull(3,3) not empty!')
end

disp('getStringMatrixAsList')
if exists('getStringMatrixAsList') then
	error('getStringMatrixAsList already defined!')
end
getStringMatrixAsList = putStringMatrixAsList
disp(typeof(getStringMatrixAsList))
disp(getStringMatrixAsList)
if ~type(getStringMatrixAsList)==10 then
	error('getStringMatrixAsList not cell!')
end

disp('getStringMatrixAsListWN')
if exists('getStringMatrixAsListWN') then
	error('getStringMatrixAsListWN already defined!')
end
getStringMatrixAsListWN = putStringMatrixAsListWN
disp(typeof(getStringMatrixAsListWN))
disp(getStringMatrixAsListWN)
if ~type(getStringMatrixAsListWN)==10 then
	error('getStringMatrixAsListWN not cell!')
end
if ~isempty(getStringMatrixAsListWN(1,1)) then
	error('getStringMatrixAsListWN(1,1) not empty!')
end
if ~isempty(getStringMatrixAsListWN(2,2)) then
	error('getStringMatrixAsListWN(2,2) not empty!')
end
if ~isempty(getStringMatrixAsListWN(3,3)) then
	error('getStringMatrixAsListWN(3,3) not empty!')
end
