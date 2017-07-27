disp('getString')
if exists('getString') then
	error('getString already defined!')
end
getString = putString
disp(typeof(getString))
disp(getString)
if ~isa(getString, 'char') then
	error('getString not char!')
end

disp('getStringWithNull')
if exists('getStringWithNull') then
	error('getStringWithNull already defined!')
end
getStringWithNull = putStringWithNull
disp(typeof(getStringWithNull))
disp(getStringWithNull)
if ~isa(getStringWithNull, 'double') then
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
if ~isa(getStringVector, 'cell') then
	error('getStringVector not cell!')
end


disp('getStringVectorWithNull')
if exists('getStringVectorWithNull') then
	error('getStringVectorWithNull already defined!')
end
getStringVectorWithNull = putStringVectorWithNull
disp(typeof(getStringVectorWithNull))
disp(getStringVectorWithNull)
if ~isa(getStringVectorWithNull, 'cell') then
	error('getStringVectorWithNull not cell!')
end
if ~isempty(getStringVectorWithNull{2}) then
	error('getStringVectorWithNull{2} not empty!')
end

disp('getStringVectorAsList')
if exists('getStringVectorAsList') then
	error('getStringVectorAsList already defined!')
end
getStringVectorAsList = putStringVectorAsList
disp(typeof(getStringVectorAsList))
disp(getStringVectorAsList)
if ~isa(getStringVectorAsList, 'cell') then
	error('getStringVectorAsList not cell!')
end

disp('getStringVectorAsListWithNull')
if exists('getStringVectorAsListWithNull') then
	error('getStringVectorAsListWithNull already defined!')
end
getStringVectorAsListWithNull = putStringVectorAsListWithNull
disp(typeof(getStringVectorAsListWithNull))
disp(getStringVectorAsListWithNull)
if ~isa(getStringVectorAsListWithNull, 'cell') then
	error('getStringVectorAsListWithNull not cell!')
end
if ~isempty(getStringVectorAsListWithNull{2}) then
	error('getStringVectorAsListWithNull{2} not empty!')
end

disp('getStringMatrix')
if exists('getStringMatrix') then
	error('getStringMatrix already defined!')
end
getStringMatrix = putStringMatrix
disp(typeof(getStringMatrix))
disp(getStringMatrix)
if ~isa(getStringMatrix, 'cell') then
	error('getStringMatrix not cell!')
end


disp('getStringMatrixWithNull')
if exists('getStringMatrixWithNull') then
	error('getStringMatrixWithNull already defined!')
end
getStringMatrixWithNull = putStringMatrixWithNull
disp(typeof(getStringMatrixWithNull))
disp(getStringMatrixWithNull)
if ~isa(getStringMatrixWithNull, 'cell') then
	error('getStringMatrixWithNull not cell!')
end
if ~isempty(getStringMatrixWithNull{1,1}) then
	error('getStringMatrixWithNull{1,1} not empty!')
end
if ~isempty(getStringMatrixWithNull{2,2}) then
	error('getStringMatrixWithNull{2,2} not empty!')
end
if ~isempty(getStringMatrixWithNull{3,3}) then
	error('getStringMatrixWithNull{3,3} not empty!')
end

disp('getStringMatrixAsList')
if exists('getStringMatrixAsList') then
	error('getStringMatrixAsList already defined!')
end
getStringMatrixAsList = putStringMatrixAsList
disp(typeof(getStringMatrixAsList))
disp(getStringMatrixAsList)
if ~isa(getStringMatrixAsList, 'cell') then
	error('getStringMatrixAsList not cell!')
end

disp('getStringMatrixAsListWithNull')
if exists('getStringMatrixAsListWithNull') then
	error('getStringMatrixAsListWithNull already defined!')
end
getStringMatrixAsListWithNull = putStringMatrixAsListWithNull
disp(typeof(getStringMatrixAsListWithNull))
disp(getStringMatrixAsListWithNull)
if ~isa(getStringMatrixAsListWithNull, 'cell') then
	error('getStringMatrixAsListWithNull not cell!')
end
if ~isempty(getStringMatrixAsListWithNull{1,1}) then
	error('getStringMatrixAsListWithNull{1,1} not empty!')
end
if ~isempty(getStringMatrixAsListWithNull{2,2}) then
	error('getStringMatrixAsListWithNull{2,2} not empty!')
end
if ~isempty(getStringMatrixAsListWithNull{3,3}) then
	error('getStringMatrixAsListWithNull{3,3} not empty!')
end
