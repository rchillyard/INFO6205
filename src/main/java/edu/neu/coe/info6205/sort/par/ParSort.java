package edu.neu.coe.info6205.sort.par;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;

class ParSort {

    public static int cutoff = 1000;

    public static void sort(int[] array, int from, int to) {
        int size = to - from;
        if (size < cutoff) Arrays.sort(array, from, to);
        else {
            CompletableFuture<int[]> parsort1 = null; // TODO implement me
            CompletableFuture<int[]> parsort2 = null; // TODO implement me
            CompletableFuture<int[]> parsort = parsort1.
                    thenCombine(parsort2, (xs1, xs2) -> {
                        int[] result = new int[xs1.length + xs2.length];
                        // TODO implement me
                        return result;
                    });

            parsort.whenComplete((result, throwable) -> {}); // TODO implement me
            parsort.join();
        }
    }

    private static CompletableFuture<int[]> parsort(int[] array, int from, int to) {
        return CompletableFuture.supplyAsync(
                () -> {
                    int[] result = new int[to  - from];
                    // TODO implement me
                    return result;
                }
        );
    }
}
