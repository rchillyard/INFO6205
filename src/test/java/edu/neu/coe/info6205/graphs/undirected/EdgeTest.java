/*
 * Copyright (c) 2017. Phasmid Software
 */

package edu.neu.coe.info6205.graphs.undirected;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class EdgeTest {

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

}
