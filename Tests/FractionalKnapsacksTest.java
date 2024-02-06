package Tests;

import fractional.FractionalKnapsacks;
import knapsacks.Knapsack;
import org.junit.jupiter.api.Test;
import zeroone.ZeroOneInterface;
import zeroone.ZeroOneKnapsacks;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class FractionalKnapsacksTest {

    @Test
    void bruteForceFractional() throws FileNotFoundException {
        Solutions solutions = new Solutions();
        FractionalKnapsacks algos = new FractionalKnapsacks();
        int numOfInputs = 6;
        for (int i = 1; i <= 2; i++) {
            Knapsack sack = new Knapsack("inputs" + i + ".csv");
            algos.bruteForceFractional(sack);
            double roundedNum = (double) ((int) (sack.calculateCurrentValue() * 100)) / 100;
            assertEquals(solutions.getCorrectAmountFractional().get(i - 1), roundedNum);
        }
    }

    @Test
    void greedyFractional() throws FileNotFoundException {
        Solutions solutions = new Solutions();
        FractionalKnapsacks algos = new FractionalKnapsacks();
        int numOfInputs = 6;
        for (int i = 1; i <= 2; i++) {
            Knapsack sack = new Knapsack("inputs" + i + ".csv");
            algos.bruteForceFractional(sack);
            double roundedNum = (double) ((int) (sack.calculateCurrentValue() * 100)) / 100;
            assertEquals(solutions.getCorrectAmountFractional().get(i - 1), roundedNum);
        }
    }

    @Test
    void dynamicFractional() throws FileNotFoundException {
        Solutions solutions = new Solutions();
        FractionalKnapsacks algos = new FractionalKnapsacks();
        int numOfInputs = 6;
        for (int i = 1; i <= 2; i++) {
            Knapsack sack = new Knapsack("inputs" + i + ".csv");
            algos.bruteForceFractional(sack);
            double roundedNum = (double) ((int) (sack.calculateCurrentValue() * 100)) / 100;
            assertEquals(solutions.getCorrectAmountFractional().get(i - 1), roundedNum);
        }
    }
}