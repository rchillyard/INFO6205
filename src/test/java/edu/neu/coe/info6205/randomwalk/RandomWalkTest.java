/*
 * Copyright (c) 2017. Phasmid Software
 */

package edu.neu.coe.info6205.randomwalk;

import edu.neu.coe.info6205.bqs.Bag;
import edu.neu.coe.info6205.bqs.Bag_Array;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RandomWalkTest {

    /**
     * Test method for Bag
     */
    @Test
    public void testRandomWalk() {
        RandomWalk rw = new RandomWalk();
        rw.move(1, 0);
        assertEquals(rw.distance(), 1.0, 1.0E-7);
    }

}