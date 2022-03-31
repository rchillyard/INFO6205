package edu.neu.coe.info6205.sort.par;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;

public class Main {

    public static void main(String[] args) {
        processArgs(args);
        System.out.println("Degree of parallelism: " + ForkJoinPool.getCommonPoolParallelism());
        Random random = new Random();
        int[] array = new int[4000000];
        ArrayList<Long> timeList = new ArrayList<>();
        ForkJoinPool forkJoinPool = new ForkJoinPool(1024);

        //Cutoff
        for(int i = 0; i <= 10; i++) {
            ParSort.height = i;
            System.out.println("Height: " + i);
            for (int j = 1; j < 50; j += 5) {
                ParSort.cutoff = (8000000 * j) / 100;
                long time;
                long startTime = System.currentTimeMillis();
                for (int k = 0; k < 10; k++) {
                    for (int inner = 0; inner < array.length; inner++) array[inner] = random.nextInt(10000000);
                    ParSort.sort(array, 0, array.length, forkJoinPool, 0);
                }
                long endTime = System.currentTimeMillis();
                time = (endTime - startTime);
                timeList.add(time);
                System.out.println(time);
            }
        }

        //Height
        for(int l = 0; l <= 10; l++){
            ParSort.height = l;
            ParSort.cutoff = (10000000 * 20)/100;

            long time;
            long startTime = System.currentTimeMillis();
            for (int m = 0; m < 10; m++) {
                for (int n = 0; n < array.length; n++) array[n] = random.nextInt(10000000);
                ParSort.sort(array, 0, array.length, forkJoinPool, 0);
            }
            long endTime = System.currentTimeMillis();
            time = (endTime - startTime);
            timeList.add(time);

            System.out.println(time);
        }
        try {
            FileOutputStream fis = new FileOutputStream("./src/result.csv");
            OutputStreamWriter isr = new OutputStreamWriter(fis);
            BufferedWriter bw = new BufferedWriter(isr);
            int j = 0;
            for (long i : timeList) {
                String content = (double) 10000 * (j + 1) / 2000000 + "," + (double) i / 10 + "\n";
                j++;
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
