package edu.neu.coe.info6205.symbolTable.tree;

import edu.neu.coe.info6205.util.*;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

import static edu.neu.coe.info6205.symbolTable.tree.BSTOptimisedDeletion.random;
import static edu.neu.coe.info6205.util.SortBenchmarkHelper.getWords;
import static edu.neu.coe.info6205.util.Utilities.formatDecimal3Places;


public class BSTBenchmark<K extends Comparable<K>, V> extends Benchmark_Timer<K[]> {

    public static void main(final String[] args) {
        logger.info("BSTBenchmark: with args: " + Arrays.toString(args));
        final Stream<Integer> stream = Arrays.stream(args).map(Integer::parseInt);
        stream.forEach(BSTBenchmark::doBenchmark);
    }

    private static void doBenchmark(final int x) {
        String resource = "eng-uk_web_2002_" + (x < 50000 ? "10K" : x < 200000 ? "100K" : "1M") + "-words.txt";
        try {
            final int mode = 2;
            final double initialSampleFraction = 0.8;
            final double runSampleFraction = 0.2;
            final int nRuns = 1000;
            final String[] words = getWords(resource, SortBenchmark::getLeipzigWords);
            logger.info("creating benchmark with " + x + " words from " + resource);
            final int runSampleSize = (int) (words.length * runSampleFraction);
            logger.info("creating BST with mode " + mode + " and " + words.length + " words");
            final BstDetail<String, Integer> bst = createBST(mode, words, initialSampleFraction);
            logger.info("BST has " + bst.size() + " nodes initially");
            final Stats stats = new Stats(bst.size());
            final BSTBenchmark<String, Integer> benchmark = new BSTBenchmark<>(String.class, bst, words, nRuns, SortBenchmark.timeLoggersLinearithmic, stats);
            final Supplier<String[]> supplier = () -> Utilities.fillRandomArray(String.class, random, runSampleSize, r -> words[r.nextInt(words.length)]);
            final double result = benchmark.runBenchmark(supplier);
            logger.info("Stats: " + stats + "; average milliseconds: " + formatDecimal3Places(result));
        } catch (FileNotFoundException e) {
            logger.error("BSTBenchmark: cannot find word file: " + e.getLocalizedMessage());
        }
    }

    private static BstDetail<String, Integer> createBST(final int mode, final String[] words, final double sampleRate) {
        final BSTOptimisedDeletion<String, Integer> bst = new BSTOptimisedDeletion<>(mode);
        final int sampleSize = (int) (words.length * sampleRate);
        final String[] initialStrings = Utilities.fillRandomArray(String.class, random, sampleSize, r -> words[r.nextInt(words.length)]);
        final Map<String, Integer> map = new HashMap<>();
        for (String initialString : initialStrings) map.put(initialString, initialString.length());
        bst.putAll(map);
        return bst;
    }

    public double runBenchmark(final Supplier<K[]> supplier) {
        return runFromSupplier(supplier, nRuns);
    }

    /**
     * Constructor for a SorterBenchmark where we provide the following parameters:
     *
     * @param tClass      the class of K.
     * @param bst         the bst.
     * @param ks          the array of Ts.
     * @param nRuns       the number of runs to perform in this benchmark.
     * @param timeLoggers the time-loggers.
     * @param stats       the statistics to be returned.
     */
    public BSTBenchmark(final Class<K> tClass, final BstDetail<K, V> bst, final K[] ks, final int nRuns, final TimeLogger[] timeLoggers, final Stats stats) {
        super("BST benchmark", createPreProcessor(), createExperiment(bst), createPostProcessor(bst, stats));
        this.tClass = tClass;
        this.ks = ks;
        this.nRuns = nRuns;
        this.timeLoggers = timeLoggers;
    }

    private static <X> UnaryOperator<X[]> createPreProcessor() {
        return xs -> {
            logger.debug("pre-processor: size of array is: " + xs.length + " with first element: " + xs[0]);
            return xs;
        };
    }

    private static <X extends Comparable<X>> Consumer<X[]> createExperiment(final BST<X, ?> bst) {
        return xs -> {
            logger.debug("experiment: before: bst has " + bst.size());
            for (X x : xs) bst.delete(x);
            for (X x : xs) bst.put(x, null);
            logger.debug("experiment: after: bst has " + bst.size());
        };
    }

    private static <X extends Comparable<X>> Consumer<X[]> createPostProcessor(final BstDetail<X, ?> bst, final Stats stats) {
        return xs -> {
            final double meanDepth = bst.meanDepth();
            final double sqrt = Math.sqrt(bst.size());
            stats.setMeanDepth(bst.size(), meanDepth);
            logger.debug("BST nodes: " + bst.size() + " mean depth: " + formatDecimal3Places(meanDepth) + " sqrt(n): " + formatDecimal3Places(sqrt));
        };
    }

    static class Stats {
        public Stats(final int nodes) {
            this.initialNodes = nodes;
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder();
            sb.append("initialNodes: ").append(initialNodes);
            sb.append(", nodes: ").append(nodes);
            sb.append(", initialMeanDepth: ").append(formatDecimal3Places(initialMeanDepth));
            sb.append(", meanDepth: ").append(formatDecimal3Places(meanDepth));
            return sb.toString();
        }

        void setMeanDepth(final int nodes, final double meanDepth) {
            if (initialMeanDepth == 0) initialMeanDepth = meanDepth;
            this.meanDepth = meanDepth;
            this.nodes = nodes;
        }

        final int initialNodes;
        double meanDepth = 0;
        double initialMeanDepth = 0;
        int nodes;
    }

    final static LazyLogger logger = new LazyLogger(BSTBenchmark.class);

    private final Class<K> tClass;
    private final K[] ks;
    private final int nRuns;
    private final TimeLogger[] timeLoggers;
}