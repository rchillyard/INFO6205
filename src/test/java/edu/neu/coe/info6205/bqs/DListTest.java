/*
 * Copyright (c) 2017. Phasmid Software
 */

package edu.neu.coe.info6205.bqs;

import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.*;


public class DListTest {

    @Test
    public void testDList0() {
        DList<Integer> list = new DList<>();
        assertTrue(list.isEmpty());
        assertEquals(list.size(), 0);
        assertFalse(list.iterator().hasNext());
        assertTrue(list.isEmpty());
    }

    @Test
    public void testDList1() {
        DList<Integer> list = new DList<>();
        list.addBeforeElement(1, null);
        assertFalse(list.isEmpty());
        assertEquals(list.size(), 1);
        Iterator<Integer> items = list.iterator();
        assertTrue(items.hasNext());
        assertEquals(Integer.valueOf(1), items.next());
    }

    @Test
    public void testDList2() {
        DList<Integer> list = new DList<>(1);
        assertFalse(list.isEmpty());
        assertEquals(list.size(), 1);
        Iterator<Integer> items = list.iterator();
        assertTrue(items.hasNext());
        assertEquals(Integer.valueOf(1), items.next());
    }

    @Test
    public void testDList3() {
        DList<Integer> list = new DList<>();
        list.addBeforeElement(1, null);
        assertFalse(list.isEmpty());
        assertEquals(list.toString(), "1, ");
        DList<Integer>.D_Element first = list.findFirst(1);
        assertEquals(Integer.valueOf(1), first.item);
        list.remove(first);
        assertEquals(list.size(), 0);
    }

    @Test
    public void testDList4() throws BQSException {
        DList<Integer> list = new DList<>(1);
        list.remove(1);
        assertTrue(list.isEmpty());
    }

    @Test
    public void testDList5() {
        DList<Integer> list = new DList<>();
        list.addBeforeElement(1, null);
        assertFalse(list.isEmpty());
        DList<Integer>.D_Element first = list.findFirst(1);
        assertEquals(Integer.valueOf(1), first.item);
        list.addAfterElement(2, first);
        assertEquals(list.toString(), "1, 2, ");
        assertEquals(list.size(), 2);
    }

    @Test
    public void testDList6() {
        DList<Integer> list = new DList<>();
        list.addBeforeElement(1, null);
        assertFalse(list.isEmpty());
        DList<Integer>.D_Element first = list.findLast(1);
        assertEquals(Integer.valueOf(1), first.item);
        list.addAfterElement(2, first);
        assertEquals(list.toString(), "1, 2, ");
        assertEquals(list.size(), 2);
    }

    @Test
    public void testDList7() throws BQSException {
        DList<Integer> list = new DList<>(1);
        list.addAfter(2, 1);
        assertEquals(list.toString(), "1, 2, ");
        assertEquals(list.size(), 2);
    }
}
