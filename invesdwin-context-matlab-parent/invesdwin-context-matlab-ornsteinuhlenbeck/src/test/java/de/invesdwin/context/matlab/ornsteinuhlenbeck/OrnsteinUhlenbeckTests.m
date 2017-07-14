library(LSPM)
library(DEoptim)
library(matrixStats)
library(snow)

#clust <- makeSOCKcluster(2)
clust <- NULL

trades <- cbind(c(.5, -.3, .4, -.2),c(0.1, -.15, .4, -.1))
#trades <- c(2, -3, 10, -5)
probs <- cbind(c(0.25,0.25,0.25,0.25), c(0.25,0.25,0.25,0.25))
lspobj <- lsp(trades,probs)

DEctrl <- list(NP=30,itermax=200)
#result <- optimalf(lspobj, snow=clust, control=DEctrl)
#result <- optimalf(lspobj, probDrawdown, 0.1, DD=0.2, horizon=4, snow=clust, control=DEctrl)
result <- optimalf(lspobj, probRuin, 0.1, DD=0.2, horizon=4, snow=clust, control=DEctrl)
profit <- result$G-1
optimalf <- result$f

colProds(trades)
profit
optimalf
format(round(optimalf, 6), nsmall = 6)