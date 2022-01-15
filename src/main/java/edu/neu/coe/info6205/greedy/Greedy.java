package edu.neu.coe.info6205.greedy;

import java.util.function.BiFunction;
import java.util.function.Function;

public class Greedy<T, R> {

    private final Function<T, T> fGreedy;
    private final BiFunction<T, T, T> fAdjust;
    private final BiFunction<T, R, R> fResult;
    private final Function<T, Boolean> fDone;

    public Greedy(final Function<T, T> fGreedy, final BiFunction<T, T, T> fAdjust, final BiFunction<T, R, R> fResult, final Function<T, Boolean> fDone) {
        this.fGreedy = fGreedy;
        this.fAdjust = fAdjust;
        this.fResult = fResult;
        this.fDone = fDone;
    }

    public R run(T t, R r) {
        while (!fDone.apply(t)) {
            T greedy = fGreedy.apply(t);
            r = fResult.apply(greedy, r);
            t = fAdjust.apply(t, greedy);
        }
        return r;
    }
}
