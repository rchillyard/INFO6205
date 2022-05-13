/*
 * Copyright (c) 2017. Phasmid Software
 */

package edu.neu.coe.info6205.union_find;

import edu.neu.coe.info6205.util.PrivateMethodTester;
import org.junit.Test;

import static org.junit.Assert.*;

public class UF_HWQUPC_Test {

    @Test
    public void testToString() {
        Connections h = new UF_HWQUPC(2);
        assertEquals("UF_HWQUPC:\n" +
                "  count: 2\n" +
                "  path compression? true\n" +
                "  parents: [0, 1]\n" +
                "  heights: [1, 1]", h.toString());
    }

    /**
     *
     */
    @Test
    public void testIsConnected01() {
        Connections h = new UF_HWQUPC(2);
        assertFalse(h.isConnected(0, 1));
    }

    /**
     *
     */
    @Test(expected = IllegalArgumentException.class)
    public void testIsConnected02() {
        Connections h = new UF_HWQUPC(1);
        assertTrue(h.isConnected(0, 1));
    }

    /**
     *
     */
    @Test
    public void testIsConnected03() {
        Connections h = new UF_HWQUPC(2);
        final PrivateMethodTester tester = new PrivateMethodTester(h);
        assertNull(tester.invokePrivate("updateParent", 0, 1));
        assertTrue(h.isConnected(0, 1));
    }

    /**
     *
     */
    @Test
    public void testConnect01() {
        Connections h = new UF_HWQUPC(2);
        h.connect(0, 1);
    }

    /**
     *
     */
    @Test
    public void testConnect02() {
        Connections h = new UF_HWQUPC(2);
        h.connect(0, 1);
        h.connect(0, 1);
        assertTrue(h.isConnected(0, 1));
    }

    /**
     *
     */
    @Test
    public void testFind0() {
        UF h = new UF_HWQUPC(1);
        assertEquals(0, h.find(0));
    }

    /**
     *
     */
    @Test
    public void testFind1() {
        UF h = new UF_HWQUPC(2);
        h.connect(0, 1);
        assertEquals(0, h.find(0));
        assertEquals(0, h.find(1));
    }

    /**
     *
     */
    @Test
    public void testFind2() {
        UF h = new UF_HWQUPC(3, false);
        h.connect(0, 1);
        assertEquals(0, h.find(0));
        assertEquals(0, h.find(1));
        h.connect(2, 1);
        assertEquals(0, h.find(0));
        assertEquals(0, h.find(1));
        assertEquals(0, h.find(2));
    }

    /**
     *
     */
    @Test
    public void testFind3() {
        UF h = new UF_HWQUPC(6, false);
        h.connect(0, 1);
        h.connect(0, 2);
        h.connect(3, 4);
        h.connect(3, 5);
        assertEquals(0, h.find(0));
        assertEquals(0, h.find(1));
        assertEquals(0, h.find(2));
        assertEquals(3, h.find(3));
        assertEquals(3, h.find(4));
        assertEquals(3, h.find(5));
        h.connect(0, 3);
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
     *
     */
    @Test
    public void testFind4() {
        UF h = new UF_HWQUPC(6);
        h.connect(0, 1);
        h.connect(0, 2);
        h.connect(3, 4);
        h.connect(3, 5);
        assertEquals(0, h.find(0));
        assertEquals(0, h.find(1));
        assertEquals(0, h.find(2));
        assertEquals(3, h.find(3));
        assertEquals(3, h.find(4));
        assertEquals(3, h.find(5));
        h.connect(0, 3);
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
     *
     */
    @Test(expected = IllegalArgumentException.class)
    public void testFind5() {
        UF h = new UF_HWQUPC(1);
        h.find(1);
    }


    /**
     *
     */
    @Test
    public void testConnected01() {
        Connections h = new UF_HWQUPC(10);
//        h.show();
        assertFalse(h.isConnected(0, 1));
    }
}