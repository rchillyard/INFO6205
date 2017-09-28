/*
 * Copyright (c) 2017. Phasmid Software
 */

package edu.neu.coe.info6205.randomwalk;

import org.junit.Test;

import static org.junit.Assert.*;

public class RandomWalkTest {

    /**
     */
    @Test
    public void testMove1() {
        RandomWalk rw = new RandomWalk();
        rw.move(1, 0);
        assertEquals(rw.distance(), 1.0, 1.0E-7);
        rw.move(1, 0);
        assertEquals(rw.distance(), 2.0, 1.0E-7);
        rw.move(-1, 0);
        assertEquals(rw.distance(), 1.0, 1.0E-7);
        rw.move(-1, 0);
        assertEquals(rw.distance(), 0.0, 1.0E-7);
    }

    /**
     */
    @Test
    public void testMove2() {
        RandomWalk rw = new RandomWalk();
        rw.move(0, 1);
        assertEquals(rw.distance(), 1.0, 1.0E-7);
        rw.move(0, 1);
        assertEquals(rw.distance(), 2.0, 1.0E-7);
        rw.move(0, -1);
        assertEquals(rw.distance(), 1.0, 1.0E-7);
        rw.move(0, -1);
        assertEquals(rw.distance(), 0.0, 1.0E-7);
    }

    /**
     */
    @Test
    public void testMove3() {
        RandomWalk rw = new RandomWalk();
        double root2 = Math.sqrt(2);
        rw.move(1, 1);
        assertEquals(rw.distance(), root2, 1.0E-7);
        rw.move(1, 1);
        assertEquals(rw.distance(), 2 * root2, 1.0E-7);
        rw.move(0, -2);
        assertEquals(rw.distance(), 2.0, 1.0E-7);
        rw.move(-2, 0);
        assertEquals(rw.distance(), 0.0, 1.0E-7);
    }

    /**
     */
    @Test
    public void testRandomWalk() {
        RandomWalk rw = new RandomWalk();
        rw.move(1, 0);
        assertEquals(rw.distance(), 1.0, 1.0E-7);
    }

}