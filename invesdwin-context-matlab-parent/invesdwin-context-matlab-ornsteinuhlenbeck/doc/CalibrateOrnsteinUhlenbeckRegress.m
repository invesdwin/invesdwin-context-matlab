function [ mu, sigma, lambda ] = CalibrateOrnsteinUhlenbeckRegress(S, deltat)

if (size(S,2) > size(S,1))
  S = S';
end
% Regress S(t)-S(t-1) against S(t-1).
[ k,dummy,resid ] = regress(S(2:end)-S(1:end-1),[ ones(size(S(1:end-1))) S(1:end-1) ] );
a = k(1); b = k(2); 
lambda = -b/deltat;
 mu = a/lambda/deltat; 
sigma = std(resid) / sqrt(deltat);

end
