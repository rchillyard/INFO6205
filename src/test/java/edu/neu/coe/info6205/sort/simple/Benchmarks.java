package edu.neu.coe.info6205.sort.simple;

import edu.neu.coe.info6205.sort.BaseHelper;
import edu.neu.coe.info6205.sort.GenericSort;
import edu.neu.coe.info6205.sort.Helper;
import edu.neu.coe.info6205.util.Benchmark;
import edu.neu.coe.info6205.util.Benchmark_Timer;
import edu.neu.coe.info6205.util.LazyLogger;
import edu.neu.coe.info6205.util.Utilities;
import org.junit.Test;

import java.util.Arrays;
import java.util.function.Supplier;

/**
 * Unit tests which are in fact benchmarks of the various sort methods.
 * Keep in mind that we are sorting objects here (Integers). not primitives.
 */
public class Benchmarks {

    @Test
    public void testBubbleSortBenchmark() {
        String description = "BubbleSort";
        Helper<Integer> helper = new BaseHelper<>(description, N);
        final GenericSort<Integer> sort = new BubbleSort<>(helper);
        runBenchmark(description, sort, helper);
    }

    @Test
    public void testInsertionSortBenchmark() {
        String description = "Insertion sort";
        Helper<Integer> helper = new BaseHelper<>(description, N);
        final GenericSort<Integer> sort = new InsertionSort<>(helper);
        runBenchmark(description, sort, helper);
    }

    @Test
    public void testInsertionSortOptBenchmark() {
        String description = "Optimized Insertion sort";
        Helper<Integer> helper = new BaseHelper<>(description, N);
        final GenericSort<Integer> sort = new InsertionSortOpt<>(helper);
        runBenchmark(description, sort, helper);
    }

    @Test
    public void testIntroSortBenchmark() {
        String description = "Intro sort";
        final Helper<Integer> helper = new BaseHelper<>(description, N);
        final GenericSort<Integer> sort = new IntroSort<>(helper);
        runBenchmark(description, sort, helper);
    }

    @Test
    public void testMergeSortBenchmark() {
        String description = "Merge sort";
        final Helper<Integer> helper = new BaseHelper<>(description, N);
        final GenericSort<Integer> sort = new MergeSortBasic<>(helper);
        runBenchmark(description, sort, helper);
    }

    @Test
    public void testQuickSort3WayBenchmark() {
        String description = "3-way Quick sort";
        final Helper<Integer> helper = new BaseHelper<>(description, N);
        final GenericSort<Integer> sort = new QuickSort_3way<>(helper);
        runBenchmark(description, sort, helper);
    }

    @Test
    public void testQuickSortDualPivotSortBenchmark() {
        String description = "Dual-pivot Quick sort";
        final Helper<Integer> helper = new BaseHelper<>(description, N);
        final GenericSort<Integer> sort = new QuickSort_DualPivot<>(helper);
        runBenchmark(description, sort, helper);
    }

    @Test
    public void testSelectionSortBenchmark() {
        String description = "Selection sort";
        Helper<Integer> helper = new BaseHelper<>(description, N);
        final GenericSort<Integer> sort = new SelectionSort<>(helper);
        runBenchmark(description, sort, helper);
    }

    @Test
    public void testShellSortBenchmark() {
        String description = "3Shell sort";
        final Helper<Integer> helper = new BaseHelper<>(description, N);
        final GenericSort<Integer> sort = new ShellSort<>(3, helper);
        runBenchmark(description, sort, helper);
    }

    public void runBenchmark(String description, GenericSort<Integer> sort, Helper<Integer> helper) {
        helper.init(N);
        Supplier<Integer[]> supplier = () -> helper.random(Integer.class, r -> r.nextInt());
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
}
