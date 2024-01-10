/*
 * Copyright (c) 2017. Phasmid Software
 */

package edu.neu.coe.info6205.graphs.undirected;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class EdgeTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    /**
     * Test method for Edge
     */
    @Test
    public void testEdge() {
        Edge<Integer, String> target = new Edge<>(1, 2, "hello");
        assertNotNull(target);
        final Integer v = target.get();
        assertEquals(Integer.valueOf(1), v);
        final Integer other = target.getOther(v);
        assertEquals(Integer.valueOf(2), other);
        assertEquals(Integer.valueOf(1), target.getOther(other));
        assertEquals("hello", target.getAttribute());
    }

    @Test
    public void equalsTest() {
        Edge<Integer, String> target1 = new Edge<>(1, 2, "hello");
        Edge<Integer, String> target2 = new Edge<>(2, 1, "hello");
        assertTrue(target1.equals(target2));
    }

    @Test
    public void hashCodeTest() {
    }

    @Test
    public void toStringTest() {
    }
}