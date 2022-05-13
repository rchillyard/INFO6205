package edu.neu.coe.info6205.dynamicProgramming.lucas;

import org.junit.Test;

import static org.junit.Assert.*;

public class FibonacciTest {

    @Test
    public void testGet1() {
        Fibonacci fibonacci = new Fibonacci();
        assertEquals(1, fibonacci.get(0));
        assertEquals(1, fibonacci.get(1));
        assertEquals(2, fibonacci.get(2));
        assertEquals(3, fibonacci.get(3));
        assertEquals(5, fibonacci.get(4));
        assertEquals(8, fibonacci.get(5));
        assertEquals(13, fibonacci.get(6));
        assertEquals(21, fibonacci.get(7));
    }

    @Test
    public void testGet2() {
        assertEquals(1, new Fibonacci().get(0));
        assertEquals(1, new Fibonacci().get(1));
        assertEquals(2, new Fibonacci().get(2));
        assertEquals(3, new Fibonacci().get(3));
        assertEquals(5, new Fibonacci().get(4));
        assertEquals(8, new Fibonacci().get(5));
        assertEquals(13, new Fibonacci().get(6));
        assertEquals(21, new Fibonacci().get(7));
    }
}