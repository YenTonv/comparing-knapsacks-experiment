package fractional;

import knapsacks.*;

public interface FractionalInterface {

    /**
     * Generates the optimal fractional knapsack (treats weight as a stack of weight 1 items) using the
     * brute force method.
     * @param knapsack is the Knapsack object to optimize.
     */
    public void bruteForceFractional(Knapsack knapsack);

    /**
     * Generates the optimal fractional knapsack (treats weight as a stack of weight 1 items) using the
     * greedy method.
     * @param knapsack is the Knapsack object to optimize.
     */
    public void greedyFractional(Knapsack knapsack);

    /**
     * Generates the optimal fractional knapsack (treats weight as a stack of weight 1 items) using the
     * dynamic programming method.
     * @param knapsack is the Knapsack object to optimize.
     */
    public void dynamicFractional(Knapsack knapsack);
}
