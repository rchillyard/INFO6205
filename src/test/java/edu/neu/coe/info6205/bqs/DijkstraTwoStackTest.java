/*
 * Copyright (c) 2017. Phasmid Software
 */

package edu.neu.coe.info6205.bqs;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DijkstraTwoStackTest {

    /**
     * Test method for Stack
     */
    @Test
    public void testTwoStack() throws BQSException {
        assertEquals(1, new DijkstraTwoStack("1").evaluate());
        assertEquals(3, new DijkstraTwoStack("1 + 2").evaluate());
        assertEquals(14, new DijkstraTwoStack("2 * ( 4 + 3 )").evaluate());
        assertEquals(2, new DijkstraTwoStack("2 * ( 4 - 3 )").evaluate());
        assertEquals(101, new DijkstraTwoStack("1 + ( ( 2 + 3 ) * ( 4 * 5 ) )").evaluate());
    }

    /**
     * Test method for Stack
     */
    @Test(expected = BQSException.class)
    public void testTwoStackFail1() throws BQSException {
        new DijkstraTwoStack("").evaluate();
    }


    /**
     * Test method for Stack
     */
    @Test(expected = BQSException.class)
    public void testTwoStackFail2() throws BQSException {
        new DijkstraTwoStack("(").evaluate();
    }

    /**
     * Test method for Stack
     */
    @Test(expected = BQSException.class)
    public void testTwoStackFail3() throws BQSException {
        new DijkstraTwoStack(")").evaluate();
    }

    /**
     * Test method for Stack
     */
    @Test(expected = ArithmeticException.class)
    public void testTwoStackFail4() throws BQSException {
        new DijkstraTwoStack("1 / 0").evaluate();
    }

}