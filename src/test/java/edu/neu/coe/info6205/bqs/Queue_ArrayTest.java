package edu.neu.coe.info6205.bqs;

import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.*;

public class Queue_ArrayTest {

    @Test
    public void poll() {
        Queue<Integer> queue = new Queue_Array<>(7);
        assertNull(queue.poll());
        queue.offer(1);
        Object object = queue.poll();
        assertEquals(1, object);
    }

    @Test
    public void isEmpty() {
        Queue<Integer> queue = new Queue_Array<>(7);
        assertTrue(queue.isEmpty());
        queue.offer(1);
        assertFalse(queue.isEmpty());
    }

    @Test
    public void iterator() {
        Integer[] Integers = new Integer[7];
        Queue_Array<Integer> queue = new Queue_Array<>(Integers, 5, 5);
        queue.show();
        queue.offer(1);
        queue.show();
        queue.offer(2);
        queue.show();
        queue.offer(3);
        queue.show();
        queue.offer(4);
        queue.show();
        Iterator<Integer> iterator = queue.iterator();
        assertTrue(iterator.hasNext());
        assertEquals(Integer.valueOf(1), iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals(Integer.valueOf(2), iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals(Integer.valueOf(3), iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals(Integer.valueOf(4), iterator.next());
        assertFalse(iterator.hasNext());
    }

    @Test
    public void size() {
        Queue_Array<Integer> queue = new Queue_Array<>(7);
        assertEquals(0, queue.size());
        queue.offer(1);
        assertEquals(1, queue.size());
    }

    @Test
    public void offer0() {
        Integer[] Integers = new Integer[7];
        Queue_Array<Integer> queue = new Queue_Array<>(Integers, 5, 5);
        queue.offer(1);
        queue.offer(2);
        queue.offer(3);
        queue.offer(4);
        queue.offer(5);
        queue.offer(6);
        queue.show();
        queue.offer(7);
        queue.show();
        Object[] array = queue.asArray();
        assertArrayEquals(new Object[]{1, 2, 3, 4, 5, 6, 7}, array);
    }

    @Test
    public void offer1() {
        Queue_Array<Integer> queue = new Queue_Array<>(7);
        queue.offer(1);
        queue.offer(2);
        queue.offer(3);
        queue.offer(4);
        queue.offer(5);
        queue.offer(6);
        queue.offer(7);
        queue.offer(8);
        queue.show();
        assertEquals(8, queue.size());
        Object[] array = queue.asArray();
        assertArrayEquals(new Object[]{1, 2, 3, 4, 5, 6, 7, 8}, array);
    }

    @Test
    public void offer2() {
        Integer[] Integers = new Integer[7];
        Queue_Array<Integer> queue = new Queue_Array<>(Integers, 5, 5);
        queue.offer(1);
        queue.offer(2);
        queue.offer(3);
        queue.offer(4);
        queue.offer(5);
        queue.offer(6);
        queue.offer(7);
        queue.offer(8);
        queue.show();
        assertEquals(8, queue.size());
        Object[] array = queue.asArray();
        assertArrayEquals(new Object[]{1, 2, 3, 4, 5, 6, 7, 8}, array);
    }

    @Test
    public void asArray0() {
        Queue_Array<Integer> queue = new Queue_Array<>(7);
        assertEquals(0, queue.size());
        Object[] array = queue.asArray();
        assertArrayEquals(new Object[0], array);
    }

    @Test
    public void asArray1() {
        Integer[] Integers = new Integer[7];
        Queue_Array<Integer> queue = new Queue_Array<>(Integers, 5, 5);
        queue.offer(1);
        queue.offer(2);
        queue.offer(3);
        queue.offer(4);
        Object[] array = queue.asArray();
        assertArrayEquals(new Object[]{1, 2, 3, 4}, array);
    }
}