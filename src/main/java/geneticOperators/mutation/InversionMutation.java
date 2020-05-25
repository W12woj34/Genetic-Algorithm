package geneticOperators.mutation;

import java.util.List;

import static constances.AlgorithmParams.MUTATION_PROB;

public class InversionMutation extends Mutation {

    public InversionMutation() {
        super();
    }

    @Override
    public void makeMutation(List<Integer> genotype) {

        double result;
        int inverseStartPos;
        int inverseEndPos;
        int inverseTmp;
        for (int i = 0; i < genotype.size(); i++) {
            result = random.nextDouble();
            if (result < MUTATION_PROB) {
                inverseStartPos = i;
                do {
                    inverseEndPos = random.nextInt(genotype.size());
                } while (inverseStartPos == inverseEndPos);

                if (inverseStartPos > inverseEndPos) {
                    inverseTmp = inverseStartPos;
                    inverseStartPos = inverseEndPos;
                    inverseEndPos = inverseTmp;
                }

                for (int j = inverseStartPos, k = inverseEndPos; j < k; j++, k--) {
                    inverseTmp = genotype.get(j);
                    genotype.set(j, genotype.get(k));
                    genotype.set(k, inverseTmp);
                }

            }
        }
    }

}
