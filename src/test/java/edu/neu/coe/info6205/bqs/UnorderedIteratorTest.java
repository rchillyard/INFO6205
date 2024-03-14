package edu.neu.coe.info6205.bqs;

import com.google.common.collect.ImmutableList;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;

import static org.junit.Assert.*;

public class UnorderedIteratorTest {

    @Test
    public void testEmptyList() {
        Iterator<Integer> target = UnorderedIterator.createDeterministic(new ArrayList<>(), 0L);
        assertFalse(target.hasNext());
    }

    @Test
    public void testList1() {
        ArrayList<Integer> list = new ArrayList<>(ImmutableList.of(1, 2, 3));
        Iterator<Integer> target = UnorderedIterator.createDeterministic(list, 0L);
        assertTrue(target.hasNext());
        assertEquals(Integer.valueOf(1), target.next());
        assertTrue(target.hasNext());
        assertEquals(Integer.valueOf(3), target.next());
        assertTrue(target.hasNext());
        assertEquals(Integer.valueOf(2), target.next());
        assertFalse(target.hasNext());
    }

    @Test
    public void testList2() {
        ArrayList<Integer> list = new ArrayList<>(ImmutableList.of(1, 2, 3));
        Iterator<Integer> target = UnorderedIterator.createDeterministic(list, 2L);
        assertTrue(target.hasNext());
        assertEquals(Integer.valueOf(2), target.next());
        assertTrue(target.hasNext());
        assertEquals(Integer.valueOf(1), target.next());
        assertTrue(target.hasNext());
        assertEquals(Integer.valueOf(3), target.next());
        assertFalse(target.hasNext());
    }

    @Test
    public void testArray() {
        Integer[] array = new Integer[]{1, 2, 3};
        Iterator<Integer> target = new UnorderedIterator<>(array);
        target.next();
        target.next();
        target.next();
        assertFalse(target.hasNext());
    }
}