library(LSPM)
library(DEoptim)
#library(snow)

#clust <- makeSOCKcluster(2)
clust <- NULL

#trades <- cbind(c(.5, -.3, .4, -.2),c(0.1, -.15, .4, -.1))
#trades <- c(2, -3, 10, -5)
#probabilities <- cbind(c(0.25,0.25,0.25,0.25), c(0.25,0.25,0.25,0.25))

if(length(trades) == 0){
	stop("No trades!")
}

portfolio <- lsp(trades,probabilities)

DEctrl <- list(NP=30,itermax=200)
result <- optimalf(portfolio, snow=clust, control=DEctrl)
#result <- optimalf(portfolio, probDrawdown, 0.1, DD=0.2, horizon=4, snow=clust, control=DEctrl)
#result <- optimalf(portfolio, probRuin, 0.1, DD=0.2, horizon=4, snow=clust, control=DEctrl)
profit <- result$G-1
if(profit == -Inf){
	profit <- -999
}else if(profit == Inf){
	profit <- 999
}
	
optimalf <- result$f
