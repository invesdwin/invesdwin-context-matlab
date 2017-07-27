disp('getByte')
if exists('getByte') then
	error('getByte already defined!')
end
getByte = putByte
disp(class(getByte))
disp(getByte)
if ~isa(getByte, 'int8') then
	error('getByte not int8!')
end

disp('getByteVector')
if exists('getByteVector') then
	error('getByteVector already defined!')
end
getByteVector = putByteVector
disp(class(getByteVector))
disp(getByteVector)
if ~isa(getByteVector, 'int8') then
	error('getByteVector not int8!')
end

disp('getByteVectorAsList')
if exists('getByteVectorAsList') then
	error('getByteVectorAsList already defined!')
end
getByteVectorAsList = putByteVectorAsList
disp(class(getByteVectorAsList))
disp(getByteVectorAsList)
if ~isa(getByteVectorAsList, 'int8') then
	error('getByteVectorAsList not int8!')
end

disp('getByteMatrix')
if exists('getByteMatrix') then
	error('getByteMatrix already defined!')
end
getByteMatrix = putByteMatrix
disp(class(getByteMatrix))
disp(getByteMatrix)
if ~isa(getByteMatrix, 'int8') then
	error('getByteMatrix not int8!')
end

disp('getByteMatrixAsList')
if exists('getByteMatrixAsList') then
	error('getByteMatrixAsList already defined!')
end
getByteMatrixAsList = putByteMatrixAsList
disp(class(getByteMatrixAsList))
disp(getByteMatrixAsList)
if ~isa(getByteMatrixAsList, 'int8') then
	error('getByteMatrixAsList not int8!')
end
