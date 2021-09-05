package edu.neu.coe.info6205.sort.linearithmic;

import edu.neu.coe.info6205.sort.Helper;
import edu.neu.coe.info6205.util.Config;

import java.util.ArrayList;
import java.util.List;

public class QuickSort_Basic<X extends Comparable<X>> extends QuickSort<X> {

    public static final String DESCRIPTION = "QuickSort basic";

    public QuickSort_Basic(String description, int N, Config config) {
        super(description, N, config);
        setPartitioner(createPartitioner());
    }

    /**
     * Constructor for QuickSort_Basic
     *
     * @param helper an explicit instance of Helper to be used.
     */
    public QuickSort_Basic(Helper<X> helper) {
        super(helper);
        setPartitioner(createPartitioner());
    }

    /**
     * Constructor for QuickSort_Basic
     *
     * @param N      the number elements we expect to sort.
     * @param config the configuration.
     */
    public QuickSort_Basic(int N, Config config) {
        this(DESCRIPTION, N, config);
    }

    /**
     * Constructor for QuickSort_Basic
     *
     * @param config the configuration.
     */
    public QuickSort_Basic(Config config) {
        this(0, config);
    }

    @Override
    public Partitioner<X> createPartitioner() {
        return new Partitioner_Basic(getHelper());
    }

    public class Partitioner_Basic implements Partitioner<X> {

        public Partitioner_Basic(Helper<X> helper) {
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
            final int lo = partition.from;
            final int hi = partition.to;
            X v = xs[lo];
            int i = lo;
            int j = hi;
            // NOTE: we are trying to avoid checking on instrumented for every time in the inner loop for performance reasons (probably a silly idea).
            // NOTE: if we were using Scala, it would be easy to set up a comparer function and a swapper function. With java, it's possible but much messier.
            if (helper.instrumented()) {
                while (true) {
                    while (helper.less(xs[++i], v)) if (i == hi - 1) break;
                    while (helper.less(v, xs[--j])) if (j == lo) break;
                    if (i >= j) break;
                    helper.swap(xs, i, j);
                }
                helper.swap(xs, lo, j);
            } else {
                while (true) {
                    while (xs[++i].compareTo(v) < 0) if (i == hi - 1) break;
                    while (xs[--j].compareTo(v) > 0) if (j == lo) break;
                    if (i >= j) break;
                    swap(xs, i, j);
                }
                swap(xs, lo, j);
            }

            List<Partition<X>> partitions = new ArrayList<>();
            partitions.add(new Partition<>(xs, lo, j));
            partitions.add(new Partition<>(xs, j + 1, hi));
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

