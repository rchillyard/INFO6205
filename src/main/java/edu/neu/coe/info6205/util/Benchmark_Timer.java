/*
 * Copyright (c) 2018. Phasmid Software
 */
package edu.neu.coe.info6205.util;

import edu.neu.coe.info6205.sort.elementary.InsertionSort;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

import static edu.neu.coe.info6205.util.Utilities.formatWhole;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * This class implements a simple Benchmark utility for measuring the running
 * time of algorithms. It is part of the repository for the INFO6205 class,
 * taught by Prof. Robin Hillyard
 * <p>
 * It requires Java 8 as it uses function types, in particular,
 * UnaryOperator&lt;T&gt; (a function of T => T), Consumer&lt;T&gt; (essentially
 * a function of T => Void) and Supplier&lt;T&gt; (essentially a function of
 * Void => T).
 * <p>
 * In general, the benchmark class handles three phases of a "run:"
 * <ol>
 * <li>The pre-function which prepares the input to the study function (field
 * fPre) (may be null);</li>
 * <li>The study function itself (field fRun) -- assumed to be a mutating
 * function since it does not return a result;</li>
 * <li>The post-function which cleans up and/or checks the results of the study
 * function (field fPost) (may be null).</li>
 * </ol>
 * <p>
 * Note that the clock does not run during invocations of the pre-function and
 * the post-function (if any).
 *
 * @param <T> The generic type T is that of the input to the function f which
 * you will pass in to the constructor.
 */
public class Benchmark_Timer<T> implements Benchmark<T> {

    /**
     * Calculate the appropriate number of warmup runs.
     *
     * @param m the number of runs.
     * @return at least 2 and at most m/10.
     */
    static int getWarmupRuns(int m) {
        return Integer.max(2, Integer.min(10, m / 10));
    }

    /**
     * Run function f m times and return the average time in milliseconds.
     *
     * @param supplier a Supplier of a T
     * @param m the number of times the function f will be called.
     * @return the average number of milliseconds taken for each run of function
     * f.
     */
    @Override
    public double runFromSupplier(Supplier<T> supplier, int m) {
        logger.info("Begin run:" + description + " with " + formatWhole(m) + " runs");
        // Warmup phase
        final Function<T, T> function = t -> {
            fRun.accept(t);
            return t;
        };
        new Timer().repeat(getWarmupRuns(m), supplier, function, fPre, null);

        // Timed phase
        return new Timer().repeat(m, supplier, function, fPre, fPost);
    }

    /**
     * Constructor for a Benchmark_Timer with option of specifying all three
     * functions.
     *
     * @param description the description of the benchmark.
     * @param fPre a function of T => T. Function fPre is run before each
     * invocation of fRun (but with the clock stopped). The result of fPre (if
     * any) is passed to fRun.
     * @param fRun a Consumer function (i.e. a function of T => Void). Function
     * fRun is the function whose timing you want to measure. For example, you
     * might create a function which sorts an array. When you create a lambda
     * defining fRun, you must return "null."
     * @param fPost a Consumer function (i.e. a function of T => Void).
     */
    public Benchmark_Timer(String description, UnaryOperator<T> fPre, Consumer<T> fRun, Consumer<T> fPost) {
        this.description = description;
        this.fPre = fPre;
        this.fRun = fRun;
        this.fPost = fPost;
    }

    /**
     * Constructor for a Benchmark_Timer with option of specifying all three
     * functions.
     *
     * @param description the description of the benchmark.
     * @param fPre a function of T => T. Function fPre is run before each
     * invocation of fRun (but with the clock stopped). The result of fPre (if
     * any) is passed to fRun.
     * @param fRun a Consumer function (i.e. a function of T => Void). Function
     * fRun is the function whose timing you want to measure. For example, you
     * might create a function which sorts an array.
     */
    public Benchmark_Timer(String description, UnaryOperator<T> fPre, Consumer<T> fRun) {
        this(description, fPre, fRun, null);
    }

    /**
     * Constructor for a Benchmark_Timer with only fRun and fPost Consumer
     * parameters.
     *
     * @param description the description of the benchmark.
     * @param fRun a Consumer function (i.e. a function of T => Void). Function
     * fRun is the function whose timing you want to measure. For example, you
     * might create a function which sorts an array. When you create a lambda
     * defining fRun, you must return "null."
     * @param fPost a Consumer function (i.e. a function of T => Void).
     */
    public Benchmark_Timer(String description, Consumer<T> fRun, Consumer<T> fPost) {
        this(description, null, fRun, fPost);
    }

    /**
     * Constructor for a Benchmark_Timer where only the (timed) run function is
     * specified.
     *
     * @param description the description of the benchmark.
     * @param f a Consumer function (i.e. a function of T => Void). Function f
     * is the function whose timing you want to measure. For example, you might
     * create a function which sorts an array.
     */
    public Benchmark_Timer(String description, Consumer<T> f) {
        this(description, null, f, null);
    }

    private final String description;
    private final UnaryOperator<T> fPre;
    private final Consumer<T> fRun;
    private final Consumer<T> fPost;

    final static LazyLogger logger = new LazyLogger(Benchmark_Timer.class);
    public static void main(String[] args) {
        Random r1 = new Random();
        InsertionSort insertionSort = new InsertionSort();

        for (int n=500; n<30000; n=n*2) {

            ArrayList<Integer> arrayListRandom = new ArrayList<>();
            for (int i=0; i<n; i++)
                arrayListRandom.add(r1.nextInt(n));

            ArrayList<Integer> arrayListOrdered = new ArrayList<>();
            for (int i=0; i<n; i++)
                arrayListOrdered.add(i+1);

            ArrayList<Integer> arrayListReverse = new ArrayList<>();
            for (int i=0; i<n; i++)
                arrayListReverse.add(n-i);

            ArrayList<Integer> arrayListPartial = new ArrayList<>();
            for (int i=0; i<n; i++) {
                if (i>n/2) {
                    arrayListPartial.add(r1.nextInt(n));
                } else {
                    arrayListPartial.add(i);
                }
            }

            Integer[] arrayRandom = arrayListRandom.toArray(new Integer[0]);
            Integer[] arrayOrdered = arrayListOrdered.toArray(new Integer[0]);
            Integer[] arrayReverse = arrayListReverse.toArray(new Integer[0]);
            Integer[] arrayPartial = arrayListPartial.toArray(new Integer[0]);

            Benchmark<Boolean> benchmarkRandom = new Benchmark_Timer<>("Random Sort", b -> {
                insertionSort.sort(arrayRandom.clone(), 0, arrayRandom.length);
            });
            double resultRandom = benchmarkRandom.run(true, 10);

            Benchmark<Boolean> benchmarkOrdered = new Benchmark_Timer<>("Ordered Sort", b -> {
                insertionSort.sort(arrayOrdered.clone(), 0, arrayOrdered.length);
            });
            double resultOrdered = benchmarkOrdered.run(true, 10);

            Benchmark<Boolean> benchmarkReverse = new Benchmark_Timer<>("Reverse Sort", b -> {
                insertionSort.sort(arrayReverse.clone(), 0, arrayReverse.length);
            });
            double resultReverse = benchmarkReverse.run(true, 10);

            Benchmark<Boolean> benchmarkPartial = new Benchmark_Timer<>("Partial Sort", b -> {
                insertionSort.sort(arrayPartial.clone(), 0, arrayPartial.length);
            });
            double resultPartial = benchmarkPartial.run(true, 10);

            System.out.println("Time for random: " + "for n value of: " + n + ": " + resultRandom);
            System.out.println("Time for ordered: " + "for n value of: " + n + ": " + resultOrdered);
            System.out.println("Time for reverse: " + "for n value of: " + n + ": " + resultReverse);
            System.out.println("Time for partial: " + "for n value of: " + n + ": " + resultPartial);
        }

    }
}
