package edu.neu.coe.info6205.sort.classic;

import edu.neu.coe.info6205.bqs.Bag;
import edu.neu.coe.info6205.bqs.Bag_Array;
import edu.neu.coe.info6205.sort.GenericHelper;
import edu.neu.coe.info6205.sort.GenericSortWithGenericHelper;
import edu.neu.coe.info6205.sort.Sort;
import edu.neu.coe.info6205.sort.SortException;
import edu.neu.coe.info6205.util.Config;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * This sort method sorts elements according to their class, i.e. the sort key is the value of x.classify().
 *
 * @param <X> the underlying type which must extend Classify.
 */
public class ClassicSort<X extends Classify<X>> extends GenericSortWithGenericHelper<X> implements Sort<X> {

    public static final String DESCRIPTION = "Classic sort";

    @Override
    public void sort(X[] xs, int from, int to) {
        Map<Integer, Bag<X>> map = new HashMap<>();
        for (int i = from; i < to; i++) {
            int classs = xs[i].classify();
            Bag<X> xBag = map.getOrDefault(classs, new Bag_Array<>());
            xBag.add(xs[i]);
            map.put(classs, xBag);
        }

        // Iterate over the bags in class order, copying each bag back to the original array.
        Set<Integer> classes = map.keySet();
        int i = from;
        for (int classs : classes) {
            if (i >= to) throw new SortException("ClassicSort: logic error: " + i + ", " + to);
            Bag<X> xBag = map.get(classs);
            // FIXME Apparently, we can't use asArray. So, we will use iterator instead.
//            X[] array = xBag.asArray();
//            System.arraycopy(array, 0, xs, i, array.length);
//            i += array.length;

            // XXX alternative code
            for (X x : xBag) xs[i++] = x;
        }
    }

    @Override
    public String toString() {
        return getHelper().toString();
    }

    /**
     * Perform initializing step for this Sort.
     *
     * @param n the number of elements to be sorted.
     */
    @Override
    public void init(int n) {
    }

    /**
     * Post-process the given array, i.e. after sorting has been completed.
     *
     * @param xs an array of Xs.
     */
    @Override
    public void postProcess(X[] xs) {
    }

    @Override
    public void close() {
        if (closeHelper) getHelper().close();
    }

    ClassicSort(GenericHelper<X> helper) {
        super(helper);
        closeHelper = true;
    }

    ClassicSort() throws IOException {
        super(DESCRIPTION, 0, Config.load(ClassicSort.class));
        closeHelper = true;
    }

    private final boolean closeHelper;

}
