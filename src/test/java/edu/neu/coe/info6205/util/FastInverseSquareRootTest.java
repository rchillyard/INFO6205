package edu.neu.coe.info6205.util;

import org.junit.Test;

import static edu.neu.coe.info6205.util.FastInverseSquareRoot.invSqrt;
import static org.junit.Assert.assertEquals;

public class FastInverseSquareRootTest {

    @Test
    public void testInvSqrtPi() {
        double y1 = invSqrt((float) Math.PI, false);
        assertEquals(Math.PI, 1.0 / y1 / y1, 0.11);
        double y2 = invSqrt((float) Math.PI, true);
        assertEquals(Math.PI, 1.0 / y2 / y2, 0.01);
    }

    @Test
    public void testInvSqrtTwo() {
        double y1 = invSqrt(2.0f, false);
        assertEquals(2.0, 1.0 / y1 / y1, 0.10);
        double y2 = invSqrt(2.0f, true);
        assertEquals(2.0, 1.0 / y2 / y2, 0.005);
    }
}