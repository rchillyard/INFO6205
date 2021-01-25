/*
  (c) Copyright 2018, 2019 Phasmid Software
 */
package edu.neu.assignment;

import edu.neu.coe.info6205.sort.BaseHelper;
import edu.neu.coe.info6205.sort.Helper;
import edu.neu.coe.info6205.sort.HelperFactory;
import edu.neu.coe.info6205.sort.SortWithHelper;
import edu.neu.coe.info6205.util.Config;
import edu.neu.coe.info6205.util.LazyLogger;
import edu.neu.coe.info6205.util.Timer;

import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;
import java.util.function.UnaryOperator;

import static org.junit.Assert.assertTrue;

public class InsertionSort<X extends Comparable<X>> extends SortWithHelper<X> {

    /**
     * Constructor for any sub-classes to use.
     *
     * @param description the description.
     * @param N           the number of elements expected.
     * @param config      the configuration.
     */
    protected InsertionSort(String description, int N, Config config) {
        super(description, N, config);
    }

    /**
     * Constructor for InsertionSort
     *
     * @param N      the number elements we expect to sort.
     * @param config the configuration.
     */
    public InsertionSort(int N, Config config) {
        this(DESCRIPTION, N, config);
    }

    public InsertionSort() {
        this(new BaseHelper<>(DESCRIPTION));
    }

    /**
     * Constructor for InsertionSort
     *
     * @param helper an explicit instance of Helper to be used.
     */
    public InsertionSort(Helper<X> helper) {
        super(helper);
    }

    /**
     * Sort the sub-array xs:from:to using insertion sort.
     *
     * @param xs   sort the array xs from "from" to "to".
     * @param from the index of the first element to sort
     * @param to   the index of the first element not to sort
     */
    public void sort(X[] xs, int from, int to) {
        rangeCheck(xs.length, from, to);
        final Helper<X> helper = getHelper();
        // TO BE IMPLEMENTED
        for (int i = from+1; i < to; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if(helper.compare(xs, j, j+1) > 0) {
//                if (xs[j].compareTo(xs[j + 1]) > 0) {
                    helper.swap(xs, j, j + 1);
                } else {
                    break;
                }
            }
        }
    }

    /**
     * This is used by unit tests.
     *
     * @param ys  the array to be sorted.
     * @param <Y> the underlying element type.
     */
    public static <Y extends Comparable<Y>> void mutatingInsertionSort(Y[] ys) {
        new InsertionSort<Y>().mutatingSort(ys);
    }

    public static final String DESCRIPTION = "Insertion sort";


    static void rangeCheck(int arrayLength, int fromIndex, int toIndex) {
        if (fromIndex > toIndex) {
            throw new IllegalArgumentException("fromIndex(" + fromIndex + ") > toIndex(" + toIndex + ")");
        } else if (fromIndex < 0) {
            throw new ArrayIndexOutOfBoundsException(fromIndex);
        } else if (toIndex > arrayLength) {
            throw new ArrayIndexOutOfBoundsException(toIndex);
        }
    }

    final static LazyLogger logger = new LazyLogger(InsertionSort.class);

    public static void main(String[] args) throws IOException {
        int repeatTimes = 5;
        int n = 100;

        Config config = Config.load();
        Helper<Integer> helper = HelperFactory.create("InsertionSort", n, config);
        helper.init(n);
        Integer[] nums = helper.random(Integer.class, r -> r.nextInt(1000));
        // random
        double meanTime = new Timer().repeat(repeatTimes, () -> nums, t -> {
            SortWithHelper<Integer> sorter = new InsertionSort<>(helper);
            t = sorter.sort(t);
            assertTrue(helper.sorted(t));
            return null;
        });
        logger.info("random: "+meanTime);

        // ordered
        meanTime = new Timer().repeat(repeatTimes, () -> nums, t -> {
            SortWithHelper<Integer> sorter = new InsertionSort<>(helper);
            t = sorter.sort(t);
            assertTrue(helper.sorted(t));
            return null;
        });
        logger.info("ordered: "+meanTime);

        // partially-order
        UnaryOperator<Integer[]> partiallySort = t->{
            SortWithHelper<Integer> sorter = new InsertionSort<>(helper);
            Random random = new Random();
            int max = random.nextInt(t.length);
            int min = random.nextInt(max);
            sorter.sort(t, min, max);
            return t;
        };
        meanTime = new Timer().repeat(repeatTimes, () -> nums, t -> {
            SortWithHelper<Integer> sorter = new InsertionSort<>(helper);
            t = sorter.sort(t);
            assertTrue(helper.sorted(t));
            return null;
        }, partiallySort, null);
        logger.info("partially-order: "+meanTime);

        // reversed-order
        UnaryOperator<Integer[]> totalSort = t -> {
            return Arrays.stream(t).sorted(Comparator.reverseOrder()).toArray(Integer[]::new);
        };
        meanTime = new Timer().repeat(repeatTimes, () -> nums, t -> {
            SortWithHelper<Integer> sorter = new InsertionSort<>(helper);
            t = sorter.sort(t);
            assertTrue(helper.sorted(t));
            return null;
        }, totalSort, null);
        logger.info("reversed-order: "+meanTime);
    }
}
