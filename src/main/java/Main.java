import algorithms.Algorithm;
import algorithms.GeneticAlgorithm;
import algorithms.GreedyAlgorithm;
import algorithms.RandomAlgorithm;
import problems.SalesmanProblem;
import utils.DataReader;

import java.util.ArrayList;

import org.apache.commons.lang3.tuple.ImmutablePair;

import static constances.AlgorithmParams.FILE;

public class Main {


    public static void main(String[] args) {

        String file = "src/main/resources/" + FILE;

        DataReader reader = new DataReader();
        SalesmanProblem problem = new SalesmanProblem(reader.getMatrix(file));
        Algorithm greedyAlgorithm = new GreedyAlgorithm(problem);
        Algorithm randomAlgorithm = new RandomAlgorithm(problem);
        Algorithm geneticAlgorithm = new GeneticAlgorithm(problem);

        ImmutablePair<ArrayList<Integer>, Double> resultRandom = randomAlgorithm.solveProblem();
        ImmutablePair<ArrayList<Integer>, Double> resultGreedy = greedyAlgorithm.solveProblem();
        ImmutablePair<ArrayList<Integer>, Double> resultGenetic = geneticAlgorithm.solveProblem();

        System.out.println("Algorytm Zachłanny:");
        displayPath(resultGreedy.left);
        System.out.println();
        System.out.println(resultGreedy.right);
        System.out.println();
        System.out.println("Algorytm Losowy:");
        displayPath(resultRandom.left);
        System.out.println();
        System.out.println(resultRandom.right);
        System.out.println();
        System.out.println("Algorytm Genetyczny:");
        displayPath(resultGenetic.left);
        System.out.println();
        System.out.println(resultGenetic.right);

    }


    private static void displayPath(ArrayList<Integer> path) {
        for (Integer city : path) {
            System.out.print((city + 1) + " ");
        }
    }
}
