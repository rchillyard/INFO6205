package edu.neu.coe.info6205.util;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Random;
import java.util.function.Function;

public class Utilities {
    /**
     * There is really no better way that I could find to do this with library/language methods.
     * Don't try to inline this if the generic type extends something like Comparable, or you will get a ClassCastException.
     *
     * @param ts  a collection of Ts.
     * @param <T> the underlying type of ts.
     * @return an array T[].
     */
    public static <T> T[] asArray(Collection<T> ts) {
        if (ts.isEmpty()) throw new RuntimeException("ts may not be empty");
        @SuppressWarnings("unchecked") T[] result = (T[]) Array.newInstance(ts.iterator().next().getClass(), 0);
        return ts.toArray(result);
    }

    /**
     * Create a string representing an double, with three decimal places.
     *
     * @param x the number to show.
     * @return a String representing the number rounded to three decimal places.
     */
    public static String formatDecimal3Places(double x) {
        double scaleFactor = 1000.0;
        return String.format("%.3f", round(x * scaleFactor) / scaleFactor);
    }

    /**
     * Create a string representing an integer, with commas to separate thousands.
     *
     * @param x the integer.
     * @return a String representing the number with commas.
     */
    public static String formatWhole(int x) {
        return String.format("%,d", x);
    }

    public static String asInt(double x) {
        final int i = round(x);
        return formatWhole(i);
    }

    public static int round(double x) {
        return (int) (Math.round(x));
    }

    public static <T> T[] fillRandomArray(Class<T> clazz, Random random, int n, Function<Random, T> f) {
        @SuppressWarnings("unchecked") T[] result = (T[]) Array.newInstance(clazz, n);
        for (int i = 0; i < n; i++) result[i] = f.apply(random);
        return result;
    }

    /**
     * Method to calculate log to tbe base 2 of n.
     *
     * @param n the number whose log we need.
     * @return lg n.
     */
    public static double lg(double n) {
        return Math.log(n) / Math.log(2);
    }
}
