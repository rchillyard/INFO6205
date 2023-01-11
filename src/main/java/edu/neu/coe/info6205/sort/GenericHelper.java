package edu.neu.coe.info6205.sort;

import edu.neu.coe.info6205.util.Config;

import java.util.Random;
import java.util.function.Function;

public interface GenericHelper<X> {
    /**
     * @return true if this is an instrumented Helper.
     */
    boolean instrumented();

    /**
     * Method to perform a general swap, i.e. between xs[i] and xs[j]
     *
     * @param xs the array of X elements.
     * @param i  the index of the lower of the elements to be swapped.
     * @param j  the index of the higher of the elements to be swapped.
     */
    default void swap(X[] xs, int i, int j)  {
        X x = xs[j];
        xs[j] = xs[i];
        xs[i] = x;
    }

    /**
     * Method to perform a stable swap, i.e. between xs[i] and xs[i-1]
     *
     * @param xs the array of X elements.
     * @param i  the index of the higher of the adjacent elements to be swapped.
     */
    default void swapStable(X[] xs, int i) {
        swap(xs, i - 1, i);
    }

    /**
     * Copy the element at source[j] into target[i]
     *
     * @param source the source array.
     * @param i      the target index.
     * @param target the target array.
     * @param j      the source index.
     */
    default void copy(X[] source, int i, X[] target, int j) {
        target[j] = source[i];
    }

    /**
     * Method to generate an array of randomly chosen X elements.
     *
     * @param m     the number of random elements required.
     * @param clazz the class of X.
     * @param f     a function which takes a Random and generates a random value of X.
     * @return an array of X of length determined by the current value according to setN.
     */
    X[] random(int m, Class<X> clazz, Function<Random, X> f);

    /**
     * Method to generate an array of randomly chosen X elements.
     * The length of the returned array is dependent on the value of n used to initialize this Helper.
     *
     * @param clazz the class of X.
     * @param f     a function which takes a Random and generates a random value of X.
     * @return an array of X of length determined by the current value according to setN.
     */
    default X[] random(Class<X> clazz, Function<Random, X> f) {
        return random(getN(), clazz, f);
    }

    /**
     * Method to generate an array of two randomly chosen X elements.
     *
     * @param clazz the class of X.
     * @param f     a function which takes a Random and generates a random value of X.
     * @return an array of X of length determined by the current value according to setN.
     */
    default X[] randomPair(Class<X> clazz, Function<Random, X> f) {
        return random(2, clazz, f);
    }

    /**
     * @return the description of this Helper.
     */
    String getDescription();

    /**
     * Get the configuration associated with this Helper.
     *
     * @return an instance of Config.
     */
    Config getConfig();

    /**
     * @param n the size to be managed.
     * @throws BaseHelper.HelperException if n is inconsistent.
     */
    void init(int n);

    /**
     * Get the current value of N.
     *
     * @return the value of N.
     */
    int getN();

    /**
     * Close this Helper, freeing up any resources used.
     */
    void close();
}
