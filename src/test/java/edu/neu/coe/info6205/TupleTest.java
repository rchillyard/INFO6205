/*
 * Copyright (c) 2017. Phasmid Software
 */

package edu.neu.coe.info6205;

import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.assertTrue;

public class TupleTest {

    /**
     * Test method for Tuple
     */
    @SuppressWarnings("EqualsWithItself")
    @Test
    public void testTuple() {
        Tuple tuple1 = new Tuple(1, Math.PI);
        Tuple tuple2 = new Tuple(2, Math.E);
        assertTrue("tuple1.hashCode()==340593922", tuple1.hashCode() == 340593922);
        assertTrue("tuple2.hashCode()==-888018783", tuple2.hashCode() == -888018783);
        assertTrue("tuple1.equals(tuple1)", tuple1.equals(tuple1));
        assertTrue("tuple2.equals(tuple2)", tuple2.equals(tuple2));
        assertTrue("!tuple1.equals(tuple2)", !tuple1.equals(tuple2));
        assertTrue("tuple1.toString()==\"Tuple(1, 3.141592653589793)\"", tuple1.toString().equals("Tuple(1, 3.141592653589793)"));
    }

    @Test
    public void testTupleRandom() {
        Random random = new Random();
        int n = 100;
        double[] doubles = new double[n];
        int[] ints = new int[n];
        for (int i = 0; i < n; i++) {
            ints[i] = random.nextInt(10);
            doubles[i] = random.nextDouble();
        }
        Tuple tuple = new Tuple(ints[0], doubles[0]);
        final int hashTuple = tuple.hashCode();
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++) {
                Tuple t = new Tuple(ints[i], doubles[j]);
                final int h = t.hashCode();
                final boolean eq = t.equals(tuple);
                boolean ok = eq == (h == hashTuple);
                assertTrue("problem with i=" + i + " and j=" + j, ok);
            }
    }

}
