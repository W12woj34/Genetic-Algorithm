package geneticOperators;

import java.util.ArrayList;
import java.util.List;

public class Individual implements Cloneable, Comparable<Individual> {

    List<Integer> genotype;
    Double value;

    public Individual(List<Integer> genotype, Double value) {
        this.genotype = genotype;
        this.value = value;
    }

    public List<Integer> getGenotype() {
        return genotype;
    }

    public void setGenotype(List<Integer> genotype) {
        this.genotype = genotype;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    @Override
    public int compareTo(Individual individual) {
        return Double.compare(this.value, individual.value);
    }

    @Override
    protected Individual clone() throws CloneNotSupportedException {
        Individual clone;

        clone = (Individual) super.clone();
        clone.genotype = new ArrayList<>(this.genotype);

        return clone;
    }

}