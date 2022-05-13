package edu.neu.coe.info6205.util;

import edu.neu.coe.info6205.sort.elementary.InsertionSort;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class SorterBenchmarkTest {

    @Before
    public void setUp() throws IOException {
        String[] strings = {"Hello", "Goodbye", "Ciao", "Willkommen"};
        benchmark = new SorterBenchmark<>(String.class, new InsertionSort<>(Config.load(getClass())), strings, 100, new TimeLogger[]{new TimeLogger("test", (x, n) -> x / n)});
    }

    @SuppressWarnings("EmptyMethod")
    @After
    public void tearDown() {
        // NOTE: Nothing to do.
    }

    @Test
    public void run() {
        benchmark.run(4);
    }

    SorterBenchmark<String> benchmark = null;

}