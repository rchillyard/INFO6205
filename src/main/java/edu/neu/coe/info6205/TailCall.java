package edu.neu.coe.info6205;/*
 * Copyright (c) 2018. Phasmid Software
 */

import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * Interface to define the behavior of tail calls.
 *
 * @param <T> the underlying type of the TailCall.
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
        throw new Error("not implemented");
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
                .orElseThrow((Supplier<Throwable>) () -> new Error("TailCall logic error"))
                .result();
    }
}
