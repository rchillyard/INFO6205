/*
 * Copyright (c) 2017. Phasmid Software
 */

package edu.neu.coe.info6205.bqs;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StackTest {

    /**
     * Test method for Bag
     */
    @Test
    public void testStack() {
        Stack<Integer> stack = new Stack_LinkedList<Integer>();
        assertTrue(stack.isEmpty());
        stack.push(1);
        assertFalse(stack.isEmpty());
        assertEquals( stack.pop(), new Integer(1));
    }

}
