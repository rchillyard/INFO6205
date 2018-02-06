package edu.neu.coe.info6205.sort.simple;

public interface Sort<X extends Comparable<X>> {

    /**
     * Generic sort method.
     *
     * @param xs sort the array xs in place
     */
    default void sort(X[] xs) {
        sort(xs, 0, xs.length);
    }

    /**
     * Generic sort method.
     *
     * @param xs   sort the array xs in place
     * @param from the index of the first element to sort
     * @param to   the index of the first element not to sort
     */
    void sort(X[] xs, int from, int to);

}
