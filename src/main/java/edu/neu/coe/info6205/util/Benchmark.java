/*
 * Copyright (c) 2018. Phasmid Software
 */

package edu.neu.coe.info6205.util;

import edu.neu.coe.info6205.sort.simple.Helper;
import edu.neu.coe.info6205.sort.simple.InsertionSort;
import edu.neu.coe.info6205.sort.simple.SelectionSort;
import edu.neu.coe.info6205.sort.simple.Sort;

import java.util.Arrays;
import java.util.Random;
import java.util.function.Function;

/**
 * @param <T> The generic type T is that of the input to the function f which you will pass in to the constructor.
 */
public class Benchmark<T> {

    /**
     * Constructor for a Benchmark with option of specifying all three functions.
     *
     * @param fPre a function of T => T.
     *          Function fPre is run before each invocation of fRun (but with the clock stopped).
     *             The result of fPre (if any) is passed to fRun.
     * @param fRun a function of T => Void.
     *          Function fRun is the function whose timing you want to measure. For example, you might create a function which sorts an array.
     *          When you create a lambda defining fRun, you must return "null."
     * @param fPost a function of T => Void.
     *          Function fPost is run after each invocation of fRun (but with the clock stopped).
     */
    public Benchmark(Function<T, T> fPre, Function<T, Void> fRun, Function<T, Void> fPost) {
        this.fPre = fPre;
        this.fRun = fRun;
        this.fPost = fPost;
    }

    /**
     * Constructor for a Benchmark with option of specifying all three functions.
     *
     * @param fPre a function of T => T.
     *             Function fPre is run before each invocation of fRun (but with the clock stopped).
     *             The result of fPre (if any) is passed to fRun.
     * @param fRun a function of T => Void.
     *             Function fRun is the function whose timing you want to measure. For example, you might create a function which sorts an array.
     *             When you create a lambda defining fRun, you must return "null."
     */
    public Benchmark(Function<T, T> fPre, Function<T, Void> fRun) {
        this(fPre, fRun, null);
    }

    /**
     * Constructor for a Benchmark where only the (timed) run function is specified.
     *
     * @param f a function of T => Void.
     *          Function f is the function whose timing you want to measure. For example, you might create a function which sorts an array.
     *          When you create a lambda defining f, you must return "null."
     */
    public Benchmark(Function<T, Void> f) {
        this(null, f);
    }

    /**
     * Run function f m times and return the average time in milliseconds.
     *
     * @param t the value that will in turn be passed to function f.
     * @param m the number of times the function f will be called.
     * @return the average number of milliseconds taken for each run of function f.
     */
    public double run(T t, int m) {
        // Warmup phase
        int warmupRuns = Integer.min(2, Integer.max(10, m / 10));
        for (int i = 0; i < warmupRuns; i++) doRun(t, true);
        // Timed phase
        long totalTime = 0;
        for (int i = 0; i < m; i++) totalTime += doRun(t, false);
        return (double) totalTime / m / 1000000;
    }

    private long doRun(T t, boolean warmup) {
        T t1 = fPre != null ? fPre.apply(t) : t;
        if (warmup) {
            fRun.apply(t1);
            return 0;
        }
        long start = System.nanoTime();
        fRun.apply(t1);
        long nanos = System.nanoTime() - start;
        if (fPost != null) fPost.apply(t1);
        return nanos;
    }

    private final Function<T, T> fPre;
    private final Function<T, Void> fRun;
    private final Function<T, Void> fPost;

    /**
     * Everything below this point has to do with a particular example of running a Benchmark.
     * In this case, we time three types of simple sort on a randome integer array of length 1000.
     * Each test is run 200 times.
     *
     * @param args the command-line arguments, of which none are significant.
     */
    public static void main(String[] args) {
        Random random = new Random();
        int m = 100; // This is the number of repetitions: sufficient to give a good mean value of timing
        int n = 100000; // This is the size of the array
        for (int k = 0; k < 5; k++) {
            Integer[] array = new Integer[n];
            for (int i = 0; i < n; i++) array[i] = random.nextInt();
            benchmarkSort(array, "InsertionSort: " + n, new InsertionSort<>(), m);
            benchmarkSort(array, "SelectionSort: " + n, new SelectionSort<>(), m);
//        benchmarkSort(array, "ShellSort    ", new ShellSort<>(3), m);
            n = n * 2;
        }
    }

    private static void benchmarkSort(Integer[] array, String name, Sort<Integer> sorter, int m) {
        Function<Integer[], Integer[]> preFunction = (xs) -> {
            return Arrays.copyOf(array, array.length);
        };
        Function<Integer[], Void> sortFunction = (xs) -> {
            sorter.sort(xs, false);
            return null;
        };
        final Helper<Integer> helper = sorter.getHelper();
        Function<Integer[], Void> cleanupFunction = (xs) -> {
            if (!helper.sorted(xs)) throw new RuntimeException("not sorted"); return null;
        };
        Benchmark<Integer[]> bm = new Benchmark<>(preFunction, sortFunction, cleanupFunction);
        double x = bm.run(array, m);
        System.out.println(name + ": " + x + " millisecs");
    }
}
