package geneticOperators.crossover;


import java.util.List;
import java.util.Random;

public abstract class Crossover {

    Random random;

    public Crossover() {
        random = new Random();
        random.setSeed(123);
    }

    public abstract List<List<Integer>> makeCrossover(List<Integer> firstParent, List<Integer> secondParent);
}
