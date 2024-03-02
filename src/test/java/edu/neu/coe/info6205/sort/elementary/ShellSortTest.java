/*
 * Copyright (c) 2017. Phasmid Software
 */

package edu.neu.coe.info6205.sort.elementary;

import edu.neu.coe.info6205.sort.BaseHelperTest;
import edu.neu.coe.info6205.sort.GenericSort;
import edu.neu.coe.info6205.sort.Helper;
import edu.neu.coe.info6205.sort.InstrumentedHelper;
import edu.neu.coe.info6205.util.Config;
import edu.neu.coe.info6205.util.LazyLogger;
import edu.neu.coe.info6205.util.PrivateMethodTester;
import edu.neu.coe.info6205.util.StatPack;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

@SuppressWarnings("ALL")
public class ShellSortTest {

    @BeforeClass
    public static void setupClass() {
        try {
            config = Config.load(BaseHelperTest.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSedgewick0() throws IOException {
        ShellSort<Integer> shellSort = new ShellSort<>(4);
        ShellSort<Integer>.H h = shellSort.new H(0);
        assertEquals(1, h.sedgewick(0));
        assertEquals(5, h.sedgewick(1));
        assertEquals(19, h.sedgewick(2));
        assertEquals(41, h.sedgewick(3));
    }

    @Test
    public void testSedgewick50() throws IOException {
        ShellSort<Integer> shellSort = new ShellSort<>(4);
        ShellSort<Integer>.H h = shellSort.new H(50);
        assertEquals(41, h.first());
        assertEquals(19, h.next());
        assertEquals(5, h.next());
        assertEquals(1, h.next());
        assertEquals(0, h.next());
    }

    @Test
    public void hSortKnuth3() {
        GenericSort<Integer> sorter = new ShellSort<>(3, config);
        PrivateMethodTester t = new PrivateMethodTester(sorter);
        Integer[] xs = {15, 3, -1, 2, 4, 1, 0, 5, 8, 6, 1, 9, 17, 7, 11};
        Integer[] zs = {4, 1, -1, 2, 8, 3, 0, 5, 15, 6, 1, 9, 17, 7, 11};
        Class[] classes = {int.class, Comparable[].class, int.class, int.class};
        t.invokePrivateExplicit("hSort", classes, 4, xs, 0, xs.length);
        assertArrayEquals(zs, xs);
    }

    @Test
    public void sortKnuth1() throws Exception {
        Integer[] xs = {15, 3, -1, 2, 4, 1, 0, 5, 8, 6, 1, 9, 17, 7, 11};
        Integer[] zs = {-1, 0, 1, 1, 2, 3, 4, 5, 6, 7, 8, 9, 11, 15, 17};
        assertArrayEquals(zs, new ShellSort<Integer>(3, config).sort(xs));
    }

    @Test
    public void sortPratt1a() throws Exception {
        Integer[] xs = {15, 3, -1, 2, 4, 1, 0, 5, 8, 6, 1, 9, 17, 7, 11};
        Integer[] zs = {-1, 0, 1, 1, 2, 3, 4, 5, 6, 7, 8, 9, 11, 15, 17};
        assertArrayEquals(zs, new ShellSort<Integer>(5, config).sort(xs));
    }

    @Test
    public void sortKnuth2() throws Exception {
        Integer[] xs = {15, 3, -1, 2, 4, 1, 0, 5, 8, 6, 1, 9, 17, 7, 11};
        Integer[] zs = {-1, 0, 1, 1, 2, 3, 4, 5, 6, 7, 8, 9, 11, 15, 17};
        GenericSort ss = new ShellSort<Integer>(3, config);
        ss.sort(xs, 0, xs.length);
        assertArrayEquals(zs, xs);
    }

    @Test
    public void sortPratt2a() throws Exception {
        Integer[] xs = {15, 3, -1, 2, 4, 1, 0, 5, 8, 6, 1, 9, 17, 7, 11};
        Integer[] zs = {-1, 0, 1, 1, 2, 3, 4, 5, 6, 7, 8, 9, 11, 15, 17};
        GenericSort ss = new ShellSort<Integer>(5, config);
        ss.sort(xs, 0, xs.length);
        assertArrayEquals(zs, xs);
    }

    @Test
    public void sortKnuth3() throws Exception {
        Integer[] xs = {15, 3, -1, 2, 4, 1, 0, 5, 8, 6, 1, 9, 17, 7, 11};
        Integer[] zs = {15, -1, 3, 2, 4, 1, 0, 5, 8, 6, 1, 9, 17, 7, 11};
        GenericSort ss = new ShellSort<Integer>(3, config);
        ss.sort(xs, 1, 3);
        assertArrayEquals(zs, xs);
    }

    @Test
    public void sortKnuth3a() throws Exception {
        Integer[] xs = {15, 3, -1, 2, 4, 1, 0, 5, 8, 6, 1, 9, 17, 7, 11};
        Integer[] zs = {15, -1, 3, 2, 4, 1, 0, 5, 8, 6, 1, 9, 17, 7, 11};
        GenericSort ss = new ShellSort<Integer>(5, config);
        ss.sort(xs, 1, 3);
        assertArrayEquals(zs, xs);
    }

    @Test
    public void sortKnuth4() throws Exception {
        Integer[] xs = {15, 3, -1, 2, 4, 1, 0, 5, 8, 6, 1, 9, 17, 7, 11};
        Integer[] zs = {15, 3, -1, 2, 4, 1, 0, 5, 8, 6, 1, 7, 9, 11, 17};
        GenericSort ss = new ShellSort<Integer>(3, config);
        ss.sort(xs, 11, xs.length);
        assertArrayEquals(zs, xs);
    }

    @Test
    public void sortPratt4a() throws Exception {
        Integer[] xs = {15, 3, -1, 2, 4, 1, 0, 5, 8, 6, 1, 9, 17, 7, 11};
        Integer[] zs = {15, 3, -1, 2, 4, 1, 0, 5, 8, 6, 1, 7, 9, 11, 17};
        GenericSort ss = new ShellSort<Integer>(5, config);
        ss.sort(xs, 11, xs.length);
        assertArrayEquals(zs, xs);
    }

    @Test
    public void sortKnuth7() throws Exception {
        doShellSortTest(1000, 3);
    }

    @Test
    public void sortPratt7a() throws Exception {
        doShellSortTest(1000, 5);
    }

    @Test
    public void sortKnuth5a() throws Exception {
        doShellSortTest(10, 3);
    }

    @Test
    public void sortPratt5b() throws Exception {
        doShellSortTest(10, 5);
    }

    @Test
    public void sortKnuth6a() throws Exception {
        doShellSortTest(100, 3);
    }

    @Test
    public void sortPratt6b() throws Exception {
        doShellSortTest(100, 5);
    }

    @Test
    public void sortInsertionSortH1() {
        Integer[] xs = {15, 3, -1, 2, 4, 1, 0, 5, 8, 6, 1, 9, 17, 7, 11};
        Integer[] zs = {-1, 0, 1, 1, 2, 3, 4, 5, 6, 7, 8, 9, 11, 15, 17};
        assertArrayEquals(zs, new ShellSort<Integer>(1, config).sort(xs));
    }

    @Test
    public void sortShellH2() {
        Integer[] xs = {15, 3, -1, 2, 4, 1, 0, 5, 8, 6, 1, 9, 17, 7, 11};
        Integer[] zs = {-1, 0, 1, 1, 2, 3, 4, 5, 6, 7, 8, 9, 11, 15, 17};
        assertArrayEquals(zs, new ShellSort<Integer>(2, config).sort(xs));
    }

    @Test
    public void sortPrattH3() {
        Integer[] xs = {15, 3, -1, 2, 4, 1, 0, 5, 8, 6, 1, 9, 17, 7, 11};
        Integer[] zs = {-1, 0, 1, 1, 2, 3, 4, 5, 6, 7, 8, 9, 11, 15, 17};
        assertArrayEquals(zs, new ShellSort<Integer>(5, config).sort(xs));
    }

    @Test
    public void doShellSortKnuth() {
        Integer[] xs = {15, 3, -1, 2, 4, 1, 0, 5, 8, 6, 1, 9, 17, 7, 11};
        InstrumentedHelper<Integer> helper = new InstrumentedHelper<>("ShellSort with instrumentation", xs.length, config);
        ShellSort.doShellSort(3, helper, xs);
    }

    @Test
    public void doInstrumentedRandomDoubleShellSortKnuth() throws IOException {
        ShellSort.doRandomDoubleShellSort(3, 1000, 10, Config.load());
    }

    private void doShellSortTest(int N, final int gapSequence) throws IOException {
        final Config config = Config.load(getClass());
        InstrumentedHelper<Integer> helper = new InstrumentedHelper<>("ShellSort", N, config);
        Integer[] xs = (Integer[]) helper.random(Integer.class, random -> random.nextInt(N * 2));
        helper.init(N);
        helper.preProcess(xs);
        ShellSort<Integer> sorter = new ShellSort<>(gapSequence, helper);
        sorter.setShellFunction((h) -> showInversions(h));
        sorter.mutatingSort(xs);
        helper.postProcess(xs);
        assertTrue(helper.sorted(xs));
        showStatistics(helper);
    }

    private void showStatistics(Helper<Integer> helper) {
        if (InstrumentedHelper.class.isAssignableFrom(helper.getClass())) {
            StatPack statPack = ((InstrumentedHelper<Integer>) helper).getStatPack();
            double inversions = statPack.getStatistics(InstrumentedHelper.INVERSIONS).mean();
            double compares = statPack.getStatistics(InstrumentedHelper.COMPARES).mean();
            double swaps = statPack.getStatistics(InstrumentedHelper.SWAPS).mean();
            double fixes = statPack.getStatistics(InstrumentedHelper.FIXES).mean();
            System.out.println(inversions + ", " + compares + ", " + swaps + " " + fixes);
        }
    }

    private void showInversions(Helper<Integer> helper) {
        if (InstrumentedHelper.class.isAssignableFrom(helper.getClass())) {
            InstrumentedHelper<Integer> instrumentedHelper = (InstrumentedHelper<Integer>) helper;
            System.out.println("inversions: " + instrumentedHelper.inversions(instrumentedHelper.getRandomArray()));
            System.out.println("compares: " + instrumentedHelper.getCompares());
            System.out.println("swaps: " + instrumentedHelper.getSwaps());
        }
    }

    final static LazyLogger logger = new LazyLogger(ShellSort.class);

    private static Config config;
}