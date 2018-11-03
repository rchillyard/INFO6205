/*
 * Copyright (c) 2017. Phasmid Software
 */

package edu.neu.coe.info6205.util;

import edu.neu.coe.info6205.sort.simple.InsertionSort;
import edu.neu.coe.info6205.sort.simple.SelectionSort;
import edu.neu.coe.info6205.sort.simple.Sort;
import org.junit.Test;

import java.util.Random;
import java.util.function.Function;

import static org.junit.Assert.assertEquals;

@SuppressWarnings("ALL")
public class BenchmarkTest {

    @Test
    public void sort() throws Exception {
        Random random = new Random();
        int m = 100; // This is the number of repetitions: sufficient to give a good mean value of timing
        int n = 1000; // This is the size of the array to be sorted.
        Integer[] array = new Integer[n];
        for (int i = 0; i < n; i++) array[i] = random.nextInt();
        double ts = benchmarkSort(array, "SelectionSort", new SelectionSort<>(), m);
        double ti = benchmarkSort(array, "InsertionSort", new InsertionSort<>(), m);
        // The timing for selection sort and insertion sort should be about the same for random input.
        assertEquals(1, ts / ti, 0.2);
    }

    private static double benchmarkSort(Integer[] array, String name, Sort<Integer> sorter, int m) {
        Function<Integer[], Void> sortFunction = (xs) -> {
            sorter.sort(xs);
            return null;
        };
        Benchmark<Integer[]> bm = new Benchmark<>(sortFunction);
        return bm.run(array, m);
    }

}