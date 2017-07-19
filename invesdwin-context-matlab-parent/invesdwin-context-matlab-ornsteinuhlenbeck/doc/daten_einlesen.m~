% https://www.sitmo.com/?p=134
 
if exist('OCTAVE_VERSION', 'builtin') ~= 0
  pkg load statistics
end
 
% Date  ,Open  ,High  ,Low  ,Close  ,Volume  ,Adj Close

M=csvread('apple.csv',1,1); 

% M=csvread('rimm.csv',1,1);


%  als erstes matrix flippen, da die neuesten einträge als erstes kommen...

for i=1:length(M)-1
  A(i,:)=M(end-i,:);
end


S=A(:,6);
deltat=1;
[mu, sigma, lambda]=CalibrateOrnsteinUhlenbeckRegress(S,deltat);

%  [ S0 ] = SimulateOrnsteinUhlenbeck( S, mu, sigma, lambda, deltat, t );
anzahl_montecarloversuche=100
for i=1:anzahl_montecarloversuche
  [ S_predicted ] = SimulateOrnsteinUhlenbeck( S(end,1), mu, sigma, lambda, deltat, 100 );
ergebnis{i}.ergebnios=S_predicted;
end


S_avg=zeros(100,1);
for i=1:anzahl_montecarloversuche
  %S_neu=[S',ergebnis{i}.ergebnios'];
  S_avg=S_avg+ergebnis{i}.ergebnios;
%   plot(S_neu,'r')
hold on
%  plot(S)
% keyboard
hold off
end 
S_neu=[S',S_avg'/anzahl_montecarloversuche];
plot(S_neu,'r')
hold on
 plot(S)
% keyboard
hold off


%  %  %  %  %  %  TEST: ab 2828 bis ende 2868 voraussagen
T=A(1:2828,6);
deltat=1;
[mu, sigma, lambda]=CalibrateOrnsteinUhlenbeckRegress(T,deltat);

%  [ S0 ] = SimulateOrnsteinUhlenbeck( S, mu, sigma, lambda, deltat, t );
anzahl_montecarloversuche=100
for i=1:anzahl_montecarloversuche
  [ T_predicted ] = SimulateOrnsteinUhlenbeck( T(end,1), mu, sigma, lambda, deltat, 100 );
ergebnis{i}.ergebnios=T_predicted;
end


%  
%  
for i=1:anzahl_montecarloversuche
  T_neu=[T',ergebnis{i}.ergebnios'];
  plot(T_neu(1,2800:2868),'rx')
hold on
 plot(T(2800:end))
hold on
 plot(S(2800:2868),'go')
keyboard
hold off
end 























function S = OU_Simulate(S0, dT, n, mu, sigma, lambda)

a = exp(-lambda*dT);
b = mu*(1-a);
c = sigma*sqrt((1-a*a)/2/lambda);

S = [S0 filter(1,[1 -a], b+c*randn(1,n), a*S0)];
end



















function [ S ] = SimulateOrnsteinUhlenbeck( S0, mu, sigma, lambda, deltat, t )
periods = floor(t / deltat); 
S = zeros(periods, 1); S(1) = S0; 
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











