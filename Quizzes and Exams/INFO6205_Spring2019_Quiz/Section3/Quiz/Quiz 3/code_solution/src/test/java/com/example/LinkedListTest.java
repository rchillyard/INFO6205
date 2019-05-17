package com.example;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.*;

public class LinkedListTest {

    @Test
    public void testAddLinkedList() {
        LinkedList<Integer> list = new LinkedList<Integer>();
        assertTrue(list.isEmpty());
        list.add(1);
        assertEquals(new Integer(1), list.getHead());
    }

    @Test
    public void testAddRemoveLinkedList() throws Exception {
        LinkedList<Integer> list = new LinkedList<Integer>();
        list.add(1);
        assertEquals(new Integer(1), list.getHead());
        assertEquals(new Integer(1), list.remove());
        assertTrue(list.isEmpty());
    }

    @Test
    public void testRetrieveLinkedList() throws Exception {
        LinkedList<Integer> list = new LinkedList<Integer>();
        list.add(1);
        list.add(2);
        list.add(3);
        assertFalse(list.isEmpty());
        assertEquals(new Integer(3), list.getNthElement(0));
        assertEquals(new Integer(2), list.getNthElement(1));
        assertEquals(new Integer(1), list.getNthElement(2));
    }
}