package edu.neu.coe.info6205.sort.counting;

import edu.neu.coe.info6205.sort.elementary.InsertionSortMSD;

/**
 * Class to implement Most significant digit string sort (a radix sort).
 */
public class MSDStringSort {

    /**
     * Sort an array of Strings using MSDStringSort.
     *
     * @param a the array to be sorted.
     */
    public static void sort(String[] a) {
        int n = a.length;
        aux = new String[n];
        sort(a, 0, n, 0);
    }

    /**
     * Sort from a[lo] to a[hi] (exclusive), ignoring the first d characters of each String.
     * This method is recursive.
     *
     * @param a the array to be sorted.
     * @param lo the low index.
     * @param hi the high index (one above the highest actually processed).
     * @param d the number of characters in each String to be skipped.
     */
    private static void sort(String[] a, int lo, int hi, int d) {
        if (hi < lo + cutoff) InsertionSortMSD.sort(a, lo, hi, d);
        else {
            int[] count = new int[radix + 2];        // Compute frequency counts.
            for (int i = lo; i < hi; i++)
                count[charAt(a[i], d) + 2]++;
            for (int r = 0; r < radix + 1; r++)      // Transform counts to indices.
                count[r + 1] += count[r];
            for (int i = lo; i < hi; i++)     // Distribute.
                aux[count[charAt(a[i], d) + 1]++] = a[i];
            // Copy back.
            if (hi - lo >= 0) System.arraycopy(aux, 0, a, lo, hi - lo);
            // Recursively sort for each character value.
            // FIXME
            // END 
        }
    }

    private static int charAt(String s, int d) {
        if (d < s.length()) return s.charAt(d);
        else return -1;
    }

    private static final int radix = 256;
    private static final int cutoff = 15;
    private static String[] aux;       // auxiliary array for distribution
}