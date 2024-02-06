package Tests;

import fractional.FractionalKnapsacks;
import knapsacks.Item;
import knapsacks.Knapsack;
import zeroone.ZeroOneKnapsacks;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class Solutions {

    private List<Double> correctAmount01;
    private List<Double> correctAmountFractional;

    public Solutions() throws FileNotFoundException {
        ZeroOneKnapsacks algos01 = new ZeroOneKnapsacks();
        FractionalKnapsacks algosFractional = new FractionalKnapsacks();
        List<Double> solutions01 = new ArrayList<>();
        List<Double> solutionsFractional = new ArrayList<>();
        int numOfFiles = 6;
        for (int i = 1; i <= numOfFiles; i++) {
            Knapsack sack01 = new Knapsack("inputs" + i + ".csv");
            Knapsack sackFractional = new Knapsack("inputs" + i + ".csv");
            //Using dynamic01 algo because the correctness was manually verified
            algos01.dynamic01(sack01);
            //Using brute force fractional algo because the correctness was manually verified
            algosFractional.bruteForceFractional(sackFractional);

            double roundedNum = (double) ((int) (sackFractional.calculateCurrentValue() * 100)) / 100;
            solutions01.add(sack01.calculateCurrentValue());
            solutionsFractional.add(roundedNum);
        }
        correctAmount01 = solutions01;
        correctAmountFractional = solutionsFractional;
    }

    public List<Double> getCorrectAmount01() {
        return this.correctAmount01;
    }

    public List<Double> getCorrectAmountFractional() {
        return this.correctAmountFractional;
    }

}
