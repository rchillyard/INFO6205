/*
 * Copyright (c) 2017. Phasmid Software
 */

package edu.neu.coe.info6205;

import org.junit.Test;

import static org.junit.Assert.*;

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

}
