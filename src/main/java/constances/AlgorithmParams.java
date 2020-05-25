package constances;

public final class AlgorithmParams {

    private AlgorithmParams() {
    }

    public static double MUTATION_PROB = 0.01;
    public static double CROSSOVER_PROB = 0.9;
    public static int GENERATIONS_NUMBER = 400;
    public static int POPULATION_SIZE = 500;
    public static int TOURNAMENT_SIZE = 30;

    public static final String FILE = "berlin52.tsp";
}
