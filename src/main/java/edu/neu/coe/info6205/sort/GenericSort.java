package edu.neu.coe.info6205.sort;


import edu.neu.coe.info6205.util.Utilities;

import java.util.Arrays;
import java.util.Collection;

/**
 * Interface GenericSort which defines the various sort methods for sorting elements of type X.
 * NOTE this definition does not assume that X extends Comparable of X.
 *
 * @param <X> the type of the elements to be sorted.
 */
public interface GenericSort<X> {

    /**
     * Generic, non-mutating sort method which allows for explicit determination of the makeCopy option.
     *
     * @param xs       sort the array xs, returning the sorted result, leaving xs unchanged.
     * @param makeCopy if set to true, we make a copy first and sort that.
     */
    X[] sort(X[] xs, boolean makeCopy);

    /**
     * Generic, non-mutating sort method.
     *
     * @param xs sort the array xs, returning the sorted result, leaving xs unchanged.
     */
    default X[] sort(X[] xs) {
        return sort(xs, true);
    }

    /**
     * Generic, mutating sort method.
     * Note that there is no return value.
     *
     * @param xs the array to be sorted.
     */
    default void mutatingSort(X[] xs) {
        sort(xs, false);
    }

    /**
     * Generic, mutating sort method which operates on a sub-array.
     *
     * @param xs   sort the array xs from "from" until "to" (exclusive of to).
     * @param from the index of the first element to sort.
     * @param to   the index of the first element not to sort.
     */
    void sort(X[] xs, int from, int to);

    /**
     * Method to take a Collection of X and return an Iterable of X in order.
     *
     * @param xs the collection of X elements.
     * @return a sorted iterable of X.
     */
    default Iterable<X> sort(Collection<X> xs) {
        if (xs.isEmpty()) return xs;
        final X[] array = Utilities.asArray(xs);
        mutatingSort(array);
        return Arrays.asList(array);
    }
}
