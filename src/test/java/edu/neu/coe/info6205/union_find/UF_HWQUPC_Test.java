/*
 * Copyright (c) 2017. Phasmid Software
 */

package edu.neu.coe.info6205.union_find;

import edu.neu.coe.info6205.util.PrivateMethodTester;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class UFHWQUPCTest {

    /**
     */
    @Test
    public void testFind0() {
        UF h = new Connections_HWQUPC(10);
//        h.show();
        assertEquals(0, h.find(0));
    }

    /**
     */
    @Test
    public void testFind1() {
        Connections_HWQUPC h = new Connections_HWQUPC(10);
        h.connect(0,1);
//        h.show();
        assertEquals(0, h.find(0));
        assertEquals(0, h.find(1));
    }

    /**
     */
    @Test
    public void testFind2() {
        Connections_HWQUPC h = new Connections_HWQUPC(10);
//        h.show();
        h.connect(0,1);
//        h.show();
        assertEquals(0, h.find(0));
        assertEquals(0, h.find(1));
        h.connect(2,1);
//        h.show();
        assertEquals(0, h.find(0));
        assertEquals(0, h.find(1));
        assertEquals(0, h.find(2));
    }

    /**
     */
    @Test
    public void testFind3() {
        Connections_HWQUPC h = new Connections_HWQUPC(10);
        h.connect(0,1);
        h.connect(0,2);
        h.connect(3,4);
        h.connect(3,5);
//        h.show();
        assertEquals(0, h.find(0));
        assertEquals(0, h.find(1));
        assertEquals(0, h.find(2));
        assertEquals(3, h.find(3));
        assertEquals(3, h.find(4));
        assertEquals(3, h.find(5));
        h.connect(0,3);
//        h.show();
        assertEquals(0, h.find(0));
        assertEquals(0, h.find(1));
        assertEquals(0, h.find(2));
        assertEquals(0, h.find(3));
        assertEquals(0, h.find(4));
        assertEquals(0, h.find(5));
        final PrivateMethodTester tester = new PrivateMethodTester(h);
        assertEquals(3, tester.invokePrivate("getParent", 4));
        assertEquals(3, tester.invokePrivate("getParent", 5));
    }

    /**
     */
    @Test
    public void testFind4() {
        Connections_HWQUPC h = new Connections_HWQUPC(10);
        h.setPathCompression(true);
        h.connect(0,1);
        h.connect(0,2);
        h.connect(3,4);
        h.connect(3,5);
//        h.show();
        assertEquals(0, h.find(0));
        assertEquals(0, h.find(1));
        assertEquals(0, h.find(2));
        assertEquals(3, h.find(3));
        assertEquals(3, h.find(4));
        assertEquals(3, h.find(5));
        h.connect(0,3);
//        h.show();
        assertEquals(0, h.find(0));
        assertEquals(0, h.find(1));
        assertEquals(0, h.find(2));
        assertEquals(0, h.find(3));
        assertEquals(0, h.find(4));
        assertEquals(0, h.find(5));
        final PrivateMethodTester tester = new PrivateMethodTester(h);
        assertEquals(0, tester.invokePrivate("getParent", 4));
        assertEquals(0, tester.invokePrivate("getParent", 5));
    }

    /**
     */
    @Test
    public void testConnected01() {
        Connections h = new Connections_HWQUPC(10);
//        h.show();
        assertFalse(h.isConnected(0,1));
    }

}