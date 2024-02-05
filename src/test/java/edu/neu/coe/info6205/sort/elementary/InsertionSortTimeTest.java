package edu.neu.coe.info6205.sort.elementary;

import edu.neu.coe.info6205.sort.Helper;
import edu.neu.coe.info6205.util.Benchmark_Timer;
import edu.neu.coe.info6205.util.Config;
import edu.neu.coe.info6205.util.TimeLogger;
import io.cucumber.java.sl.In;
import scala.Int;

import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class InsertionSortTimeTest {
    public InsertionSortTimeTest(int runs, int arrayLength) {
        this.runs = runs;
        this.arrayLength = arrayLength;
    }

    public void runBenchmark() {
        System.out.println("InsertionSortBenchmark: ArrayLength= " + arrayLength);
        sortBenchmark("RandomArray", sort(), randomArraySupplier(), timeLoggers);
        sortBenchmark("PartiallyOrderedArray", sort(), partiallyOrderedArraySupplier(), timeLoggers);
        sortBenchmark("OrderedArray", sort(), orderedArraySupplier(), timeLoggers);
        sortBenchmark("ReversedArray", sort(), reversedArraySupplier(), timeLoggers);

    }

    public static void main(String[] args) {
        new InsertionSortTimeTest(100,25).runBenchmark();
        new InsertionSortTimeTest(50, 50).runBenchmark();
        new InsertionSortTimeTest(20, 100).runBenchmark();
        new InsertionSortTimeTest(10, 200).runBenchmark();
        new InsertionSortTimeTest(5, 400).runBenchmark();
        new InsertionSortTimeTest(3, 800).runBenchmark();
        new InsertionSortTimeTest(2, 1600).runBenchmark();
    }

    private Consumer<Integer[]> sort() {
        return (array) -> new InsertionSort<Integer>().sort(array, 0, array.length);
    }

    public void sortBenchmark(String description, Consumer<Integer[]> function, Supplier<Integer[]> arraySupplier, TimeLogger[] timeLoggers) {
        Benchmark_Timer<Integer[]> benchmarkTimer = new Benchmark_Timer<>(description, function);
        double time = benchmarkTimer.runFromSupplier(arraySupplier, runs);

        for (TimeLogger timeLogger: timeLoggers) {
            timeLogger.log(time, arrayLength);
        }
    }

    private Supplier<Integer[]> randomArraySupplier() {
        return () -> {
            Random random = new Random();
            Integer[] array = new Integer[arrayLength];
            for (int i = 0; i < array.length; i++) {
                array[i] = random.nextInt(arrayLength);
            }
            return array;
        };
    }

    private final static TimeLogger[] timeLoggers = {
            new TimeLogger("Raw time per run (mSec): ", (time, n) -> time),
            new TimeLogger("Normalized time per run (n^2): ", (time, n) -> time / n / n * 1e6)
    };

    public Supplier<Integer[]> orderedArraySupplier() {
        return () -> {
            Integer[] array = new Integer[arrayLength];
            for (int i = 0; i < array.length; i++) {
                array[i] = i;
            }
            return array;
        };
    }

    public Supplier<Integer[]> partiallyOrderedArraySupplier() {
        return () -> {
            Random random = new Random();
            Integer[] array = new Integer[arrayLength];
            for (int i = 0; i < array.length / 2; i++) {
                    array[i] = i;
            }
            for (int i = array.length / 2; i < array.length; i++) {
                array[i] = random.nextInt(arrayLength);
            }
            return array;
        };
    }

    public Supplier<Integer[]> reversedArraySupplier() {
        return () -> {
            Integer[] array = new Integer[arrayLength];
            for (int i = array.length - 1; i >= 0 ; i--) {
                array[i] = i;
            }
            return array;
        };
    }

    private final int runs;

    private final int arrayLength;
}
