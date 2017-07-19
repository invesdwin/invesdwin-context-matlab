function [ mu, sigma, lambda ] = CalibrateOrnsteinUhlenbeckLeastSquares(S, deltat, bigt)

if (size(S,2) > size(S,1)) 
  S = S'; 
end

[ k,dummy,resid ] = regress(S(2:end),[ ones(size(S(1:end-1))) S(1:end-1) ] );
 a = k(1); b = k(2); 
lambda = -log(b)/deltat;
 mu = a/(1-b);
 sigma = std(resid) * sqrt( 2*lambda/(1-b^2) );

end