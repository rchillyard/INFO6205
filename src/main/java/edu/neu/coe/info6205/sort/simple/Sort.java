package edu.neu.coe.info6205.sort.simple;

import java.util.Arrays;

public interface Sort<X extends Comparable<X>> {

    /**
     * Generic, non-mutating sort method.
     *
     * @param xs sort the array xs, returning the sorted result, leaving xs unchanged.
     */
    default X[] sort(X[] xs) {
        getHelper().setN(xs.length);
        X[] result = Arrays.copyOf(xs, xs.length);
        sort(result, 0, result.length);
        return result;
    }

    /**
     * Generic, mutating sort method which operates on a sub-array
     *
     * @param xs   sort the array xs from "from" to "to".
     * @param from the index of the first element to sort
     * @param to   the index of the first element not to sort
     */
    void sort(X[] xs, int from, int to);

    /**
     * Get the Helper associated with this Sort.
     * @return the Helper
     */
    Helper<X> getHelper();
}
