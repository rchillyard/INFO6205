/*
 * Copyright (c) 2017. Phasmid Software
 */

package edu.neu.coe.info6205.bqs;

import org.junit.Test;

import static org.junit.Assert.*;

public class StackTest {

    /**
     * Test method for Stack
     */
    @Test
    public void testStack() throws BQSException {
        Stack<Integer> stack = new Stack_LinkedList<>();
        assertTrue(stack.isEmpty());
        stack.push(1);
        assertFalse(stack.isEmpty());
        Integer item = stack.pop();
        assertEquals(Integer.valueOf(1), item);
    }

}