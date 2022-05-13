/*
  (c) Copyright 2018, 2019 Phasmid Software
 */
package edu.neu.coe.info6205.util;

import edu.neu.coe.info6205.pq.PQException;
import edu.neu.coe.info6205.pq.PriorityQueue;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Consumer;
import java.util.regex.Pattern;

import static edu.neu.coe.info6205.util.SortBenchmarkHelper.getWords;

public class PQBenchmark {

    public PQBenchmark(Config config) {
        this.config = config;
    }

    public static void main(String[] args) throws IOException {
        Config config = Config.load(PQBenchmark.class);
        logger.info("SortBenchmark.main: " + config.get("huskysort", "version") + " with word counts: " + Arrays.toString(args));
        if (args.length == 0) logger.warn("No word counts specified on the command line");
        PQBenchmark benchmark = new PQBenchmark(config);
        System.out.println("with floyd: " + benchmark.insertDeleteN(10000, 1000, true));
        System.out.println("no floyd: " + benchmark.insertDeleteN(10000, 1000, false));
    }

    // Insert and delete random integer array with floyd methods according to parameter
    private void insertArray(int[] a, final boolean floyd) {
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>(a.length, true, Comparator.naturalOrder(), floyd);
        final Random random = new Random();
        for (int j : a) {
            pq.give(j);
            if (random.nextBoolean()) {
                try {
                    pq.take();
                } catch (PQException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private double insertDeleteN(final int n, int m, final boolean floyd) {
        final Random ran = new Random();
        int[] random = new int[n];
        for (int i = 0; i < n; i++) {
            random[i] = ran.nextInt(n);
        }
        Benchmark<Boolean> bm = new Benchmark_Timer<>(
                "testPQwithFloydoff",
                null,
                b -> insertArray(random, floyd),
                null
        );
        return bm.run(true, m);

    }

    /**
     * For mergesort, the number of array accesses is actually 6 times the number of comparisons.
     * That's because, in addition to each comparison, there will be approximately two copy operations.
     * Thus, in the case where comparisons are based on primitives,
     * the normalized time per run should approximate the time for one array access.
     */
    public final static TimeLogger[] timeLoggersLinearithmic = {
            new TimeLogger("Raw time per run (mSec): ", (time, n) -> time),
            new TimeLogger("Normalized time per run (n log n): ", (time, n) -> time / minComparisons(n) / 6 * 1e6)
    };

    final static LazyLogger logger = new LazyLogger(PQBenchmark.class);

    final static Pattern regexLeipzig = Pattern.compile("[~\\t]*\\t(([\\s\\p{Punct}\\uFF0C]*\\p{L}+)*)");

    /**
     * This is based on log2(n!)
     *
     * @param n the number of elements.
     * @return the minimum number of comparisons possible to sort n randomly ordered elements.
     */
    static double minComparisons(int n) {
        double lgN = Utilities.lg(n);
        return n * (lgN - LgE) + lgN / 2 + 1.33;
    }

    /**
     * This is the mean number of inversions in a randomly ordered set of n elements.
     * For insertion sort, each (low-level) swap fixes one inversion, so on average there are this number of swaps.
     * The minimum number of comparisons is slightly higher.
     *
     * @param n the number of elements
     * @return one quarter n-squared more or less.
     */
    static double meanInversions(int n) {
        return 0.25 * n * (n - 1);
    }

    private static Collection<String> lineAsList(String line) {
        List<String> words = new ArrayList<>();
        words.add(line);
        return words;
    }

    private static Collection<String> getLeipzigWords(String line) {
        return getWords(regexLeipzig, line);
    }

    // TODO: to be eliminated soon.
    private static Benchmark<LocalDateTime[]> benchmarkFactory(String description, Consumer<LocalDateTime[]> sorter, Consumer<LocalDateTime[]> checker) {
        return new Benchmark_Timer<>(
                description,
                (xs) -> Arrays.copyOf(xs, xs.length),
                sorter,
                checker
        );
    }

    private static final double LgE = Utilities.lg(Math.E);

    boolean isConfigBoolean(String section, String option) {
        return config.getBoolean(section, option);
    }

    private final Config config;
}
