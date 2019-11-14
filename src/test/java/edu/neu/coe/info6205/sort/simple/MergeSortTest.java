/*
 * Copyright (c) 2017. Phasmid Software
 */

package edu.neu.coe.info6205.sort.simple;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

@SuppressWarnings("ALL")
public class MergeSortTest {

    @Test
    public void testSort() throws Exception {
        Integer[] xs = new Integer[4];
        xs[0] = 3;
        xs[1] = 4;
        xs[2] = 2;
        xs[3] = 1;
        Sort<Integer> s = new MergeSortBasic<>();
        Integer[] ys = s.sort(xs);
        assertEquals(Integer.valueOf(1), ys[0]);
        assertEquals(Integer.valueOf(2), ys[1]);
        assertEquals(Integer.valueOf(3), ys[2]);
        assertEquals(Integer.valueOf(4), ys[3]);
    }


}