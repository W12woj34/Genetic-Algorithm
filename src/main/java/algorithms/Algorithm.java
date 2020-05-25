package algorithms;


import org.apache.commons.lang3.tuple.ImmutablePair;
import problems.SalesmanProblem;

import java.util.ArrayList;

public abstract class Algorithm {

    protected SalesmanProblem problem;
    protected ArrayList<Integer> solution = new ArrayList<>();
    protected Double solutionValue;


    public Algorithm(SalesmanProblem problem) {
        this.problem = problem;
    }


    public abstract ImmutablePair<ArrayList<Integer>, Double> solveProblem();
}
