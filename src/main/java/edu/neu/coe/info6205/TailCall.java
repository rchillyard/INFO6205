package edu.neu.coe.info6205;/*
 * Copyright (c) 2018. Phasmid Software
 */

import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * Interface to define the behavior of tail call recursion.
 * This uses a version of trampolining which makes use of a lazy list provided by the Stream mechanism.
 *
 * @param <T> the underlying type of the result of the TailCall.
 */
@FunctionalInterface
public interface TailCall<T> extends Supplier<TailCall<T>> {

    /**
     * Method to determine if this TailCall has been completed.
     *
     * @return true if complete.
     */
    default boolean isComplete() {
        return false;
    }

    /**
     * Method to get the result of this TailCall.
     *
     * @return a value of T.
     */
    default T result() {
        throw new RuntimeException("not implemented");
    }

    /**
     * Method to invoke this TailCall.
     *
     * @return a value of T.
     * @throws Throwable if there is a logic error.
     */
    default T invoke() throws Throwable {
        return Stream.iterate(this, TailCall::get)
                .filter(TailCall::isComplete)
                .findFirst()
                .orElseThrow(TailCalls.getExceptionSupplier("TailCall logic error"))
                .result();
    }
}

/**
 * Convenience class to provide some utilities for TailCall recursion.
 */
class TailCalls {
    /**
     * Method to make a tail call.
     *
     * @param nextCall the next TailCall.
     * @param <T>      the underlying type.
     * @return nextCall.
     */
    static <T> TailCall<T> call(final TailCall<T> nextCall) {
        return nextCall;
    }

    /**
     * Method to form a "done" TailCall value.
     *
     * @param value (of type T) the value to be returned.
     * @param <T>   the underlying type.
     * @return value.
     */
    static <T> TailCall<T> done(final T value) {
        return new TailCall<T>() {
            public boolean isComplete() {
                return true;
            }

            public T result() {
                return value;
            }

            public TailCall<T> get() {
                throw new RuntimeException("never called");
            }
        };
    }

    static Supplier<Throwable> getExceptionSupplier(final String message) {
        return () -> new RuntimeException(message);
    }
}