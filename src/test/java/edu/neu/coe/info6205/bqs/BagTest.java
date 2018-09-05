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
    public void testBag1() {
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
    public void testBag2() {
        Bag<Integer> bag = new Bag_Array<>();
        System.out.println(bag);
        bag.add(1);
        bag.add(1);
        System.out.println(bag);
        assertTrue(bag.size() == 2);
    }


}
