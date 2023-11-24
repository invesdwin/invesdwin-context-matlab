disp('putUuid')
disp(putUuid)

getSecretStaticCallback = callback('getSecretStatic', putUuid)
disp('getSecretStaticCallback')
disp(getSecretStaticCallback)

getSecretCallback = callback('getSecret', putUuid)
disp('getSecretCallback')
disp(getSecretCallback)

getSecretExpressionCallback = callback('getSecretExpression', putUuid)
disp('getSecretExpressionCallback')
disp(getSecretExpressionCallback)

callback('voidMethod')