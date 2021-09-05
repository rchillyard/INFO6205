package edu.neu.coe.info6205.sort.linearithmic;

import edu.neu.coe.info6205.sort.Helper;
import edu.neu.coe.info6205.sort.SortWithHelper;
import edu.neu.coe.info6205.sort.elementary.InsertionSort;
import edu.neu.coe.info6205.util.Config;

import java.util.Arrays;

public class MergeSortBasic<X extends Comparable<X>> extends SortWithHelper<X> {

    public static final String DESCRIPTION = "MergeSort";

    /**
     * Constructor for MergeSort
     * <p>
     * NOTE this is used only by unit tests, using its own instrumented helper.
     *
     * @param helper an explicit instance of Helper to be used.
     */
    public MergeSortBasic(Helper<X> helper) {
        super(helper);
        insertionSort = new InsertionSort<>(helper);
    }

    /**
     * Constructor for MergeSort
     *
     * @param N      the number elements we expect to sort.
     * @param config the configuration.
     */
    public MergeSortBasic(int N, Config config) {
        super(DESCRIPTION + ":" + getConfigString(config), N, config);
        insertionSort = new InsertionSort<>(getHelper());
    }

    private static String getConfigString(Config config) {
        StringBuilder stringBuilder = new StringBuilder();
        if (config.getBoolean("mergesort", "insurance")) stringBuilder.append(" with insurance comparison");
        return stringBuilder.toString();
    }

    @Override
    public X[] sort(X[] xs, boolean makeCopy) {
        getHelper().init(xs.length);
        X[] result = makeCopy ? Arrays.copyOf(xs, xs.length) : xs;
        // TODO don't copy but just allocate according to the xs/aux interchange optimization
        aux = Arrays.copyOf(xs, xs.length);
        sort(result, 0, result.length);
        return result;
    }

    @Override
    public void sort(X[] a, int from, int to) {
        @SuppressWarnings("UnnecessaryLocalVariable") int lo = from;
        if (to <= lo + getHelper().cutoff()) {
            insertionSort.sort(a, from, to);
            return;
        }
        int mid = from + (to - from) / 2;
        sort(a, lo, mid);
        sort(a, mid, to);
        System.arraycopy(a, from, aux, from, to - from);
        getHelper().incrementCopies(to - from);
        merge(aux, a, lo, mid, to);
    }

    private void merge(X[] aux, X[] a, int lo, int mid, int hi) {
        final Helper<X> helper = getHelper();
        int i = lo;
        int j = mid;
        int k = lo;
        for (; k < hi; k++)
            if (i >= mid) helper.copy(aux, j++, a, k);
            else if (j >= hi) helper.copy(aux, i++, a, k);
            else if (helper.less(aux[j], aux[i])) {
                helper.incrementFixes(mid - i);
                helper.copy(aux, j++, a, k);
            } else helper.copy(aux, i++, a, k);
    }


    private X[] aux = null;
    private final InsertionSort<X> insertionSort;
}

