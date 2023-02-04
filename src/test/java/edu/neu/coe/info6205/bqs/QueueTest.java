/*
 * Copyright (c) 2017. Phasmid Software
 */

package edu.neu.coe.info6205.bqs;

import org.junit.Test;

import static org.junit.Assert.*;

public class QueueTest {

    @Test
    public void testQueue0() {
        Queue<Integer> queue = new Queue_Elements<>();
        assertTrue(queue.isEmpty());
        assertNull(queue.dequeue());
        assertTrue(queue.isEmpty());
    }

    @Test
    public void testQueue1() {
        Queue<Integer> queue = new Queue_Elements<>();
        assertTrue(queue.isEmpty());
        queue.enqueue(1);
        assertFalse(queue.isEmpty());
        assertEquals(Integer.valueOf(1), queue.dequeue());
        assertTrue(queue.isEmpty());
    }

    @Test
    public void testQueue2() {
        Queue<Integer> queue = new Queue_Elements<>();
        assertTrue(queue.isEmpty());
        queue.enqueue(1);
        queue.enqueue(2);
        assertFalse(queue.isEmpty());
        assertEquals(Integer.valueOf(1), queue.dequeue());
        assertEquals(Integer.valueOf(2), queue.dequeue());
        assertTrue(queue.isEmpty());
    }

    @Test
    public void testQueue3() {
        Queue<Integer> queue = new Queue_Elements<>();
        assertTrue(queue.isEmpty());
        queue.enqueue(1);
        queue.enqueue(2);
        assertFalse(queue.isEmpty());
        assertEquals(Integer.valueOf(1), queue.dequeue());
        queue.enqueue(3);
        queue.enqueue(4);
        assertEquals(Integer.valueOf(2), queue.dequeue());
        assertEquals(Integer.valueOf(3), queue.dequeue());
        assertEquals(Integer.valueOf(4), queue.dequeue());
        assertTrue(queue.isEmpty());
    }
}
