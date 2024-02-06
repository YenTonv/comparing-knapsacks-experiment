package fractional;

import knapsacks.Item;
import knapsacks.Knapsack;


import java.util.Collections;
import java.util.Comparator;
import java.util.ArrayList;
import java.util.List;

public class FractionalKnapsacks implements FractionalInterface{

    public FractionalKnapsacks(){

    }

    /**
     * {@inheritDoc}
     * By: Yen Ton
     * @param knapsack
     */
    @Override
    public void bruteForceFractional(Knapsack knapsack) {
        int weight = 0;
        double value = 0;
        int pos = 0;
        List<Item> tempItems = new ArrayList<>();
        List<Item> skipItems = new ArrayList<>();
        List<Item> availableItems = knapsack.getAvailableItems();
        bruteForceFractional(knapsack, knapsack.getCapacity(), availableItems, tempItems, skipItems, weight, value, pos);

    } // theoretical efficiency: O( N! )


    /** Recursive helper method for brute force fractional method.
     *
     * @param knapsack is the knapsack to process
     * @param capacity is the capacity of this portion of the knapsack
     * @param availableItems is the number of available items in this portion of the knapsack
     * @param tempItems is the temporary list of items
     * @param skipItems is the list of items to skip
     * @param weight is the weight of this portion of the knapsack
     * @param value is the value of this portion of the knapsack
     * @param pos is the position we're at in processing the knapsack
     */
    private void bruteForceFractional(Knapsack knapsack, int capacity, List<Item> availableItems, List<Item> tempItems, List<Item> skipItems, int weight, double value, int pos) {

        //base case: return if all the position has been visited or the total weight of the tempItems list equal the capacity
        if (capacity == weight) {
            if (knapsack.getCurrentValue() < value) {
                knapsack.replaceOptimalItems(tempItems, weight, value);
            }
            return;
        }

        Item currentItem = availableItems.get(pos);
        if (pos == availableItems.size() - 1) {
            // find the best fractional item to add at the end of the optimal list
            double maxRatio = currentItem.getValuePerUnit();
            Item maxItem = currentItem;
            for (int i = 0; i <= skipItems.size() - 1; i++) {
                Item currentSkip = skipItems.get(i);
                if (currentSkip.getWeight() > (capacity - weight) && currentSkip.getValuePerUnit() > maxRatio) {
                    maxRatio = currentSkip.getValuePerUnit();
                    maxItem = currentSkip;
                }
            }
            double fracValue = (capacity - weight) * maxRatio;
            String name = maxItem.getName() + " modified";
            maxItem = new Item(name, capacity - weight, fracValue);
            tempItems.add(maxItem);
            if (knapsack.getCurrentValue() < value + fracValue) {
                knapsack.replaceOptimalItems(tempItems, capacity, value + fracValue);
            }
            return;
        }

        //explore next item
        List<Item> newSkipItems = new ArrayList<Item>(skipItems);
        newSkipItems.add(currentItem);
        bruteForceFractional(knapsack, capacity, availableItems, new ArrayList<Item>(tempItems), newSkipItems, weight, value, pos + 1);

        //explore possibilities after adding current item to the temp list
        if (weight + currentItem.getWeight() <= capacity) {
            List<Item> newTempItems = new ArrayList<Item>(tempItems);
            newTempItems.add(currentItem);
            bruteForceFractional(knapsack, capacity, availableItems, newTempItems, skipItems,
                    weight + currentItem.getWeight(), value + currentItem.getTotalValue(), pos + 1);
        }
    }

    /**
     * {@inheritDoc}
     * By: Aodhan Bower
     * @param knapsack
     */
    @Override
    public void greedyFractional(Knapsack knapsack) {
        List<Item> availableItems = knapsack.getAvailableItems();
        availableItems.sort(Comparator.comparing(Item::getValuePerUnit));
        int j = knapsack.getAvailableItems().size() - 1;
        for(int i = j; i >= 0; i--){
            Item toAdd = availableItems.remove(i);
            if(toAdd.getWeight() + knapsack.getCurrentWeight() > knapsack.getCapacity()){
                int curWeight = 0;
                double curVal = 0;
                double increment = toAdd.getValuePerUnit();
                while(knapsack.getCurrentWeight() + curWeight <= knapsack.getCapacity()){
                    curWeight++;
                    curVal += increment;
                }
                String name = toAdd.getName() + " modified";

                Item newItem = new Item(name,curWeight,curVal);
                knapsack.addOptimalItem(newItem);
            }
            else{
                knapsack.addOptimalItem(toAdd);
            }
        }


    } // theoretical efficiency: O( N^2 )

    /**
     * {@inheritDoc}
     * Implements the fractional knapsack using a dynamic programming algorithm by breaking each item apart into
     * multiple items of the minimum weight increment (in the case of this project, weight 1) and value of the value
     * per weight unit of the original item, then runs it using the 0/1 knapsack dynamic programming algorithm.
     * This method is not recommended as the much larger table makes it far slower than just using the greedy algorithm,
     * but doing it this way does produce correct results.
     * By: Mike Murphy
     * @param knapsack
     */
    @Override
    public void dynamicFractional(Knapsack knapsack) {
        // Get the initial list of available items
        List<Item> items = knapsack.getAvailableItems();

        // Make a copy of the original knapsack so we can tear it apart
        Knapsack k2 = knapsack.copyOf();

        // Clear the item list so we can rebuild it
        k2.resetAvailableItemList();

        // Break every item into w portions of that item with value v/w and add them to the copy of the knapsack
        for (Item i : items) {
            for (int j = 0; j < i.getWeight(); j++) {
                k2.addAvailableItem(new Item(i.getName(), 1, i.getValuePerUnit()));
            }
        }

        // Create a dynamic programming grid one column wider than the capacity and one row deeper than the available
        // items list
        double[][] dg = new double[k2.getAvailableItems().size() + 1][k2.getCapacity() + 1];

        // follow the dynamic programming 01 algorithm with the new deconstructed item list to build the grid
        for (int j = 1; j < k2.getAvailableItems().size() + 1; j++) {
            Item item = k2.getAvailableItems().get(j - 1);
            for (int i = 1; i < k2.getCapacity() + 1; i++) {
                dg[j][i] = Math.max(dg[j - 1][i], item.getTotalValue() + dg[j - 1][Math.max(0,i - item.getWeight())]);
            }
        }

        // dummy variable to represent the current item we're adding
        Item itemToAdd;

        // go back through the grid and look for value changes row to row.  If the value changes, the current item is in
        // the optimal list, so add it to the bag and move left by w columns
        for (int i = dg[0].length - 1; i > 1; i--) {
            for (int j = dg.length - 1; j > 0; j--) {
                if (dg[j][i] != dg[j - 1][i]) {
                    itemToAdd = k2.getAvailableItems().get(j - 1);
                    k2.addOptimalItem(itemToAdd);
                    i -= itemToAdd.getWeight();
                }
            }
        }

        // Now we have to reassemble the items, create dummy variables for Name, Weight, and Value
        String cur = k2.getOptimalItems().get(0).getName();
        int w = 0;
        double v = 0.0;

        // keep going through the list until the name changes, then sum up weight and value and add the item to the
        // original knapsack
        for (Item i : k2.getOptimalItems()) {
            if (i.getName().equals(cur)) {
                w += i.getWeight();
                v += i.getTotalValue();
            } else {
                knapsack.addOptimalItem(new Item(cur, w, v));
                cur = i.getName();
                w = i.getWeight();
                v = i.getTotalValue();
            }
        }

        // Be sure to also add the last item encountered
        knapsack.addOptimalItem(new Item(cur, w, v));


    } // theoretical efficiency: O(N * M)
}
