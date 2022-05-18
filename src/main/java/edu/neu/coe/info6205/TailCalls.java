package edu.neu.coe.info6205;

/**
 * Convenience class to provide some utilities for TailCall recursion.
 */
public class TailCalls {
    /**
     * Method to make a tail call.
     *
     * @param nextCall the next TailCall.
     * @param <T>      the underlying type.
     * @return nextCall.
     */
    public static <T> TailCall<T> call(final TailCall<T> nextCall) {
        return nextCall;
    }

    /**
     * Constant done value.
     *
     * @param value (of type T) the value to be returned.
     * @param <T>   the underlying type.
     * @return value.
     */
    public static <T> TailCall<T> done(final T value) {
        return new TailCall<T>() {
            @Override
            public boolean isComplete() {
                return true;
            }

            @Override
            public T result() {
                return value;
            }

            @Override
            public TailCall<T> get() {
                throw new Error("not implemented");
            }
        };
    }
}
