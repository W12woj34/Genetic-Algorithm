package geneticOperators.selection;

import geneticOperators.Individual;

import java.util.List;
import java.util.Random;

public abstract class Selection {

    protected Random random;


    public Selection() {
        random = new Random();
        random.setSeed(123);
    }

    public abstract Individual makeSelection(List<Individual> population);
}
