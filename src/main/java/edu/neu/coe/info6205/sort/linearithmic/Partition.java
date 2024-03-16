package edu.neu.coe.info6205.sort.linearithmic;

/**
 * Class to represent a partition for Quicksort.
 *
 * @param <X> the underlying type of the array.
 */
public class Partition<X extends Comparable<X>> {
    /**
     * @param xs the array to be sorted.
     * @param from the index of the first element to be sorted.
     * @param to the index of the first element NOT to be sorted.
     */
    public Partition(X[] xs, int from, int to) {
        this.xs = xs;
        this.from = from;
        this.to = to;
    }

    public String toString() {
        return "Partition{" +
                "xs: " + xs.length + " elements" +
                ", from=" + from +
                ", to=" + to +
                '}';
    }

    public final X[] xs;
    public final int from;
    public final int to;
}