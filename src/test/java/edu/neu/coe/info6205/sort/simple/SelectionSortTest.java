/*
 * Copyright (c) 2017. Phasmid Software
 */

package edu.neu.coe.info6205.sort.simple;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@SuppressWarnings("ALL")
public class SelectionSortTest {

    @Test
    public void sort() throws Exception {

        final List<Integer> list = new ArrayList<>();
        list.add(3);
        list.add(4);
        list.add(2);
        list.add(1);
        Integer[] xs = list.toArray(new Integer[0]);
        new SelectionSort<Integer>().sort(xs);
        assertEquals(Integer.valueOf(1), xs[0]);
        assertEquals(Integer.valueOf(2), xs[1]);
        assertEquals(Integer.valueOf(3), xs[2]);
        assertEquals(Integer.valueOf(4), xs[3]);
    }

}