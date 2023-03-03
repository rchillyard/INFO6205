package edu.neu.coe.info6205.sort;

import edu.neu.coe.info6205.sort.elementary.HeapSort;
import edu.neu.coe.info6205.sort.linearithmic.MergeSort;
import edu.neu.coe.info6205.sort.linearithmic.QuickSort_DualPivot;
import edu.neu.coe.info6205.util.*;

import java.io.File;
import java.io.FileWriter;
import java.security.spec.ECField;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ForkJoinPool;

import static java.util.concurrent.CompletableFuture.runAsync;


public class DiffSortBenchMark {

    public static void main(String[] args) {
        try {
            File fileHeap = new File("HeapBenchMark.csv");
            //File fileMerge = new File("MergeBenchMark.csv");
            File fileQuick = new File("QuickBenchMark.csv");
            fileHeap.createNewFile();
            fileQuick.createNewFile();
            //fileMerge.createNewFile();
            FileWriter fileWriterHeap = new FileWriter(fileHeap);
            //FileWriter fileWriterMerge = new FileWriter(fileMerge);
            FileWriter fileWriterQuick = new FileWriter(fileQuick);

            fileWriterHeap.write(getHeaderString());
            //fileWriterMerge.write(getHeaderString());
            fileWriterQuick.write(getHeaderString());
            boolean instrumentation = true;


            System.out.println("Degree of parallelism: " + ForkJoinPool.getCommonPoolParallelism());
            Config config = Config.setupConfig("true", "", "1", "", "");

            int start = 500000;
            int end = 8000000;

            CompletableFuture<FileWriter> heapSort = runHeapSort(start, end, config, fileWriterHeap);
            CompletableFuture<FileWriter> quickSort = runQuickSort(start, end, config, fileWriterQuick);
            //CompletableFuture<FileWriter> mergeSort = runMergeSort(start, end, config, fileWriterMerge);

            //mergeSort.join();
            quickSort.join();
            heapSort.join();
            //mergeSort.join();


        } catch (Exception e) {
            System.out.println("error while sorting main" + e);
        }
    }


    private static CompletableFuture runHeapSort(int start, int end, Config config, FileWriter fileWriter) {
        return CompletableFuture.runAsync(
                () -> {

                    for (int n = start; n <= end; n *= 2) {
                        Helper<Integer> helper = HelperFactory.create("HeapSort", n, config);
                        HeapSort<Integer> sort = new HeapSort<>(helper);
                        final int val = n;
                        Integer[] arr = helper.random(Integer.class, r -> r.nextInt(val));
                        SorterBenchmark sorterBenchmark = new SorterBenchmark<>(Integer.class,
                                (Integer[] array) -> {
                                    for (int i = 0; i < array.length; i++) {
                                        array[i] = array[i];
                                    }
                                    return array;
                                },
                                sort, arr, 1, timeLoggersLinearithmic);
                        double time = sorterBenchmark.run(n);
                        try {
                            fileWriter.write(createCsvString(n, time, ((InstrumentedHelper) helper).getStatPack(), config.isInstrumented()));
                            //System.out.println(((InstrumentedHelper) helper).getStatPack());
                        } catch (Exception e) {
                            System.out.println("error while writing file Heap" + e);
                        }
                    }
                    try {
                        fileWriter.flush();
                        fileWriter.close();
                    } catch (Exception e) {
                        System.out.println("error while closing file Heap" + e);
                    }

                }
        );
    }


    private static CompletableFuture runMergeSort(int start, int end, Config config, FileWriter fileWriter) {
        return runAsync(
                () -> {
                     for (int n = start; n <= end; n *= 2) {
                         Helper<Integer> helper = HelperFactory.create("MergeSort", n, config);
                         MergeSort<Integer> sort = new MergeSort<>(helper);
                         final int val = n;
                         Integer[] arr = helper.random(Integer.class, r -> r.nextInt(val));
                         SorterBenchmark sorterBenchmark = new SorterBenchmark<>(Integer.class,
                                 (Integer[] array) -> {
                                     for (int i = 0; i < array.length; i++) {
                                         array[i] = array[i];
                                     }
                                     return array;
                                 },
                                 sort, arr, 1, timeLoggersLinearithmic);
                         double time = sorterBenchmark.run(n);
                         try {
                             fileWriter.write(createCsvString(n, time, ((InstrumentedHelper) helper).getStatPack(), config.isInstrumented()));
                         } catch (Exception e) {
                             System.out.println("error while writing file Merge" + e);
                         }
                     }
                    try {
                        fileWriter.flush();
                        fileWriter.close();
                    } catch (Exception e) {
                        System.out.println("error while closing file Merge" + e);
                    }
                }
        );
    }


    private static CompletableFuture runQuickSort(int start, int end, Config config, FileWriter fileWriter) {
        return runAsync(
                () -> {
                    for (int n = start; n <= end; n *= 2) {
                        Helper<Integer> helper = HelperFactory.create("QuickSort", n, config);
                        QuickSort_DualPivot<Integer> sort = new QuickSort_DualPivot<>(helper);
                        final int val = n;
                        Integer[] arr = helper.random(Integer.class, r -> r.nextInt(val));
                        SorterBenchmark sorterBenchmark = new SorterBenchmark<>(Integer.class,
                                (Integer[] array) -> {
                                    for (int i = 0; i < array.length; i++) {
                                        array[i] = array[i];
                                    }
                                    return array;
                                },
                                sort, arr, 1, timeLoggersLinearithmic);
                        double time = sorterBenchmark.run(n);
                        try {
                            fileWriter.write(createCsvString(n, time, ((InstrumentedHelper) helper).getStatPack(), config.isInstrumented()));
                        } catch (Exception e) {
                            System.out.println("error while writing file Quick" + e);
                        }
                    }
                    try {
                        fileWriter.flush();
                        fileWriter.close();
                    } catch (Exception e) {
                        System.out.println("error while closing file Quick" + e);
                    }
                }

        );
    }

    public final static TimeLogger[] timeLoggersLinearithmic = {
            new TimeLogger("Raw time per run (mSec): ", (time, n) -> time)
    };

    private static String createCsvString(int n, double time, StatPack statPack, boolean instrumentation) {
        StringBuilder sb = new StringBuilder();
        sb.append(n+",");
        sb.append(time+",");

        if (instrumentation) {
            /*
            sb.append(Utilities.asInt(statPack.getStatistics("hits").mean()) + ",");
            sb.append(Utilities.asInt(statPack.getStatistics("hits").stdDev()) + ",");
            sb.append(Utilities.formatDecimal3Places(statPack.getStatistics("hits").normalizedMean()) + ",");

            sb.append(Utilities.asInt(statPack.getStatistics("swaps").mean()) + ",");
            sb.append(Utilities.asInt(statPack.getStatistics("swaps").stdDev()) + ",");
            sb.append(Utilities.formatDecimal3Places(statPack.getStatistics("swaps").normalizedMean()) + ",");

            sb.append(Utilities.asInt(statPack.getStatistics("compares").mean()) + ",");
            sb.append(Utilities.asInt(statPack.getStatistics("compares").stdDev()) + ",");
            sb.append(Utilities.formatDecimal3Places(statPack.getStatistics("compares").normalizedMean()) + ",");

            sb.append(Utilities.asInt(statPack.getStatistics("fixes").mean()) + ",");
            sb.append(Utilities.asInt(statPack.getStatistics("fixes").stdDev()) + ",");
            sb.append(Utilities.formatDecimal3Places(statPack.getStatistics("fixes").normalizedMean()) + "\n");

             */

            sb.append(statPack.getStatistics("hits").mean() + ",");
            sb.append(statPack.getStatistics("hits").stdDev() + ",");
            sb.append(statPack.getStatistics("hits").normalizedMean() + ",");

            sb.append(statPack.getStatistics("swaps").mean() + ",");
            sb.append(statPack.getStatistics("swaps").stdDev() + ",");
            sb.append(statPack.getStatistics("swaps").normalizedMean() + ",");

            sb.append(statPack.getStatistics("compares").mean() + ",");
            sb.append(statPack.getStatistics("compares").stdDev() + ",");
            sb.append(statPack.getStatistics("compares").normalizedMean() + ",");

            sb.append(statPack.getStatistics("fixes").mean() + ",");
            sb.append(statPack.getStatistics("fixes").stdDev() + ",");
            sb.append(statPack.getStatistics("fixes").normalizedMean() + "\n");
        } else {
            sb.append("\n");
        }
        System.out.println();
        return sb.toString();

    }

    private static String getHeaderString() {
        StringBuilder sb = new StringBuilder();
        sb.append("N,");
        sb.append("Time,");

        sb.append("hits:Mean,");
        sb.append("hits:StdDev,");
        sb.append("hits:NormalizedMean,");

        sb.append("swaps:Mean,");
        sb.append("swaps:StdDev,");
        sb.append("swaps:NormalizedMean,");

        sb.append("compares:Mean,");
        sb.append("compares:StdDev,");
        sb.append("compares:NormalizedMean,");

        sb.append("fixes:Mean,");
        sb.append("fixes:StdDev,");
        sb.append("fixes:NormalizedMean\n");

        return sb.toString();

    }


}
