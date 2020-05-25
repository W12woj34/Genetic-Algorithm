package algorithms;

import problems.SalesmanProblem;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import static constances.AlgorithmParams.GENERATIONS_NUMBER;
import static constances.AlgorithmParams.POPULATION_SIZE;

public class RandomAlgorithm extends Algorithm {

    private Random random;

    public RandomAlgorithm(SalesmanProblem problem) {
        super(problem);
        random = new Random();
        random.setSeed(123);
    }

    @Override
    public ImmutablePair<ArrayList<Integer>, Double> solveProblem() {
        ArrayList<ImmutablePair<ArrayList<Integer>, Double>> solutions = new ArrayList<>();
        ArrayList<Double> solutionsValues = new ArrayList<>();

        for (int k = 0; k < POPULATION_SIZE * GENERATIONS_NUMBER; k++) {

            ArrayList<Integer> solution = new ArrayList<>();
            ArrayList<Integer> cities = new ArrayList<>();
            for (int i = 0; i < problem.getMatrix().size(); i++) {
                cities.add(i);
            }


            int cityId;
            for (int i = 0; i < problem.getMatrix().size(); i++) {

                cityId = random.nextInt(cities.size());
                solution.add(cities.get(cityId));
                cities.remove(cities.get(cityId));
            }


            solutionValue = problem.calculateResult(solution);
            solutions.add(new ImmutablePair<>(solution, solutionValue));
            solutionsValues.add(solutionValue);
        }

        int bestSolutionIndex = 0;
        for (int i = 0; i < solutionsValues.size(); i++) {
            if (solutionsValues.get(bestSolutionIndex) > solutionsValues.get(i)) {
                bestSolutionIndex = i;
            }
        }

        double suma = 0.0;
        Collections.sort(solutionsValues);
        System.out.println(solutionsValues.get(0) + " Random Best");
        System.out.println(solutionsValues.get(solutionsValues.size() - 1) + " Random Worst");
        for (Double value : solutionsValues) {
            suma += value;
        }
        System.out.println(suma / solutionsValues.size() + " Random AVG");
        return solutions.get(bestSolutionIndex);

    }


}
