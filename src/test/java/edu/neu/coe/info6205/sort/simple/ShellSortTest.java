/*
 * Copyright (c) 2017. Phasmid Software
 */

package edu.neu.coe.info6205.sort.simple;

import edu.neu.coe.info6205.util.PrivateMethodTester;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@SuppressWarnings("ALL")
public class ShellSortTest {

    @Test
    public void hSort1() {
        ShellSort<Integer> sorter = new ShellSort<>(3);
        PrivateMethodTester t = new PrivateMethodTester(sorter);
        Integer[] xs = {15, 3, -1, 2, 4, 1, 0, 5, 8, 6, 1, 9, 17, 7, 11};
        Integer[] zs = {4, 1, -1, 2, 8, 3, 0, 5, 15, 6, 1, 9, 17, 7, 11};
        Class[] classes = {int.class, Comparable[].class, int.class, int.class};
        t.invokePrivateExplicit("hSort", classes, 4, xs, 0, xs.length);
        assertEquals(zs, xs);

    }

    @Test
    public void sort1() throws Exception {
        Integer[] xs = {15, 3, -1, 2, 4, 1, 0, 5, 8, 6, 1, 9, 17, 7, 11};
        Integer[] zs = {-1, 0, 1, 1, 2, 3, 4, 5, 6, 7, 8, 9, 11, 15, 17};
        assertEquals(zs, new ShellSort<Integer>(3).sort(xs));
    }

    @Test
    public void sort2() throws Exception {
        Integer[] xs = {15, 3, -1, 2, 4, 1, 0, 5, 8, 6, 1, 9, 17, 7, 11};
        Integer[] zs = {-1, 0, 1, 1, 2, 3, 4, 5, 6, 7, 8, 9, 11, 15, 17};
        Sort ss = new ShellSort<Integer>(3);
        ss.sort(xs, 0, xs.length);
        assertEquals(zs, xs);
    }

    @Test
    public void sort3() throws Exception {
        Integer[] xs = {15, 3, -1, 2, 4, 1, 0, 5, 8, 6, 1, 9, 17, 7, 11};
        Integer[] zs = {15, -1, 3, 2, 4, 1, 0, 5, 8, 6, 1, 9, 17, 7, 11};
        Sort ss = new ShellSort<Integer>(3);
        ss.sort(xs, 1, 3);
        assertEquals(zs, xs);
    }

    @Test
    public void sort4() throws Exception {
        Integer[] xs = {15, 3, -1, 2, 4, 1, 0, 5, 8, 6, 1, 9, 17, 7, 11};
        Integer[] zs = {15, 3, -1, 2, 4, 1, 0, 5, 8, 6, 1, 7, 9, 11, 17};
        Sort ss = new ShellSort<Integer>(3);
        ss.sort(xs, 11, xs.length);
        assertEquals(zs, xs);
    }

    //  TODO  @Test
    public void sort5() throws Exception {
        Helper<Integer> helper = new Helper<>("ShellSort", 1000);
        Integer[] xs = (Integer[]) helper.random(random -> random.nextInt());
        SelectionSort<Integer> sorter = new SelectionSort<Integer>(helper);
        Integer[] ys = sorter.sort(xs);
        assertTrue(helper.sorted(ys));
        System.out.println(helper);

    }

}