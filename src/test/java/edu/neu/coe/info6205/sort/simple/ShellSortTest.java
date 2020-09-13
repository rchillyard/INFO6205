/*
 * Copyright (c) 2017. Phasmid Software
 */

package edu.neu.coe.info6205.sort.simple;

import edu.neu.coe.info6205.sort.BaseHelper;
import edu.neu.coe.info6205.sort.GenericSort;
import edu.neu.coe.info6205.util.LazyLogger;
import edu.neu.coe.info6205.util.PrivateMethodTester;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;

@SuppressWarnings("ALL")
public class ShellSortTest {

    @Test
    public void hSort3() {
        GenericSort<Integer> sorter = new ShellSort<>(3);
        PrivateMethodTester t = new PrivateMethodTester(sorter);
        Integer[] xs = {15, 3, -1, 2, 4, 1, 0, 5, 8, 6, 1, 9, 17, 7, 11};
        Integer[] zs = {4, 1, -1, 2, 8, 3, 0, 5, 15, 6, 1, 9, 17, 7, 11};
        Class[] classes = {int.class, Comparable[].class, int.class, int.class};
        t.invokePrivateExplicit("hSort", classes, 4, xs, 0, xs.length);
        assertArrayEquals(zs, xs);
    }

    @Test
    public void sort1() throws Exception {
        Integer[] xs = {15, 3, -1, 2, 4, 1, 0, 5, 8, 6, 1, 9, 17, 7, 11};
        Integer[] zs = {-1, 0, 1, 1, 2, 3, 4, 5, 6, 7, 8, 9, 11, 15, 17};
        assertArrayEquals(zs, new ShellSort<Integer>(3).sort(xs));
    }

    @Test
    public void sort2() throws Exception {
        Integer[] xs = {15, 3, -1, 2, 4, 1, 0, 5, 8, 6, 1, 9, 17, 7, 11};
        Integer[] zs = {-1, 0, 1, 1, 2, 3, 4, 5, 6, 7, 8, 9, 11, 15, 17};
        GenericSort ss = new ShellSort<Integer>(3);
        ss.sort(xs, 0, xs.length);
        assertArrayEquals(zs, xs);
    }

    @Test
    public void sort3() throws Exception {
        Integer[] xs = {15, 3, -1, 2, 4, 1, 0, 5, 8, 6, 1, 9, 17, 7, 11};
        Integer[] zs = {15, -1, 3, 2, 4, 1, 0, 5, 8, 6, 1, 9, 17, 7, 11};
        GenericSort ss = new ShellSort<Integer>(3);
        ss.sort(xs, 1, 3);
        assertArrayEquals(zs, xs);
    }

    @Test
    public void sort4() throws Exception {
        Integer[] xs = {15, 3, -1, 2, 4, 1, 0, 5, 8, 6, 1, 9, 17, 7, 11};
        Integer[] zs = {15, 3, -1, 2, 4, 1, 0, 5, 8, 6, 1, 7, 9, 11, 17};
        GenericSort ss = new ShellSort<Integer>(3);
        ss.sort(xs, 11, xs.length);
        assertArrayEquals(zs, xs);
    }

    @Ignore // TEST
    public void sort5() throws Exception {
        BaseHelper<Integer> helper = new BaseHelper<>("ShellSort", 1000);
        Integer[] xs = (Integer[]) helper.random(Integer.class, random -> random.nextInt());
        GenericSort<Integer> sorter = new SelectionSort<Integer>(helper);
        Integer[] ys = sorter.sort(xs);
        assertTrue(helper.sorted(ys));
        System.out.println(helper);

    }

    @Test
    public void sortH1() {
        Integer[] xs = {15, 3, -1, 2, 4, 1, 0, 5, 8, 6, 1, 9, 17, 7, 11};
        Integer[] zs = {-1, 0, 1, 1, 2, 3, 4, 5, 6, 7, 8, 9, 11, 15, 17};
        assertArrayEquals(zs, new ShellSort<Integer>(1).sort(xs));
    }

    @Test
    public void sortH2() {
        Integer[] xs = {15, 3, -1, 2, 4, 1, 0, 5, 8, 6, 1, 9, 17, 7, 11};
        Integer[] zs = {-1, 0, 1, 1, 2, 3, 4, 5, 6, 7, 8, 9, 11, 15, 17};
        assertArrayEquals(zs, new ShellSort<Integer>(2).sort(xs));
    }

    final static LazyLogger logger = new LazyLogger(ShellSort.class);


}