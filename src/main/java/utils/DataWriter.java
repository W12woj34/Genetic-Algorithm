package utils;


import java.io.*;
import java.util.*;

public class DataWriter {


    public void saveBestSolutions(String path, List<String> solutions) {


        try {
            File file = new File(path);
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            for (String value : solutions) {
                writer.write(value + System.getProperty("line.separator"));
            }
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
