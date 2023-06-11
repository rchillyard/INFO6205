package edu.neu.coe.info6205.functions;

import org.junit.Test;

import static org.junit.Assert.*;

public class LambertWTest {

    public static final double TOLERANCE = 1E-6;

    @Test(expected = Exception.class)
    public void w0_() throws Exception {
        new LambertW().W(0, -1, TOLERANCE);
    }

    @Test(expected = Exception.class)
    public void w_1_() throws Exception {
        new LambertW().W(-1, 0.001, TOLERANCE);
    }

    @Test
    public void w00() throws Exception {
        assertEquals(-1, new LambertW().W(0, -1 / Math.E, TOLERANCE), 5E-3); // CONSIDER why not so precise?
        assertEquals(0, new LambertW().W(0, 0, TOLERANCE), 1E-8);
    }

    @Test
    public void w01() throws Exception {
        final double log2 = Math.log(2);
        assertEquals(0.56351356, new LambertW().W(0, 0.99, TOLERANCE), 1E-7);
        assertEquals(0.56714329, new LambertW().W(0, 1, TOLERANCE), 1E-7);
        assertEquals(1, new LambertW().W(0, Math.E, TOLERANCE), 1E-7);
        assertEquals(log2, new LambertW().W(0, 2 * log2, TOLERANCE), 1E-7);
        assertEquals(Math.E, new LambertW().W(0, Math.exp(Math.E + 1), TOLERANCE), 1E-7);
        double x = 2 / Math.E; // NOTE: should work for any 1 / e < x
        double w = Math.log(x);
        assertEquals(w, new LambertW().W(0, x * w, TOLERANCE), 1E-5);
    }


    @Test
    public void w_10() throws Exception {
        assertEquals(-1, new LambertW().W(-1, -1 / Math.E, TOLERANCE), 5E-3); // CONSIDER why not so precise?
        assertEquals(-3.6, new LambertW().W(-1, -0.1, TOLERANCE), 3E-2);
        double x = 1 / Math.E / 2; // NOTE: should work for any 0 < x < 1 / e
        double w = Math.log(x);
        assertEquals(w, new LambertW().W(-1, x * w, TOLERANCE), 1E-5);
    }

    @Test
    public void w2() {
        // NOTE: test the solutions to the equation x - 4 log2(x) = 0
        // equivalent to x ln(2)/4 - x = 0
        // Where this equation relates to the cutoff from merge sort to insertion sort.
        final double z = -Math.log(2) / 4;
        final Double[] ws = new LambertW().W(z, TOLERANCE);
        assertEquals(2, ws.length);
        assertEquals(1.23962773, ws[0] / z, 1E-6);
        assertEquals(16, ws[1] / z, 1E-6);
    }

    @Test
    public void w1() {
        final double z = 0.5;
        final Double[] ws = new LambertW().W(z, TOLERANCE);
        assertEquals(1, ws.length);
        assertEquals(0.3517337, ws[0], 1E-6);
    }
}