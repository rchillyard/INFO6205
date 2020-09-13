package edu.neu.coe.info6205.sort.simple;

import edu.neu.coe.info6205.sort.BaseHelper;
import edu.neu.coe.info6205.sort.Helper;
import edu.neu.coe.info6205.sort.InstrumentedHelper;
import edu.neu.coe.info6205.util.Config;

import java.util.ArrayList;
import java.util.List;

public class QuickSort_3way<X extends Comparable<X>> extends QuickSort<X> {

    public static final String DESCRIPTION = "QuickSort 3 way";

    /**
     * Constructor for QuickSort_3way
     *
     * @param helper an explicit instance of Helper to be used.
     */
    public QuickSort_3way(Helper<X> helper) {
        super(helper);
        setPartitioner(createPartitioner());
    }

    public QuickSort_3way() {
        this(new BaseHelper<>(DESCRIPTION));
    }

    /**
     * Constructor for QuickSort_3way
     *
     * @param N      the number elements we expect to sort.
     * @param config the configuration.
     */
    public QuickSort_3way(int N, Config config) {
        super(DESCRIPTION, N, config);
        setPartitioner(createPartitioner());
    }

    /**
     * Constructor for QuickSort_3way which always uses an instrumented helper with a specific seed.
     * <p>
     * NOTE used by unit tests.
     *
     * @param N    the number of elements to be sorted.
     * @param seed the seed for the random number generator.
     */
    public QuickSort_3way(int N, long seed, Config config) {
        this(new InstrumentedHelper<>(DESCRIPTION, N, config));
    }

    public Partitioner<X> createPartitioner() {
        return new Partitioner_3Way(getHelper());
    }

    class Partitioner_3Way implements Partitioner<X> {

        /**
         * Method to partition the given partition into smaller partitions.
         *
         * @param partition the partition to divide up.
         * @return an array of partitions, whose length depends on the sorting method being used.
         */
        public List<Partition<X>> partition(Partition<X> partition) {
            // CONSIDER merge with Partitioner_DualPivot
            X[] xs = partition.xs;
            int lt = partition.from;
            int gt = partition.to - 1;
            helper.swapConditional(xs, lt, gt);
            X v = xs[lt];
            int i = lt + 1;
            // NOTE: we are trying to avoid checking on instrumented for every time in the inner loop for performance reasons (probably a silly idea).
            // NOTE: if we were using Scala, it would be easy to set up a comparer function and a swapper function. With java, it's possible but much messier.
            if (helper.instrumented())
                while (i <= gt) {
                    int cmp = helper.compare(xs[i], v);
                    if (cmp < 0) helper.swap(xs, lt++, i++);
                    else if (cmp > 0) helper.swap(xs, i, gt--);
                    else i++;
                }
            else
                while (i <= gt) {
                    int cmp = xs[i].compareTo(v);
                    if (cmp < 0) swap(xs, lt++, i++);
                    else if (cmp > 0) swap(xs, i, gt--);
                    else i++;
                }

            List<Partition<X>> partitions = new ArrayList<>();
            partitions.add(new Partition<>(xs, partition.from, lt));
            partitions.add(new Partition<>(xs, gt + 1, partition.to));
            return partitions;
        }

        public Partitioner_3Way(Helper<X> helper) {
            this.helper = helper;
        }

        private void swap(X[] ys, int i, int j) {
            if (helper != null) helper.swap(ys, i, j);
            else {
                X temp = ys[i];
                ys[i] = ys[j];
                ys[j] = temp;
            }
        }

        private final Helper<X> helper;
    }
}

