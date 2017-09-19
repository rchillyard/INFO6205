/*
 * Copyright (c) 2017. Phasmid Software
 */

package edu.neu.coe.info6205.bqs;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class QueueTest {

    @Test
    public void testQueue1() {
        Queue<Integer> queue = new Queue_Elements<>();
        assertTrue(queue.isEmpty());
        queue.enqueue(1);
        assertFalse(queue.isEmpty());
        assertEquals( queue.dequeue(), new Integer(1));
    }

    @Test
    public void testQueue2() {
        Queue<Integer> queue = new Queue_Elements<>();
        assertTrue(queue.isEmpty());
        queue.enqueue(1);
        queue.enqueue(2);
        assertFalse(queue.isEmpty());
        assertEquals( queue.dequeue(), new Integer(1));
        assertEquals( queue.dequeue(), new Integer(2));
    }
}
