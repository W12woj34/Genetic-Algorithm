package problems;

import java.util.ArrayList;
import java.util.List;

public class SalesmanProblem {

    private ArrayList<ArrayList<Double>> matrix;

    public SalesmanProblem(ArrayList<ArrayList<Double>> matrix) {
        this.matrix = matrix;
    }

    public double calculateResult(List<Integer> path) {

        double result = 0;
        for (int i = 1; i < path.size(); i++) {
            result += matrix.get(path.get(i - 1)).get(path.get(i));
        }
        result += matrix.get(path.get(path.size()-1)).get(path.get(0));
        return result;
    }

    public ArrayList<ArrayList<Double>> getMatrix() {
        return matrix;
    }

    public void setMatrix(ArrayList<ArrayList<Double>> matrix) {
        this.matrix = matrix;
    }
}
