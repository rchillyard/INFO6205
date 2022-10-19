package edu.neu.coe.info6205.sort.elementary;

import edu.neu.coe.info6205.util.Benchmark_Timer;
import edu.neu.coe.info6205.util.TimeLogger;

import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class InsertionSortBenchmark {
    public static void main(String[] args) {
        for (int n = 1000;n <= 16000; n *= 2) {
            InsertionSortBenchmark benchmark = new InsertionSortBenchmark(100, n);
            benchmark.runBenchmarks();
        }
    }

    public InsertionSortBenchmark(int runs, int n) {
        this.runs = runs;
        this.n = n;
    }

    private void runBenchmarks() {
        InsertionSort<Integer> insertionSort = new InsertionSort<>();

        System.out.println("InsertionSort Benchmarks: N = " + n);
        Integer[] arrayRandom = generateRandomArray(n, "random");
        // Cloning the array or the array passed to run() will always be ordered.
        double timeRandom = new Benchmark_Timer<>("random array",
                (xs) -> insertionSort.sort(arrayRandom.clone(), 0, arrayRandom.length)).run(arrayRandom, runs);
        for (TimeLogger tl : timeLoggers) tl.log(timeRandom, n);

        Integer[] arrayOrdered = generateRandomArray(n, "ordered");
        double timeOrdered = new Benchmark_Timer<>("Ordered array",
                (xs) -> insertionSort.sort(arrayOrdered.clone(), 0, arrayOrdered.length)).run(arrayOrdered, runs);
        for (TimeLogger tl : timeLoggers) tl.log(timeOrdered, n);

        Integer[] arrayPartial = generateRandomArray(n, "partial");
        double timePartial = new Benchmark_Timer<>("Partially-ordered array",
                (xs) -> insertionSort.sort(arrayPartial.clone(), 0, arrayPartial.length)).run(arrayPartial, runs);
        for (TimeLogger tl : timeLoggers) tl.log(timePartial, n);

        Integer[] arrayReverse = generateRandomArray(n, "reverse");
        double timeReverse = new Benchmark_Timer<>("Reverse-ordered array",
                (xs) -> insertionSort.sort(arrayReverse.clone(), 0, arrayReverse.length)).run(arrayReverse, runs);
        for (TimeLogger tl : timeLoggers) tl.log(timeReverse, n);
    }

    private static Integer[] generateRandomArray(int n, String type) {
        Integer[] result = new Integer[n];
        Random rand = new Random();
        for (int i = 0; i < n; i++) {
            result[i] = rand.nextInt();
        }
        if (type.equals("random")) return result;
        if (type.equals("ordered")) Arrays.sort(result);;
        if (type.equals("reverse")) Arrays.sort(result, Collections.reverseOrder());
        if (type.equals("partial")) {
            int pivot = rand.nextInt(n - 1);
            Integer[] partial = new Integer[pivot];
            for (int i = 0; i < partial.length; i++) partial[i] = rand.nextInt();
            Arrays.sort(partial);
            for (int j = 0; j < partial.length; j++) result[j] = partial[j];
        }
        return result;
    }

    private final static TimeLogger[] timeLoggers = {
            new TimeLogger("Raw time per run (mSec): ", (time, n) -> time),
    };

    private final int runs;
    private final int n;
}
