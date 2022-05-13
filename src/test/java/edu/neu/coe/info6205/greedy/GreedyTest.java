package edu.neu.coe.info6205.greedy;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class GreedyTest {

    /**
     * Class to model Zeckendorf's theorem.
     * See also Zeckendorf.java in the main tree.
     * This version of Zeckendorf uses the Greedy class.
     */
    class GreedyZ {

        GreedyZ() {
            fibonacci = new Fibonacci();
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
        Collection<Long> run(Long x) {
            fibonacci.ensure(x);
            return greedy.run(x, new ArrayList<>());
        }

        // We have to do it this cumbersome way because Java is not really a functional language
        private Collection<Long> concatenate(Long l, Collection<Long> ls) {
            ArrayList<Long> r = new ArrayList<>(ls);
            r.add(l);
            return r;
        }

        // Get the largest Fibonacci number which is smaller than x
        private long getLargestFibonacci(long x) {
            return fibonacci.getLargest(x);
        }

        private final Fibonacci fibonacci;
        private final Greedy<Long, Collection<Long>> greedy;
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
