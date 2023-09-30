/*
  (c) Copyright 2018, 2019 Phasmid Software
 */
package edu.neu.coe.info6205.sort;

import java.util.Arrays;

/**
 * Interface Sort.
 * This interface defines the behavior of a generic sort.
 * Note that the underlying type X is not required to be Comparable.
 *
 * @param <X> the underlying type.
 */
public interface Sort<X> extends GenericSort<X> {

    /**
     * Generic, non-mutating sort method which allows for explicit determination of the makeCopy option.
     *
     * @param xs       sort the array xs, returning the sorted result, leaving xs unchanged.
     * @param makeCopy if set to true, we make a copy first and sort that.
     */
    default X[] sort(X[] xs, boolean makeCopy) {
        init(xs.length);
        X[] result = makeCopy ? Arrays.copyOf(xs, xs.length) : xs;
        sort(result, 0, result.length);
        return result;
    }

    /**
     * Perform initializing step for this Sort.
     * <p>
     * CONSIDER merging this with preProcess logic.
     *
     * @param n the number of elements to be sorted.
     */
    void init(int n);

    /**
     * Perform pre-processing step for this Sort.
     *
     * @param xs the elements to be pre-processed.
     */
    default X[] preProcess(X[] xs) {
        init(xs.length);
        return xs;
    }

    /**
     * Post-process the given array, i.e. after sorting has been completed.
     *
     * @param xs an array of Xs.
     */
    void postProcess(X[] xs);

    void close();

}