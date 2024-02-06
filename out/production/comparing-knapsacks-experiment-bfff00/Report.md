# Team #bfff00 Knapsacks

## Experiment design

## Brute Force

To do the brute force algorithm, we try to find a way to try out every single combination of items to put in the knapsack. We decided to use a recursion. Starting from the first item, we try the case where we put that item into the knapsack and in the case that it is not. Each case will become a subtree, where other items are also tried out. Fractional knapsack brute force is very similar to 01 knapsack brute force. The only difference is in the base case, where we try to split the last item into fractions. 

## Greedy

## Dynamic Programming

## Findings

Looking at the graphs, we see that the bruteforce algorithm only works with a small number of elements (this is true for both 01 and fractional knapsack). For fractional knapsack, the greedy algorithm performs the best when it gives the right results with the least time, while the dynamic program method takes almost twice the time. 

For 01 knapsack, 01 greedy gives us some wrong results because that is the nature of greedy algorithm. Greedy algorithms also become slow when there is an increase in elements. This means dynamic programing not only gives correct results but also scales better. 

As far as an alternative algorithm, our brute force recursion is already similar to a divide and conquer approach. We probably can make it better by trying to end each subtree early instead of testing every possible combination. For example, if we sort the list before the recursion, we may find a way to know whether a subtree is worth exploring.


![01 Profits](https://user-images.githubusercontent.com/53946628/223330349-1cbe11d5-c0ac-452f-a09b-cafd72309688.png)


![FractionalProfits](https://user-images.githubusercontent.com/53946628/223330381-a4e31b69-5cda-440a-b9f1-05465ff3e9d7.png)
