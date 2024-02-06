package zeroone;

import knapsacks.*;

public interface ZeroOneInterface {

    /**
     * Generates the optimal 0/1 knapsack (can only take the whole item) using the brute force method.
     * @param knapsack is the Knapsack object to optimize.
     */
    public void bruteForce01(Knapsack knapsack);

    /**
     * Generates the optimal 0/1 knapsack (can only take the whole item) using the greedy method.
     * @param knapsack is the Knapsack object to optimize.
     */
    public void greedy01(Knapsack knapsack);

    /**
     * Generates the optimal 0/1 knapsack (can only take the whole item) using the dynamic programming method.
     * @param knapsack is the Knapsack object to optimize.
     */
    public void dynamic01(Knapsack knapsack);
}
