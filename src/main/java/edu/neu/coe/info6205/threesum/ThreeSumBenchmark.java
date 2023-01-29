package edu.neu.coe.info6205.threesum;

import edu.neu.coe.info6205.util.Benchmark_Timer;
import edu.neu.coe.info6205.util.Stopwatch;
import edu.neu.coe.info6205.util.TimeLogger;
import edu.neu.coe.info6205.util.Utilities;

import java.time.Duration;
import java.util.Date;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class ThreeSumBenchmark {
    /**
     *
     * @param runs  number of rum times
     * @param n the length of supplier array
     * @param m the upper and lower bound of elements in array
     */
    public ThreeSumBenchmark(int runs, int n, int m) {  //n is , m is
        this.runs = runs;
        this.supplier = new Source(n, m).intsSupplier(10);
        this.n = n;
    }

    public void runBenchmarks() {
        System.out.println("ThreeSumBenchmark: N=" + n);
        benchmarkThreeSum("ThreeSumQuadratic", (xs) -> new ThreeSumQuadratic(xs).getTriples(), n, timeLoggersQuadratic);
        benchmarkThreeSum("ThreeSumQuadrithmic", (xs) -> new ThreeSumQuadrithmic(xs).getTriples(), n, timeLoggersQuadrithmic);
        benchmarkThreeSum("ThreeSumCubic", (xs) -> new ThreeSumCubic(xs).getTriples(), n, timeLoggersCubic);
        System.out.println();
    }

    public static void main(String[] args) {

            new ThreeSumBenchmark(100, 250, 250).runBenchmarks();
            new ThreeSumBenchmark(50, 500, 500).runBenchmarks();
            new ThreeSumBenchmark(20, 1000, 1000).runBenchmarks();
            new ThreeSumBenchmark(10, 2000, 2000).runBenchmarks();
            new ThreeSumBenchmark(5, 4000, 4000).runBenchmarks();
            new ThreeSumBenchmark(3, 8000, 8000).runBenchmarks();
            new ThreeSumBenchmark(2, 16000, 16000).runBenchmarks();
    }

    private void benchmarkThreeSum(final String description, final Consumer<int[]> function, int n, final TimeLogger[] timeLoggers) {
        if (description.equals("ThreeSumCubic") && n > 4000) return;
        // FIXME
        // timeLoggers;
//        try (Stopwatch stopwatch = new Stopwatch()) {
//        Date date = new Date();
//            double startTime = date.getTime();

//            function.accept(supplier.get());
//            System.out.println(timeLoggers[0].toString());
//            timeLoggers[0].
//        }
//        Benchmark_Timer<int[]> timer = new Benchmark_Timer<>(description, function);
//        timer.runFromSupplier(supplier, n);

        long startTime = System.nanoTime();
        for (int i = 0; i < runs; i++) {
            function.accept(supplier.get());
        }
        long endTime = System.nanoTime();
        double costMillTime = (endTime - startTime) / 1_000_000;
        System.out.println(description + " running: " + runs + " runs cost " + costMillTime + "ms");
//        double averageMillTime = (endTime - startTime) / (double) n / 1_000_000;
        timeLoggers[0].log(costMillTime, runs);
//        timeLoggers[1].log(costMillTime, runs);

        // END 
    }

    private final static TimeLogger[] timeLoggersCubic = {
            new TimeLogger("Raw time per run (mSec): ", (time, n) -> time / n),
            new TimeLogger("Normalized time per run (n^3): ", (time, n) -> time / n / n / n * 1e6)
    };
    private final static TimeLogger[] timeLoggersQuadrithmic = {
            new TimeLogger("Raw time per run (mSec): ", (time, n) -> time / n),
            new TimeLogger("Normalized time per run (n^2 log n): ", (time, n) -> time / n / n / Utilities.lg(n) * 1e6)
    };
    private final static TimeLogger[] timeLoggersQuadratic = {
            new TimeLogger("Raw time per run (mSec): ", (time, n) -> time / n),
            new TimeLogger("Normalized time per run (n^2): ", (time, n) -> time / n / n * 1e6)
    };

    private final int runs;
    private final Supplier<int[]> supplier;
    private final int n;
}
