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
        assertEquals(Integer.valueOf(1), list.getHead());
        assertFalse(list.isEmpty());
        assertEquals(Integer.valueOf(1), list.remove());
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
        assertEquals(Integer.valueOf(1), iterator.next());
        assertFalse(iterator.hasNext());
    }

    /**
     * Test method for LinkedList
     */
    @Test
    public void testEquals() {
        LinkedList<Integer> list1 = new LinkedList_Elements<>();
        list1.add(1);
        LinkedList<Integer> list2 = new LinkedList_Elements<>();
        list2.add(1);
        assertEquals(list1, list2);
    }

    /**
     * Test method for LinkedList
     */
    @Test
    public void testNotEquals() {
        LinkedList<Integer> list1 = new LinkedList_Elements<>();
        list1.add(1);
        LinkedList<Integer> list2 = new LinkedList_Elements<>();
        list2.add(1);
        list2.add(2);
        assertNotEquals(list1, list2);
    }

    /**
     * Test method for LinkedList
     */
    @Test
    public void testHashCode() {
        LinkedList<Integer> list1 = new LinkedList_Elements<>();
        list1.add(1);
        LinkedList<Integer> list2 = new LinkedList_Elements<>();
        list2.add(1);
        assertEquals(list1.hashCode(), list2.hashCode());
    }

}
