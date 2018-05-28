/*
 * Copyright (c) 2017. Phasmid Software
 */

package edu.neu.coe.info6205.randomwalk;

import edu.neu.coe.info6205.util.PrivateMethodTester;
import org.junit.Test;

import static org.junit.Assert.*;

public class RandomWalkTest {

    @Test
    public void testMove0() {
        RandomWalk rw = new RandomWalk();
        PrivateMethodTester pmt = new PrivateMethodTester(rw);
        pmt.invokePrivate("move",1,0);
        assertEquals(1.0, rw.distance(), 1.0E-7);
    }

    /**
     */
    @Test
    public void testMove1() {
        RandomWalk rw = new RandomWalk();
        PrivateMethodTester pmt = new PrivateMethodTester(rw);
        pmt.invokePrivate("move",1,0);
        assertEquals(1.0, rw.distance(), 1.0E-7);
        pmt.invokePrivate("move",1,0);
        assertEquals(2.0, rw.distance(), 1.0E-7);
        pmt.invokePrivate("move",-1,0);
        assertEquals(1.0, rw.distance(), 1.0E-7);
        pmt.invokePrivate("move",-1,0);
        assertEquals(0.0, rw.distance(), 1.0E-7);
    }

    /**
     */
    @Test
    public void testMove2() {
        RandomWalk rw = new RandomWalk();
        PrivateMethodTester pmt = new PrivateMethodTester(rw);
        pmt.invokePrivate("move",0,1);
        assertEquals(1.0, rw.distance(), 1.0E-7);
        pmt.invokePrivate("move",0,1);
        assertEquals(2.0, rw.distance(), 1.0E-7);
        pmt.invokePrivate("move",0,-1);
        assertEquals(1.0, rw.distance(), 1.0E-7);
        pmt.invokePrivate("move",0,-1);
        assertEquals(0.0, rw.distance(), 1.0E-7);
    }

    /**
     */
    @Test
    public void testMove3() {
        RandomWalk rw = new RandomWalk();
        double root2 = Math.sqrt(2);
        PrivateMethodTester pmt = new PrivateMethodTester(rw);
        pmt.invokePrivate("move",1,1);
        assertEquals(root2, rw.distance(), 1.0E-7);
        pmt.invokePrivate("move",1,1);
        assertEquals(2 * root2, rw.distance(), 1.0E-7);
        pmt.invokePrivate("move",0,-2);
        assertEquals(2.0, rw.distance(), 1.0E-7);
        pmt.invokePrivate("move",-2,0);
        assertEquals(0.0, rw.distance(), 1.0E-7);
    }

    /**
     */
    @Test
    public void testRandomWalk(){
        for(int i = 0; i < 5000; i++){
            RandomWalk rw = new RandomWalk();
            assertEquals(rw.randomWalkMulti(100, 100), 10, 3);
        }
    }

    @Test
    public void testRandomWalk2() {
        for (int i = 0; i < 5000; i++) {
            RandomWalk rw = new RandomWalk();
            assertNotSame(0, rw.randomWalkMulti(1, 1));
        }
    }
}