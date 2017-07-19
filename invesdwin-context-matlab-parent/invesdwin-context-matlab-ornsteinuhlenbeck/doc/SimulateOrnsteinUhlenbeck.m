 

function [ S ] = SimulateOrnsteinUhlenbeck( S0, mu, sigma, lambda, deltat, t )
periods = floor(t / deltat); 

S = zeros(periods, 1); 
%  keyboard
S(1) = S0; 
exp_minus_lambda_deltat = exp(-lambda*deltat); 
% Calculate the random term. 
if (lambda == 0) 
% Handle the case of lambda = 0 i.e. no mean reversion.
  dWt = sqrt(deltat) * randn(periods,1); 
else 
  dWt = sqrt((1-exp(-2*lambda* deltat))/(2*lambda)) * randn(periods,1); 
end
 % And iterate through time calculating each price. 
for t=2:1:periods
  S(t) = S(t-1)*exp_minus_lambda_deltat + mu*(1-exp_minus_lambda_deltat) + sigma*dWt(t); 
end 



end
