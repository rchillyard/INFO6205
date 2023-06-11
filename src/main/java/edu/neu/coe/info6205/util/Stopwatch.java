package edu.neu.coe.info6205.util;

/**
 * Simple benchmarking tool: Stopwatch.
 * There is no warm-up here, no pause/resume and no repetition (for these use Timer).
 * It is simply a convenient way to time an execution with results in milliseconds.
 * <p>
 * Once the Stopwatch is started (i.e. constructed), you "read" the stopwatch by calling lap(),
 * which returns the number of milliseconds since the previous lap (or since the start).
 */
public class Stopwatch implements AutoCloseable {

    /**
     * Construct and start a Stopwatch.
     */
    public Stopwatch() {
        start = System.nanoTime();
    }

    /**
     * @return the number of milliseconds elapsed since the last lap (or start).
     * @throws AssertionError if this Stopwatch has been closed already.
     */
    public long lap() {
        assert start != null : "Stopwatch is closed";
        final long lapStart = start;
        start = System.nanoTime();
        return (start - lapStart) / 1000000;
    }

    /**
     * Close this Stopwatch.
     * NOTE that you should run the Stopwatch within a try-with-resource statement. See unit tests.
     */
    public void close() {
        start = null;
    }

    private Long start;
}
