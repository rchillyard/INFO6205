package edu.neu.coe.info6205.sort;

import edu.neu.coe.info6205.sort.elementary.*;
import edu.neu.coe.info6205.sort.linearithmic.IntroSort;
import edu.neu.coe.info6205.sort.linearithmic.MergeSort;
import edu.neu.coe.info6205.sort.linearithmic.QuickSort_3way;
import edu.neu.coe.info6205.sort.linearithmic.QuickSort_DualPivot;
import edu.neu.coe.info6205.util.*;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.util.function.Supplier;

/**
 * Unit tests which are in fact benchmarks of the various sort methods.
 * Keep in mind that we are sorting objects here (Integers). not primitives.
 */
public class Benchmarks {

    @BeforeClass
    public static void setupClass() {
        try {
            config = Config.load(Benchmarks.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Ignore // Slow
    public void testBubbleSortBenchmark() {
        String description = "BubbleSort";
        Helper<Integer> helper = new BaseHelper<>(description, N, config);
        final GenericSort<Integer> sort = new BubbleSort<>(helper);
        runBenchmark(description, sort, helper);
    }

    @Test
    public void testInsertionSortBenchmark() {
        String description = "Insertion sort";
        Helper<Integer> helper = new BaseHelper<>(description, N, config);
        final GenericSort<Integer> sort = new InsertionSort<>(helper);
        runBenchmark(description, sort, helper);
    }

    @Test
    public void testInsertionSortOptBenchmark() {
        String description = "Optimized Insertion sort";
        Helper<Integer> helper = new BaseHelper<>(description, N, config);
        final GenericSort<Integer> sort = new InsertionSortOpt<>(helper);
        runBenchmark(description, sort, helper);
    }

    @Test
    public void testIntroSortBenchmark() {
        String description = "Intro sort";
        final Helper<Integer> helper = new BaseHelper<>(description, N, config);
        final GenericSort<Integer> sort = new IntroSort<>(helper);
        runBenchmark(description, sort, helper);
    }

    @Test
    public void testMergeSortBenchmark() {
        String description = "Merge sort";
        final Helper<Integer> helper = new BaseHelper<>(description, N, config);
        final GenericSort<Integer> sort = new MergeSort<>(helper);
        runBenchmark(description, sort, helper);
    }

    @Test
    public void testQuickSort3WayBenchmark() {
        String description = "3-way Quick sort";
        final Helper<Integer> helper = new BaseHelper<>(description, N, config);
        final GenericSort<Integer> sort = new QuickSort_3way<>(helper);
        runBenchmark(description, sort, helper);
    }

    @Test
    public void testQuickSortDualPivotSortBenchmark() {
        String description = "Dual-pivot Quick sort";
        final Helper<Integer> helper = new BaseHelper<>(description, N, config);
        final GenericSort<Integer> sort = new QuickSort_DualPivot<>(helper);
        runBenchmark(description, sort, helper);
    }

    @Test
    public void testSelectionSortBenchmark() {
        String description = "Selection sort";
        Helper<Integer> helper = new BaseHelper<>(description, N, config);
        final GenericSort<Integer> sort = new SelectionSort<>(helper);
        runBenchmark(description, sort, helper);
    }

    @Test
    public void testShellSortBenchmark() {
        String description = "3Shell sort";
        final Helper<Integer> helper = new BaseHelper<>(description, N, config);
        final GenericSort<Integer> sort = new ShellSort<>(5, helper);
        runBenchmark(description, sort, helper);
    }

    public void runBenchmark(String description, GenericSort<Integer> sort, Helper<Integer> helper) {
        helper.init(N);
        Supplier<Integer[]> supplier = () -> helper.random(Integer.class, Random::nextInt);
        final Benchmark<Integer[]> benchmark = new Benchmark_Timer<>(
                description + " for " + N + " Integers",
                (xs) -> Arrays.copyOf(xs, xs.length),
                sort::mutatingSort,
                null
        );
        logger.info(Utilities.formatDecimal3Places(benchmark.runFromSupplier(supplier, 100)) + " ms");
    }

    final static LazyLogger logger = new LazyLogger(Benchmarks.class);

    public static final int N = 2000;

    private static Config config;

}
