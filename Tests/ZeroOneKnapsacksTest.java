package Tests;

import knapsacks.Knapsack;
import org.junit.jupiter.api.Test;
import zeroone.ZeroOneInterface;
import zeroone.ZeroOneKnapsacks;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class ZeroOneKnapsacksTest {

    @Test
    void bruteForce01() throws FileNotFoundException {
        Solutions solutions = new Solutions();
        ZeroOneInterface algos = new ZeroOneKnapsacks();
        int numOfInputs = 6;
        for (int i = 1; i <= numOfInputs; i++) {
            Knapsack sack = new Knapsack("inputs" + i + ".csv");
            algos.bruteForce01(sack);
            assertEquals(solutions.getCorrectAmount01().get(i - 1), sack.calculateCurrentValue());
        }
    }

    //Not applicable for testing because this is a suboptimal algorithm and will produce wrong results even if the
    //algorithm is written perfectly.
    @Test
    void greedy01() {
    }

    @Test
    void dynamic01() throws FileNotFoundException {
        Solutions solutions = new Solutions();
        ZeroOneInterface algos = new ZeroOneKnapsacks();
        int numOfInputs = 6;
        for (int i = 1; i <= numOfInputs; i++) {
            Knapsack sack = new Knapsack("inputs" + i + ".csv");
            algos.dynamic01(sack);
            assertEquals(solutions.getCorrectAmount01().get(i - 1), sack.calculateCurrentValue());
        }
    }
}