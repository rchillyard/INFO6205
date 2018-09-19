/*
 * Copyright (c) 2018. Phasmid Software
 */

package edu.neu.coe.info6205.util;

import edu.neu.coe.info6205.sort.simple.InsertionSort;
import edu.neu.coe.info6205.sort.simple.SelectionSort;
import edu.neu.coe.info6205.sort.simple.Sort;

import java.util.Random;
import java.util.function.Function;

public class Benchmark<T> {

    public Benchmark(Function<T, Void> f) {
        this.f = f;
    }

    public double run(T t, int m) {
        doRun(t, 10);
        final long start = System.nanoTime();
        doRun(t, m);
        final long end = System.nanoTime();
        return ((double) end - start) / m / 1000000;
    }

    private void doRun(T t, int n) {
        for (int i = 0; i < n; i++) f.apply(t);
    }

    private final Function<T, Void> f;

    public static void main(String[] args) {
        Random random = new Random();
        int m = 100; // This is the number of repetitions: sufficient to give a good mean value of timing
        Integer[] array = new Integer[1000];
        for (int i = 0; i < 1000; i++) array[i] = random.nextInt();
        int n = 200;
        // TODO You need to apply doubling to n
        // FIXME Need to refresh before each run
        benchmarkSort(array, n, "InsertionSort", new InsertionSort<>(), m);
        benchmarkSort(array, n, "SelectionSort", new SelectionSort<>(), m);
    }

    private static void benchmarkSort(Integer[] xs, Integer n, String name, Sort<Integer> sorter, int m) {
        Function<Integer, Void> sortFunction = (x) -> {
            sorter.sort(xs, 0, x);
            return null;
        };
        Benchmark<Integer> bm = new Benchmark<>(sortFunction);
        double x = bm.run(n, m);
        System.out.println(name + ": " + x + " millisecs for n=" + n);
    }
}
