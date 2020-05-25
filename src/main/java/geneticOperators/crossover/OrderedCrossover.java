package geneticOperators.crossover;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class OrderedCrossover extends Crossover {

    public OrderedCrossover() {
        super();
    }

    @Override
    public List<List<Integer>> makeCrossover(List<Integer> firstParent, List<Integer> secondParent) {

        List<List<Integer>> children = new ArrayList<>();
        List<Integer> child = new ArrayList<>(Collections.nCopies(firstParent.size(), -1));

        int crossStartPos;
        int crossEndPos;
        int crossTmp;
        crossStartPos = random.nextInt(firstParent.size());
        do {
            crossEndPos = random.nextInt(firstParent.size());
        } while (crossStartPos == crossEndPos);

        if (crossStartPos > crossEndPos) {
            crossTmp = crossStartPos;
            crossStartPos = crossEndPos;
            crossEndPos = crossTmp;
        }

        for (int i = crossStartPos; i < crossEndPos; i++) {
            child.set(i, firstParent.get(i));
        }

        for (int i = 0; i < child.size(); i++) {
            if (child.get(i) == -1) {
                for (int j = 0; j < secondParent.size(); j++) {
                    if (!child.contains(secondParent.get(j))) {
                        child.set(i, secondParent.get(j));
                        break;
                    }
                }
            }

        }

        children.add(child);
        return children;
    }


}
