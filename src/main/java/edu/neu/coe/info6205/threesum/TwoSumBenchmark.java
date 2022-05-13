package edu.neu.coe.info6205.threesum;

import edu.neu.coe.info6205.util.Benchmark_Timer;
import edu.neu.coe.info6205.util.TimeLogger;
import edu.neu.coe.info6205.util.Utilities;

import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class TwoSumBenchmark {
    public TwoSumBenchmark(int runs, int n, int m) {
        this.runs = runs;
        this.supplier = new Source(n, m).intsSupplier(10);
        this.n = n;
    }

    public void runBenchmarks() {
        System.out.println("TwoSumBenchmark: N=" + n);
        benchmarkTwoSum("TwoSumWithCalipers", (xs) -> new TwoSumWithCalipers(xs).getPairs(), n, timeLoggersQuadratic);
        benchmarkTwoSum("TwoSumQuadratic", (xs) -> new TwoSumQuadratic(xs).getPairs(), n, timeLoggersQuadratic);
    }

    public static void main(String[] args) {
        new TwoSumBenchmark(100, 250, 250).runBenchmarks();
        new TwoSumBenchmark(50, 500, 500).runBenchmarks();
        new TwoSumBenchmark(20, 1000, 1000).runBenchmarks();
        new TwoSumBenchmark(10, 2000, 2000).runBenchmarks();
        new TwoSumBenchmark(5, 4000, 4000).runBenchmarks();
        new TwoSumBenchmark(3, 8000, 8000).runBenchmarks();
        new TwoSumBenchmark(2, 16000, 16000).runBenchmarks();
    }

    private void benchmarkTwoSum(final String description, final Consumer<int[]> function, int n, final TimeLogger[] timeLoggers) {
        if (description.equals("ThreeSumCubic") && n > 4000) return;
        // FIXME
        // END 
    }

    private final static TimeLogger[] timeLoggersCubic = {
            new TimeLogger("Raw time per run (mSec): ", (time, n) -> time),
            new TimeLogger("Normalized time per run (n^3): ", (time, n) -> time / n / n / n * 1e6)
    };
    private final static TimeLogger[] timeLoggersQuadrithmic = {
            new TimeLogger("Raw time per run (mSec): ", (time, n) -> time),
            new TimeLogger("Normalized time per run (n^2 log n): ", (time, n) -> time / n / n / Utilities.lg(n) * 1e6)
    };
    private final static TimeLogger[] timeLoggersQuadratic = {
            new TimeLogger("Raw time per run (mSec): ", (time, n) -> time),
            new TimeLogger("Normalized time per run (n^2): ", (time, n) -> time / n / n * 1e6)
    };

    private final int runs;
    private final Supplier<int[]> supplier;
    private final int n;
}
