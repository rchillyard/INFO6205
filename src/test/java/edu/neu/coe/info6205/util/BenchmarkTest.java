/*
 * Copyright (c) 2017. Phasmid Software
 */

package edu.neu.coe.info6205.util;

import edu.neu.coe.info6205.sort.simple.*;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Function;

import static org.junit.Assert.assertEquals;

@SuppressWarnings("ALL")
public class BenchmarkTest {

    int pre = 0;
    int run = 0;
    int post = 0;

    @Test
    public void testWaitPeriods() throws Exception {
        int nRuns = 2;
        int warmups = 2;
        Benchmark<Boolean> bm = new Benchmark<>(
                b -> {
                    GoToSleep(100L, -1);
                    return null;
                },
                b -> {
                    GoToSleep(200L, 0);
                },
                b -> {
                    GoToSleep(50L, 1);
                }
        );
        double x = bm.run(true, nRuns);
        assertEquals(nRuns, post);
        assertEquals(nRuns + warmups, run);
        assertEquals(nRuns + warmups, pre);
        assertEquals(200, x, 10);
    }

    private void GoToSleep(long mSecs, int which) {
        try {
            Thread.sleep(mSecs);
            if (which == 0) run++;
            else if (which > 0) post++;
            else pre++;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testBenchmarkWithQuickSort() throws Exception {
        Random random = new Random();
        int m = 100; // This is the number of repetitions: sufficient to give a good mean value of timing
        int n = 1000; // This is the size of the array to be sorted.
        Integer[] array = new Integer[n];
        for (int i = 0; i < n; i++) array[i] = random.nextInt();
        double tqs3 = benchmarkSort(array, "QuickSort3way", new QuickSort_3way<>(), m);
        double tqs = benchmarkSort(array, "QuickSort", new QuickSort<>(), m);
        System.out.println(tqs);
        System.out.println(tqs3);
        assertEquals(1, tqs/tqs3, 0.4);
    }

    @Ignore
    public void testSimpleSorts() throws Exception {
        Random random = new Random();
        int m = 100; // This is the number of repetitions: sufficient to give a good mean value of timing
        int n = 1000; // This is the size of the array to be sorted.
        Integer[] array = new Integer[n];
        for (int i = 0; i < n; i++) array[i] = random.nextInt();
        double ts = benchmarkSort(array, "SelectionSort", new SelectionSort<>(), m);
        double ti = benchmarkSort(array, "InsertionSort", new InsertionSort<>(), m);
        // The timing for selection sort and insertion sort should be about the same for random input.
        assertEquals(1, ts / ti, 0.4);
    }

    private static double benchmarkSort(Integer[] array, String name, Sort<Integer> sorter, int m) {
        Consumer<Integer[]> sortFunction = (xs) -> {
            sorter.sort(xs);
        };
        Benchmark<Integer[]> bm = new Benchmark<>(sortFunction);
        return bm.run(array, m);
    }

}