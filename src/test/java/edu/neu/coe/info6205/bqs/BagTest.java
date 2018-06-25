/*
 * Copyright (c) 2017. Phasmid Software
 */

package edu.neu.coe.info6205.bqs;

import org.junit.Test;

import static org.junit.Assert.*;

public class BagTest {

    /**
     * Test method for Bag
     */
    @Test
    public void testBagAdd1() {
        Bag<Integer> bag = new Bag_Array<>();
        assertTrue(bag.size() == 0);
        assertTrue(bag.isEmpty());
        assertFalse((bag.iterator()).hasNext());
        bag.add(1);
        assertTrue(bag.size() == 1);
        assertFalse(bag.isEmpty());
        assertTrue((bag.iterator()).hasNext());
        assertEquals(new Integer(1), bag.iterator().next());
    }

    /**
     * Test method for Bag
     */
    @Test
    public void testBagAdd2() {
        Bag<Integer> bag = new Bag_Array<>();
        assertTrue(bag.size() == 0);
        assertTrue(bag.isEmpty());
        assertFalse((bag.iterator()).hasNext());
        for (int i = 0; i < 32; i++)
            bag.add(i);
        bag.add(32);
        assertTrue(bag.size() == 33);
        assertFalse(bag.isEmpty());
        assertTrue((bag.iterator()).hasNext());
        assertEquals(new Integer(0), bag.iterator().next());
    }

}
