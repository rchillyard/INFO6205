package edu.neu.coe.info6205.util;

import org.junit.Test;

import static edu.neu.coe.info6205.util.FastInverseSquareRoot.invSqrt;
import static org.junit.Assert.assertEquals;

public class FastInverseSquareRootTest {

    @Test
    public void testInvSqrtPi() {
        double y1 = invSqrt((float) Math.PI, 0);
        assertEquals(Math.PI, 1.0 / y1 / y1, 0.11);
        double y2 = invSqrt((float) Math.PI, 1);
        assertEquals(Math.PI, 1.0 / y2 / y2, 0.003);
        double y3 = invSqrt((float) Math.PI, 2);
        assertEquals(Math.PI, 1.0 / y3 / y3, 0.000002);
    }

    @Test
    public void testInvSqrtTwo() {
        double y1 = invSqrt(2.0f, 0);
        assertEquals(2.0, 1.0 / y1 / y1, 0.10);
        double y2 = invSqrt(2.0f, 1);
        assertEquals(2.0, 1.0 / y2 / y2, 0.002);
        double y3 = invSqrt((float) 2.0, 2);
        assertEquals(2.0, 1.0 / y3 / y3, 0.0000008);
    }
}