package edu.neu.coe.info6205.greedy;

import edu.neu.coe.info6205.util.PrivateMethodTester;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class GreedyTest {

    /**
     * Class to model Zeckendorf's theorem.
     * See also Zeckendorf.java in the main tree.
     * This version of Zeckendorf uses the Greedy class.
     */
    @SuppressWarnings("Duplicates")
    class GreedyZ {

        GreedyZ() {
            fibonacci = new long[2];
            fibonacci[0] = 1;
            fibonacci[1] = 1;
            greedy = new Greedy<>(
                    this::getLargestFibonacci,
                    (Long l1, Long l2) -> l1 - l2,
                    this::concatenate,
                    (Long l) -> l <= 0
            );
        }

        /**
         * Method to run the greedy algorithm defined by greedy.
         *
         * @param x the value for which we want the Zeckendorf representation.
         * @return the Zeckendorf representation.
         */
        ArrayList<Long> run(Long x) {
            ensureFibonacci(x);
            return greedy.run(x, new ArrayList<>());
        }

        // This is the definition of the Fibonacci series.
        // NOTE: caller must ensure that 2 <= i <= fibonacci.length
        private long calculateFibonacci(int i) {
            return fibonacci[i - 2] + fibonacci[i - 1];
        }

        // We have to do it this cumbersome way because Java is not really a functional language
        private ArrayList<Long> concatenate(Long l, ArrayList<Long> ls) {
            ArrayList<Long> r = new ArrayList<>(ls);
            r.add(l);
            return r;
        }

        // Get the largest Fibonacci number which is smaller than x
        private long getLargestFibonacci(long x) {
            int index = fibonacci.length - 1;
            while (fibonacci[index] > x) index--;
            return fibonacci[index];
        }

        // Ensure the set of Fibonacci numbers is long enough
        private void ensureFibonacci(long x) {
            while (fibonacci[fibonacci.length - 1] < x) extendFibonacci();
        }

        private void extendFibonacci() {
            int length = fibonacci.length;
            long[] temp = new long[length * 2];
            System.arraycopy(fibonacci, 0, temp, 0, length);
            fibonacci = temp;
            for (int i = length; i < temp.length; i++)
                fibonacci[i] = calculateFibonacci(i);
        }

        // for testing only
        private int size() {
            return fibonacci.length;
        }

        // for testing only
        private long fib(int x) {
            return fibonacci[x];
        }

        private long[] fibonacci;
        private final Greedy<Long, ArrayList<Long>> greedy;
    }

    /**
     * Test method for Zeckendorf
     */
    @Test
    public void testZeckendorf() {
        GreedyZ target = new GreedyZ();
        PrivateMethodTester t = new PrivateMethodTester(target);
        assertEquals(2, t.invokePrivate("size"));
        assertEquals(1L, t.invokePrivate("fib", 0));
        assertEquals(1L, t.invokePrivate("fib", 1));
    }

    /**
     * Test method for extendFibonacci
     */
    @Test
    public void testExtendFibonacci() {
        GreedyZ target = new GreedyZ();
        PrivateMethodTester t = new PrivateMethodTester(target);
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
     * Test method for calculateFibonacci
     */
    @Test
    public void testCalculateFibonacci() {
        GreedyZ target = new GreedyZ();
        PrivateMethodTester t = new PrivateMethodTester(target);
        t.invokePrivate("ensureFibonacci", 5L);
        assertEquals(2L, t.invokePrivate("calculateFibonacci", 2));
        assertEquals(3L, t.invokePrivate("calculateFibonacci", 3));
        assertEquals(5L, t.invokePrivate("calculateFibonacci", 4));
        assertEquals(8L, t.invokePrivate("calculateFibonacci", 5));
    }

    /**
     * Test method for ensureFibonacci
     */
    @Test
    public void testEnsureFibonacci() {
        GreedyZ target = new GreedyZ();
        PrivateMethodTester t = new PrivateMethodTester(target);
        t.invokePrivate("ensureFibonacci", 1L);
        assertEquals(2, t.invokePrivate("size"));
        t.invokePrivate("ensureFibonacci", 2L);
        assertEquals(4, t.invokePrivate("size"));
        t.invokePrivate("ensureFibonacci", 3L);
        assertEquals(4, t.invokePrivate("size"));
        t.invokePrivate("ensureFibonacci", 4L);
        assertEquals(8, t.invokePrivate("size"));
        t.invokePrivate("ensureFibonacci", 5L);
        assertEquals(8, t.invokePrivate("size"));
    }

    /**
     * Test method for getLargestFibonacci
     */
    @Test
    public void testGetLargestFibonacci() {
        GreedyZ target = new GreedyZ();
        PrivateMethodTester t = new PrivateMethodTester(target);
        assertEquals(1L, t.invokePrivate("getLargestFibonacci", 1L));
        t.invokePrivate("ensureFibonacci", 2L);
        assertEquals(2L, t.invokePrivate("getLargestFibonacci", 2L));
        assertEquals(3L, t.invokePrivate("getLargestFibonacci", 4L));
        t.invokePrivate("ensureFibonacci", 5L);
        assertEquals(5L, t.invokePrivate("getLargestFibonacci", 6L));
    }

    /**
     * Test method for get
     */
    @Test
    public void testGet1() {
        GreedyZ target = new GreedyZ();
        Iterator<Long> fibs = target.run(10L).iterator();
        assertEquals(new Long(8), fibs.next());
        assertEquals(new Long(2), fibs.next());
        assertFalse(fibs.hasNext());
    }

    /**
     * Test method for get
     */
    @Test
    public void testGet2() {
        GreedyZ target = new GreedyZ();
        Iterator<Long> fibs = target.run(100L).iterator();
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
        GreedyZ target = new GreedyZ();
        Iterator<Long> fibs = target.run(1000L).iterator();
        assertEquals(new Long(987), fibs.next());
        assertEquals(new Long(13), fibs.next());
        assertFalse(fibs.hasNext());
    }

    /**
     * Test method for get
     */
    @Test
    public void testGet4() {
        GreedyZ target = new GreedyZ();
        Iterator<Long> fibs = target.run(10000L).iterator();
        assertEquals(new Long(6765), fibs.next());
        assertEquals(new Long(2584), fibs.next());
        assertEquals(new Long(610), fibs.next());
        assertEquals(new Long(34), fibs.next());
        assertEquals(new Long(5), fibs.next());
        assertEquals(new Long(2), fibs.next());
        assertFalse(fibs.hasNext());
    }

}
