package com.info6205;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class InsertionTest {

    @Test
    public void testSort() {
        final List<Integer> list = new ArrayList<>();
        list.add(3);
        list.add(4);
        list.add(2);
        list.add(1);
        Integer[] xs = list.toArray(new Integer[0]);
        Insertion<Integer> sorter = new Insertion<Integer>();
        sorter.sort(xs);
        assertTrue(xs[0] == 1);
        assertTrue(xs[1] == 2);
        assertTrue(xs[2] == 3);
        assertTrue(xs[3] == 4);
    }

    @Test
    public void testSwap() {
        final List<Integer> list = new ArrayList<>();
        list.add(3);
        list.add(4);
        list.add(2);
        list.add(1);
        Integer[] xs = list.toArray(new Integer[0]);
        Insertion<Integer> sorter = new Insertion<Integer>();
        sorter.swap(xs, 0, 2);
        sorter.swap(xs, 1, 3);
        assertTrue(xs[0] == 2);
        assertTrue(xs[1] == 1);
        assertTrue(xs[2] == 3);
        assertTrue(xs[3] == 4);
    }

    @Test
    public void countSortingSwapCounts1() {
        final List<Integer> list = new ArrayList<>();
        list.add(3);
        list.add(4);
        list.add(2);
        list.add(1);
        Integer[] xs = list.toArray(new Integer[0]);
        Insertion<Integer> sorter = new Insertion<Integer>();
        sorter.sort(xs);
        assertTrue(sorter.swaps == 5);
    }

    @Test
    public void countSortingSwapCounts2() {
        final List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        Integer[] xs = list.toArray(new Integer[0]);
        Insertion<Integer> sorter = new Insertion<Integer>();
        sorter.sort(xs);
        assertTrue(sorter.swaps == 0);
    }

}
