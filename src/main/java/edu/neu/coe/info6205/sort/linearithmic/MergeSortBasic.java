package edu.neu.coe.info6205.sort.linearithmic;

import edu.neu.coe.info6205.sort.Helper;
import edu.neu.coe.info6205.sort.InstrumentedHelper;
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
        // CONSIDER don't copy but just allocate according to the xs/aux interchange optimization
        aux = Arrays.copyOf(xs, xs.length);
        sort(result, 0, result.length);
        return result;
    }

    @Override
    public void sort(X[] a, int from, int to) {
        if (to <= from + getHelper().cutoff()) {
            insertionSort.sort(a, from, to);
            return;
        }
        final int n = to - from;
        int mid = from + n / 2;
        sort(a, from, mid);
        sort(a, mid, to);
        System.arraycopy(a, from, aux, from, n);
        getHelper().incrementCopies(n);
        getHelper().incrementHits(2 * n);
        merge(aux, a, from, mid, to);
    }

    /**
     * This method is designed to count inversions in linearithmic time, using merge sort.
     *
     * @param ys  an array of comparable Y elements.
     * @param <Y> the underlying type of the elements.
     * @return the number of inversions in ys, which remains unchanged.
     */
    public static <Y extends Comparable<Y>> int countInversions(Y[] ys) {
        final Config config = Config.setupConfigFixes();
        MergeSortBasic<Y> sorter = new MergeSortBasic<>(ys.length, config);
        Y[] sorted = sorter.sort(ys, true); // CONSIDER passing false
        InstrumentedHelper<Y> helper = (InstrumentedHelper<Y>) sorter.getHelper();
        return helper.getFixes();
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

