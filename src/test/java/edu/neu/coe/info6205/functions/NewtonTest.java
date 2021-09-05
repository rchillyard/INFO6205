/*
 * Copyright (c) 2017. Phasmid Software
 */

package edu.neu.coe.info6205.functions;

import org.junit.Test;

import static org.junit.Assert.*;

public class NewtonTest {

    @Test
    public void testNewton1() {
        // Build the Newton's Approximation problem to be solved: cos(x) = x
        Newton newton = new Newton("cos(x) - x", (double x) -> Math.cos(x) - x, (double x) -> -Math.sin(x) - 1);

        // Solve the problem starting with a value of x = 1;
        // requiring a precision of 10^-7;
        // and giving up after 200 tries.
        Either<String, Double> result = newton.solve(1.0, 200, 1E-7);

        assertTrue("result is a Double", result.isRight());
        Double x = result.getRight();
        assertEquals(0.73908513338528, x, 0.0001);
    }

    @Test
    public void testNewton2() {
        // Build the Newton's Approximation problem to be solved: cos(x) = x
        Newton newton = new Newton("cos(x) - x", (double x) -> Math.cos(x) - x, (double x) -> -Math.sin(x) - 1);

        // Demonstrate that we cannot solve the problem starting with a value of x = -1;
        // requiring a precision of 10^-7;
        // and giving up after 2 tries.
        Either<String, Double> result = newton.solve(-1.0, 2, 1E-7);

        assertFalse("result is a Double", result.isRight());
        String x = result.getLeft();
        assertEquals("cos(x) - x=0 did not converge given x0=-1.0, maxTries=2, and tolerance=1.0E-7", x);
    }

    @Test
    public void testNewton3() {
        // Build the Newton's Approximation problem to be solved: cos(x) = x
        Newton newton = new Newton("cos(x) - x", (double x) -> Math.cos(x) - x, (double x) -> -Math.sin(x) - 1);

        // Demonstrate that we cannot solve the problem starting with a value of x = -1;
        // requiring a precision of 10^-7;
        // and giving up after 2 tries.
        Either<String, Double> result = newton.solve(-Math.PI / 2, 100, 1E-7);

        assertFalse("result is a Double", result.isRight());
        String x = result.getLeft();
        assertEquals("cos(x) - x=0 did not converge given x0=-1.5707963267948966, maxTries=100, and tolerance=1.0E-7", x);
    }

}
