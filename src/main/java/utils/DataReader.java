package utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class DataReader {


    public ArrayList<ArrayList<Double>> getMatrix(String path) {

        File file = new File(path);
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ArrayList<ArrayList<Double>> matrix = new ArrayList<>();
        ArrayList<City> cities = new ArrayList<>();

        String bufor;
        boolean collect = false;
        while (scanner != null && scanner.hasNextLine()) {

            bufor = scanner.nextLine();

            if (bufor.startsWith("EOF")) {
                collect = false;
            }

            if (collect) {
                String[] cityDataS = bufor.split(" ");


                List<String> cityData = new ArrayList<>();
                for (String string : cityDataS) {
                    if (!string.equals("")) {
                        cityData.add(string);
                    }
                }


                cities.add(new City(Integer.parseInt(cityData.get(0)), Double.parseDouble(cityData.get(1)), Double.parseDouble(cityData.get(2))));
            }

            if (bufor.startsWith("NODE_COORD_SECTION")) {
                collect = true;
            }
        }

        double distance;
        for (int i = 0; i < cities.size(); i++) {

            ArrayList<Double> distancesForCity = new ArrayList<>();

            for (City city : cities) {

                distance = Math.sqrt(Math.pow((cities.get(i).x - city.x), 2) + Math.pow((cities.get(i).y - city.y), 2));
                distancesForCity.add(distance);
            }

            matrix.add(distancesForCity);
        }

        return matrix;
    }


    static class City {

        int id;
        double x;
        double y;

        City(int id, double x, double y) {
            this.id = id;
            this.x = x;
            this.y = y;
        }
    }

}
