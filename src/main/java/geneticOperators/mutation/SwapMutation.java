package geneticOperators.mutation;

import java.util.List;

import static constances.AlgorithmParams.MUTATION_PROB;

public class SwapMutation extends Mutation {

    public SwapMutation() {
        super();
    }

    @Override
    public void makeMutation(List<Integer> genotype) {

        double result;
        int swapPos;
        int swapTmp;
        for (int i = 0; i < genotype.size(); i++) {
            result = random.nextDouble();
            if (result < MUTATION_PROB) {
                do {
                    swapPos = random.nextInt(genotype.size());
                } while (swapPos == i);
                swapTmp = genotype.get(i);
                genotype.set(i, genotype.get(swapPos));
                genotype.set(swapPos, swapTmp);
            }

        }


    }
}
