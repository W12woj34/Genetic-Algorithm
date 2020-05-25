package algorithms;

import problems.SalesmanProblem;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.util.ArrayList;
import java.util.Collections;

public class GreedyAlgorithm extends Algorithm {


    public GreedyAlgorithm(SalesmanProblem problem) {
        super(problem);
    }

    @Override
    public ImmutablePair<ArrayList<Integer>, Double> solveProblem() {

        Integer startCity;
        ArrayList<ImmutablePair<ArrayList<Integer>, Double>> sollutions = new ArrayList<>();
        ArrayList<Double> sollutionsValues = new ArrayList<>();

        for (int k = 0; k < problem.getMatrix().size(); k++) {

            ArrayList<Integer> solution = new ArrayList<>();

            startCity = k;

            solution.add(startCity);

            ArrayList<Route> possibleRoutes = new ArrayList<>();

            for (int j = 0; j < problem.getMatrix().size() - 1; j++) {

                possibleRoutes.clear();

                for (int i = 0; i < problem.getMatrix().size(); i++) {

                    if (!solution.contains(i)) {
                        possibleRoutes.add(new Route(problem.getMatrix().get(solution.get(solution.size() - 1)).get(i), i));
                    }
                }
                Collections.sort(possibleRoutes);
                solution.add(possibleRoutes.get(0).destination);
            }

            solutionValue = problem.calculateResult(solution);

            sollutions.add(new ImmutablePair<>(solution, solutionValue));
            sollutionsValues.add(solutionValue);
        }

        int bestSollutionIndex = 0;
        for (int i = 0; i < sollutionsValues.size(); i++) {
            if (sollutionsValues.get(bestSollutionIndex) > sollutionsValues.get(i)) {
                bestSollutionIndex = i;
            }
        }


        return sollutions.get(bestSollutionIndex);
    }

    static class Route implements Comparable<Route> {

        private Integer destination;
        private double length;


        Route(double length, Integer destination) {
            this.destination = destination;
            this.length = length;
        }

        @Override
        public int compareTo(Route o) {
            return Double.compare(this.length, o.length);
        }

    }
}
