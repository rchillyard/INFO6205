/*
 * Copyright (c) 2017. Phasmid Software
 */

package edu.neu.coe.info6205.sort.linearithmic;

import edu.neu.coe.info6205.sort.*;
import edu.neu.coe.info6205.sort.elementary.InsertionSort;
import edu.neu.coe.info6205.util.Config;
import edu.neu.coe.info6205.util.LazyLogger;
import edu.neu.coe.info6205.util.PrivateMethodTester;
import edu.neu.coe.info6205.util.StatPack;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@SuppressWarnings("ALL")
public class MergeSortTest {

    @BeforeClass
    public static void beforeClass() throws IOException {
        config = Config.load(MergeSortTest.class);
    }

    @Test
    public void testSort1() throws Exception {
        Integer[] xs = new Integer[4];
        xs[0] = 3;
        xs[1] = 4;
        xs[2] = 2;
        xs[3] = 1;
        // NOTE: first we ensure that there is no cutoff to insertion sort going on.
        final Config config = Config.setupConfig("true", "", "0", "1", "");
        GenericSort<Integer> s = new MergeSort<>(xs.length, config);
        Integer[] ys = s.sort(xs);
        assertEquals(Integer.valueOf(1), ys[0]);
        assertEquals(Integer.valueOf(2), ys[1]);
        assertEquals(Integer.valueOf(3), ys[2]);
        assertEquals(Integer.valueOf(4), ys[3]);
    }

    @Test
    public void testSort1a() throws Exception {
        Integer[] xs = new Integer[5];
        xs[0] = 3;
        xs[1] = 4;
        xs[2] = 2;
        xs[3] = 1;
        xs[4] = 0;
        // NOTE: first we ensure that there is no cutoff to insertion sort going on.
        final Config config = Config.setupConfig("true", "", "0", "1", "");
        GenericSort<Integer> s = new MergeSort<>(xs.length, config);
        Integer[] ys = s.sort(xs);
        assertEquals(Integer.valueOf(0), ys[0]);
        assertEquals(Integer.valueOf(1), ys[1]);
        assertEquals(Integer.valueOf(2), ys[2]);
        assertEquals(Integer.valueOf(3), ys[3]);
        assertEquals(Integer.valueOf(4), ys[4]);
    }

    @Test
    public void testSort2() throws Exception {
        int k = 7;
        int N = (int) Math.pow(2, k);
        // NOTE this depends on the cutoff value for merge sort.
        int levels = k - 2;
        final Config config = Config.setupConfig("true", "0", "1", "", "");
        final Helper<Integer> helper = HelperFactory.create("merge sort", N, config);
        System.out.println(helper);
        Sort<Integer> s = new MergeSort<>(helper);
        s.init(N);
        final Integer[] xs = helper.random(Integer.class, r -> r.nextInt(10000));
        assertEquals(Integer.valueOf(1360), xs[0]);
        helper.preProcess(xs);
        Integer[] ys = s.sort(xs);
        helper.postProcess(ys);
        final PrivateMethodTester privateMethodTester = new PrivateMethodTester(helper);
        final StatPack statPack = (StatPack) privateMethodTester.invokePrivate("getStatPack");
        System.out.println(statPack);
        final int compares = (int) statPack.getStatistics(InstrumentedHelper.COMPARES).mean();
        final int inversions = (int) statPack.getStatistics(InstrumentedHelper.INVERSIONS).mean();
        final int fixes = (int) statPack.getStatistics(InstrumentedHelper.FIXES).mean();
        final int swaps = (int) statPack.getStatistics(InstrumentedHelper.SWAPS).mean();
        final int copies = (int) statPack.getStatistics(InstrumentedHelper.COPIES).mean();
        final int worstCompares = N * k - N + 1;
        System.out.println("Compares" + compares);
        System.out.println("Worst Compares" + worstCompares);
        assertTrue(compares <= worstCompares);
        assertEquals(inversions, fixes);
        assertEquals(levels * N, copies);
    }

    @Test
    public void testSort3() throws Exception {
        int k = 7;
        int N = (int) Math.pow(2, k);
        final Helper<Integer> helper1 = HelperFactory.create("insertion sort", N, Config.setupConfig("true", "0", "1", "", ""));
        System.out.println(helper1);
        final Integer[] xs = helper1.random(Integer.class, r -> r.nextInt(10000));
        assertEquals(Integer.valueOf(1360), xs[0]);
        new InsertionSort<Integer>(helper1).mutatingSort(xs);
        helper1.postProcess(xs);
        final Helper<Integer> helper2 = HelperFactory.create("merge sort", N, Config.setupConfig("true", "", "0", "1", ""));
        System.out.println(helper2);
        Sort<Integer> mergeSort = new MergeSort<>(helper2);
        mergeSort.init(N);
        helper2.preProcess(xs);
        Integer[] ys = mergeSort.sort(xs);
        helper2.postProcess(ys);
        final PrivateMethodTester privateMethodTester1 = new PrivateMethodTester(helper1);
        final StatPack statPack1 = (StatPack) privateMethodTester1.invokePrivate("getStatPack");
        final int inversions = (int) statPack1.getStatistics(InstrumentedHelper.INVERSIONS).mean();
        final PrivateMethodTester privateMethodTester2 = new PrivateMethodTester(helper2);
        final StatPack statPack2 = (StatPack) privateMethodTester2.invokePrivate("getStatPack");
        System.out.println(statPack2);
        final int compares = (int) statPack2.getStatistics(InstrumentedHelper.COMPARES).mean();
        final int fixes = (int) statPack2.getStatistics(InstrumentedHelper.FIXES).mean();
        final int swaps = (int) statPack2.getStatistics(InstrumentedHelper.SWAPS).mean();
        final int copies = (int) statPack2.getStatistics(InstrumentedHelper.COPIES).mean();
        final int expectedCompares = N * k / 2;
        assertEquals(expectedCompares, compares);
        assertEquals(inversions, fixes);
        assertEquals(k * N, copies);
    }

    @Test
    public void testSort4() throws Exception {
        final int k = 7;
        ArrayList<Long> time = new ArrayList<Long>();
        final int N = (int) Math.pow(2, k);
        final Helper<Integer> helper1 = HelperFactory.create("insertion sort", N, Config.setupConfig2("true", "0", "1", "", "", "false", "false"));
        System.out.println(helper1);
        final Integer[] xs = helper1.random(Integer.class, r -> r.nextInt(10000));
        System.nanoTime();
        GenericSort<Integer> s = new MergeSort<>(xs.length, config);
        for (int i = 0; i <= 1000; i++) {
            Long start = System.nanoTime();
            Integer[] ys = s.sort(xs);
            Long end = System.nanoTime();
            Long t = (end - start);
            time.add(t);
        }
        long sum = 0;
        for (Long t : time) {
            sum += t;
        }
        long avg = sum / 1000;
        System.out.println("average time random_CutOff: " + avg);

    }

    @Test
    public void testSort5() throws Exception {
        final int k = 7;
        ArrayList<Long> time = new ArrayList<Long>();
        final int N = (int) Math.pow(2, k);
        final Helper<Integer> helper1 = HelperFactory.create("insertion sort", N, Config.setupConfig2("true", "0", "1", "", "", "false", "true"));
        System.out.println(helper1);
        final Integer[] xs = helper1.random(Integer.class, r -> r.nextInt(10000));
        System.nanoTime();
        GenericSort<Integer> s = new MergeSort<>(xs.length, config);
        for (int i = 0; i <= 1000; i++) {
            Long start = System.nanoTime();
            Integer[] ys = s.sort(xs);
            Long end = System.nanoTime();
            Long t = (end - start);
            time.add(t);
        }
        long sum = 0;
        for (Long t : time) {
            sum += t;
        }
        long avg = sum / 1000;
        System.out.println("average time random_Cutoff + NoCopy: " + avg);
    }

    @Test
    public void testSort6() throws Exception {
        final int k = 7;
        ArrayList<Long> time = new ArrayList<Long>();
        final int N = (int) Math.pow(2, k);
        final Helper<Integer> helper1 = HelperFactory.create("insertion sort", N, Config.setupConfig2("true", "0", "1", "", "", "true", "false"));
        System.out.println(helper1);
        final Integer[] xs = helper1.random(Integer.class, r -> r.nextInt(10000));
        System.nanoTime();
        GenericSort<Integer> s = new MergeSort<>(xs.length, config);
        for (int i = 0; i <= 1000; i++) {
            Long start = System.nanoTime();
            Integer[] ys = s.sort(xs);
            Long end = System.nanoTime();
            Long t = (end - start);
            time.add(t);
        }
        long sum = 0;
        for (Long t : time) {
            sum += t;
        }
        long avg = sum / 1000;
        System.out.println("average time random_Cutoff + Insurance: " + avg);
    }

    @Test
    public void testSort7() throws Exception {
        final int k = 7;
        ArrayList<Long> time = new ArrayList<Long>();
        final int N = (int) Math.pow(2, k);
        final Helper<Integer> helper1 = HelperFactory.create("insertion sort", N, Config.setupConfig2("true", "0", "1", "", "", "true", "true"));
        System.out.println(helper1);
        final Integer[] xs = helper1.random(Integer.class, r -> r.nextInt(10000));
        System.nanoTime();
        GenericSort<Integer> s = new MergeSort<>(xs.length, config);
        for (int i = 0; i <= 1000; i++) {
            Long start = System.nanoTime();
            Integer[] ys = s.sort(xs);
            Long end = System.nanoTime();
            Long t = (end - start);
            time.add(t);
        }
        long sum = 0;
        for (Long t : time) {
            sum += t;
        }
        long avg = sum / 1000;
        System.out.println("average time random_Cutoff + Insurance + NoCopy: " + avg);
    }

    @Test
    public void testSort8_partialsorted() throws Exception {
        final int k = 7;
        ArrayList<Long> time = new ArrayList<Long>();
        final int N = (int) Math.pow(2, k);
        final Helper<Integer> helper1 = HelperFactory.create("insertion sort", N, Config.setupConfig2("true", "0", "1", "", "", "false", "false"));
        System.out.println(helper1);
        Integer[] xs_sorted = helper1.random(Integer.class, r -> r.nextInt(10000));
        Arrays.sort(xs_sorted);
        Integer[] xs_unsorted = helper1.random(Integer.class, r -> r.nextInt(10000));
        ArrayList<Integer> xs_orignal = new ArrayList<Integer>(Arrays.asList(xs_sorted));
        xs_orignal.addAll(Arrays.asList(xs_unsorted));
        final Integer[] xs = xs_orignal.toArray(new Integer[xs_orignal.size()]);
        System.nanoTime();
        GenericSort<Integer> s = new MergeSort<>(xs.length, config);
        for (int i = 0; i <= 1000; i++) {
            Long start = System.nanoTime();
            Integer[] ys = s.sort(xs);
            Long end = System.nanoTime();
            Long t = (end - start);
            time.add(t);
        }
        long sum = 0;
        for (Long t : time) {
            sum += t;
        }
        long avg = sum / 1000;
        System.out.println("partial sorted average time partialsorted_Cutoff: " + avg);

    }

    @Test
    public void testSort9_partialsorted() throws Exception {
        final int k = 7;
        ArrayList<Long> time = new ArrayList<Long>();
        final int N = (int) Math.pow(2, k);
        final Helper<Integer> helper1 = HelperFactory.create("insertion sort", N, Config.setupConfig2("true", "0", "1", "", "", "false", "true"));
        System.out.println(helper1);
        Integer[] xs_sorted = helper1.random(Integer.class, r -> r.nextInt(10000));
        Arrays.sort(xs_sorted);
        Integer[] xs_unsorted = helper1.random(Integer.class, r -> r.nextInt(10000));
        ArrayList<Integer> xs_orignal = new ArrayList<Integer>(Arrays.asList(xs_sorted));
        xs_orignal.addAll(Arrays.asList(xs_unsorted));
        final Integer[] xs = xs_orignal.toArray(new Integer[xs_orignal.size()]);

        System.nanoTime();
        GenericSort<Integer> s = new MergeSort<>(xs.length, config);
        for (int i = 0; i <= 1000; i++) {
            Long start = System.nanoTime();
            Integer[] ys = s.sort(xs);
            Long end = System.nanoTime();
            Long t = (end - start);
            time.add(t);
        }
        long sum = 0;
        for (Long t : time) {
            sum += t;
        }
        long avg = sum / 1000;
        System.out.println("partial sorted average time partialsorted_Cutoff + NoCopy: " + avg);

    }

    @Test
    public void testSort10_partialsorted() throws Exception {
        final int k = 7;
        ArrayList<Long> time = new ArrayList<Long>();
        final int N = (int) Math.pow(2, k);
        final Helper<Integer> helper1 = HelperFactory.create("insertion sort", N, Config.setupConfig2("true", "0", "1", "", "", "true", "false"));
        System.out.println(helper1);
        Integer[] xs_sorted = helper1.random(Integer.class, r -> r.nextInt(10000));
        Arrays.sort(xs_sorted);
        Integer[] xs_unsorted = helper1.random(Integer.class, r -> r.nextInt(10000));
        ArrayList<Integer> xs_orignal = new ArrayList<Integer>(Arrays.asList(xs_sorted));
        xs_orignal.addAll(Arrays.asList(xs_unsorted));
        final Integer[] xs = xs_orignal.toArray(new Integer[xs_orignal.size()]);

        System.nanoTime();
        GenericSort<Integer> s = new MergeSort<>(xs.length, config);
        for (int i = 0; i <= 1000; i++) {
            Long start = System.nanoTime();
            Integer[] ys = s.sort(xs);
            Long end = System.nanoTime();
            Long t = (end - start);
            time.add(t);
        }
        long sum = 0;
        for (Long t : time) {
            sum += t;
        }
        long avg = sum / 1000;
        System.out.println("partial sorted average time partialsorted_Cutoff + Insurance: " + avg);
    }

    @Test
    public void testSort11_partialsorted() throws Exception {
        final int k = 7;
        ArrayList<Long> time = new ArrayList<Long>();
        final int N = (int) Math.pow(2, k);
        final Helper<Integer> helper1 = HelperFactory.create("insertion sort", N, Config.setupConfig2("true", "0", "1", "", "", "true", "true"));
        System.out.println(helper1);
        Integer[] xs_sorted = helper1.random(Integer.class, r -> r.nextInt(10000));
        Arrays.sort(xs_sorted);
        Integer[] xs_unsorted = helper1.random(Integer.class, r -> r.nextInt(10000));
        ArrayList<Integer> xs_orignal = new ArrayList<Integer>(Arrays.asList(xs_sorted));
        xs_orignal.addAll(Arrays.asList(xs_unsorted));
        final Integer[] xs = xs_orignal.toArray(new Integer[xs_orignal.size()]);

        System.nanoTime();
        GenericSort<Integer> s = new MergeSort<>(xs.length, config);
        for (int i = 0; i <= 1000; i++) {
            Long start = System.nanoTime();
            Integer[] ys = s.sort(xs);
            Long end = System.nanoTime();
            Long t = (end - start);
            time.add(t);
        }
        long sum = 0;
        for (Long t : time) {
            sum += t;
        }
        long avg = sum / 1000;
        System.out.println("partial sorted average time partialsorted_Cutoff + Insurance + NoCopy: " + avg);
    }

    @Test
    public void testSort12() {
        Config config1 = config.copy(MergeSort.MERGESORT, MergeSort.INSURANCE, "true");
        Config config2 = config1.copy(MergeSort.MERGESORT, MergeSort.NOCOPY, "false");
        MergeSort<Integer> sorter = new MergeSort<>(8, config2);
        System.out.println("testing " + sorter);
        Helper<Integer> helper = sorter.getHelper();
        Integer[] ints = helper.random(Integer.class, r -> r.nextInt(1000));
        Integer[] sorted = sorter.sort(ints);
        assertTrue(helper.sorted(sorted));
    }

    @Test
    public void testSort13() {
        MergeSort<Integer> sorter = new MergeSort<>(8, config.copy(MergeSort.MERGESORT, MergeSort.INSURANCE, "false").copy(MergeSort.MERGESORT, MergeSort.NOCOPY, "true"));
        System.out.println("testing " + sorter);
        Helper<Integer> helper = sorter.getHelper();
        Integer[] ints = helper.random(Integer.class, r -> r.nextInt(1000));
        Integer[] sorted = sorter.sort(ints);
        assertTrue(helper.sorted(sorted));
    }

    @Test
    public void testSort14() {
        MergeSort<Integer> sorter = new MergeSort<>(8, config.copy(MergeSort.MERGESORT, MergeSort.INSURANCE, "true").copy(MergeSort.MERGESORT, MergeSort.NOCOPY, "true"));
        System.out.println("testing " + sorter);
        Helper<Integer> helper = sorter.getHelper();
        Integer[] ints = helper.random(Integer.class, r -> r.nextInt(1000));
        Integer[] sorted = sorter.sort(ints);
        assertTrue(helper.sorted(sorted));
    }

    final static LazyLogger logger = new LazyLogger(MergeSort.class);

    private static Config config;
}