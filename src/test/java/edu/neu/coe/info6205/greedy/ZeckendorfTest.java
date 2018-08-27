package edu.neu.coe.info6205.greedy;

import edu.neu.coe.info6205.util.PrivateMethodTester;
import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class ZeckendorfTest {
    /**
     * Test method for Zeckendorf
     */
    @Test
    public void testZeckendorf() {
        Zeckendorf z = new Zeckendorf();
        PrivateMethodTester t = new PrivateMethodTester(z);
        assertEquals(2, t.invokePrivate("size"));
        assertEquals(1L, t.invokePrivate("fib", 0));
        assertEquals(1L, t.invokePrivate("fib", 1));
    }

    /**
     * Test method for extendFibonacci
     */
    @Test
    public void testExtendFibonacci() {
        Zeckendorf z = new Zeckendorf();
        PrivateMethodTester t = new PrivateMethodTester(z);
        t.invokePrivate("extendFibonacci");
        assertEquals(4, t.invokePrivate("size"));
        assertEquals(2L, t.invokePrivate("fib", 2));
        assertEquals(3L, t.invokePrivate("fib", 3));
        t.invokePrivate("extendFibonacci");
        assertEquals(8, t.invokePrivate("size"));
        assertEquals(5L, t.invokePrivate("fib", 4));
        assertEquals(8L, t.invokePrivate("fib", 5));
        assertEquals(13L, t.invokePrivate("fib", 6));
        assertEquals(21L, t.invokePrivate("fib", 7));

    }

    /**
     * Test method for getLargestFibonacci
     */
    @Test
    public void testGetLargestFibonacci() {
        Zeckendorf z = new Zeckendorf();
        PrivateMethodTester t = new PrivateMethodTester(z);
        assertEquals(1L, t.invokePrivate("getLargestFibonacci", 1L));
        assertEquals(2L, t.invokePrivate("getLargestFibonacci", 2L));
        assertEquals(3L, t.invokePrivate("getLargestFibonacci", 4L));
        assertEquals(5L, t.invokePrivate("getLargestFibonacci", 6L));
    }

    /**
     * Test method for get
     */
    @Test
    public void testGet1() {
        Zeckendorf z = new Zeckendorf();
        Iterator<Long> fibs = z.get(10).iterator();
        assertEquals(new Long(8), fibs.next());
        assertEquals(new Long(2), fibs.next());
        assertFalse(fibs.hasNext());
    }

    /**
     * Test method for get
     */
    @Test
    public void testGet2() {
        Zeckendorf z = new Zeckendorf();
        Iterator<Long> fibs = z.get(100).iterator();
        assertEquals(new Long(89), fibs.next());
        assertEquals(new Long(8), fibs.next());
        assertEquals(new Long(3), fibs.next());
        assertFalse(fibs.hasNext());
    }

    /**
     * Test method for get
     */
    @Test
    public void testGet3() {
        Zeckendorf z = new Zeckendorf();
        Iterator<Long> fibs = z.get(1000).iterator();
        assertEquals(new Long(987), fibs.next());
        assertEquals(new Long(13), fibs.next());
        assertFalse(fibs.hasNext());
    }

    /**
     * Test method for get
     */
    @Test
    public void testGet4() {
        Zeckendorf z = new Zeckendorf();
        Iterator<Long> fibs = z.get(10000).iterator();
        assertEquals(new Long(6765), fibs.next());
        assertEquals(new Long(2584), fibs.next());
        assertEquals(new Long(610), fibs.next());
        assertEquals(new Long(34), fibs.next());
        assertEquals(new Long(5), fibs.next());
        assertEquals(new Long(2), fibs.next());
        assertFalse(fibs.hasNext());
    }

}
