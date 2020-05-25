package geneticOperators.crossover;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CycleCrossover extends Crossover {

    public CycleCrossover() {
        super();
    }

    @Override
    public List<List<Integer>> makeCrossover(List<Integer> firstParent, List<Integer> secondParent) {

        List<List<Integer>> children = new ArrayList<>();
        List<Integer> child = new ArrayList<>(Collections.nCopies(firstParent.size(), -1));

        List<Integer> cycle = new ArrayList<>();
        cycle.add(0);
        int nextElement;
        while (true) {
            nextElement = firstParent.get(cycle.get(cycle.size()-1));
            if (nextElement == 0) {
                break;
            }
            cycle.add(nextElement);
        }

        child.set(0, cycle.get(0));
        for (int i = 1; i < cycle.size(); i++) {
            child.set(cycle.get(i - 1), cycle.get(i));
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
