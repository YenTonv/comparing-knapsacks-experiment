package zeroone;

import knapsacks.Knapsack;
import knapsacks.Item;
import java.util.Collections;
import java.util.Comparator;
import java.util.ArrayList;
import java.util.List;

public class ZeroOneKnapsacks implements ZeroOneInterface {
    /**
     * {@inheritDoc}
     * By: Yen Ton
     * @param knapsack
     */
    @Override
    public void bruteForce01(Knapsack knapsack) {
        int weight = 0;
        double value = 0;
        int pos = 0;
        List<Item> tempItems = new ArrayList<>();
        List<Item> availableItems = knapsack.getAvailableItems();
        bruteForce01(knapsack, knapsack.getCapacity(), availableItems, tempItems, weight, value, pos);

    } // theoretical efficiency: O( N! )


    /** Recursive helper method for brute force fractional method.
     *
     * @param knapsack is the knapsack to process
     * @param capacity is the capacity of this portion of the knapsack
     * @param availableItems is the number of available items in this portion of the knapsack
     * @param tempItems is the temporary list of items
     * @param weight is the weight of this portion of the knapsack
     * @param value is the value of this portion of the knapsack
     * @param pos is the position we're at in processing the knapsack
     */
    private void bruteForce01(Knapsack knapsack, int capacity, List<Item> availableItems, List<Item> tempItems, int weight, double value, int pos){

        //base case: return if all the position has been visited or the total weight of the tempItems list equal the capacity
        //if the current value of the tempItems list is larger than th
        if (pos == availableItems.size() - 1 || capacity == weight){
            if (knapsack.getCurrentValue() < value){
                knapsack.replaceOptimalItems(tempItems, weight, value);
            }
            return;
        }


        bruteForce01(knapsack, capacity, availableItems, new ArrayList<Item>(tempItems), weight, value,pos + 1);

        Item currentItem = availableItems.get(pos);

        if (weight + currentItem.getWeight() <= capacity) {
            List<Item> newTempItems = new ArrayList<Item>(tempItems);
            newTempItems.add(availableItems.get(pos));
            bruteForce01(knapsack, capacity, availableItems, newTempItems,
                    weight + currentItem.getWeight(), value + currentItem.getTotalValue(),pos + 1);
        }
    }

    /**availableItems.getCapacity
     * {@inheritDoc}
     * By Aodhan Bower
     * @param knapsack
     */
    @Override
    public void greedy01(Knapsack knapsack) {
        List<Item> availableItems = knapsack.getAvailableItems();
        availableItems.sort(Comparator.comparing(Item::getValuePerUnit));
        int cur = availableItems.size() - 1;
        for(int i = cur; i >= 0; i-- ) {
            Item toAdd = availableItems.remove(i);
            if(knapsack.getCapacity() == knapsack.getCurrentWeight()){
                return;
            }
            else if(toAdd.getWeight() + knapsack.getCurrentWeight() <= knapsack.getCapacity()){
                knapsack.addOptimalItem(toAdd);
            }



        }
    } // theoretical efficiency: O( N^2 )

    /**
     * {@inheritDoc}
     * By: Jason Wilkie
     * @param knapsack
     */
    @Override
    public void dynamic01(Knapsack knapsack) {
        List<Item> availableItems = knapsack.getAvailableItems();
        int numOfItems = availableItems.size() + 1;
        int capacity = knapsack.getCapacity() + 1;
        double[][] dp = new double[numOfItems][capacity];
        for (int i = 1; i < dp.length; i++) {
            Item currentItem = availableItems.get(i - 1);
            for (int j = 0; j < dp[0].length; j++) {
                double firstComparison = dp[i - 1][j];
                double secondComparison = j - currentItem.getWeight() >= 0 ? getComparison(dp, currentItem, i, j) : -1.0;
                dp[i][j] = Math.max(firstComparison, secondComparison);
            }
        }
        int xIndex = dp[0].length - 1;
        int yIndex = dp.length - 1;
        Item currItem = availableItems.get(yIndex - 1);
        while (canGetAnotherItem(currItem, dp, xIndex, yIndex)) {
            currItem = availableItems.get(yIndex - 1);
            if (dp[yIndex][xIndex] != dp[yIndex - 1][xIndex]) {
                xIndex = xIndex - currItem.getWeight();
                knapsack.addOptimalItem(currItem);
                yIndex--;
            } else {
                yIndex--;
            }
        }
    }// theoretical efficiency: O( N*M )

    /**
     * Helper method for 01 Dynamic algorithm.  Compares two items in the dynamic programming grid.
     * @param dp is the grid
     * @param item is item being considered
     * @param i is the column position in the grid
     * @param j is the row position in the grid
     * @return is the result of the comparison
     */
    private double getComparison(double[][] dp, Item item, int i, int j) {
        return dp[i - 1][j - item.getWeight()] + item.getTotalValue();
    }

    /**
     * Helper method for 01 Dynamic algorithm.  Determines whether another item will still fit in the knapsack.
     * @param item is the item being considered
     * @param dp is the dynamic programming grid
     * @param x is the column position in the grid
     * @param y is the row position in the grid
     * @return is true if another item will fit, false otherwise
     */
    private boolean canGetAnotherItem(Item item, double[][] dp, int x, int y) {
        return x - item.getWeight() >= 0 || y - 1 > -1 || dp[y][x] != 0;
    }
}
