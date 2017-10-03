/*
 * Copyright (c) 2017. Phasmid Software
 */

package edu.neu.coe.info6205.union_find;

import edu.neu.coe.info6205.randomwalk.RandomWalk;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class HWQUPCTest {

    /**
     */
    @Test
    public void testFind0() {
        HWQUPC h = new HWQUPC(10);
//        h.show();
        assertEquals(0, h.find(0));
    }

    /**
     */
    @Test
    public void testFind1() {
        HWQUPC h = new HWQUPC(10);
        h.union(0,1);
//        h.show();
        assertEquals(0, h.find(0));
        assertEquals(0, h.find(1));
    }

    /**
     */
    @Test
    public void testFind2() {
        HWQUPC h = new HWQUPC(10);
        h.union(0,1);
//        h.show();
        assertEquals(0, h.find(0));
        assertEquals(0, h.find(1));
        h.union(2,1);
//        h.show();
        assertEquals(0, h.find(0));
        assertEquals(0, h.find(1));
        assertEquals(0, h.find(2));
    }

    /**
     */
    @Test
    public void testFind3() {
        HWQUPC h = new HWQUPC(10);
        h.union(0,1);
        h.union(0,2);
        h.union(3,4);
        h.union(3,5);
//        h.show();
        assertEquals(0, h.find(0));
        assertEquals(0, h.find(1));
        assertEquals(0, h.find(2));
        assertEquals(3, h.find(3));
        assertEquals(3, h.find(4));
        assertEquals(3, h.find(5));
        h.union(0,3);
//        h.show();
        assertEquals(0, h.find(0));
        assertEquals(0, h.find(1));
        assertEquals(0, h.find(2));
        assertEquals(0, h.find(3));
        assertEquals(0, h.find(4));
        assertEquals(0, h.find(5));
        assertEquals(3,h.getParent(4));
        assertEquals(3,h.getParent(5));
    }

    /**
     */
    @Test
    public void testFind4() {
        HWQUPC h = new HWQUPC(10);
        h.setPathCompression(true);
        h.union(0,1);
        h.union(0,2);
        h.union(3,4);
        h.union(3,5);
//        h.show();
        assertEquals(0, h.find(0));
        assertEquals(0, h.find(1));
        assertEquals(0, h.find(2));
        assertEquals(3, h.find(3));
        assertEquals(3, h.find(4));
        assertEquals(3, h.find(5));
        h.union(0,3);
//        h.show();
        assertEquals(0, h.find(0));
        assertEquals(0, h.find(1));
        assertEquals(0, h.find(2));
        assertEquals(0, h.find(3));
        assertEquals(0, h.find(4));
        assertEquals(0, h.find(5));
        assertEquals(0,h.getParent(4));
        assertEquals(0,h.getParent(5));
    }

    /**
     */
    @Test
    public void testConnected01() {
        HWQUPC h = new HWQUPC(10);
//        h.show();
        assertFalse(h.connected(0,1));
    }

}