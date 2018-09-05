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
        assertEquals(2, t.invokePrivate("size"));
        assertEquals(1L, t.invokePrivate("fib", 0));
        assertEquals(1L, t.invokePrivate("fib", 1));
    }

    /**
     * Test method for extend
     */
    @Test
    public void testExtend() {
        Fibonacci z = new Fibonacci();
        PrivateMethodTester t = new PrivateMethodTester(z);
        t.invokePrivate("extend");
        assertEquals(4, t.invokePrivate("size"));
        assertEquals(2L, t.invokePrivate("fib", 2));
        assertEquals(3L, t.invokePrivate("fib", 3));
        t.invokePrivate("extend");
        assertEquals(8, t.invokePrivate("size"));
        assertEquals(5L, t.invokePrivate("fib", 4));
        assertEquals(8L, t.invokePrivate("fib", 5));
        assertEquals(13L, t.invokePrivate("fib", 6));
        assertEquals(21L, t.invokePrivate("fib", 7));

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
