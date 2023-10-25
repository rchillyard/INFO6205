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

import static java.lang.Math.floor;

/**
 * This code has been fleshed out by Ziyao Qiao. Thanks very much.
 * CONSIDER tidy it up a bit.
 */
public class Main {

    public static void main(String[] args) {
        for (int threads=1;threads<=8;threads*=2) {
            ForkJoinPool pool = new ForkJoinPool(threads);
            processArgs(args);
            System.out.println("Degree of parallelism: " + ForkJoinPool.getCommonPoolParallelism());
            Random random = new Random();
            for(int asize=1000000;asize<=10000000;asize+=2000000){
                long mintime=1000000;
            int[] array = new int[10000000];
            ArrayList<Long> timeList = new ArrayList<>();
            for (double jj = 0.002;jj < 0.71;jj*=2 ) {
            //for (double j = 10000; j < 5000000; j *= 2) {
                //ParSort.cutoff = 10000 * (j + 100);
                ParSort.cutoff = (int) floor(array.length*jj);
                //ParSort.cutoff = (int) j;
                //j=j*2;
                // for (int i = 0; i < array.length; i++) array[i] = random.nextInt(10000000);
                long time;
                long startTime = System.currentTimeMillis();
                //int noofthread;
                for (int t = 0; t < 10; t++) {
                    for (int i = 0; i < array.length; i++) array[i] = random.nextInt(10000000);
                    ParSort.sort(array, 0, array.length, pool);
                }
                long endTime = System.currentTimeMillis();
                time = (endTime - startTime);
                timeList.add(time);

                if (time<=mintime) mintime=time;


                System.out.println("cutoff：" + (ParSort.cutoff) + "\t\t10times Time:" + time/10 + "ms" + " threads: " + threads + " arraysize :" + asize);

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

            }  System.out.println(mintime+"  "+threads+ "  "+ asize   );
            }
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
