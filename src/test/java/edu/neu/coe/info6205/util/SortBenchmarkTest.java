package edu.neu.coe.info6205.util;

import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;

import static org.junit.Assert.*;

public class SortBenchmarkTest {

    @Test
    public void doMain() throws IOException {
        Config config = Config.load(SortBenchmark.class);
        new SortBenchmark(config).doMain(new String[]{"1000"});
    }

    @Test
    public void doIntegerSorts() {
    }

    @Test
    public void sortLocalDateTimes() {
    }

    @Test
    public void benchmarkStringSorters() {
    }

    @Test
    public void benchmarkStringSortersInstrumented() {
    }

    @Test
    public void runStringSortBenchmark() {
    }

    @Test
    public void testRunStringSortBenchmark() {
    }

    @Test
    public void runIntegerSortBenchmark() {
    }

    @Test
    public void minComparisons() {
    }

    @Test
    public void meanInversions() {
    }

    @Test
    public void getLeipzigWords() {
    }
}