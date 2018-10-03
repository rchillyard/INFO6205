package edu.neu.coe.info6205.sort.simple;

public interface Sort<X extends Comparable<X>> {

    /**
     * Generic, non-mutating sort method.
     *
     * @param xs sort the array xs, returning the sorted result, leaving xs unchanged.
     */
    default X[] sort(X[] xs) {
        return sort(xs, 0, xs.length);
    }

    /**
     * Generic, non-mutating sort method.
     *
     * @param xs sort the array xs, returning the sorted result, leaving xs unchanged.
     * @param from the index of the first element to sort
     * @param to   the index of the first element not to sort
     */
    X[] sort(X[] xs, int from, int to);

}
