package edu.neu.coe.info6205.sort.par;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.*;
import java.util.concurrent.ForkJoinPool;

/**
 * This code has been fleshed out by Ziyao Qiao. Thanks very much.
 * CONSIDER tidy it up a bit.
 */
public class Main {

    public static void main(String[] args) {
        processArgs(args);
        for (int i = 2000000; i <= 16000000; i = i*2) {
            runSorting(i, 4, 2);
            runSorting(i, 8, 3);
            runSorting(i, 16, 4);
            runSorting(i, 32, 5);
            runSorting(i, 64, 6);
        }

    }


    private static void runSorting(int arrayLength, int parallelism, int depth) {
        //System.out.println("Degree of parallelism: " + ForkJoinPool.getCommonPoolParallelism());
        //java.util.concurrent.ForkJoinPool.commonPool().
        System.out.println("Degree of parallelism: " + parallelism + " ArrayLength: " + arrayLength + " depth: " + depth);
        Random random = new Random();
        int[] array = new int[arrayLength];
        ArrayList<Long> timeList = new ArrayList<>();
        ParSort.maxDepth = depth;
        ParSort.maxRunningThread = parallelism;
        int increment = ((arrayLength/2) - 25000)/20;
        for (int j = 25000; j <= (arrayLength/2); j += increment) {
            ParSort.cutoff = (j);
            //for (int i = 0; i < array.length; i++) array[i] = random.nextInt(10000000);
            long time;
            long startTime = System.currentTimeMillis();
            for (int t = 0; t < 10; t++) {
                for (int i = 0; i < array.length; i++) array[i] = random.nextInt(10000000);
                ParSort.sort(array, 0, array.length, new ForkJoinPool(parallelism), 0);
            }
            long endTime = System.currentTimeMillis();
            time = (endTime - startTime);
            timeList.add(time);

            System.out.println("cutoff：" + (ParSort.cutoff) + "\t\t10 times Time:" + time + "ms");

        }
        try {
            FileOutputStream fis = new FileOutputStream("./src/" + arrayLength + "_parallelism_" + parallelism + ".csv");
            OutputStreamWriter isr = new OutputStreamWriter(fis);
            BufferedWriter bw = new BufferedWriter(isr);
            int j = 25000;
            for (long i : timeList) {
                String content = (double) (j) / arrayLength + "," + (double) i / 10 + "\n";
                j += increment;
                bw.write(content);
                bw.flush();
            }
            bw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void processArgs(String[] args) {
        String[] xs = args;
        while (xs.length > 0)
            if (xs[0].startsWith("-")) xs = processArg(xs);
    }

    private static String[] processArg(String[] xs) {
        String[] result = new String[0];
        System.arraycopy(xs, 2, result, 0, xs.length - 2);
        processCommand(xs[0], xs[1]);
        return result;
    }

    private static void processCommand(String x, String y) {
        if (x.equalsIgnoreCase("N")) setConfig(x, Integer.parseInt(y));
        else
            // TODO sort this out
            if (x.equalsIgnoreCase("P")) //noinspection ResultOfMethodCallIgnored
                ForkJoinPool.getCommonPoolParallelism();
    }

    private static void setConfig(String x, int i) {
        configuration.put(x, i);
    }

    @SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
    private static final Map<String, Integer> configuration = new HashMap<>();


}
