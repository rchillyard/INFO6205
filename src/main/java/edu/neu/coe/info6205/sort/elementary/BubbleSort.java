/*
  (c) Copyright 2018, 2019 Phasmid Software
 */
package edu.neu.coe.info6205.sort.elementary;

import edu.neu.coe.info6205.sort.BaseHelper;
import edu.neu.coe.info6205.sort.Helper;
import edu.neu.coe.info6205.sort.HelperFactory;
import edu.neu.coe.info6205.sort.SortWithHelper;
import edu.neu.coe.info6205.util.Config;
import edu.neu.coe.info6205.util.LazyLogger;
import edu.neu.coe.info6205.util.Stopwatch;

import java.io.IOException;
import java.util.Random;

/**
 * Class to sort arrays of (comparable) Xs which extends SortWithHelper and Sort.
 *
 * @param <X> the underlying type of elements to be sorted (must support Comparable).
 */
public class BubbleSort<X extends Comparable<X>> extends SortWithHelper<X> {

    /**
     * Constructor for BubbleSort
     *
     * @param N      the number elements we expect to sort.
     * @param config the configuration.
     */
    public BubbleSort(int N, Config config) {
        super(DESCRIPTION, N, config);
    }

    public BubbleSort(Config config) {
        this(new BaseHelper<>(DESCRIPTION, config));
    }

    /**
     * Constructor for BubbleSort
     *
     * @param helper an explicit instance of Helper to be used.
     */
    public BubbleSort(Helper<X> helper) {
        super(helper);
    }

    /**
     * Sort the sub-array xs:from:to using bubble sort.
     *
     * @param xs   sort the array xs from "from" to "to".
     * @param from the index of the first element to sort.
     * @param to   the index of the first element not to sort.
     */
    public void sort(X[] xs, int from, int to) {
        final Helper<X> helper = getHelper();
        for (int j = to; j > from; j--)
            if (optimizedInnerLoopSuccess(xs, helper, from, j))
                break;
    }

    /**
     * "Optimized" inner loop of bubble sort (see Wikipedia: <a href="https://en.wikipedia.org/wiki/Bubble_sort#Implementation">Bubble sort implementation</a>)
     * The optimization is that we only loop until we reach the (j-1)th element because the jth element and beyond
     * cannot possibly be out of order.
     *
     * @param xs     the complete array to be sorted.
     * @param helper the helper.
     * @param from   the index of the first element to sort.
     * @param j      the index of the first element not to sort.
     * @return true if we passed through the elements without swapping any.
     */
    private boolean optimizedInnerLoopSuccess(X[] xs, Helper<X> helper, int from, int j) {
        boolean swapped = false;
        for (int i = from + 1; i < j; i++) swapped |= helper.swapStableConditional(xs, i);
        return !swapped;
    }

    /**
     * This is used only by unit tests.
     *
     * @param ys  the array to be sorted.
     * @param <Y> the underlying element type.
     */
    public static <Y extends Comparable<Y>> void mutatingBubbleSort(Y[] ys) throws IOException {
        new BubbleSort<Y>(Config.load(BubbleSort.class)).mutatingSort(ys);
    }

    public static final String DESCRIPTION = "Bubble sort";

    public static void main(String[] args) throws IOException {
        bubbleSortMain(10000);
        insertionSortMain(10000);
    }

    private static void bubbleSortMain(int n) throws IOException {
        BubbleSort<Integer> bubbleSort = new BubbleSort<Integer>(HelperFactory.create("Bubble sort", n, Config.load(BubbleSort.class)));
        Helper<Integer> helper = bubbleSort.getHelper();
        logger.info("Begin BubbleSort");
        try (Stopwatch stopwatch = new Stopwatch()) {
            for (int i = 0; i < 10; i++)
                doSort(bubbleSort, helper, stopwatch);
        }
        logger.info("End BubbleSort");
    }

    private static void insertionSortMain(int n) throws IOException {
        SortWithHelper<Integer> sorter = new InsertionSort<Integer>(HelperFactory.create("Insertion sort", n, Config.load(BubbleSort.class)));
        Helper<Integer> helper = sorter.getHelper();
        logger.info("Begin InsertionSort");
        try (Stopwatch stopwatch = new Stopwatch()) {
            for (int i = 0; i < 10; i++)
                doSort(sorter, helper, stopwatch);
        }
        logger.info("End InsertionSort");
    }

    private static void doSort(SortWithHelper<Integer> sorter, Helper<Integer> helper, Stopwatch stopwatch) {
        Integer[] integers = helper.random(Integer.class, Random::nextInt);
        Integer[] sorted = sorter.sort(integers);
        if (!helper.sorted(sorted)) System.err.println("Not sorted");
        System.out.println(helper.getDescription() + " " + integers.length + " integers: " + stopwatch.lap());
    }

    final static LazyLogger logger = new LazyLogger(BubbleSort.class);

}