/*
 * Copyright (c) 2017. Phasmid Software
 */

package edu.neu.coe.info6205.bqs;

import edu.neu.coe.info6205.Tuple;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BagTest {

    /**
     * Test method for Bag
     */
    @Test
    public void testBag() {
        Bag bag = new BagImpl<Integer>();
        assertTrue(bag.size()==0);
        assertTrue(bag.isEmpty());
        assertFalse(((Iterator<Integer>) bag.iterator()).hasNext());
        bag.add(1);
        assertTrue(bag.size()==1);
        assertFalse(bag.isEmpty());
        assertTrue(( bag.iterator()).hasNext());
        assertEquals(( bag.iterator()).next(), 1);
    }

}
