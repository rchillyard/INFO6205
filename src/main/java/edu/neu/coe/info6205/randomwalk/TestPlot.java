package edu.neu.coe.info6205.daim;

import com.opencsv.CSVWriter;
import edu.neu.coe.info6205.randomwalk.RandomWalk;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class TestPlot {
    static String filePath = "plotData/randomWalkPlot.csv";
    static final int totalSteps = 1000;
    static int numSteps;
    static final int numExperiments = 1000;
    static String[] distance = new String[totalSteps];

    public static void main(String[] args) {
        for (int i = 0; i < totalSteps; i++) {
            numSteps = i;
            distance[i] = String.valueOf(RandomWalk.randomWalkMulti(numSteps, numExperiments));
        }

        writeDataLineByLine(filePath, distance);
    }
    public static void writeDataLineByLine(String filePath, String[] distance)
    {
        File file = new File(filePath);
        try {
            // create FileWriter object with file as parameter
            FileWriter outputfile = new FileWriter(file);

            // create CSVWriter object filewriter object as parameter

            CSVWriter writer = new CSVWriter(outputfile);

            writer.writeNext(distance);

            // closing writer connection
            writer.close();
        }
        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
