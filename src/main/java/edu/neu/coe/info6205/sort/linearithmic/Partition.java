package edu.neu.coe.info6205.sort.linearithmic;

public class Partition<X extends Comparable<X>> {
    public final X[] xs;
    public final int from;
    public final int to;

    public Partition(X[] xs, int from, int to) {
        this.xs = xs;
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "Partition{" +
                "xs: " + xs.length + " elements" +
                ", from=" + from +
                ", to=" + to +
                '}';
    }
}
