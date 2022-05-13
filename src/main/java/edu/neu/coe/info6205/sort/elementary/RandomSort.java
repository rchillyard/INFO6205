/*
  (c) Copyright 2018, 2019 Phasmid Software
 */
package edu.neu.coe.info6205.sort.elementary;

import edu.neu.coe.info6205.sort.BaseHelper;
import edu.neu.coe.info6205.sort.Helper;
import edu.neu.coe.info6205.sort.SortWithHelper;
import edu.neu.coe.info6205.util.Config;
import edu.neu.coe.info6205.util.QuickRandom;
import edu.neu.coe.info6205.util.Utilities;

import java.io.IOException;
import java.util.Random;

/**
 * Class to implement Random Sort.
 * Random Sort, like Shell Sort, performs pre-processing of the array with (conditional) long-distance swaps in an attempt
 * to get the array almost ordered.
 * Like the decision-tree model of merge sort, Random sort performs N log N such conditional swaps before resorting to insertion sort.
 * It appears to take almost 10 times as long as quick sort for 5,000 elements.
 * I'm not sure why it should be so bad.
 *
 * @param <X> the type of element on which we will be sorting (must implement Comparable).
 */
public class RandomSort<X extends Comparable<X>> extends SortWithHelper<X> {

    /**
     * Constructor for RandomSort
     *  @param N      the number elements we expect to sort.
     * @param config the configuration.
     */
    public RandomSort(int N, Config config, Random random) {
        super(DESCRIPTION, N, config);
        this.random = random;
    }

    /**
     * Constructor for RandomSort
     *  @param N      the number elements we expect to sort.
     * @param config the configuration.*/
    public RandomSort(int N, Config config) {
        this(N, config, new Random());
    }

    public RandomSort() throws IOException {
        this(new BaseHelper<>(DESCRIPTION, Config.load(RandomSort.class)), new Random());
    }

    /**
     * Constructor for RandomSort
     *
     * @param helper an explicit instance of Helper to be used.
     */
    public RandomSort(Helper<X> helper, Random random) {
        super(helper);
        this.random = random;
    }

    /**
     * Constructor for RandomSort
     *
     * @param helper an explicit instance of Helper to be used.
     * @param seed   an explicit seed.
     */
    public RandomSort(Helper<X> helper, long seed) {
        this(helper, new Random(seed));
    }

    /**
     * Constructor for RandomSort
     *
     * @param helper an explicit instance of Helper to be used.
     */
    public RandomSort(Helper<X> helper) {
        this(helper, new Random());
    }

    /**
     * Constructor for RandomSort
     */
    public RandomSort(Config config) {
        this(new BaseHelper<>(DESCRIPTION, config), new Random());
    }

    /**
     * Method to sort a sub-array of an array of Xs.
     * <p>
     *
     * @param xs an array of Xs to be sorted in place.
     */
    public void sort(X[] xs, int from, int to) {
        int N = to - from;
        final Helper<X> helper = getHelper();
        QuickRandom r = new QuickRandom(N);
        if (N > CUTOFF) {
//            final int inversions = helper.inversions(xs);
            int m = (int) ((Utilities.lg(N) + EXTRA) * N * FACTOR);
            for (int i = m; i > 0; i--) {
                int j = r.get() + from;
//                System.out.println("Random Sort: i="+i+", j="+j+", m="+m);
                if (helper.swapConditional(xs, j, r.get())) i--;
            }
//            final int fixes = inversions - helper.inversions(xs);
//            System.out.println("pre-processor: inversions="+inversions+", fixes="+fixes+", comparisons="+m);
        }
        new InsertionSort<>(helper).sort(xs, from, to);
//        String s = helper.showStats();
//        System.out.println("after insertion sort: "+s);
    }

    public static final String DESCRIPTION = "Random sort";

    public static <T extends Comparable<T>> void sort(T[] ts) throws IOException {
        new RandomSort<T>().mutatingSort(ts);
    }

    /**
     * The number to be added to lg N in order to get the best efficiency from the pre-process.
     */
    public static final int EXTRA = 0;

    /**
     * The number to be multiplied by N lg N in order to get the best efficiency from the pre-process.
     */
    public static final double FACTOR = 0.7;

    private final Random random;

    private static final int CUTOFF = 16;
}
