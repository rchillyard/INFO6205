package edu.neu.coe.info6205.sort.par;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ForkJoinPool;

/**
 * This code has been fleshed out by Ziyao Qiao. Thanks very much.
 * CONSIDER tidy it up a bit.
 */
class ParSort {

    public static int cutoff = 1000;

    public static int maxRunningThread;

    public static int maxDepth;

    public static void sort(int[] array, int from, int to, ForkJoinPool myPool, int depth) {
        //int currentlyRunningThread = myPool.getRunningThreadCount();
        if (to - from < cutoff){// || (depth >= maxDepth && myPool.getRunningThreadCount() >= maxRunningThread)) {
            Arrays.sort(array, from, to);
        }
        else {
            //System.out.println("#Depth: "+depth+", from: "+from+", to: "+to+ ", current Running Thread: "+myPool.getRunningThreadCount());

            // FIXME next few lines should be removed from public repo.
            CompletableFuture<int[]> parsort1 = parsort(array, from, from + (to - from) / 2, myPool, depth); // TO IMPLEMENT
            CompletableFuture<int[]> parsort2 = parsort(array, from + (to - from) / 2, to, myPool, depth); // TO IMPLEMENT
            CompletableFuture<int[]> parsort = parsort1.thenCombine(parsort2, (xs1, xs2) -> {
                int[] result = new int[xs1.length + xs2.length];
                // TO IMPLEMENT
                int i = 0;
                int j = 0;
                for (int k = 0; k < result.length; k++) {
                    if (i >= xs1.length) {
                        result[k] = xs2[j++];
                    } else if (j >= xs2.length) {
                        result[k] = xs1[i++];
                    } else if (xs2[j] < xs1[i]) {
                        result[k] = xs2[j++];
                    } else {
                        result[k] = xs1[i++];
                    }
                }
                return result;
            });

            parsort.whenComplete((result, throwable) -> System.arraycopy(result, 0, array, from, result.length));
            //System.out.println("#"+from+", "+to+ " : "+myPool.commonPool().getQueuedTaskCount());
            parsort.join();
        }
    }

    private static CompletableFuture<int[]> parsort(int[] array, int from, int to, ForkJoinPool myPool, final int depth) {
        return CompletableFuture.supplyAsync(
                () -> {
                    int[] result = new int[to - from];
                    // TO IMPLEMENT
                    System.arraycopy(array, from, result, 0, result.length);
                    //System.out.println("#Depth: "+depth+", from: "+from+", to: "+to+ ", current Running Thread: "+myPool.commonPool().getRunningThreadCount());
                    sort(result, 0, to - from, myPool, depth + 1);
                    return result;
                }, myPool
        );
    }
}