package edu.neu.coe.info6205.sort.counting;

import edu.neu.coe.info6205.sort.Helper;
import edu.neu.coe.info6205.sort.HelperFactory;
import edu.neu.coe.info6205.sort.SortWithHelper;
import edu.neu.coe.info6205.sort.elementary.InsertionSortMSD;
import edu.neu.coe.info6205.util.Config;

/**
 * Class to implement Most significant digit string sort (a radix sort).
 */
public class MSDStringSort extends SortWithHelper<String> {
    public MSDStringSort(Helper<String> helper) {
        super(helper);
    }

    public MSDStringSort(String description, int N, Config config) {
        this(HelperFactory.create(description, N, config));
        closeHelper = true;
    }

    /**
     * Generic, mutating sort method which operates on a sub-array.
     *
     * @param xs   sort the array xs from "from" until "to" (exclusive of to).
     * @param from the index of the first element to sort.
     * @param to   the index of the first element not to sort.
     */
    public void sort(String[] xs, int from, int to) {
        sort(xs, getHelper(), from, to, 0);
    }

    /**
     * Sort from a[lo] to a[hi] (exclusive), ignoring the first d characters of each String.
     * This method is recursive.
     *
     * @param a      the array to be sorted.
     * @param helper the Helper.
     * @param lo     the low index.
     * @param hi     the high index (one above the highest actually processed).
     * @param d      the number of characters in each String to be skipped.
     */
    private static void sort(String[] a, Helper<String> helper, int lo, int hi, int d) {
        if (hi < lo + cutoff) InsertionSortMSD.sort(a, lo, hi, d);
        else {
            String[] aux = new String[hi - lo];       // CONSIDER having an aux which does for all levels. Is that even possible?
            int[] count = new int[radix + 2];        // Compute frequency counts.
            for (int i = lo; i < hi; i++)
                count[charAt(a[i], d) + 2]++;
            for (int r = 0; r < radix + 1; r++)      // Transform counts to indices.
                count[r + 1] += count[r];
            for (int i = lo; i < hi; i++)     // Distribute.
                // TODO use helper.get(a,i) instead of a[i].
                helper.copy(a, i, aux, count[charAt(a[i], d) + 1]++); //aux[count[charAt(a[i], d) + 1]++] = a[i];
            // Copy back.
            if (hi - lo >= 0) helper.copyBlock(aux, 0, a, lo, hi - lo);
            // Recursively sort for each character value.
            // TO BE IMPLEMENTED 
throw new RuntimeException("implementation missing");
        }
    }

    private static int charAt(String s, int d) {
        if (d < s.length()) return s.charAt(d);
        else return -1;
    }

    private static final int radix = 256;
    private static final int cutoff = 15;
//    private static String[] aux;       // auxiliary array for distribution
}