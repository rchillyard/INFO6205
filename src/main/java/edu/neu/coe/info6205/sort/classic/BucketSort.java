package edu.neu.coe.info6205.sort.classic;

import edu.neu.coe.info6205.bqs.Bag;
import edu.neu.coe.info6205.bqs.Bag_Array;
import edu.neu.coe.info6205.sort.BaseHelper;
import edu.neu.coe.info6205.sort.GenericSort;
import edu.neu.coe.info6205.sort.Helper;
import edu.neu.coe.info6205.sort.Sort;
import edu.neu.coe.info6205.sort.elementary.InsertionSort;
import edu.neu.coe.info6205.util.Config;
import edu.neu.coe.info6205.util.LazyLogger;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * Bucket Sort.
 * <p>
 * CONSIDER re-implementing by doing ClassicSort first (based on buckets) then do insertion sort.
 *
 * @param <X> the underlying type which must extend Comparable.
 */
public class BucketSort<X extends Comparable<X>> implements Sort<X> {

    public static final String DESCRIPTION = "Bucket sort";

    public void sort(X[] xs, int from, int to) {
        logger.info(helper.inversions(xs));
        // Determine the min, max and gap.
        double min = Double.MAX_VALUE;
        double max = Double.MIN_VALUE;
        Number[] ys = (Number[]) xs;
        for (int i = from; i < to; i++) {
            if (ys[i].doubleValue() < min) min = ys[i].doubleValue();
            if (max < ys[i].doubleValue()) max = ys[i].doubleValue();
        }
        double gap = (max - min) / bucket.length;

        // Assign the elements to buckets
        for (int i = from; i < to; i++) {
            int index = (int) Math.floor((ys[i].doubleValue() - min) / gap);
            if (index == bucket.length) index--;
            bucket[index].add(xs[i]);
        }

        unloadBuckets(bucket, xs, helper);

        logger.info(sort.toString());
        logger.info(helper.inversions(xs));
    }

    @Override
    public String toString() {
        return helper.toString();
    }

    /**
     * Perform initializing step for this Sort.
     *
     * @param n the number of elements to be sorted.
     */
    public void init(int n) {
        // NOTE this does nothing.
    }

    /**
     * Post-process the given array, i.e. after sorting has been completed.
     *
     * @param xs an array of Xs.
     */
    public void postProcess(X[] xs) {
        helper.postProcess(xs);
    }

    public void close() {
        if (closeHelper) helper.close();
    }

    BucketSort(int buckets, BaseHelper<X> helper) throws IOException {
        //noinspection unchecked
        bucket = (Bag<X>[]) Array.newInstance(Bag.class, buckets);
        for (int i = 0; i < buckets; i++) bucket[i] = new Bag_Array<>();
        this.helper = helper;
        sort = new InsertionSort<>(Config.load(getClass()));
    }

    BucketSort(int buckets) throws IOException {
        this(buckets, new BaseHelper<>(DESCRIPTION, Config.load(BucketSort.class)));
        closeHelper = true;
    }

    private final static LazyLogger logger = new LazyLogger(BucketSort.class);

    /**
     * Method to unload and sort the buckets into the array xs.
     *
     * @param buckets an array of Bag of X elements.
     * @param xs      an array of X elements to be filled.
     * @param helper  a helper whose compare method we will use.
     * @param <X>     the underlying type of the array and the Helper.
     */
    @SuppressWarnings("unchecked")
    private static <X extends Comparable<X>> void unloadBuckets(Bag<X>[] buckets, X[] xs, final Helper<X> helper) {
        Index index = new Index();
        Arrays.stream(buckets).forEach(xes -> {
            final Object[] objects = xes.asArray();
            Arrays.sort(objects, (o, t1) -> helper.compare((X) o, (X) t1));
            for (Object x : objects) xs[index.getNext()] = (X) x;
        });
    }

    static class Index {
        int index = 0;

        int getNext() {
            return index++;
        }
    }

    private final BaseHelper<X> helper;
    private final Bag<X>[] bucket;
    private final GenericSort<X> sort;
    private boolean closeHelper = false;

}