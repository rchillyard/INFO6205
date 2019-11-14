package edu.neu.coe.info6205.sort.simple;

import java.util.Arrays;

public class QuickSort_3way<X extends Comparable<X>> implements Sort<X> {
    /**
     * Constructor for InsertionSort
     *
     * @param helper an explicit instance of Helper to be used.
     */
    public QuickSort_3way(Helper<X> helper) {
        this.helper = helper;
    }

    public QuickSort_3way() {
        this(new Helper<>("3-way QuickSort"));
    }

    static class Partition {
        final int lt;
        final int gt;

        public Partition(int lt, int gt) {
            this.lt = lt;
            this.gt = gt;
        }
    }

    @Override
    public X[] sort(X[] xs, boolean makeCopy) {
        getHelper().setN(xs.length);
        X[] result = makeCopy ? Arrays.copyOf(xs, xs.length) : xs;
        // TODO make this consistent with other uses of sort where the upper limit of the range is result.length
        sort(result, 0, result.length - 1);
        return result;
    }

    @Override
    public void sort(X[] a, int from, int to) {
        @SuppressWarnings("UnnecessaryLocalVariable") int lo = from;
        int hi = to;
        if (hi <= lo) return;
        Partition partition = partition(a, lo, hi);
        sort(a, lo, partition.lt - 1);
        sort(a, partition.gt + 1, hi);
    }

    public Partition partition(X[] a, int lo, int hi) {
        int lt = lo, gt = hi;
        if (helper.less(a[hi], a[lo])) swap(a, lo,hi);
        X v = a[lo];
        int i = lo + 1;
        while (i <= gt) {
            int cmp = helper.compare(a[i],v);
            if (cmp < 0) helper.swap(a, lo, hi, lt++, i++);
            else if (cmp > 0) helper.swap(a, lo, hi, i, gt--);
            else i++;
        }
        return new Partition(lt, gt);
    }

    // Used when we don't want to check or count swaps
    public static void swap(Object[] a, int i, int j) {
        Object temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    @Override
    public Helper<X> getHelper() {
        return helper;
    }

    private final Helper<X> helper;
}

