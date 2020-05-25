package geneticOperators.mutation;

import java.util.List;
import java.util.Random;

public abstract class Mutation {

    protected Random random;
    public Mutation() {
        random = new Random();
        random.setSeed(123);
    }

    public abstract void makeMutation(List<Integer> genotype);
}
