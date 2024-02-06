package knapsacks;

import fractional.FractionalInterface;
import fractional.FractionalKnapsacks;
import zeroone.ZeroOneInterface;
import zeroone.ZeroOneKnapsacks;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {

    // constants for randomly generated knapsacks
    public static final int MAX_WEIGHT = 75;
    public static final int MAX_VALUE = 150;

    // runs the experiment for six knapsacks of increasing size
    public static void main(String[] args) throws FileNotFoundException{

        runKnapsackExperiment(6);

        /*
        Knapsack k = generateRandomKnapsack("6", 175, 30);
        outputKnapsackToFile("inputs6.csv", k);

         */
    }

    /**
     * Runs each algorithm and outputs the results
     * @param numKnapsacks is the number of knapsacks in the experiment
     */

    public static void runKnapsackExperiment(int numKnapsacks) {
        runKnapsackExperiment01Greedy(numKnapsacks);
        runKnapsackExperiment01Dynamic(numKnapsacks);
        runKnapsackExperiment01BruteForce(numKnapsacks);
        runKnapsackExperimentFractionalGreedy(numKnapsacks);
        runKnapsackExperimentFractionalDynamic(numKnapsacks);
        runKnapsackExperimentFractionalBruteForce(numKnapsacks);
    }

    /**
     * Runs the 01 Greedy experiment
     * @param numKnapsacks is the number of knapsacks to optimize
     */
    public static void runKnapsackExperiment01Greedy(int numKnapsacks) {
        try {
            String outputFile = "01Greedy.csv";
            File outFile = new File(outputFile);
            FileWriter fw = new FileWriter(outputFile);
            ZeroOneInterface runner = new ZeroOneKnapsacks();
            StringBuilder sb = new StringBuilder("Capacity,NumElements,Time(ns),Profit");
            long start;
            long dur;
            double profit;

            Knapsack k;

            for (int i = numKnapsacks; i > 0; i--) {
                k = parseKnapsackFromFile("inputs" + i + ".csv");
                start = System.nanoTime();
                runner.greedy01(k);
                dur = System.nanoTime() - start;
                profit = k.getCurrentValue();
                k = parseKnapsackFromFile("inputs" + i + ".csv");
                sb.append("\n" + k.getCapacity() + "," + k.getAvailableItems().size() + "," + dur + "," + profit);
            }

            fw.write(sb.toString());
            System.out.println(sb.toString());
            fw.close();

        } catch (IOException e) {
            System.out.println(e);
        }

    }

    /**
     * Runs the 01 Dynamic Programming experiment
     * @param numKnapsacks is the number of knapsacks to optimize
     */
    public static void runKnapsackExperiment01Dynamic(int numKnapsacks) {
        try {
            String outputFile = "01Dynamic.csv";
            File outFile = new File(outputFile);
            FileWriter fw = new FileWriter(outputFile);
            ZeroOneInterface runner = new ZeroOneKnapsacks();
            StringBuilder sb = new StringBuilder("Capacity,NumElements,Time(ns)");
            long start;
            long dur;
            double profit;

            Knapsack k;

            for (int i = numKnapsacks; i > 0; i--) {
                k = parseKnapsackFromFile("inputs" + i + ".csv");
                start = System.nanoTime();
                runner.dynamic01(k);
                dur = System.nanoTime() - start;
                profit = k.getCurrentValue();
                k = parseKnapsackFromFile("inputs" + i + ".csv");
                sb.append("\n" + k.getCapacity() + "," + k.getAvailableItems().size() + "," + dur + "," + profit);
            }

            fw.write(sb.toString());
            System.out.println(sb.toString());
            fw.close();

        } catch (IOException e) {
            System.out.println(e);
        }

    }

    /**
     * Runs the 01 Brute Force experiment
     * @param numKnapsacks is the number of knapsacks to optimize
     */
    public static void runKnapsackExperiment01BruteForce(int numKnapsacks) {
        try {
            String outputFile = "01BruteForce.csv";
            File outFile = new File(outputFile);
            FileWriter fw = new FileWriter(outputFile);
            ZeroOneInterface runner = new ZeroOneKnapsacks();
            StringBuilder sb = new StringBuilder("Capacity,NumElements,Time(ns)");
            long start;
            long dur;
            double profit;

            Knapsack k;

            for (int i = numKnapsacks; i > 0; i--) {
                k = parseKnapsackFromFile("inputs" + i + ".csv");
                start = System.nanoTime();
                runner.bruteForce01(k);
                dur = System.nanoTime() - start;
                profit = k.getCurrentValue();
                k = parseKnapsackFromFile("inputs" + i + ".csv");
                sb.append("\n" + k.getCapacity() + "," + k.getAvailableItems().size() + "," + dur + "," + profit);
            }

            fw.write(sb.toString());
            System.out.println(sb.toString());
            fw.close();

        } catch (IOException e) {
            System.out.println(e);
        }

    }

    /**
     * Runs the Fractional Greedy experiment
     * @param numKnapsacks is the number of knapsacks to optimize
     */
    public static void runKnapsackExperimentFractionalGreedy(int numKnapsacks) {
        try {
            String outputFile = "FractionalGreedy.csv";
            File outFile = new File(outputFile);
            FileWriter fw = new FileWriter(outputFile);
            FractionalInterface runner = new FractionalKnapsacks();
            StringBuilder sb = new StringBuilder("Capacity,NumElements,Time(ns)");
            long start;
            long dur;
            double profit;

            Knapsack k;

            for (int i = numKnapsacks; i > 0; i--) {
                k = parseKnapsackFromFile("inputs" + i + ".csv");
                start = System.nanoTime();
                runner.greedyFractional(k);
                dur = System.nanoTime() - start;
                profit = k.getCurrentValue();
                k = parseKnapsackFromFile("inputs" + i + ".csv");
                sb.append("\n" + k.getCapacity() + "," + k.getAvailableItems().size() + "," + dur + "," + profit);
            }

            fw.write(sb.toString());
            System.out.println(sb.toString());
            fw.close();

        } catch (IOException e) {
            System.out.println(e);
        }

    }

    /**
     * Runs the Fractional Dynamic Programming experiment
     * @param numKnapsacks is the number of knapsacks to optimize
     */
    public static void runKnapsackExperimentFractionalDynamic(int numKnapsacks) {
        try {
            String outputFile = "FractionalDynamic.csv";
            File outFile = new File(outputFile);
            FileWriter fw = new FileWriter(outputFile);
            FractionalInterface runner = new FractionalKnapsacks();
            StringBuilder sb = new StringBuilder("Capacity,NumElements,Time(ns)");
            long start;
            long dur;
            double profit;

            Knapsack k;

            for (int i = numKnapsacks; i > 0; i--) {
                k = parseKnapsackFromFile("inputs" + i + ".csv");
                start = System.nanoTime();
                runner.dynamicFractional(k);
                dur = System.nanoTime() - start;
                profit = k.getCurrentValue();
                k = parseKnapsackFromFile("inputs" + i + ".csv");
                sb.append("\n" + k.getCapacity() + "," + k.getAvailableItems().size() + "," + dur + "," + profit);
            }

            fw.write(sb.toString());
            System.out.println(sb.toString());
            fw.close();

        } catch (IOException e) {
            System.out.println(e);
        }

    }

    /**
     * Runs the Fractional Brute Force experiment
     * @param numKnapsacks is the number of knapsacks to optimize
     */
    public static void runKnapsackExperimentFractionalBruteForce(int numKnapsacks) {
        try {
            String outputFile = "FractionalBruteForce.csv";
            File outFile = new File(outputFile);
            FileWriter fw = new FileWriter(outputFile);
            FractionalInterface runner = new FractionalKnapsacks();
            StringBuilder sb = new StringBuilder("Capacity,NumElements,Time(ns)");
            long start;
            long dur;
            double profit;

            Knapsack k;

            for (int i = numKnapsacks; i > 0; i--) {
                k = parseKnapsackFromFile("inputs" + i + ".csv");
                start = System.nanoTime();
                runner.bruteForceFractional(k);
                dur = System.nanoTime() - start;
                profit = k.getCurrentValue();
                k = parseKnapsackFromFile("inputs" + i + ".csv");
                sb.append("\n" + k.getCapacity() + "," + k.getAvailableItems().size() + "," + dur + "," + profit);
            }

            fw.write(sb.toString());
            System.out.println(sb.toString());
            fw.close();

        } catch (IOException e) {
            System.out.println(e);
        }

    }

    /**
     * Takes an input .csv file and creates a Knapsack object from it.
     * @param inputFile is the filename to import.
     * @return is the parsed Knapsack object.
     */
    public static Knapsack parseKnapsackFromFile(String inputFile) {
        Scanner in = null;
        Knapsack k;
        String[] characteristics;
        String[] weights;
        String[] values;

        try {
            in = new Scanner(new File(inputFile));
        } catch (FileNotFoundException e) {
            System.out.println(e);
            return new Knapsack();
        }

        characteristics = in.nextLine().split(",");
        k = new Knapsack(characteristics[0], Integer.parseInt(characteristics[1]));

        values = in.nextLine().split(",");
        weights = in.nextLine().split(",");

        for (int i = 0; i < weights.length; i++) {
            k.addAvailableItem(new Item("Item " + i, Integer.parseInt(weights[i]), Integer.parseInt(values[i])));
        }

        return k;
    }

    /**
     * Generates a Knapsack object with a random list of items.
     * @param name is the number (name) of the knapsack.
     * @param capacity is the capacity of the knapsack.
     * @param numberOfAvailableItems is the number of random items to add to the available list.
     * @return is the Knapsack object generated randomly.
     */
    public static Knapsack generateRandomKnapsack(String name, int capacity, int numberOfAvailableItems) {
        Knapsack k = new Knapsack(name, capacity);

        for (int i = 0; i < numberOfAvailableItems; i++) {
            k.addAvailableItem(generateRandomItem("Item " + i));
        }

        return k;
    }

    /**
     * Generates a new Item object with a random weight and value.
     * @param name is the name of the Item.
     * @return is the Item object generated.
     */
    public static Item generateRandomItem(String name) {
        return new Item(name, generateRandomNumber(1,MAX_WEIGHT), generateRandomNumber(1, MAX_VALUE));
    }

    /**
     * Generates a random number between min and max inclusive.
     * @param min the minimum allowable number
     * @param max the maximum allowable number
     * @return number between max and min
     */
    public static int generateRandomNumber(int min, int max){
        return (int)Math.floor(Math.random()*(max-min+1)+min);
    }

    /**
     * Outputs a given knapsack to a .csv file.
     * @param outputFile is the name of the file to create with the knapsack info.
     * @param knapsack is the knapsack to write to the file.
     */
    public static void outputKnapsackToFile(String outputFile, Knapsack knapsack) {
        try {
            File outFile = new File(outputFile);
            FileWriter fr = new FileWriter(outputFile);
            StringBuilder characteristics = new StringBuilder(knapsack.getName() + "," + knapsack.getCapacity());
            StringBuilder itemWeights = new StringBuilder();
            StringBuilder itemValues = new StringBuilder();
            int itemCount = 0;
            int totalItems = knapsack.getAvailableItems().size();

            for (Item i : knapsack.getAvailableItems()) {
                if (itemCount > 0 && itemCount < totalItems - 1) characteristics.append(",");
                itemWeights.append(i.getWeight() + (itemCount < totalItems - 1 ? "," : ""));
                itemValues.append((int)i.getTotalValue() + (itemCount++ < totalItems - 1 ? "," : ""));
            }

            fr.write(characteristics.toString()+"\n");
            fr.write(itemValues.toString()+"\n");
            fr.write(itemWeights.toString()+"\n");

            fr.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

}
