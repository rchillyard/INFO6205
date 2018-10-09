package edu.neu.coe.info6205.greedy;

import edu.neu.coe.info6205.util.PrivateMethodTester;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FibonacciTest {
    /**
     * Test method for Fibonacci
     */
    @Test
    public void testZeckendorf() {
        Fibonacci z = new Fibonacci();
        PrivateMethodTester t = new PrivateMethodTester(z);
        assertEquals("size should be 2", 2, t.invokePrivate("size"));
        assertEquals("fib(0) should be 1", 1L, t.invokePrivate("fib", 0));
        assertEquals("fib(1) should be 1", 1L, t.invokePrivate("fib", 1));
    }

    /**
     * Test method for extend
     */
    @Test
    public void testExtend() {
        Fibonacci z = new Fibonacci();
        PrivateMethodTester t = new PrivateMethodTester(z);
        t.invokePrivate("extend");
        int size = 4;
        assertEquals("after 1 extend, size should be " + size, size, t.invokePrivate("size"));
        assertEquals("fib(2) should be 2", 2L, t.invokePrivate("fib", 2));
        assertEquals("fib(3) should be 3", 3L, t.invokePrivate("fib", 3));
        t.invokePrivate("extend");
        size = 8;
        assertEquals("after 2 extends, size should be " + size, size, t.invokePrivate("size"));
        assertEquals("fib(4) should be 5", 5L, t.invokePrivate("fib", 4));
        assertEquals("fib(5) should be 8", 8L, t.invokePrivate("fib", 5));
        assertEquals("fib(6) should be 13", 13L, t.invokePrivate("fib", 6));
        assertEquals("fib(7) should be 21", 21L, t.invokePrivate("fib", 7));
    }

    /**
     * Test method for calculateFibonacci
     */
    @Test
    public void testCalculateFibonacci() {
        Fibonacci z = new Fibonacci();
        PrivateMethodTester t = new PrivateMethodTester(z);
        t.invokePrivate("ensure", 5L);
        assertEquals(2L, t.invokePrivate("calculate", 2));
        assertEquals(3L, t.invokePrivate("calculate", 3));
        assertEquals(5L, t.invokePrivate("calculate", 4));
        assertEquals(8L, t.invokePrivate("calculate", 5));
    }

    /**
     * Test method for ensure
     */
    @Test
    public void testEnsureFibonacci() {
        Fibonacci z = new Fibonacci();
        PrivateMethodTester t = new PrivateMethodTester(z);
        t.invokePrivate("ensure", 1L);
        assertEquals(2, t.invokePrivate("size"));
        t.invokePrivate("ensure", 2L);
        assertEquals(4, t.invokePrivate("size"));
        t.invokePrivate("ensure", 3L);
        assertEquals(4, t.invokePrivate("size"));
        t.invokePrivate("ensure", 4L);
        assertEquals(8, t.invokePrivate("size"));
        t.invokePrivate("ensure", 5L);
        assertEquals(8, t.invokePrivate("size"));
    }

    /**
     * Test method for getLargest
     */
    @Test
    public void testGetLargestFibonacci() {
        Fibonacci z = new Fibonacci();
        PrivateMethodTester t = new PrivateMethodTester(z);
        assertEquals(1L, t.invokePrivate("getLargest", 1L));
        t.invokePrivate("ensure", 2L);
        assertEquals(2L, t.invokePrivate("getLargest", 2L));
        assertEquals(3L, t.invokePrivate("getLargest", 4L));
        t.invokePrivate("ensure", 5L);
        assertEquals(5L, t.invokePrivate("getLargest", 6L));
    }

}
