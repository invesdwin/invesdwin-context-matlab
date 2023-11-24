disp('getString')
if exists('getString') then
	error('getString already defined!')
end
getString = putString
disp(typeof(getString))
disp(getString)
if typeof(getString)~='string' then
	error('getString not char!')
end

disp('getStringWithNull')
if exists('getStringWithNull') then
	error('getStringWithNull already defined!')
end
getStringWithNull = putStringWithNull
disp(typeof(getStringWithNull))
disp(getStringWithNull)
if typeof(getStringWithNull)==1 then
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
if typeof(getStringVector)~='string' then
	error('getStringVector not cell!')
end


disp('getStringVectorWithNull')
if exists('getStringVectorWithNull') then
	error('getStringVectorWithNull already defined!')
end
getStringVectorWithNull = putStringVectorWithNull
disp(typeof(getStringVectorWithNull))
disp(getStringVectorWithNull)
if typeof(getStringVectorWithNull)~='string' then
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
if typeof(getStringVectorAsList)~='string' then
	error('getStringVectorAsList not cell!')
end

disp('getStringVectorAsListWN')
if exists('getStringVectorAsListWN') then
	error('getStringVectorAsListWN already defined!')
end
getStringVectorAsListWN = putStringVectorAsListWN
disp(typeof(getStringVectorAsListWN))
disp(getStringVectorAsListWN)
if typeof(getStringVectorAsListWN)~='string' then
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
if typeof(getStringMatrix)~='string' then
	error('getStringMatrix not cell!')
end


disp('getStringMatrixWithNull')
if exists('getStringMatrixWithNull') then
	error('getStringMatrixWithNull already defined!')
end
getStringMatrixWithNull = putStringMatrixWithNull
disp(typeof(getStringMatrixWithNull))
disp(getStringMatrixWithNull)
if typeof(getStringMatrixWithNull)~='string' then
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
if typeof(getStringMatrixAsList)~='string' then
	error('getStringMatrixAsList not cell!')
end

disp('getStringMatrixAsListWN')
if exists('getStringMatrixAsListWN') then
	error('getStringMatrixAsListWN already defined!')
end
getStringMatrixAsListWN = putStringMatrixAsListWN
disp(typeof(getStringMatrixAsListWN))
disp(getStringMatrixAsListWN)
if typeof(getStringMatrixAsListWN)~='string' then
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
