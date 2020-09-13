package edu.neu.coe.info6205.sort;

import edu.neu.coe.info6205.util.Utilities;

import java.util.Random;
import java.util.function.Function;

public class BaseHelper<X extends Comparable<X>> implements Helper<X> {

    /**
     * @return false
     */
    public boolean instrumented() {
        return false;
    }

    /**
     * Method to determine if one X value is less than another.
     *
     * @param v the candidate element.
     * @param w the comparand element.
     * @return true only if v is less than w.
     */
    public boolean less(X v, X w) {
        return v.compareTo(w) < 0;
    }

    /**
     * Compare elements i and j of xs within the subarray lo..hi
     *
     * @param xs the array.
     * @param i  one of the indices.
     * @param j  the other index.
     * @return the result of comparing xs[i] to xs[j]
     */
    public int compare(X[] xs, int i, int j) {
        // CONSIDER invoking the other compare signature
        return xs[i].compareTo(xs[j]);
    }

    /**
     * Compare v and w
     *
     * @param v the first X.
     * @param w the second X.
     * @return the result of comparing v and w.
     */
    @Override
    public int compare(X v, X w) {
        return v.compareTo(w);
    }

    /**
     * Swap the elements of array a at indices i and j.
     *
     * @param xs the array.
     * @param i  one of the indices.
     * @param j  the other index.
     */
    public void swap(X[] xs, int i, int j) {
        X temp = xs[i];
        xs[i] = xs[j];
        xs[j] = temp;
    }

    /**
     * Method to perform a stable swap using half-exchanges,
     * i.e. between xs[i] and xs[j] such that xs[j] is moved to index i,
     * and xs[i] thru xs[j-1] are all moved up one.
     * This type of swap is used by insertion sort.
     *
     * @param xs the array of Xs.
     * @param i  the index of the destination of xs[j].
     * @param j  the index of the right-most element to be involved in the swap.
     */
    public void swapInto(X[] xs, int i, int j) {
        if (j > i) {
            X x = xs[j];
            System.arraycopy(xs, i, xs, i + 1, j - i);
            xs[i] = x;
        }
    }

    /**
     * Copy the element at source[j] into target[i]
     *
     * @param source the source array.
     * @param i      the target index.
     * @param target the target array.
     * @param j      the source index.
     */
    @Override
    public void copy(X[] source, int i, X[] target, int j) {
        target[j] = source[i];
    }

    public boolean sorted(X[] xs) {
        for (int i = 1; i < xs.length; i++) if (xs[i - 1].compareTo(xs[i]) > 0) return false;
        return true;
    }

    public int inversions(X[] xs) {
        int result = 0;
        for (int i = 0; i < xs.length; i++)
            for (int j = i + 1; j < xs.length; j++)
                if (xs[i].compareTo(xs[j]) > 0) result++;
        return result;
    }

    public X[] random(Class<X> clazz, Function<Random, X> f) {
        if (n <= 0) throw new HelperException("Helper.random: not initialized");
        return Utilities.fillRandomArray(clazz, random, n, f);
    }

    /**
     * Method to post-process the array xs after sorting.
     * By default, this method does nothing.
     *
     * @param xs the array to be tested.
     */
    @Override
    public void postProcess(X[] xs) {
    }

    @Override
    public String toString() {
        return "Helper for " + description + " with " + n + " elements";
    }

    public String getDescription() {
        return description;
    }

    /**
     * @param n the size to be managed.
     * @throws HelperException if n is inconsistent.
     */
    public void init(int n) {
        if (this.n == 0 || this.n == n) this.n = n;
        else throw new HelperException("Helper: n is already set to a different value");
    }

    public int getN() {
        return n;
    }

    public void close() {
    }

    /**
     * Constructor for explicit random number generator.
     *
     * @param description the description of this Helper (for humans).
     * @param n           the number of elements expected to be sorted. The field n is mutable so can be set after the constructor.
     * @param random      a random number generator.
     */
    public BaseHelper(String description, int n, Random random) {
        this.n = n;
        this.description = description;
        this.random = random;
    }

    /**
     * Constructor for explicit seed.
     *
     * @param description the description of this Helper (for humans).
     * @param n           the number of elements expected to be sorted. The field n is mutable so can be set after the constructor.
     * @param seed        the seed for the random number generator.
     */
    public BaseHelper(String description, int n, long seed) {
        this(description, n, new Random(seed));
    }

    /**
     * Constructor to create a Helper with a random seed.
     *
     * @param description the description of this Helper (for humans).
     * @param n           the number of elements expected to be sorted. The field n is mutable so can be set after the constructor.
     */
    public BaseHelper(String description, int n) {
        this(description, n, System.currentTimeMillis());
    }

    /**
     * Constructor to create a Helper with a random seed and an n value of 0.
     *
     * @param description the description of this Helper (for humans).
     */
    public BaseHelper(String description) {
        this(description, 0);
    }

    public static final String INSTRUMENT = "instrument";

    public static class HelperException extends RuntimeException {

        public HelperException(String message) {
            super(message);
        }

        public HelperException(String message, Throwable cause) {
            super(message, cause);
        }

        public HelperException(Throwable cause) {
            super(cause);
        }

        public HelperException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
            super(message, cause, enableSuppression, writableStackTrace);
        }
    }

    protected final String description;
    protected final Random random;
    protected int n;
}
