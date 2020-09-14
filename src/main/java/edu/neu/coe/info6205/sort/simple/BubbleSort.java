/*
  (c) Copyright 2018, 2019 Phasmid Software
 */
package edu.neu.coe.info6205.sort.simple;

import edu.neu.coe.info6205.sort.BaseHelper;
import edu.neu.coe.info6205.sort.Helper;
import edu.neu.coe.info6205.sort.SortWithHelper;
import edu.neu.coe.info6205.util.Config;

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

    public BubbleSort() {
        this(new BaseHelper<>(DESCRIPTION));
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
     * Sort the sub-array xs:from:to using insertion sort.
     *
     * @param xs   sort the array xs from "from" to "to".
     * @param from the index of the first element to sort
     * @param to   the index of the first element not to sort
     */
    public void sort(X[] xs, int from, int to) {
        final Helper<X> helper = getHelper();
        for (int j = to; j > 0; j--) {
            boolean swapped = false;
            for (int i = 1; i < j; i++) swapped |= helper.swapStableConditional(xs, i);
            if (!swapped) break;
        }
    }

    /**
     * This is used by unit tests.
     *
     * @param ys  the array to be sorted.
     * @param <Y> the underlying element type.
     */
    public static <Y extends Comparable<Y>> void mutatingBubbleSort(Y[] ys) {
        new BubbleSort<Y>().mutatingSort(ys);
    }

    public static final String DESCRIPTION = "Bubble sort";

}
