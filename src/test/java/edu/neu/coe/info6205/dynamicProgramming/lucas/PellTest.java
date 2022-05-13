package edu.neu.coe.info6205.dynamicProgramming.lucas;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PellTest {

    @Test
    public void testGet1() {
        Pell pell = new Pell();
        assertEquals(0, pell.get(0));
        assertEquals(1, pell.get(1));
        assertEquals(2, pell.get(2));
        assertEquals(5, pell.get(3));
        assertEquals(12, pell.get(4));
        assertEquals(29, pell.get(5));
        assertEquals(70, pell.get(6));
        assertEquals(169, pell.get(7));
        assertEquals(408, pell.get(8));
        assertEquals(985, pell.get(9));
        assertEquals(2378, pell.get(10));
        assertEquals(5741, pell.get(11));
    }
    @Test
    public void testGet2() {
        assertEquals(0, new Pell().get(0));
        assertEquals(1, new Pell().get(1));
        assertEquals(2, new Pell().get(2));
        assertEquals(5, new Pell().get(3));
        assertEquals(124145519261542L, new Pell().get(38));
    }
    @Test
    public void testGet3() {
        assertEquals(4866752642924153522L, new Pell().get(50));
    }
    @Test
    public void testGet4() {
        assertEquals(7052354271195710746L, new Pell().get(90));
    }
}