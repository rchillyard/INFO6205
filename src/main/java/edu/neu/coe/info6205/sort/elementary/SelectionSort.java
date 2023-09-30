/*
  (c) Copyright 2018, 2019 Phasmid Software
 */
package edu.neu.coe.info6205.sort.elementary;

import edu.neu.coe.info6205.sort.BaseHelper;
import edu.neu.coe.info6205.sort.Helper;
import edu.neu.coe.info6205.sort.SortWithHelper;
import edu.neu.coe.info6205.util.Config;

import java.io.IOException;

public class SelectionSort<X extends Comparable<X>> extends SortWithHelper<X> {

    /**
     * Constructor for SelectionSort
     *
     * @param N      the number elements we expect to sort.
     * @param config the configuration.
     */
    public SelectionSort(int N, Config config) {
        super(DESCRIPTION, N, config);
    }

    public SelectionSort(Config config) {
        this(new BaseHelper<>(DESCRIPTION, config));
    }

    /**
     * Constructor for SelectionSort
     *
     * @param helper an explicit instance of Helper to be used.
     */
    public SelectionSort(Helper<X> helper) {
        super(helper);
    }

    public void sort(X[] xs, int from, int to) {
        final Helper<X> helper = getHelper();
        for (int i = from; i < to - 1; i++) {
            int min = i;
            for (int j = i + 1; j < to; j++)
                if (helper.less(xs[j], xs[min]))
                    min = j;
            helper.swap(xs, i, min);
        }
    }

    /**
     * This is used by unit tests.
     *
     * @param ys  the array to be sorted.
     * @param <Y> the underlying element type.
     */
    public static <Y extends Comparable<Y>> void mutatingSelectionSort(Y[] ys) throws IOException {
        new SelectionSort<Y>(Config.load(SelectionSort.class)).mutatingSort(ys);
    }

    public static final String DESCRIPTION = "Selection sort";

}