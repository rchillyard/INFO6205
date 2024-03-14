/*
  (c) Copyright 2018, 2019 Phasmid Software
 */
package edu.neu.coe.info6205.util;

import edu.neu.coe.info6205.sort.BaseHelper;
import edu.neu.coe.info6205.sort.Helper;
import edu.neu.coe.info6205.sort.SortWithHelper;
import edu.neu.coe.info6205.sort.elementary.BubbleSort;
import edu.neu.coe.info6205.sort.elementary.InsertionSort;
import edu.neu.coe.info6205.sort.elementary.RandomSort;
import edu.neu.coe.info6205.sort.elementary.ShellSort;
import edu.neu.coe.info6205.sort.linearithmic.TimSort;
import edu.neu.coe.info6205.sort.linearithmic.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDateTime;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import static edu.neu.coe.info6205.util.SortBenchmarkHelper.generateRandomLocalDateTimeArray;
import static edu.neu.coe.info6205.util.SortBenchmarkHelper.getWords;
import static edu.neu.coe.info6205.util.Utilities.formatWhole;

public class SortBenchmark {

    public SortBenchmark(Config config) {
        this.config = config;
    }

    public static void main(String[] args) throws IOException {
        Config config = Config.load(SortBenchmark.class);
        logger.info("SortBenchmark.main: " + config.get("SortBenchmark", "version") + " with word counts: " + Arrays.toString(args));
        if (args.length == 0) logger.warn("No word counts specified on the command line");
        new SortBenchmark(config).doMain(args);
    }

    void doMain(String[] args) {
        sortStrings(getWordCounts(args));
        doIntegerSorts(getWordCounts(args));
    }

    public void doIntegerSorts(Stream<Integer> wordCounts) {
        if (isConfigBenchmarkIntegerSorter("shellsort"))
            wordCounts.forEach(this::getSortedIntegersByShellSort);
    }

    public void sortLocalDateTimes(final int n, Config config) throws IOException {
        logger.info("Beginning LocalDateTime sorts");
        // CONSIDER why do we have localDateTimeSupplier IN ADDITION TO localDateTimes?
        Supplier<LocalDateTime[]> localDateTimeSupplier = () -> generateRandomLocalDateTimeArray(n);
        Helper<ChronoLocalDateTime<?>> helper = new BaseHelper<>("DateTimeHelper", config);
        final LocalDateTime[] localDateTimes = generateRandomLocalDateTimeArray(n);

        // CONSIDER finding the common ground amongst these sorts and get them all working together.

        // NOTE Test on date using pure tim sort.
        if (isConfigBenchmarkDateSorter("timsort"))
            logger.info(benchmarkFactory("Sort LocalDateTimes using Arrays::sort (TimSort)", Arrays::sort, null).runFromSupplier(localDateTimeSupplier, 100) + "ms");

        // NOTE this is supposed to match the previous benchmark run exactly. I don't understand why it takes rather less time.
        if (isConfigBenchmarkDateSorter("timsort")) {
            logger.info(benchmarkFactory("Repeat Sort LocalDateTimes using timSort::mutatingSort", new TimSort<>(helper)::mutatingSort, null).runFromSupplier(localDateTimeSupplier, 100) + "ms");
            // NOTE this is intended to replace the run two lines previous. It should take the exact same amount of time.
            runDateTimeSortBenchmark(LocalDateTime.class, localDateTimes, n, 100);
        }
    }

    /**
     * Method to run pure (non-instrumented) string sorter benchmarks.
     * <p>
     * NOTE: this is package-private because it is used by unit tests.
     *
     * @param words  the word source.
     * @param nWords the number of words to be sorted.
     * @param nRuns  the number of runs.
     */
    void benchmarkStringSorters(String[] words, int nWords, int nRuns) {
        logger.info("Testing pure sorts with " + formatWhole(nRuns) + " runs of sorting " + formatWhole(nWords) + " words");
        Random random = new Random();

        if (isConfigBenchmarkStringSorter("puresystemsort"))
            runPureSystemSortBenchmark(words, nWords, nRuns, random);

        if (isConfigBenchmarkStringSorter("mergesort"))
            runMergeSortBenchmark(words, nWords, nRuns, isConfigBenchmarkMergeSort("insurance"), isConfigBenchmarkMergeSort("nocopy"));

        if (isConfigBenchmarkStringSorter("quicksort3way"))
            runStringSortBenchmark(words, nWords, nRuns, new QuickSort_3way<>(nWords, config), timeLoggersLinearithmic);

        if (isConfigBenchmarkStringSorter("quicksortDualPivot"))
            runStringSortBenchmark(words, nWords, nRuns, new QuickSort_DualPivot<>(nWords, config), timeLoggersLinearithmic);

        if (isConfigBenchmarkStringSorter("quicksort"))
            runStringSortBenchmark(words, nWords, nRuns, new QuickSort_Basic<>(nWords, config), timeLoggersLinearithmic);

        if (isConfigBenchmarkStringSorter("introsort"))
            runStringSortBenchmark(words, nWords, nRuns, new IntroSort<>(nWords, config), timeLoggersLinearithmic);

        if (isConfigBenchmarkStringSorter("randomsort"))
            runStringSortBenchmark(words, nWords, nRuns, new RandomSort<>(nWords, config), timeLoggersLinearithmic);

        // NOTE: this is very slow of course, so recommendation is not to enable this option.
        if (isConfigBenchmarkStringSorter("insertionsort"))
            runStringSortBenchmark(words, nWords, nRuns / 10, new InsertionSort<>(nWords, config), timeLoggersQuadratic);

        // NOTE: this is very slow of course, so recommendation is not to enable this option.
        if (isConfigBenchmarkStringSorter("bubblesort"))
            runStringSortBenchmark(words, nWords, nRuns / 10, new BubbleSort<>(nWords, config), timeLoggersQuadratic);

    }

    /**
     * Method to run instrumented string sorter benchmarks.
     * <p>
     * NOTE: this is package-private because it is used by unit tests.
     *
     * @param words  the word source.
     * @param nWords the number of words to be sorted.
     * @param nRuns  the number of runs.
     */
    void benchmarkStringSortersInstrumented(String[] words, int nWords, int nRuns) {
        logger.info("Testing with " + formatWhole(nRuns) + " runs of sorting " + formatWhole(nWords) + " words" + (config.isInstrumented() ? " and instrumented" : ""));
        Random random = new Random();

        if (isConfigBenchmarkStringSorter("puresystemsort")) runPureSystemSortBenchmark(words, nWords, nRuns, random);

        if (isConfigBenchmarkStringSorter("mergesort"))
            runMergeSortBenchmark(words, nWords, nRuns, isConfigBenchmarkMergeSort("insurance"), isConfigBenchmarkMergeSort("nocopy"));

        if (isConfigBenchmarkStringSorter("quicksort3way"))
            runStringSortBenchmark(words, nWords, nRuns, new QuickSort_3way<>(nWords, config), timeLoggersLinearithmic);

        if (isConfigBenchmarkStringSorter("quicksortDualPivot"))
            runStringSortBenchmark(words, nWords, nRuns, new QuickSort_DualPivot<>(nWords, config), timeLoggersLinearithmic);

        if (isConfigBenchmarkStringSorter("quicksort"))
            runStringSortBenchmark(words, nWords, nRuns, new QuickSort_Basic<>(nWords, config), timeLoggersLinearithmic);

        if (isConfigBenchmarkStringSorter("introsort"))
            runStringSortBenchmark(words, nWords, nRuns, new IntroSort<>(nWords, config), timeLoggersLinearithmic);

        if (isConfigBenchmarkStringSorter("randomsort"))
            runStringSortBenchmark(words, nWords, nRuns, new RandomSort<>(nWords, config), timeLoggersLinearithmic);

        // NOTE: this is very slow of course, so recommendation is not to enable this option.
        if (isConfigBenchmarkStringSorter("insertionsort"))
            runStringSortBenchmark(words, nWords, nRuns / 10, new InsertionSort<>(nWords, config), timeLoggersQuadratic);

        // NOTE: this is very slow of course, so recommendation is not to enable this option.
        if (isConfigBenchmarkStringSorter("bubblesort"))
            runStringSortBenchmark(words, nWords, nRuns / 10, new BubbleSort<>(nWords, config), timeLoggersQuadratic);
    }

    private static void runPureSystemSortBenchmark(String[] words, int nWords, int nRuns, Random random) {
        Benchmark<String[]> benchmark = new Benchmark_Timer<>("SystemSort", null, Arrays::sort, null);
        doPureBenchmark(words, nWords, nRuns, random, benchmark);
    }

    private void sortIntegersByShellSort(int N) throws IOException {
        if (isConfigBenchmarkIntegerSorter("shellsort")) {
            int m = config.getInt(BENCHMARKINTEGERSORTERS, "mode", 5);
            int runs = config.getInt(BENCHMARKINTEGERSORTERS, "runs", 1000);
            SortWithHelper<Integer> sorter = new ShellSort<>(m, N, config);
            Integer[] numbers = sorter.getHelper().random(Integer.class, Random::nextInt);
            runIntegerSortBenchmark(numbers, N, runs, sorter, sorter::preProcess, timeLoggersSubQuadratic);
        }
    }

    private void sortStrings(Stream<Integer> wordCounts) {
        logger.info("Beginning String sorts");

        // NOTE: common words benchmark
//        benchmarkStringSorters(getWords("3000-common-words.txt", SortBenchmark::lineAsList), config.getInt("benchmarkstringsorters", "words", 1000), config.getInt("benchmarkstringsorters", "runs", 1000));

        // NOTE: Leipzig English words benchmarks (according to command-line arguments)
        wordCounts.forEach(this::doLeipzigBenchmarkEnglish);

        // NOTE: Leipzig Chines words benchmarks (according to command-line arguments)
//        doLeipzigBenchmark("zho-simp-tw_web_2014_10K-sentences.txt", 5000, 1000);
    }

    private void doLeipzigBenchmarkEnglish(int x) {
        String resource = "eng-uk_web_2002_" + (x < 50000 ? "10K" : x < 200000 ? "100K" : "1M") + "-sentences.txt";
        try {
            doLeipzigBenchmark(resource, x, Utilities.round(100000000 / minComparisons(x)));
        } catch (FileNotFoundException e) {
            logger.warn("Unable to find resource: " + resource, e);
        }
    }

    /**
     * Method to run a sorting benchmark, using an explicit preProcessor.
     *
     * @param words        an array of available words (to be chosen randomly).
     * @param nWords       the number of words to be sorted.
     * @param nRuns        the number of runs of the sort to be preformed.
     * @param sorter       the sorter to use--NOTE that this sorter will be closed at the end of this method.
     * @param preProcessor the pre-processor function, if any.
     * @param timeLoggers  a set of timeLoggers to be used.
     */
    static void runStringSortBenchmark(String[] words, int nWords, int nRuns, SortWithHelper<String> sorter, UnaryOperator<String[]> preProcessor, TimeLogger[] timeLoggers) {
        new SorterBenchmark<>(String.class, preProcessor, sorter, words, nRuns, timeLoggers).run(nWords);
        sorter.close();
    }

    /**
     * Method to run a sorting benchmark using the standard preProcess method of the sorter.
     *
     * @param words       an array of available words (to be chosen randomly).
     * @param nWords      the number of words to be sorted.
     * @param nRuns       the number of runs of the sort to be preformed.
     * @param sorter      the sorter to use--NOTE that this sorter will be closed at the end of this method.
     * @param timeLoggers a set of timeLoggers to be used.
     *                    <p>
     *                    NOTE: this method is public because it is referenced in a unit test of a different package
     */
    public static void runStringSortBenchmark(String[] words, int nWords, int nRuns, SortWithHelper<String> sorter, TimeLogger[] timeLoggers) {
        runStringSortBenchmark(words, nWords, nRuns, sorter, sorter::preProcess, timeLoggers);
    }

    /**
     * Method to run a sorting benchmark, using an explicit preProcessor.
     *
     * @param numbers      an array of available integers (to be chosen randomly).
     * @param n            the number of integers to be sorted.
     * @param nRuns        the number of runs of the sort to be preformed.
     * @param sorter       the sorter to use--NOTE that this sorter will be closed at the end of this method.
     * @param preProcessor the pre-processor function, if any.
     * @param timeLoggers  a set of timeLoggers to be used.
     */
    static void runIntegerSortBenchmark(Integer[] numbers, int n, int nRuns, SortWithHelper<Integer> sorter, UnaryOperator<Integer[]> preProcessor, TimeLogger[] timeLoggers) {
        new SorterBenchmark<>(Integer.class, preProcessor, sorter, numbers, nRuns, timeLoggers).run(n);
        sorter.close();
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

    /**
     * For (basic) insertionsort, the number of array accesses is actually 6 times the number of comparisons.
     * That's because, for each inversion, there will typically be one swap (four array accesses) and (at least) one comparison (two array accesses).
     * Thus, in the case where comparisons are based on primitives,
     * the normalized time per run should approximate the time for one array access.
     */
    final static TimeLogger[] timeLoggersQuadratic = {
            new TimeLogger("Raw time per run (mSec): ", (time, n) -> time),
            new TimeLogger("Normalized time per run (n^2): ", (time, n) -> time / meanInversions(n) / 6 * 1e6)
    };

    /**
     * For shellsort.
     */
    final static TimeLogger[] timeLoggersSubQuadratic = {
            new TimeLogger("Raw time per run (mSec): ", (time, n) -> time),
            new TimeLogger("Normalized time per run (n^(4/3)): ", (time, n) -> time / Math.pow(n, 4.0 / 3) * 1e6)
    };

    final static LazyLogger logger = new LazyLogger(SortBenchmark.class);

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

    public static Collection<String> getLeipzigWords(String line) {
        return getWords(regexLeipzig, line);
    }

    // CONSIDER: to be eliminated soon.
    private static Benchmark<LocalDateTime[]> benchmarkFactory(String description, Consumer<LocalDateTime[]> sorter, Consumer<LocalDateTime[]> checker) {
        return new Benchmark_Timer<>(
                description,
                (xs) -> Arrays.copyOf(xs, xs.length),
                sorter,
                checker
        );
    }

    private static void doPureBenchmark(String[] words, int nWords, int nRuns, Random random, Benchmark<String[]> benchmark) {
        // CONSIDER we should manage the space returned by fillRandomArray and deallocate it after use.
        final double time = benchmark.runFromSupplier(() -> Utilities.fillRandomArray(String.class, random, nWords, r -> words[r.nextInt(words.length)]), nRuns);
        for (TimeLogger timeLogger : timeLoggersLinearithmic) timeLogger.log(time, nWords);
    }

//    private void dateSortBenchmark(Supplier<LocalDateTime[]> localDateTimeSupplier, LocalDateTime[] localDateTimes, Sort<ChronoLocalDateTime<?>> dateHuskySortSystemSort, String s, int i) {
//        logger.info(benchmarkFactory(s, dateHuskySortSystemSort::sort, dateHuskySortSystemSort::postProcess).runFromSupplier(localDateTimeSupplier, 100) + "ms");
//        // NOTE: this is intended to replace the run in the previous line. It should take the exact same amount of time.
//        runDateTimeSortBenchmark(LocalDateTime.class, localDateTimes, 100000, 100, i);
//    }

    private static Stream<Integer> getWordCounts(String[] args) {
        return Arrays.stream(args).map(Integer::parseInt);
    }

    private void runMergeSortBenchmark(String[] words, int nWords, int nRuns, Boolean insurance, Boolean noCopy) {
        Config x = config.copy(MergeSort.MERGESORT, MergeSort.INSURANCE, insurance.toString()).copy(MergeSort.MERGESORT, MergeSort.NOCOPY, noCopy.toString());
        runStringSortBenchmark(words, nWords, nRuns, new MergeSort<>(nWords, x), timeLoggersLinearithmic);
    }

    private void doLeipzigBenchmark(String resource, int nWords, int nRuns) throws FileNotFoundException {
        benchmarkStringSorters(getWords(resource, SortBenchmark::getLeipzigWords), nWords, nRuns);
        if (isConfigBoolean(Config.HELPER, BaseHelper.INSTRUMENT))
            benchmarkStringSortersInstrumented(getWords(resource, SortBenchmark::getLeipzigWords), nWords, nRuns);
    }

    @SuppressWarnings("SameParameterValue")
    private void runDateTimeSortBenchmark(Class<?> tClass, ChronoLocalDateTime<?>[] dateTimes, int N, int m) throws IOException {
        final SortWithHelper<ChronoLocalDateTime<?>> sorter = new TimSort<>();
        @SuppressWarnings("unchecked") final SorterBenchmark<ChronoLocalDateTime<?>> sorterBenchmark = new SorterBenchmark<>((Class<ChronoLocalDateTime<?>>) tClass, (xs) -> Arrays.copyOf(xs, xs.length), sorter, dateTimes, m, timeLoggersLinearithmic);
        sorterBenchmark.run(N);
    }

    private void getSortedIntegersByShellSort(int x) {
        try {
            sortIntegersByShellSort(x);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static final double LgE = Utilities.lg(Math.E);

    private boolean isConfigBenchmarkStringSorter(String option) {
        return isConfigBoolean("benchmarkstringsorters", option);
    }

    private boolean isConfigBenchmarkMergeSort(String option) {
        return isConfigBoolean("mergesort", option);
    }

    private boolean isConfigBenchmarkDateSorter(String option) {
        return isConfigBoolean("benchmarkdatesorters", option);
    }

    private boolean isConfigBenchmarkIntegerSorter(String option) {
        return isConfigBoolean(BENCHMARKINTEGERSORTERS, option);
    }

    private boolean isConfigBoolean(String section, String option) {
        return config.getBoolean(section, option);
    }

    public static final String BENCHMARKINTEGERSORTERS = "benchmarkintegersorters";

    private final Config config;
}