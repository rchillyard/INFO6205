package edu.neu.coe.info6205.sort.linearithmic;

import edu.neu.coe.info6205.sort.Helper;
import edu.neu.coe.info6205.sort.SortWithHelper;
import edu.neu.coe.info6205.sort.elementary.InsertionSort;
import edu.neu.coe.info6205.util.Config;

import java.util.Arrays;

public class MergeSort<X extends Comparable<X>> extends SortWithHelper<X> {

    public static final String DESCRIPTION = "MergeSort";

    /**
     * Constructor for MergeSort
     * <p>
     * NOTE this is used only by unit tests, using its own instrumented helper.
     *
     * @param helper an explicit instance of Helper to be used.
     */
    public MergeSort(Helper<X> helper) {
        super(helper);
        insertionSort = new InsertionSort<>(helper);
    }

    /**
     * Constructor for MergeSort
     *
     * @param N      the number elements we expect to sort.
     * @param config the configuration.
     */
    public MergeSort(int N, Config config) {
        super(DESCRIPTION + ":" + getConfigString(config), N, config);
        insertionSort = new InsertionSort<>(getHelper());
    }

    @Override
    public X[] sort(X[] xs, boolean makeCopy) {
        getHelper().init(xs.length);
        X[] result = makeCopy ? Arrays.copyOf(xs, xs.length) : xs;
        sort(result, 0, result.length);
        return result;
    }

    @Override
    public void sort(X[] a, int from, int to) {
        // TODO don't copy but just allocate according to the xs/aux interchange optimization
        X[] aux = Arrays.copyOf(a, a.length);
        sort(a, aux, from, to);
    }

    private void sort(X[] a, X[] aux, int lo, int hi) {
        final Helper<X> helper = getHelper();
        Config config = helper.getConfig();
        boolean insurance = config.getBoolean(MERGESORT, INSURANCE);
        boolean noCopy = config.getBoolean(MERGESORT, NOCOPY);
        if (hi <= lo + helper.cutoff()) {
            insertionSort.sort(a, lo, hi);
            return;
        }

        // FIXME : implement merge sort with insurance and no-copy optimizations
        // END 
    }

    // TODO combine with MergeSortBasic perhaps.
    private void merge(X[] from, X[] to, int lo, int mid, int hi) {
        final Helper<X> helper = getHelper();
        int i = lo;
        int j = mid;
        for (int k = lo; k < hi; k++)
            if (i >= mid) helper.copy(from, j++, to, k);
            else if (j >= hi) helper.copy(from, i++, to, k);
            else if (helper.less(from[j], from[i])) {
                helper.incrementFixes(mid - i);
                helper.copy(from, j++, to, k);
            } else helper.copy(from, i++, to, k);
    }

    public static final String MERGESORT = "mergesort";
    public static final String NOCOPY = "nocopy";
    public static final String INSURANCE = "insurance";

    private static String getConfigString(Config config) {
        StringBuilder stringBuilder = new StringBuilder();
        if (config.getBoolean(MERGESORT, INSURANCE)) stringBuilder.append(" with insurance comparison");
        if (config.getBoolean(MERGESORT, NOCOPY)) stringBuilder.append(" with no copy");
        return stringBuilder.toString();
    }

    private final InsertionSort<X> insertionSort;
}

