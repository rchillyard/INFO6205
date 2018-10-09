/*
 * Copyright (c) 2017. Phasmid Software
 */

package edu.neu.coe.info6205.hashtable;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FrequencyCounterTest {
    @Test
    public void testIncrement0() {
        FrequencyCounter<String> fc = new FrequencyCounter<>();
        String x = "X";
        assertEquals(0, fc.get(x));
        fc.increment(x);
        assertEquals(1, fc.get(x));
    }
}
