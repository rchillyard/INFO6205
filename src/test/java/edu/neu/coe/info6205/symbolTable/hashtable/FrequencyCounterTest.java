/*
 * Copyright (c) 2017. Phasmid Software
 */

package edu.neu.coe.info6205.symbolTable.hashtable;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class FrequencyCounterTest {
    @Test
    public void testIncrement0() {
        FrequencyCounter<String> fc = new FrequencyCounter<>();
        String x = "X";
        assertEquals(0, fc.get(x).intValue());
        fc.increment(x);
        assertEquals(1, fc.get(x).intValue());
    }

    @Test
    public void relativeFrequency() {
        FrequencyCounter<String> fc = new FrequencyCounter<>();
        String x = "X";
        for (int i = 0; i < 42; i++) fc.increment(x);
        assertEquals(1.0, fc.relativeFrequency(x), 0.0000001);
        assertEquals(0.0, fc.relativeFrequency("y"), 0.0000001);
    }

    @Test
    public void relativeFrequencyAsPercentage() {
        FrequencyCounter<String> fc = new FrequencyCounter<>();
        for (int i = 0; i < 49; i++) fc.increment("X");
        fc.increment("Y");
        assertEquals(98.0, fc.relativeFrequencyAsPercentage("X"), 0.0000001);
        assertEquals(2.0, fc.relativeFrequencyAsPercentage("Y"), 0.0000001);
        assertEquals(0.0, fc.relativeFrequencyAsPercentage("Z"), 0.0000001);
    }

    @Test
    public void keys() {
        FrequencyCounter<String> fc = new FrequencyCounter<>();
        assertArrayEquals(new String[]{}, fc.keys().toArray());
        fc.increment("X");
        assertArrayEquals(new String[]{"X"}, fc.keys().toArray());
        fc.increment("Y");
        assertArrayEquals(new String[]{"X", "Y"}, fc.keys().toArray());
    }

    @Test
    public void total() {
        FrequencyCounter<String> fc = new FrequencyCounter<>();
        String x = "X";
        assertEquals(0, fc.total());
        for (int i = 0; i < 42; i++) fc.increment(x);
        assertEquals(42, fc.total());
    }
}