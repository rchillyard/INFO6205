/*
 * Copyright (c) 2017. Phasmid Software
 */

package edu.neu.coe.info6205;

import edu.neu.coe.info6205.equable.BaseEquable;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TupleTest {

    /**
     * Test method for Tuple
     */
    @SuppressWarnings("EqualsWithItself")
    @Test
    public void testTuple1() {
        BaseEquable tuple1 = new Tuple(1, Math.PI);
        BaseEquable tuple2 = new Tuple(2, Math.E);
        assertEquals("tuple1.hashCode()==340594883", 340594883, tuple1.hashCode());
        assertEquals("tuple2.hashCode()==-888017822", -888017822, tuple2.hashCode());
        assertEquals("tuple1.hashCode()==tuple1.hashCode()", tuple1.hashCode(), tuple1.hashCode());
        assertTrue("tuple1.hashCode()!=tuple2.hashCode()", tuple1.hashCode() != tuple2.hashCode());
        assertEquals("tuple1.equals(tuple1)", tuple1, tuple1);
        assertEquals("tuple2.equals(tuple2)", tuple2, tuple2);
        assertTrue("!tuple1.equals(tuple2)", !tuple1.equals(tuple2));
        assertEquals("tuple1.toString()==\"Tuple(1, 3.141592653589793)\"", "Tuple(1, 3.141592653589793)", tuple1.toString());
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
        BaseEquable tuple = new Tuple(ints[0], doubles[0]);
        final int hashTuple = tuple.hashCode();
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++) {
                BaseEquable t = new Tuple(ints[i], doubles[j]);
                final int h = t.hashCode();
                final boolean eq = t.equals(tuple);
                boolean ok = eq == (h == hashTuple);
                assertTrue("problem with i=" + i + " and j=" + j, ok);
            }
    }

    /**
     * Test method for Tuple
     */
    @SuppressWarnings("EqualsWithItself")
    @Test
    public void testTuple2() {
        BaseEquable tuple1 = new Tuple(1, 1.0);
        BaseEquable tuple2 = new Tuple(1, 1.0);
        assertEquals("tuple1.hashCode()==1072694240", 1072694240, tuple1.hashCode());
        assertEquals("tuple2.hashCode()==1072694240", 1072694240, tuple2.hashCode());
        assertEquals("tuple1.hashCode()==tuple1.hashCode()", tuple1.hashCode(), tuple1.hashCode());
        assertEquals("tuple1.hashCode()==tuple2.hashCode()", tuple1.hashCode(), tuple2.hashCode());
        assertEquals("tuple1.equals(tuple1)", tuple1, tuple1);
        assertEquals("tuple2.equals(tuple2)", tuple2, tuple2);
        assertEquals("tuple1.equals(tuple2)", tuple1, tuple2);
        assertEquals("tuple1.toString()==\"Tuple(1, 1.0)\"", "Tuple(1, 1.0)", tuple1.toString());

        Tuple.index(0xAAAABBBB);
        Tuple.index(0x11111111);
    }

}
