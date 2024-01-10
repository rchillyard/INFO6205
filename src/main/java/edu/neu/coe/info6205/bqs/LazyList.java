package edu.neu.coe.info6205.bqs;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Class to demonstrate that it is possible to create a LazyList in Java.
 * NOTE: this does not represent a true lazy list in the sense of the Scala LazyList.
 *
 * @param <T> the underlying type of the lazy list.
 */
public class LazyList<T> {
    /**
     * Method to take a number of elements from a LazyList.
     * NOTE: the result is not a new LazyList (as it really should be) but a List.
     *
     * @param n the number of elements to take from this LazyList.
     * @return a List with at most n elements.
     */
    public List<T> take(int n) {
        List<T> result = new ArrayList<>();
        LazyList<T> cursor = this;
        while (n > 0 && cursor != null) {
            result.add(cursor.head);
            cursor = cursor.tailFunction.get();
            n--;
        }
        return result;
    }

    /**
     * Constructor of a lazy list.
     *
     * @param head the head.
     * @param tailFunction a function to evaluate the tail, if necessary.
     */
    public LazyList(T head, Supplier<LazyList<T>> tailFunction) {
        this.head = head;
        this.tailFunction = tailFunction;
    }

    public final T head;
    public final Supplier<LazyList<T>> tailFunction;

    /**
     * Method to create a LazyList given a starting value and a function.
     *
     * @param start the starting value.
     * @param next the function to yield the next value.
     * @return a new LazyList.
     * @param <T> the underlying type.
     */
    public static <T> LazyList<T> iterate(final T start, Function<T, T> next) {
        final Supplier<LazyList<T>> supplier = () -> iterate(next.apply(start), next);
        return new LazyList<>(start, supplier);
    }

    /**
     * Method to create a LazyList of Integers given a starting value and an increment.
     *
     * @param start the starting value.
     * @param step the increment.
     * @return a new LazyList.
     */
    public static LazyList<Integer> from(final int start, int step) {
        return iterate(start, x -> x + step);
    }

    /**
     * Method to create a LazyList of Integers given a starting value.
     *
     * @param start the starting value.
     * @return a new LazyList.
     */
    public static LazyList<Integer> from(final int start) {
        return from(start, 1);
    }

}