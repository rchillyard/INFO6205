package edu.neu.coe.info6205.util;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StatisticsTest {

    @Test
    public void testAdd() {
        final Statistics statistics = new Statistics("test", 10);
        statistics.add(-1);
        statistics.add(0);
        statistics.add(1);
        assertEquals(3, statistics.getCount());
    }

    @Test
    public void testMean() {
        final Statistics statistics = new Statistics("test", 10);
        statistics.add(-1);
        statistics.add(0);
        statistics.add(1);
        statistics.add(2);
        assertEquals(0.5, statistics.mean(), 1E-7);
    }

    @Test
    public void testStdDev() {
        final Statistics statistics = new Statistics("test", 10);
        statistics.add(-1);
        statistics.add(0);
        statistics.add(1);
        statistics.add(4);
        assertEquals(Math.sqrt(3.5), statistics.stdDev(), 1E-7);
    }
}