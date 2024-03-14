package edu.neu.coe.info6205.sort.linearithmic;

import edu.neu.coe.info6205.sort.Helper;
import edu.neu.coe.info6205.util.Config;

import java.util.ArrayList;
import java.util.List;

public class QuickSort_Exp<X extends Comparable<X>> extends QuickSort<X> {

    public static final String DESCRIPTION = "QuickSort basic";

    public QuickSort_Exp(String description, int N, Config config) {
        super(description, N, config);
        setPartitioner(createPartitioner());
    }

    /**
     * Constructor for QuickSort_Basic
     *
     * @param helper an explicit instance of Helper to be used.
     */
    public QuickSort_Exp(Helper<X> helper) {
        super(helper);
        setPartitioner(createPartitioner());
    }

    /**
     * Constructor for QuickSort_Basic
     *
     * @param N      the number elements we expect to sort.
     * @param config the configuration.
     */
    public QuickSort_Exp(int N, Config config) {
        this(DESCRIPTION, N, config);
    }

    /**
     * Constructor for QuickSort_Basic
     *
     * @param config the configuration.
     */
    public QuickSort_Exp(Config config) {
        this(0, config);
    }

    public Partitioner<X> createPartitioner() {
        return new Partitioner_Exp(getHelper());
    }

    public class Partitioner_Exp implements Partitioner<X> {

        public Partitioner_Exp(Helper<X> helper) {
            this.helper = helper;
        }

        /**
         * Method to partition the given partition into smaller partitions.
         *
         * @param partition the partition to divide up.
         * @return an array of partitions, whose length depends on the sorting method being used.
         */
        public List<Partition<X>> partition(Partition<X> partition) {
            final X[] xs = partition.xs;
            final int from = partition.from;
            final int to = partition.to;
            final int hi = to - 1;
            int mid = from + (to - from) / 2;
            helper.swap(xs, from, mid);
            X v = xs[from];
            int i = from;
            int j = to;
            // NOTE: we are trying to avoid checking on instrumented for every time in the inner loop for performance reasons (probably a silly idea).
            // NOTE: if we were using Scala, it would be easy to set up a comparer function and a swapper function. With java, it's possible but much messier.
            if (helper.instrumented()) {
                helper.incrementHits(1);
                while (true) {
                    while (i < hi && helper.less(xs[++i], v)) {
                    }
                    while (j > from && helper.less(v, xs[--j])) {
                    }
                    if (i >= j) break;
                    helper.swap(xs, i, j);
                }
                helper.swap(xs, from, j);
            } else {
                while (true) {
                    while (i < hi && xs[++i].compareTo(v) < 0) {
                    }
                    while (j > from && xs[--j].compareTo(v) > 0) {
                    }
                    if (i >= j) break;
                    swap(xs, i, j);
                }
                swap(xs, from, j);
            }

            List<Partition<X>> partitions = new ArrayList<>();
            partitions.add(new Partition<>(xs, from, j));
            partitions.add(new Partition<>(xs, j + 1, to));
            return partitions;
        }

        private void swap(X[] ys, int i, int j) {
            X temp = ys[i];
            ys[i] = ys[j];
            ys[j] = temp;
        }

        private final Helper<X> helper;
    }
}
