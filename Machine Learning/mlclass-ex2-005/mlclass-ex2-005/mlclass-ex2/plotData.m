function plotData(X, y)
%PLOTDATA Plots the data points X and y into a new figure 
%   PLOTDATA(x,y) plots the data points with + for the positive examples
%   and o for the negative examples. X is assumed to be a Mx2 matrix.

% Create New Figure
figure; hold on;

% ====================== YOUR CODE HERE ======================
% Instructions: Plot the positive and negative examples on a
%               2D plot, using the option 'k+' for the positive
%               examples and 'ko' for the negative examples.
%

Y_pos_indices = find(y==1); 
Y_neg_indices = find(y==0); 


for i=1:size(Y_pos_indices,1)
    plot(X(Y_pos_indices(i),1),X(Y_pos_indices(i),2),'g+');
end

for i=1:size(Y_neg_indices,1)
    plot(X(Y_neg_indices(i),1),X(Y_neg_indices(i),2),'ro');
end





% =========================================================================



hold off;

end
