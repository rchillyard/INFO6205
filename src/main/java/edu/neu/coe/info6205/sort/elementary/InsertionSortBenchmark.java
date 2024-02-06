package edu.neu.coe.info6205.sort.elementary;

import edu.neu.coe.info6205.util.Benchmark_Timer;

import java.io.File;
import java.io.FileWriter;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class InsertionSortBenchmark {

    public static void main(String[] args) {
        try {


            int runs = 100;
            File file = new File("Assignment_3_BenchMark_Insertion_Sort.csv");
            if (!file.exists()) file.createNewFile();
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write("Number of Reps,N,Random,Ordered,Reverse Ordered,Partially Ordered\n");

            Consumer<Integer[]> insertionSort = (Integer[] arry) -> {
                new InsertionSort<Integer>().sort(arry);
            };


            for (int n = 500; n <= 200000; n *= 2) {
                fileWriter.write(runs+","+n+",");
                for (String description : supplierMap.keySet()) {

                    Supplier<Integer[]> supplier = supplierMap.get(description).apply(n);
                    Benchmark_Timer<Integer[]> benchmark_timer = new Benchmark_Timer<>(description, (Integer[] arr) -> {
                        int m = 0;
                        for (int i = 0; i < arr.length; i++) {
                            m = arr[i];
                        }
                        return arr;
                    }, insertionSort);
                    double averageTime = benchmark_timer.runFromSupplier(supplier, runs);
                    System.out.println("For N: " + n + ", " + description + ": average time: " + averageTime);
                    fileWriter.write(averageTime + ",");
                }
                fileWriter.write("\n");

            }
            fileWriter.flush();
            fileWriter.close();
        } catch (Exception e) {
            System.out.println("exception while benchMarking: ");
        }
    }


    private static Function<Integer,Supplier<Integer[]>> supplierRandom = (Integer n) -> {
        return () -> {
            Integer[] arry = new Integer[n];
            for (int i = 0; i < n; i++) {
                arry[i] = i;
            }
            Random random = new Random(0);
            for (int i = 0; i < n; i++) {
                int swap = random.nextInt(n);
                int temp = arry[i];
                arry[i] = arry[swap];
                arry[swap] = temp;
            }
            return arry;
        };
    };

    private static Function<Integer,Supplier<Integer[]>> supplierOrdered = (Integer n) -> {
        return () -> {
            Integer[] arry = new Integer[n];
            for (int i = 0; i < n; i++) {
                arry[i] = i;
            }
            return arry;
        };
    };

    private static Function<Integer,Supplier<Integer[]>> supplierPartiallyOrdered = (Integer n) -> {
        return () -> {
            Integer[] arry = new Integer[n];
            for (int i = 0; i < n; i++) {
                arry[i] = i;
            }
            Random random = new Random();
            for (int i = n/2; i < n; i++) {
                int swap = random.nextInt(n - n/2) + n/2;
                int temp = arry[i];
                arry[i] = arry[swap];
                arry[swap] = temp;
            }
            return arry;
        };
    };

    private static Function<Integer,Supplier<Integer[]>> supplierReverseOrdered = (Integer n) -> {
        return () -> {
            Integer[] arry = new Integer[n];
            for (int i = 0; i < n; i++) {
                arry[i] = i;
            }
            Arrays.sort(arry, Comparator.reverseOrder());
            return arry;
        };
    };


    private static Map<String, Function<Integer, Supplier<Integer[]>>> supplierMap = new HashMap<>() {
        {
         put("Random", supplierRandom);
         put("Ordered", supplierOrdered);
         put("Partially Ordered", supplierPartiallyOrdered);
         put("Reverse Ordered", supplierReverseOrdered);
        }
    };


/*
    Function<Pair,Supplier<Integer[]>> supplierRandom = (Pair nAndM) -> {
        return () -> Arrays.stream(new Source(nAndM.getX(), nAndM.getY()).intsSupplier(10)
                .get()).boxed().toArray(Integer[]::new);
    };

    Function<Pair,Supplier<Integer[]>> supplierOrdered = (Pair nAndM) -> {
        return () -> Arrays.stream(new Source(nAndM.getX(), nAndM.getY()).intsSupplier(10)
                .get()).boxed().toArray(Integer[]::new);
    };

    Function<Pair,Supplier<Integer[]>> supplierPartiallyOrdered = (Pair nAndM) -> {
        return () -> Arrays.stream(new Source(nAndM.getX(), nAndM.getY()).intsSupplier(10)
                .get()).boxed().toArray(Integer[]::new);
    };

    Function<Pair,Supplier<Integer[]>> supplierReverseOrdered = (Pair nAndM) -> {
        return () -> Arrays.stream(new Source(nAndM.getX(), nAndM.getY()).intsSupplier(10)
                .get()).boxed().toArray(Integer[]::new);
    };


 */

}
