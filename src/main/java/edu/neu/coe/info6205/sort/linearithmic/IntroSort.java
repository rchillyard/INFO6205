/*
  (c) Copyright 2018, 2019 Phasmid Software
 */
package edu.neu.coe.info6205.sort.linearithmic;

import edu.neu.coe.info6205.sort.BaseHelper;
import edu.neu.coe.info6205.sort.Helper;
import edu.neu.coe.info6205.util.Config;

import java.util.Arrays;

public class IntroSort<X extends Comparable<X>> extends QuickSort_DualPivot<X> {

    /**
     * Constructor for QuickSort_3way
     *
     * @param helper an explicit instance of Helper to be used.
     */
    public IntroSort(Helper<X> helper) {
        super(helper);
    }

    /**
     * Constructor for QuickSort_3way
     *
     * @param N      the number elements we expect to sort.
     * @param config the configuration.
     */
    public IntroSort(int N, Config config) {
        super(DESCRIPTION, N, config);
    }

    /**
     * Constructor for QuickSort_3way which always uses an instrumented helper with a specific seed.
     * <p>
     * NOTE used by unit tests.
     *
     * @param N      the number of elements to be sorted.
     * @param seed   the seed for the random number generator.
     * @param config the configuration for this sorter.
     */
    public IntroSort(int N, long seed, Config config) {
        super(DESCRIPTION, N, config);
    }

    public IntroSort(Config config) {
        this(new BaseHelper<>(DESCRIPTION, config));
    }

    public X[] sort(X[] xs, boolean makeCopy) {
        getHelper().init(xs.length);
        depthThreshold = 2 * floor_lg(xs.length);
        X[] result = makeCopy ? Arrays.copyOf(xs, xs.length) : xs;
        int from = 0, to = result.length;
        sort(result, from, to, 0);
        return result;
    }

    /**
     * @param xs   an array of Xs.
     * @param from the index of the first element to sort.
     * @param to   the index of the first element not to sort.
     */
    public void sort(X[] xs, int from, int to) {
        sort(xs, from, to, 2 * floor_lg(to - from));
    }

    /**
     * Protected method to determine to terminate the recursion of this quick sort.
     * NOTE that in this implementation, the depth is ignored.
     *
     * @param xs    the complete array from which this sub-array derives.
     * @param from  the index of the first element to sort.
     * @param to    the index of the first element not to sort.
     * @param depth the current depth of the recursion.
     * @return true if there is no further work to be done.
     */
    protected boolean terminator(X[] xs, int from, int to, int depth) {
        if (to - from <= sizeThreshold) {
            if (to > from + 1)
                getInsertionSort().sort(xs, from, to);
            return true;
        }

        if (depth >= depthThreshold) {
            heapSort(xs, from, to);
            return true;
        }

        return false;
    }

    public static final String DESCRIPTION = "Intro sort";

    /*
     * Heapsort algorithm
     */
    private void heapSort(X[] a, int from, int to) {
        Helper<X> helper = getHelper();
        int n = to - from;
        for (int i = n / 2; i >= 1; i = i - 1) {
            downHeap(a, i, n, from, helper);
        }
        for (int i = n; i > 1; i = i - 1) {
            helper.swap(a, from, from + i - 1);
            downHeap(a, 1, i - 1, from, helper);
        }
    }

    private void downHeap(X[] a, int i, int n, int lo, Helper<X> helper) {
        X d = a[lo + i - 1];
        int child;
        while (i <= n / 2) {
            child = 2 * i;
            if (helper.instrumented()) {
                if (child < n && helper.compare(a, lo + child - 1, lo + child) < 0) child++;
                if (helper.compare(d, a[lo + child - 1]) >= 0) break;
            } else {
                if (child < n && a[lo + child - 1].compareTo(a[lo + child]) < 0) child++;
                if (d.compareTo(a[lo + child - 1]) >= 0) break;
            }
            helper.incrementFixes(1);
            a[lo + i - 1] = a[lo + child - 1];
            i = child;
        }
        a[lo + i - 1] = d;
    }

    /**
     * exchange a[i] and a[j]
     *
     * @param a the array.
     * @param i one index.
     * @param j other index.
     */
    private static void swap(Object[] a, int i, int j) {
        Object temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    private static int floor_lg(int a) {
        return (int) (Math.floor(Math.log(a) / Math.log(2)));
    }

    private int depthThreshold = Integer.MAX_VALUE;

    private static final int sizeThreshold = 16;
}