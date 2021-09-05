/*
 * Copyright (c) 2017. Phasmid Software
 */

package edu.neu.coe.info6205.sort.linearithmic;

import edu.neu.coe.info6205.sort.GenericSort;
import edu.neu.coe.info6205.sort.Helper;
import edu.neu.coe.info6205.sort.SortWithHelper;
import edu.neu.coe.info6205.util.Config;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@SuppressWarnings("ALL")
public class QuickSort_BasicTest {

    @Test
    public void testSort() throws Exception {
        Integer[] xs = new Integer[4];
        xs[0] = 3;
        xs[1] = 4;
        xs[2] = 2;
        xs[3] = 1;
        GenericSort<Integer> s = new QuickSort_Basic<>(Config.load(getClass()));
        Integer[] ys = s.sort(xs);
        assertEquals(Integer.valueOf(1), ys[0]);
        assertEquals(Integer.valueOf(2), ys[1]);
        assertEquals(Integer.valueOf(3), ys[2]);
        assertEquals(Integer.valueOf(4), ys[3]);
    }

    @Test
    public void testSort2() throws Exception {
        int n = 16;
        final SortWithHelper<Integer> sorter = new QuickSort_Basic<>(Config.load(getClass()));
        final Helper<Integer> helper = sorter.getHelper();
        helper.init(n);
        final Integer[] xs = helper.random(Integer.class, r -> r.nextInt(100));
        final Integer[] sorted = sorter.sort(xs);
        assertTrue(helper.sorted(sorted));
    }

    @Test
    public void testPartition() throws Exception {
        String testString = "PABXWPPVPDPCYZ";
        char[] charArray = testString.toCharArray();
        Character[] array = new Character[charArray.length];
        for (int i = 0; i < array.length; i++) array[i] = charArray[i];
        GenericSort<Character> s = new QuickSort_Basic<Character>(Config.load(getClass()));
        Partition<Character> p = ((QuickSort<Character>) s).createPartition(array, 0, array.length - 1);
        assertEquals(0, p.from);
        assertEquals(13, p.to);
        assertEquals(Character.valueOf('P'), array[0]);
        assertEquals(Character.valueOf('Z'), array[array.length - 1]);
    }

}