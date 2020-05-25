package algorithms;

import geneticOperators.Individual;
import geneticOperators.crossover.*;
import geneticOperators.mutation.*;
import geneticOperators.selection.*;
import problems.SalesmanProblem;
import org.apache.commons.lang3.tuple.ImmutablePair;
import utils.DataWriter;

import static constances.AlgorithmParams.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class GeneticAlgorithm extends Algorithm {

    Random random;
    int problemSize;
    Individual bestSolution;
    List<Individual> population;
    Crossover crossoverOperator;
    Mutation mutationOperator;
    Selection selectionOperator;
    DataWriter writer;
    List<String> bestSolutionsForEachPopulations;

    public GeneticAlgorithm(SalesmanProblem problem) {
        super(problem);

        random = new Random();
      //  random.setSeed(123);
        problemSize = problem.getMatrix().size();
        crossoverOperator = new CycleCrossover();
        mutationOperator = new SwapMutation();
        selectionOperator = new TournamentSelection();
        writer = new DataWriter();
        bestSolutionsForEachPopulations = new ArrayList<>();
    }

    @Override
    public ImmutablePair<ArrayList<Integer>, Double> solveProblem() {

        bestSolution = null;
        population = null;
        population = generatePopulation();
        bestSolution = chooseSolution();

        for (int i = 0; i < GENERATIONS_NUMBER - 1; i++) {

            population = runIteration();
            bestSolution = chooseSolution();


        }

        bestSolutionsForEachPopulations.add("BEST " + bestSolution.getValue());
        if (selectionOperator instanceof TournamentSelection) {
            writer.saveBestSolutions("src/main/resources/" +
                    "Solutions/MUT_PROB=" + MUTATION_PROB +
                    " CROSS_PROB=" + CROSSOVER_PROB + " POP_SIZE=" + POPULATION_SIZE +
                    " GEN_NUM=" + GENERATIONS_NUMBER + " TOURNAMENT_SIZE=" + TOURNAMENT_SIZE +
                     " " + FILE, bestSolutionsForEachPopulations);
        } else {
            writer.saveBestSolutions("src/main/resources/" +
                    "Solutions/MUT_PROB=" + MUTATION_PROB +
                    " CROSS_PROB=" + CROSSOVER_PROB + " POP_SIZE=" + POPULATION_SIZE +
                    " GEN_NUM=" + GENERATIONS_NUMBER + " ROULETTE " + TOURNAMENT_SIZE +
                    FILE, bestSolutionsForEachPopulations);
        }

        return new ImmutablePair<>((ArrayList<Integer>) bestSolution.getGenotype(), bestSolution.getValue());
    }

    private Individual chooseSolution() {
        int bestIndividualIndex = 0;
        for (int i = 0; i < population.size(); i++) {

            if (population.get(i).getValue() < population.get(bestIndividualIndex).getValue()) {
                bestIndividualIndex = i;
            }
        }

        bestSolutionsForEachPopulations.add(population.get(bestIndividualIndex).getValue().toString());
        if (bestSolution == null || population.get(bestIndividualIndex).getValue() < bestSolution.getValue()) {
            return new Individual(new ArrayList<>(population.get(bestIndividualIndex).getGenotype()),
                    population.get(bestIndividualIndex).getValue());
        } else {

            return bestSolution;
        }
    }

    private List<Individual> generatePopulation() {

        List<Integer> possibleCities;
        int cityPos;
        double value;
        List<Individual> population = new ArrayList<>();
        for (int i = 0; i < POPULATION_SIZE; i++) {

            List<Integer> genotype = new ArrayList<>();
            possibleCities = IntStream.range(0, problemSize).boxed().collect(Collectors.toList());

            for (int j = 0; j < problemSize; j++) {


                cityPos = random.nextInt(possibleCities.size());
                genotype.add(possibleCities.get(cityPos));
                possibleCities.remove(cityPos);
            }

            value = problem.calculateResult(genotype);
            population.add(new Individual(genotype, value));

        }
        return population;
    }


    private List<Individual> runIteration() {


        List<Individual> nextPopulation = new ArrayList<>();
        Collections.sort(population);
        ArrayList<Integer> elite1Genotype = new ArrayList<>(population.get(0).getGenotype());
        ArrayList<Integer> elite2Genotype = new ArrayList<>(population.get(1).getGenotype());
        mutationOperator.makeMutation(elite1Genotype);
        mutationOperator.makeMutation(elite2Genotype);
        nextPopulation.add(new Individual(elite1Genotype, problem.calculateResult(elite1Genotype)));
        nextPopulation.add(new Individual(elite2Genotype, problem.calculateResult(elite2Genotype)));

        while (nextPopulation.size() < POPULATION_SIZE) {

            //selection
            Individual firstParent = selectionOperator.makeSelection(population);
            Individual secondParent;
            do {
                secondParent = selectionOperator.makeSelection(population);
            } while (firstParent == secondParent);


            //crossover
            List<List<Integer>> childGenotypes;
            if (random.nextDouble() <= CROSSOVER_PROB) {
                childGenotypes = crossoverOperator.makeCrossover(firstParent.getGenotype(), secondParent.getGenotype());
            } else {
                childGenotypes = new ArrayList<>();
                childGenotypes.add(new ArrayList<>(firstParent.getGenotype()));
                childGenotypes.add(new ArrayList<>(secondParent.getGenotype()));
            }

            //mutation
            for (List<Integer> childGenotype : childGenotypes) {
                mutationOperator.makeMutation(childGenotype);
            }

            //evaluation and add to new population
            for (List<Integer> childGenotype : childGenotypes) {
                nextPopulation.add(new Individual(childGenotype, problem.calculateResult(childGenotype)));
            }

        }

        if (nextPopulation.size() > POPULATION_SIZE) {
            nextPopulation.remove(nextPopulation.size() - 1);
        }

        return nextPopulation;
    }

}
