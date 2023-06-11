package edu.neu.coe.info6205.sort;

import edu.neu.coe.info6205.util.Config;
import edu.neu.coe.info6205.util.Utilities;

import java.util.Random;
import java.util.function.Function;

public class BaseHelper<X extends Comparable<X>> implements Helper<X> {

    /**
     * Static method to get a Helper configured for the given class.
     *
     * @param clazz the class for configuration.
     * @param <Y>   the type.
     * @return a Helper&lt;Y&gt;
     */
    public static <Y extends Comparable<Y>> Helper<Y> getHelper(final Class<?> clazz) {
        try {
            return new BaseHelper<>("Standard Helper", Config.load(clazz));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @return false
     */
    public boolean instrumented() {
        return false;
    }

    /**
     * Swap the elements of array 'a' at indices i and j.
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
     * and xs[i] through xs[j-1] are all moved up one.
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

    public boolean sorted(X[] xs) {
        for (int i = 1; i < xs.length; i++) if (xs[i - 1].compareTo(xs[i]) > 0) return false;
        return true;
    }

    public X[] random(int m, Class<X> clazz, Function<Random, X> f) {
        if (m <= 0)
            throw new HelperException("Helper.random: requesting zero random elements (helper not initialized?)");
        randomArray = null;
        randomArray = Utilities.fillRandomArray(clazz, random, m, f);
        return randomArray;
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
        // CONSIDER swapping order of description and Helper for... (see also overrides)
        return "Helper for " + description + " with " + n + " elements" + (instrumented() ? " instrumented" : "");
    }

    public String getDescription() {
        return description;
    }

    public Config getConfig() {
        return config;
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
    public BaseHelper(String description, int n, Random random, Config config) {
        this.n = n;
        this.description = description;
        this.random = random;
        this.config = config;
    }

    /**
     * Constructor for explicit seed.
     *
     * @param description the description of this Helper (for humans).
     * @param n           the number of elements expected to be sorted. The field n is mutable so can be set after the constructor.
     * @param seed        the seed for the random number generator.
     */
    public BaseHelper(String description, int n, long seed, Config config) {
        this(description, n, new Random(seed), config);
    }

    /**
     * Constructor to create a Helper with a random seed.
     *
     * @param description the description of this Helper (for humans).
     * @param n           the number of elements expected to be sorted. The field n is mutable so can be set after the constructor.
     */
    public BaseHelper(String description, int n, Config config) {
        this(description, n, System.currentTimeMillis(), config);
    }

    /**
     * Constructor to create a Helper with a random seed and an n value of 0.
     *
     * @param description the description of this Helper (for humans).
     */
    public BaseHelper(String description, Config config) {
        this(description, 0, config);
    }

    public static final String INSTRUMENT = "instrument";

    /**
     * Keep track of the random array that was generated. This is available via the InstrumentedHelper class.
     */
    protected X[] randomArray;

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

    protected final Config config;
    protected int n;
}
