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
        assertNull(queue.poll());
        assertTrue(queue.isEmpty());
    }

    @Test
    public void testQueue1() {
        Queue<Integer> queue = new Queue_Elements<>();
        assertTrue(queue.isEmpty());
        queue.offer(1);
        assertFalse(queue.isEmpty());
        assertEquals(Integer.valueOf(1), queue.poll());
        assertTrue(queue.isEmpty());
    }

    @Test
    public void testQueue2() {
        Queue<Integer> queue = new Queue_Elements<>();
        assertTrue(queue.isEmpty());
        queue.offer(1);
        queue.offer(2);
        assertFalse(queue.isEmpty());
        assertEquals(Integer.valueOf(1), queue.poll());
        assertEquals(Integer.valueOf(2), queue.poll());
        assertTrue(queue.isEmpty());
    }

    @Test
    public void testQueue3() {
        Queue<Integer> queue = new Queue_Elements<>();
        assertTrue(queue.isEmpty());
        queue.offer(1);
        queue.offer(2);
        assertFalse(queue.isEmpty());
        assertEquals(Integer.valueOf(1), queue.poll());
        queue.offer(3);
        queue.offer(4);
        assertEquals(Integer.valueOf(2), queue.poll());
        assertEquals(Integer.valueOf(3), queue.poll());
        assertEquals(Integer.valueOf(4), queue.poll());
        assertTrue(queue.isEmpty());
    }
}