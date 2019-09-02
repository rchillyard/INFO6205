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
        assertEquals(queue.dequeue(), new Integer(1));
        assertTrue(queue.isEmpty());
    }

    @Test
    public void testQueue2() {
        Queue<Integer> queue = new Queue_Elements<>();
        assertTrue(queue.isEmpty());
        queue.enqueue(1);
        queue.enqueue(2);
        assertFalse(queue.isEmpty());
        assertEquals(queue.dequeue(), new Integer(1));
        assertEquals(queue.dequeue(), new Integer(2));
        assertTrue(queue.isEmpty());
    }

    @Test
    public void testQueue3() {
        Queue<Integer> queue = new Queue_Elements<>();
        assertTrue(queue.isEmpty());
        queue.enqueue(1);
        queue.enqueue(2);
        assertFalse(queue.isEmpty());
        assertEquals(queue.dequeue(), new Integer(1));
        queue.enqueue(3);
        queue.enqueue(4);
        assertEquals(queue.dequeue(), new Integer(2));
        assertEquals(queue.dequeue(), new Integer(3));
        assertEquals(queue.dequeue(), new Integer(4));
        assertTrue(queue.isEmpty());
    }
}
