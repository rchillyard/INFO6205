package edu.neu.coe.info6205.sort.simple;

import java.util.Arrays;

public class MergeSortBasic<X extends Comparable<X>> implements Sort<X> {

    /**
     * Constructor for InsertionSort
     *
     * @param helper an explicit instance of Helper to be used.
     */
    public MergeSortBasic(Helper<X> helper) {
        this.helper = helper;
    }

    public MergeSortBasic() {
        this(new Helper<>("MergeSort"));
    }

    @Override
    public X[] sort(X[] xs, boolean makeCopy) {
        getHelper().setN(xs.length);
        X[] result = makeCopy ? Arrays.copyOf(xs, xs.length) : xs;
        aux = Arrays.copyOf(xs, xs.length); // TODO don't copy but just allocate
        // TODO make this consistent with other uses of sort where the upper limit of the range is result.length
        sort(result, 0, result.length);
        return result;
    }

    @Override
    public void sort(X[] a, int from, int to) {
        @SuppressWarnings("UnnecessaryLocalVariable") int lo = from;
        int hi = to;
        if (hi <= lo + 1) return;
        int mid = from + (to - from)/2;
        sort(a, lo, mid);
        sort(a, mid, hi);
        System.arraycopy(a, from, aux, from, to - from);
        merge(aux, a, lo, mid, hi);
    }

    private void merge(X[] aux, X[] a, int lo, int mid, int hi) {
        int i = lo;
        int j = mid;
        for (int k = lo; k < hi; k++)
            if (i >= mid) a[k] = aux[j++];
        else if (j >= hi) a[k] = aux[i++];
        else if (helper.less(aux[j], aux[i])) a[k] = aux[j++];
        else a[k] = aux[i++];
    }

    @Override
    public Helper<X> getHelper() {
        return helper;
    }

    private X[] aux = null;
    private final Helper<X> helper;
}

