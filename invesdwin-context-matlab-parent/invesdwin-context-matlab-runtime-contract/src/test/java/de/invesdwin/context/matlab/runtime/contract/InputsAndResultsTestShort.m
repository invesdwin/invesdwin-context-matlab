disp("getShort")
if exist("getShort")
	error("getShort already defined!")
end
getShort = putShort
disp(class(getShort))
disp(getShort)
if ~isa(getShort, 'int32')
	error("getShort not int32!")
end

disp("getShortVector")
if exist("getShortVector")
	error("getShortVector already defined!")
end
getShortVector = putShortVector
disp(class(getShortVector))
disp(getShortVector)
if ~isa(getShortVector, 'int32')
	error("getShortVector not int32!")
end

disp("getShortVectorAsList")
if exist("getShortVectorAsList")
	error("getShortVectorAsList already defined!")
end
getShortVectorAsList = putShortVectorAsList
disp(class(getShortVectorAsList))
disp(getShortVectorAsList)
if ~isa(getShortVectorAsList, 'int32')
	error("getShortVectorAsList not int32!")
end

disp("getShortMatrix")
if exist("getShortMatrix")
	error("getShortMatrix already defined!")
end
getShortMatrix = putShortMatrix
disp(class(getShortMatrix))
disp(getShortMatrix)
if ~isa(getShortMatrix, 'int32')
	error("getShortMatrix not int32!")
end

disp("getShortMatrixAsList")
if exist("getShortMatrixAsList")
	error("getShortMatrixAsList already defined!")
end
getShortMatrixAsList = putShortMatrixAsList
disp(class(getShortMatrixAsList))
disp(getShortMatrixAsList)
if ~isa(getShortMatrixAsList, 'int32')
	error("getShortMatrixAsList not int32!")
end
