/*
  (c) Copyright 2018, 2019 Phasmid Software
 */
package edu.neu.coe.info6205.sort.elementary;

import edu.neu.coe.info6205.sort.BaseHelper;
import edu.neu.coe.info6205.sort.Helper;
import edu.neu.coe.info6205.sort.SortWithHelper;
import edu.neu.coe.info6205.util.Config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

/**
 * Class to implement Shell Sort.
 *
 * @param <X> the type of element on which we will be sorting (must implement Comparable).
 */
public class ShellSort<X extends Comparable<X>> extends SortWithHelper<X> {

    /**
     * Constructor for ShellSort
     *
     * @param N      the number elements we expect to sort.
     * @param config the configuration.
     */
    public ShellSort(int m, int N, Config config) {
        super(DESCRIPTION, N, config);
        this.m = m;
    }

    public ShellSort() throws IOException {
        this(5);
    }

    public ShellSort(int m) throws IOException {
        this(m, new BaseHelper<>(DESCRIPTION, Config.load(ShellSort.class)));
    }

    /**
     * Constructor for ShellSort
     *
     * @param m      the "gap" (h) sequence to follow:
     *               1: ordinary insertion sort;
     *               2: use powers of two less one;
     *               3: use the sequence based on 3 (the one in the book): 1, 4, 13, etc.
     *               4: Sedgewick's sequence (not implemented).
     *               5: Pratt Sequence 2^i*3^j with i, j >= 0.
     * @param helper an explicit instance of Helper to be used.
     */
    public ShellSort(int m, Helper<X> helper) {
        super(helper);
        this.m = m;
    }

    /**
     * Constructor for ShellSort
     *
     * @param m the "gap" (h) sequence to follow:
     *          1: ordinary insertion sort;
     *          2: use powers of two less one;
     *          3: use the sequence based on 3 (the one in the book): 1, 4, 13, etc.
     *          4: Sedgewick's sequence.
     *          5: Pratt Sequence 2^i*3^j with i, j >= 0.
     */
    public ShellSort(int m, Config config) {
        this(m, new BaseHelper<>(DESCRIPTION, config));
    }

    /**
     * Method to sort a sub-array of an array of Xs.
     * <p>
     * TODO check that the treatment of from and to is correct. It seems to be according to the unit tests.
     *
     * @param xs an array of Xs to be sorted in place.
     */
    public void sort(X[] xs, int from, int to) {
        int N = to - from;
        H hh = new H(N);
        int h = hh.first();
        while (h > 0) {
            hSort(h, xs, from, to);
            if (shellFunction != null)
                shellFunction.accept(getHelper());
            h = hh.next();
        }
    }

    /**
     * Set the "shell" function which is invoked on the helper after each shell (i.e. each value of h).
     * Yes, I do realize that shell was the name of the inventor, Donald Shell.
     * But it's also a convenient name of a (set of) h-sorts which one particular h-value.
     *
     * @param shellFunction a consumer of Helper of X.
     */
    public void setShellFunction(Consumer<Helper<X>> shellFunction) {
        this.shellFunction = shellFunction;
    }

    public static final String DESCRIPTION = "Shell sort";

    /**
     * Private method to h-sort an array.
     *
     * @param h    the stride (gap) of the h-sort.
     * @param xs   the array to be sorted.
     * @param from the first index to be considered in array xs.
     * @param to   one plus the last index to be considered in array xs.
     */
    private void hSort(int h, X[] xs, int from, int to) {
        final Helper<X> helper = getHelper();
        for (int i = h + from; i < to; i++) {
            int j = i;
            while (j >= h + from && helper.swapConditional(xs, j - h, j)) j -= h;
        }
    }

    private final int m;

    private Consumer<Helper<X>> shellFunction = null;

    /**
     * Private inner class to provide h (gap) values.
     */
    class H {
        @SuppressWarnings("CanBeFinal")
        private int h = 1;
        private int i;
        private boolean started = false;
        final List<Integer> data = new ArrayList<>();

        H(int N) {

            switch (m) {
                case 1:
                    break;
                case 2:
                    while (h <= N) h = 2 * (h + 1) - 1;
                    break;
                case 3:
                    while (h <= N / 3) h = h * 3 + 1;
                    break;
                case 4:
                    i = 0;
                    while (sedgewick(i) < N) i++;
                    i--;
                    h = (int) sedgewick(i); // Note there will be loss of precision for large i
                    break;
                case 5:
                    //2^i*3^j with i, j >= 0
                    int i;
                    int j = 1;
                    while (j <= N) {
                        i = j;
                        while (i <= N) {
                            data.add(i);
                            i = i * 2;
                        }
                        j = j * 3;
                    }
                    Collections.sort(data);
                    this.i = data.size() - 1;
                    h = data.get(this.i);
                    break;

                default:
                    throw new RuntimeException("invalid m value: " + m);
            }
        }

        /**
         * Method to yield the first h value.
         * NOTE: this may only be called once.
         *
         * @return the first (largest) value of h, given the size of the problem (N)
         */
        int first() {
            if (started) throw new RuntimeException("cannot call first more than once");
            started = true;
            return h;
        }

        /**
         * Method to yield the next h value in the "gap" series.
         * NOTE: first must be called before next.
         *
         * @return the next value of h in the gap series.
         */
        int next() {
            if (started) {
                switch (m) {
                    case 1:
                        return 0;
                    case 2:
                        h = (h + 1) / 2 - 1;
                        return h;
                    case 3:
                        h = h / 3;
                        return h;
                    case 4:
                        i--;
                        return (int) sedgewick(i);
                    case 5:
                        i--;
                        if (i < 0) {
                            return 0;
                        }
                        return data.get(i);

                    default:
                        throw new RuntimeException("invalid m value: " + m);
                }
            } else {
                started = true;
                return h;
            }
        }

        long sedgewick(int k) {
            if (k < 0) return 0;
            if (k % 2 == 0) return 9L * (powerOf2(k) - powerOf2(k / 2)) + 1;
            else return 8L * powerOf2(k) - 6 * powerOf2((k + 1) / 2) + 1;
        }

        private long powerOf2(int k) {
            long value = 1;
            for (int i = 0; i < k; i++) value *= 2;
            return value;
        }
    }
}
