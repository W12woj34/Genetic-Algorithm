package geneticOperators.selection;

import geneticOperators.Individual;

import java.util.List;
import java.util.stream.Collectors;

public class RouletteSelection extends Selection {

    public RouletteSelection() {
        super();
    }

    @Override
    public Individual makeSelection(List<Individual> population) {


        double worstFitness = 0;

        for (Individual individual : population) {
            if (individual.getValue() > worstFitness) {
                worstFitness = individual.getValue();
            }
        }

        final double finalWorstFitness = worstFitness;
        List<Double> rouletteFitness = population.stream().map(individual -> (finalWorstFitness - individual.getValue()) + 1)
                .collect(Collectors.toList());
        final double sumFitness = rouletteFitness.stream().mapToDouble(Double::doubleValue).sum();

        List<Double> probabilities = rouletteFitness.stream().map(fitness -> fitness / sumFitness).collect(Collectors.toList());

        double result = random.nextDouble();
        double probSum = 0;
        for (int i = 0; i < probabilities.size(); i++) {
            probSum += probabilities.get(i);
            if (result < probSum) {
                return population.get(i);
            }
        }

        return population.get(population.size() - 1);
    }
}
