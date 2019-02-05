/*
 * Copyright (c) 2017. Phasmid Software
 */

package edu.neu.coe.info6205.graphs.dag;

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
        assertEquals(Integer.valueOf(1), target.getFrom());
        assertEquals(Integer.valueOf(2), target.getTo());
    }

    /**
     * Test method for Edge
     */
    @Test
    public void testEdgeReverse() {
        Edge<Integer, String> target = new Edge<>(1, 2, "hello");
        Edge<Integer, String> reverse = target.reverse();
        assertNotNull(reverse);
        assertEquals(Integer.valueOf(2), reverse.getFrom());
        assertEquals(Integer.valueOf(1), reverse.getTo());
    }

}
