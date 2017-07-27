disp('getShort')
if exists('getShort') then
	error('getShort already defined!')
end
getShort = putShort
disp(class(getShort))
disp(getShort)
if ~isa(getShort, 'int16') then
	error('getShort not int16!')
end

disp('getShortVector')
if exists('getShortVector') then
	error('getShortVector already defined!')
end
getShortVector = putShortVector
disp(class(getShortVector))
disp(getShortVector)
if ~isa(getShortVector, 'int16') then
	error('getShortVector not int16!')
end

disp('getShortVectorAsList')
if exists('getShortVectorAsList') then
	error('getShortVectorAsList already defined!')
end
getShortVectorAsList = putShortVectorAsList
disp(class(getShortVectorAsList))
disp(getShortVectorAsList)
if ~isa(getShortVectorAsList, 'int16') then
	error('getShortVectorAsList not int16!')
end

disp('getShortMatrix')
if exists('getShortMatrix')
	error('getShortMatrix already defined!')
end
getShortMatrix = putShortMatrix
disp(class(getShortMatrix))
disp(getShortMatrix)
if ~isa(getShortMatrix, 'int16') then
	error('getShortMatrix not int16!')
end

disp('getShortMatrixAsList')
if exists('getShortMatrixAsList') then
	error('getShortMatrixAsList already defined!')
end
getShortMatrixAsList = putShortMatrixAsList
disp(class(getShortMatrixAsList))
disp(getShortMatrixAsList)
if ~isa(getShortMatrixAsList, 'int16') then
	error('getShortMatrixAsList not int16!')
end
