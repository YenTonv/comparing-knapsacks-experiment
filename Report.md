# Team #bfff00 Knapsacks

## Experiment design
To approach this problem, we started out by creating an Item class to represent each individual Item that could be included in a knapsack, and a Knapsack class which
tracks a List of Item objects which are available to be put into the knapsack as well as another list of Item objects which indicate which of the available objects
have been selected to be put into the knapsack by one of the optimization algorithms.  Most of the functionality required for doing relevant calculations and managing the items themselves are handled by these classes which makes them easy to work with and largely self-managing.

For instance, the Item objects can return either their total value as specified in the input, or they can return a fractional value needed for deciding how much of the
item to put into a fractional knapsack.  Knapsack objects can be created either directly by randomly creating Item objects and adding them to the available Items List, or by passing in the name of a specification file to be parsed.

Once these objects were functional, we created two packages for the algorithms (separate ones for 01 vs Fractional knapsacks) each with an interface and a Java
implementation.  In the main java class, we also have methods for randomly generating Items to go into a list, for handling file output to .csv files once the
experiments have run, and for writing randomly generated knapsacks out to files for consistency of reuse.

Multiple algorithms can run off the same knapsack, but it doesn't make much sense to have multiple knapsacks per algorithm. Below
is the UML document giving a visual of the project's overall structure.

![](Project-2-UML.png)

## Brute Force

To do the brute force algorithm, we try to find a way to try out every single combination of items to put in the knapsack. We decided to use a recursion. Starting from the first item, we try the case where we put that item into the knapsack and the case that it is not. Each case will become a subtree, where other items are also tried out. Every next item creates 2 subtrees, until we arrive at the end of the availableItems list.

We use a list called tempList to keep track of the current state of each recursion node. We found that tempList is changed if it is simply passed into a recursion, so a copy of tempList is created for each recursion call.

Time complexity is O(2^n). Space complexity is also O(2^n), because we create a new templist for every tree node. To optimize space complexity, we could try removing items from tempList every time a recursion finish. That way, we wouldn't need to create so many tempList copies, and space complexity could be O(N).

Fractional knapsack brute force is very similar to 01 knapsack brute force. The only difference is in the base case, where we try to split the last item into fractions. The assumption here is only at most 1 item needs to be fractionalized. Time complexity is O(2^n) and space complexity is O(2^n), as discussed previously with 01-Knapsack.

## Greedy

## Dynamic Programming

Overall, the Dynamic Programing solution for the 01 Knapsack problem is the ideal method. Starting with just speed, the
algorithm is significantly faster than either the brute force, or the greedy solutions. At its worst in these test cases,
the algorithm takes 720 micro seconds, whereas greedy (it's closest rival) manages it in 29429 micro seconds. More abstractly
speaking, this algorithm performs in O(n * m) time, where n is the amount of available items, and m is the capacity. The
results line up with this analysis.

One thing to note here is that the greedy algorithm isn't actually accurate. It doesn't always choose the most optimal
combination of items for this specific problem. Sometimes it gets it right, but other times is does not. Another reason
why the greedy method is not the ideal algorithm for this problem.

![](https://i.imgur.com/MJdLqVL.png)

As far as implementation goes, the algorithm is fairly straight forward.
The first step is to create a dp table. This is built up by taking the maximum of the 
space right above the current index, and the space above minus the weight of the current index PLUS
the value of the current item. Once the dp matrix is built up this way, the items are selected by starting at the bottom
right, checking if the index directly above is different, if not moving up, and if so choosing the item then
moving to the left by the weight of the current item. It's a clever way of solving the problem, and for this specific problem,
an important method since other methods are either FAR too slow or even inaccurate.


For the dynamic programming method of doing fractional knapsacks, we decided to split each item apart into units with weight 1 and value (value/weight), then ran the 
dynamic 01 algorithm as normal.  As expected, this approach was slower than the 01 dynamic algorithm because there were far more items in the list that eventually
got processed, but it returned correct results.  In this case, since Greedy is much faster and always returns correct results for fractional knapsacks, that is a
better method to use.

## Findings

Looking at the graphs, we see that the bruteforce algorithm only works with a small number of elements (this is true for both 01 and fractional knapsack). For fractional knapsack, the greedy algorithm performs the best when it gives the right results with the least time, while the dynamic program method takes almost twice the time. 

For 01 knapsack, 01 greedy gives us some wrong results because that is the nature of greedy algorithm. Greedy algorithms also become slow when there is an increase in elements. This means dynamic programing not only gives correct results but also scales better. 

As far as an alternative algorithm, our brute force recursion is already similar to a divide and conquer approach. We probably can make it better by trying to end each subtree early instead of testing every possible combination. For example, if we sort the list before the recursion, we may find a way to know whether a subtree is worth exploring.


![01 Profits](https://user-images.githubusercontent.com/53946628/223330349-1cbe11d5-c0ac-452f-a09b-cafd72309688.png)


![FractionalProfits](https://user-images.githubusercontent.com/53946628/223330381-a4e31b69-5cda-440a-b9f1-05465ff3e9d7.png)
