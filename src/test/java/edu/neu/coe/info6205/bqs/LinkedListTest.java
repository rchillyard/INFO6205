/*
 * Copyright (c) 2017. Phasmid Software
 */

package edu.neu.coe.info6205.bqs;

import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.*;

public class LinkedListTest {

    /**
     * Test method for LinkedList
     */
    @Test
    public void testLinkedList1() throws BQSException {
        LinkedList<Integer> list = new LinkedList_Elements<>();
        assertTrue(list.isEmpty());
        list.add(1);
        assertEquals(new Integer(1), list.getHead());
        assertFalse(list.isEmpty());
        assertEquals(new Integer(1), list.remove());
    }

    /**
     * Test method for LinkedList
     */
    @Test
    public void testLinkedList2() {
        LinkedList<Integer> list = new LinkedList_Elements<>();
        list.add(1);
        Iterator<Integer> iterator = list.iterator();
        assertTrue(iterator.hasNext());
        assertEquals(new Integer(1), iterator.next());
        assertFalse(iterator.hasNext());
    }

}
