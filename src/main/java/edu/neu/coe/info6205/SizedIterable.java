package edu.neu.coe.info6205;

public interface SizedIterable<T> extends Iterable<T> {

    /**
     * Method to yield the size of this iterable.
     *
     * @return the size.
     */
    int size();
}
