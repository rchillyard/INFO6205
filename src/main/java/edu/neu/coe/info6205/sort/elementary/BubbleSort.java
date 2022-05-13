/*
  (c) Copyright 2018, 2019 Phasmid Software
 */
package edu.neu.coe.info6205.sort.elementary;

import edu.neu.coe.info6205.sort.BaseHelper;
import edu.neu.coe.info6205.sort.Helper;
import edu.neu.coe.info6205.sort.SortWithHelper;
import edu.neu.coe.info6205.util.Config;

import java.io.IOException;

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

}
