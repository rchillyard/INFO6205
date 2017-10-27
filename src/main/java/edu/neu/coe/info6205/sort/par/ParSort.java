package edu.neu.coe.info6205.sort.par;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;

class ParSort {

    public static int cutoff = 1000;

    public static void sort(int[] array, int from, int to) {
        int size = to - from;
        if (size < cutoff) Arrays.sort(array, from, to);
        else {
            System.err.println("sorting array in parallel from "+from+" to "+to);
            CompletableFuture<int[]> parsort1 = parsort(array, from, (from + to) / 2);
            CompletableFuture<int[]> parsort2 = parsort(array, (from + to) / 2, to);
            CompletableFuture<int[]> parsort = parsort1.
                    thenCombine(parsort2, (xs1, xs2) -> {
                        // TODO need to merge these arrays, not just copy them!!
                        int[] result = new int[xs1.length + xs2.length];
                        System.arraycopy(xs1, 0, result, 0, xs1.length);
                        System.arraycopy(xs2, 0, result, xs1.length, xs2.length);
                        return result;
                    });

            parsort.whenComplete((result, throwable) -> System.arraycopy(result, 0, array, from, result.length));
            parsort.join();
        }
    }

    private static CompletableFuture<int[]> parsort(int[] array, int from, int to) {
        return CompletableFuture.supplyAsync(
                () -> {
                    int size = to  - from;
                    System.err.println("async sorting array of size "+size+" from "+from+" to "+to);
                    sort(array, from, to);
                    int[] result = new int[size];
                    System.arraycopy(array, from, result, 0, size);
                    return result;
                }
        );
    }
}
