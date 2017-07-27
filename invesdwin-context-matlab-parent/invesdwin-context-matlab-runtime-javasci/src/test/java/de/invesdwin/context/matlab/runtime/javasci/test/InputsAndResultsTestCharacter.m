disp('getCharacter')
if exists('getCharacter') then
	error('getCharacter already defined!')
end
getCharacter = putCharacter
disp(class(getCharacter))
disp(getCharacter)
if ~isa(getCharacter, 'char') then
	error('getCharacter not char!')
end

disp('getCharacterVector')
if exists('getCharacterVector') then
	error('getCharacterVector already defined!')
end
getCharacterVector = putCharacterVector
disp(class(getCharacterVector))
disp(getCharacterVector)
if ~isa(getCharacterVector, 'cell') then
	error('getCharacterVector not cell!')
end

disp('getCharacterVectorAsList')
if exists('getCharacterVectorAsList') then
	error('getCharacterVectorAsList already defined!')
end
getCharacterVectorAsList = putCharacterVectorAsList
disp(class(getCharacterVectorAsList))
disp(getCharacterVectorAsList)
if ~isa(getCharacterVectorAsList, 'cell') then
	error('getCharacterVectorAsList not cell!')
end

disp('getCharacterMatrix')
if exists('getCharacterMatrix') then
	error('getCharacterMatrix already defined!')
end
getCharacterMatrix = putCharacterMatrix
disp(class(getCharacterMatrix))
disp(getCharacterMatrix)
if ~isa(getCharacterMatrix, 'cell') then
	error('getCharacterMatrix not cell!')
end

disp('getCharacterMatrixAsList')
if exists('getCharacterMatrixAsList') then
	error('getCharacterMatrixAsList already defined!')
end
getCharacterMatrixAsList = putCharacterMatrixAsList
disp(class(getCharacterMatrixAsList))
disp(getCharacterMatrixAsList)
if ~isa(getCharacterMatrixAsList, 'cell') then
	error('getCharacterMatrixAsList not cell!')
end