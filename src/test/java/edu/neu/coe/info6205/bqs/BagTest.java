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
        assertEquals(0, bag.size());
        assertTrue(bag.isEmpty());
        assertFalse((bag.iterator()).hasNext());
        bag.add(1);
        assertEquals(1, bag.size());
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
        assertEquals(0, bag.size());
        assertTrue(bag.isEmpty());
        assertFalse((bag.iterator()).hasNext());
        for (int i = 0; i < 32; i++)
            bag.add(i);
        bag.add(32);
        assertEquals(33, bag.size());
        assertFalse(bag.isEmpty());
        assertTrue((bag.iterator()).hasNext());
        assertEquals(new Integer(0), bag.iterator().next());
    }

    /**
     * Test method for Bag
     */
    @Test
    public void testBagIterator() {
        Bag<Integer> bag = new Bag_Array<>();
        for (int i = 1; i <= 4; i++)
            bag.add(i);
        assertEquals(4, bag.size());
        int sum = 0;
        for (Integer x : bag) sum += x;
        assertEquals(10, sum);
    }

}
