package edu.neu.coe.info6205.util;

import edu.neu.coe.info6205.sort.BaseHelper;
import edu.neu.coe.info6205.sort.Helper;

import java.io.IOException;
import java.util.Random;

public class OperationsBenchmark {
    public void runBenchmarks() {
        runLargestInteger();
        runCompareAdjacentElements();
        runCompareAdjacentElementsOptimized();
    }

    public OperationsBenchmark(Config config) {
        this.config = config;
    }

    public static void main(String[] args) throws IOException {
        Config config = Config.load(OperationsBenchmark.class);
        final OperationsBenchmark benchmark = new OperationsBenchmark(config);
        benchmark.runBenchmarks();
    }

    /**
     * This benchmark is designed to determine the cost of visiting every element of an array of Integers.
     * It's awkward to do the same thing with primitive ints so I'll leave that until later.
     */
    private void runLargestInteger() {
        final int nLargest = config.getInt("operationsbenchmark", "nlargest", DEFAULT_ARRAY_SIZE);
        final int repetitions = config.getInt("operationsbenchmark", "repetitions", 1000);
        logger.info("OperationsBenchmark.runBenchmarks: largest " + nLargest + " integers with " + repetitions + " repetitions");
        final Helper<Integer> helper = new BaseHelper<>("largest", nLargest, 0L, config);
        final Timer timer = new Timer();
        final double time = timer.repeat(repetitions,
                () -> getRandomIntegers(helper),
                OperationsBenchmark::findLargest
        );
        for (TimeLogger timeLogger : timeLoggersLinear) timeLogger.log(time, nLargest);
    }

    /**
     * This benchmark is designed to determine the cost of visiting every element of an array of Integers twice.
     */
    private void runCompareAdjacentElements() {
        final int nCompareAdjacent = config.getInt("operationsbenchmark", "ncompareadjacent", DEFAULT_ARRAY_SIZE);
        final int repetitions = config.getInt("operationsbenchmark", "repetitions", 1000);
        logger.info("OperationsBenchmark.runBenchmarks: compareAdjacent " + nCompareAdjacent + " integers with " + repetitions + " repetitions");
        final Helper<Integer> helper = new BaseHelper<>("compareAdjacent", nCompareAdjacent, 0L, config);
        final Timer timer = new Timer();
        final double time = timer.repeat(repetitions,
                () -> getRandomIntegers(helper),
                OperationsBenchmark::compareAdjacent
        );
        for (TimeLogger timeLogger : timeLoggersLinear) timeLogger.log(time, nCompareAdjacent);
    }

    /**
     * This benchmark is designed to determine the cost of visiting every element of an array of Integers twice.
     */
    private void runCompareAdjacentElementsOptimized() {
        final int nCompareAdjacent = config.getInt("operationsbenchmark", "ncompareadjacent", DEFAULT_ARRAY_SIZE);
        final int repetitions = config.getInt("operationsbenchmark", "repetitions", 1000);
        logger.info("OperationsBenchmark.runBenchmarks: compareAdjacentOptimized " + nCompareAdjacent + " integers with " + repetitions + " repetitions");
        final Helper<Integer> helper = new BaseHelper<>("compareAdjacent", nCompareAdjacent, 0L, config);
        final Timer timer = new Timer();
        final double time = timer.repeat(repetitions,
                () -> getRandomIntegers(helper),
                OperationsBenchmark::compareAdjacentOptimized
        );
        for (TimeLogger timeLogger : timeLoggersLinear) timeLogger.log(time, nCompareAdjacent);
    }

    private Integer[] getRandomIntegers(Helper<Integer> helper) {
        return helper.random(Integer.class, Random::nextInt);
    }

    private final Config config;

    private static Integer findLargest(Integer[] xs) {
        int largest = Integer.MIN_VALUE;
        for (Integer x : xs) if (x > largest) largest = x;
        return largest;
    }

    private static Boolean compareAdjacent(Integer[] xs) {
        boolean inversions = false;
        for (int i = 1; i < xs.length; i++)
            inversions = inversions | xs[i - 1] > xs[i];
        return inversions;
    }

    private static Boolean compareAdjacentOptimized(Integer[] xs) {
        boolean inversions = false;
        int x = xs[0];
        for (int i = 1; i < xs.length; i++) {
            final int y = xs[i];
            inversions = inversions | x > y;
            x = y;
        }
        return inversions;
    }
    private final static TimeLogger[] timeLoggersLinear = {
            new TimeLogger("Raw time per run (mSec): ", (time, n) -> time),
            new TimeLogger("Normalized time per run (n): ", (time, n) -> time / n * 1e6)
    };

    final static LazyLogger logger = new LazyLogger(OperationsBenchmark.class);

    public static final int DEFAULT_ARRAY_SIZE = 100000;

}
