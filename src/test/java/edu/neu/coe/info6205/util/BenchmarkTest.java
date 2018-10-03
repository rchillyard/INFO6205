/*
 * Copyright (c) 2017. Phasmid Software
 */

package edu.neu.coe.info6205.util;

import edu.neu.coe.info6205.sort.simple.InsertionSort;
import edu.neu.coe.info6205.sort.simple.SelectionSort;
import edu.neu.coe.info6205.sort.simple.ShellSort;
import edu.neu.coe.info6205.sort.simple.Sort;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Function;

import static org.junit.Assert.assertEquals;

@SuppressWarnings("ALL")
public class BenchmarkTest {

    @Test
    public void sort() throws Exception {
        Random random = new Random();
        int m = 100; // This is the number of repetitions: sufficient to give a good mean value of timing
        Integer[] array = new Integer[1000];
        for (int i = 0; i < 1000; i++) array[i] = random.nextInt();
        int n = 200;
        benchmarkSort(array, n, "InsertionSort", new InsertionSort<>(), m, 0.006);
    }

    private static void benchmarkSort(Integer[] xs, Integer n, String name, Sort<Integer> sorter, int m, double expected) {
        Function<Integer, Void> sortFunction = (x) -> {
            sorter.sort(xs, 0, x);
            return null;
        };
        Benchmark<Integer> bm = new Benchmark<>(sortFunction);
        double x = bm.run(n, m);
        System.out.println(name + ": " + x + " millisecs for n=" + n);
        assertEquals(expected, x, 0.01);
    }

}