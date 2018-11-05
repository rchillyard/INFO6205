package edu.neu.coe.info6205.sort.simple;

import java.util.Arrays;

public class QuickSort<X extends Comparable<X>> implements Sort<X> {
    private final Helper<X> helper;

    /**
     * Constructor for InsertionSort
     *
     * @param helper an explicit instance of Helper to be used.
     */
    public QuickSort(Helper<X> helper) {
        this.helper = helper;
    }

    public QuickSort() {
        this(new Helper<>("QuickSort"));
    }

    @Override
    public void sort(X[] xs, int from, int to) {
        Arrays.sort(xs, from, to);
//        for (int i = from; i < to; i++)
//            for (int j = i; j > 0; j--)
//                if (less(xs[j], xs[j - 1]))
//                    swap(xs, j, j - 1);
//                else break;
//
    }

    @Override
    public Helper<X> getHelper() {
        return helper;
    }
}

