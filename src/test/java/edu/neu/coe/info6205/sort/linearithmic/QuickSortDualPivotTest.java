/*
 * Copyright (c) 2017. Phasmid Software
 */

package edu.neu.coe.info6205.sort.linearithmic;

import edu.neu.coe.info6205.sort.*;
import edu.neu.coe.info6205.util.Config;
import edu.neu.coe.info6205.util.LazyLogger;
import edu.neu.coe.info6205.util.PrivateMethodTester;
import edu.neu.coe.info6205.util.StatPack;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static edu.neu.coe.info6205.util.Utilities.round;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@SuppressWarnings("ALL")
public class QuickSortDualPivotTest {

    @Test
    public void testSort() throws Exception {
        Integer[] xs = new Integer[4];
        xs[0] = 3;
        xs[1] = 4;
        xs[2] = 2;
        xs[3] = 1;
        GenericSort<Integer> s = new QuickSort_DualPivot<>(xs.length, config);
        Integer[] ys = s.sort(xs);
        assertEquals(Integer.valueOf(1), ys[0]);
        assertEquals(Integer.valueOf(2), ys[1]);
        assertEquals(Integer.valueOf(3), ys[2]);
        assertEquals(Integer.valueOf(4), ys[3]);
    }

    @Test
    public void testSortWithInstrumenting0() throws Exception {
        int n = 16;
        final SortWithHelper<Integer> sorter = new QuickSort_DualPivot<>(n, config);
        final Helper<Integer> helper = sorter.getHelper();
        final Integer[] xs = helper.random(Integer.class, r -> r.nextInt(10));
        final Integer[] sorted = sorter.sort(xs);
        assertTrue(helper.sorted(sorted));
    }

    @Test
    public void testSortWithInstrumenting1() throws Exception {
        int n = 541; // a prime number
        final SortWithHelper<Integer> sorter = new QuickSort_DualPivot<>(n, config);
        final Helper<Integer> helper = sorter.getHelper();
        final Integer[] xs = helper.random(Integer.class, r -> r.nextInt(97));
        final Integer[] sorted = sorter.sort(xs);
        assertTrue(helper.sorted(sorted));
    }

    @Test
    public void testSortWithInstrumenting2() throws Exception {
        int n = 1000;
        final SortWithHelper<Integer> sorter = new QuickSort_DualPivot<>(n, config);
        final Helper<Integer> helper = sorter.getHelper();
        final Integer[] xs = helper.random(Integer.class, r -> r.nextInt(100));
        final Integer[] sorted = sorter.sort(xs);
        assertTrue(helper.sorted(sorted));
    }

    @Test
    public void testSortWithInstrumenting3() throws Exception {
        int n = 1000;
        final SortWithHelper<Integer> sorter = new QuickSort_DualPivot<>(n, config);
        final Helper<Integer> helper = sorter.getHelper();
        final Integer[] xs = helper.random(Integer.class, r -> r.nextInt(1000));
        final Integer[] sorted = sorter.sort(xs);
        assertTrue(helper.sorted(sorted));
    }

    @Test
    public void testSortWithInstrumenting4() throws Exception {
        int n = 1000;
        final SortWithHelper<Integer> sorter = new QuickSort_DualPivot<>(n, config);
        final Helper<Integer> helper = sorter.getHelper();
        final Integer[] xs = helper.random(Integer.class, r -> r.nextInt(10000));
        final Integer[] sorted = sorter.sort(xs);
        assertTrue(helper.sorted(sorted));
    }

    @Test
    public void testSortWithInstrumenting5() throws Exception {
        int n = 1000;
        final SortWithHelper<Integer> sorter = new QuickSort_DualPivot<>(n, config);
        final Helper<Integer> helper = sorter.getHelper();
        final Integer[] xs = helper.random(Integer.class, r -> r.nextInt(10000));
        final Integer[] sorted = sorter.sort(xs);
        assertTrue(helper.sorted(sorted));
    }

    @Test
    public void testSortWithInstrumenting6a() throws Exception {
        Integer[] xs = new Integer[2];
        xs[0] = 3;
        xs[1] = 4;
        final Config config = Config.setupConfig("true", "0", "1", "1", "");
        final BaseHelper<Integer> helper = new InstrumentedHelper<>("test", config);
        QuickSort<Integer> sorter = new QuickSort_DualPivot<>(helper);
        Integer[] ys = sorter.sort(xs);
        assertTrue(helper.sorted(ys));
        helper.postProcess(ys);
        assertEquals(Integer.valueOf(3), ys[0]);
        assertEquals(Integer.valueOf(4), ys[1]);
        final PrivateMethodTester privateMethodTester = new PrivateMethodTester(helper);
        final StatPack statPack = (StatPack) privateMethodTester.invokePrivate("getStatPack");
        assertEquals(1, (int) statPack.getStatistics(InstrumentedHelper.COMPARES).mean());
        assertEquals(2, (int) statPack.getStatistics(InstrumentedHelper.SWAPS).mean()); // XXX check this
        assertEquals(0, (int) statPack.getStatistics(InstrumentedHelper.FIXES).mean());
        assertEquals(12, (int) statPack.getStatistics(InstrumentedHelper.HITS).mean()); // XXX Is this correct? Why not just 2?
    }

    @Test
    public void testSortWithInstrumenting6b() throws Exception {
        Integer[] xs = new Integer[2];
        xs[0] = 4;
        xs[1] = 3;
        final Config config = Config.setupConfig("true", "0", "1", "1", "");
        final BaseHelper<Integer> helper = new InstrumentedHelper<>("test", config);
        QuickSort<Integer> sorter = new QuickSort_DualPivot<>(helper);
        Integer[] ys = sorter.sort(xs);
        assertTrue(helper.sorted(ys));
        helper.postProcess(ys);
        assertEquals(Integer.valueOf(3), ys[0]);
        assertEquals(Integer.valueOf(4), ys[1]);
        final PrivateMethodTester privateMethodTester = new PrivateMethodTester(helper);
        final StatPack statPack = (StatPack) privateMethodTester.invokePrivate("getStatPack");
        assertEquals(1, (int) statPack.getStatistics(InstrumentedHelper.COMPARES).mean());
        assertEquals(3, (int) statPack.getStatistics(InstrumentedHelper.SWAPS).mean()); // XXX check this
        assertEquals(1, (int) statPack.getStatistics(InstrumentedHelper.FIXES).mean());
        assertEquals(14, (int) statPack.getStatistics(InstrumentedHelper.HITS).mean()); // XXX Is this correct? Why not just 4?
    }

    @Test
    public void testSortWithInstrumenting6c() throws Exception {
        Integer[] xs = new Integer[3];
        xs[0] = 4;
        xs[1] = 3;
        xs[2] = 5;
        final Config config = Config.setupConfig("true", "0", "1", "1", "");
        final BaseHelper<Integer> helper = new InstrumentedHelper<>("test", config);
        QuickSort<Integer> sorter = new QuickSort_DualPivot<>(helper);
        Integer[] ys = sorter.sort(xs);
        assertTrue(helper.sorted(ys));
        helper.postProcess(ys);
        assertEquals(Integer.valueOf(3), ys[0]);
        assertEquals(Integer.valueOf(4), ys[1]);
        assertEquals(Integer.valueOf(5), ys[2]);
        final PrivateMethodTester privateMethodTester = new PrivateMethodTester(helper);
        final StatPack statPack = (StatPack) privateMethodTester.invokePrivate("getStatPack");
        assertEquals(2, (int) statPack.getStatistics(InstrumentedHelper.COMPARES).mean());
        assertEquals(5, (int) statPack.getStatistics(InstrumentedHelper.SWAPS).mean()); // XXX How can this be right?
        assertEquals(1, (int) statPack.getStatistics(InstrumentedHelper.FIXES).mean());
        assertEquals(27, (int) statPack.getStatistics(InstrumentedHelper.HITS).mean()); // XXX Is this correct?
    }

    @Test
    public void testSortWithInstrumenting7() throws Exception {
        Integer[] xs = new Integer[4];
        // Inversions = 5
        xs[0] = 3;
        xs[1] = 4;
        xs[2] = 2;
        xs[3] = 1;
        final Config config = Config.setupConfig("true", "0", "1", "1", "");
        final BaseHelper<Integer> helper = new InstrumentedHelper<>("test", config);
        QuickSort<Integer> sorter = new QuickSort_DualPivot<>(helper);
        Integer[] ys = sorter.sort(xs);
        assertTrue(helper.sorted(ys));
        helper.postProcess(ys);
        assertEquals(Integer.valueOf(1), ys[0]);
        assertEquals(Integer.valueOf(2), ys[1]);
        assertEquals(Integer.valueOf(3), ys[2]);
        assertEquals(Integer.valueOf(4), ys[3]);
        final PrivateMethodTester privateMethodTester = new PrivateMethodTester(helper);
        final StatPack statPack = (StatPack) privateMethodTester.invokePrivate("getStatPack");
        assertEquals(5, (int) statPack.getStatistics(InstrumentedHelper.COMPARES).mean());
        assertEquals(8, (int) statPack.getStatistics(InstrumentedHelper.SWAPS).mean()); // XXX how can this be right?
        assertEquals(3, (int) statPack.getStatistics(InstrumentedHelper.FIXES).mean());
        assertEquals(42, (int) statPack.getStatistics(InstrumentedHelper.HITS).mean()); // XXX how can this be right?
    }

    @Test
    public void testPartition1() throws Exception {
        String testString = "PBAXWPPVPCPDZY";
        char[] charArray = testString.toCharArray();
        Character[] array = new Character[charArray.length];
        for (int i = 0; i < array.length; i++) array[i] = charArray[i];
        final Config config = Config.setupConfig("true", "0", "1", "", "");
        QuickSort<Character> sorter = new QuickSort_DualPivot<Character>(array.length, config);
        Partitioner<Character> partitioner = sorter.partitioner;
        List<Partition<Character>> partitions = partitioner.partition(QuickSort.createPartition(array));
        assertEquals(3, partitions.size());
        Partition<Character> p0 = partitions.get(0);
        assertEquals(0, p0.from);
        assertEquals(4, p0.to);
        Partition<Character> p1 = partitions.get(1);
        assertEquals(5, p1.from);
        assertEquals(12, p1.to);
        Partition<Character> p2 = partitions.get(2);
        assertEquals(13, p2.from);
        assertEquals(14, p2.to);
        char[] chars = new char[array.length];
        for (int i = 0; i < chars.length; i++) chars[i] = array[i];
        String partitionedString = new String(chars);
        assertEquals("DBACPPPVPXPWYZ", partitionedString);
    }

    @Test
    public void testPartition2() throws Exception {
        String testString = "SEAYRLFVZQTCMK";
        char[] charArray = testString.toCharArray();
        Character[] array = new Character[charArray.length];
        for (int i = 0; i < array.length; i++) array[i] = charArray[i];
        final Config config = Config.setupConfig("true", "0", "1", "", "");
        QuickSort<Character> sorter = new QuickSort_DualPivot<Character>(array.length, config);
        Partitioner<Character> partitioner = sorter.partitioner;
        List<Partition<Character>> partitions = partitioner.partition(QuickSort.createPartition(array));
        assertEquals(3, partitions.size());
        Partition<Character> p0 = partitions.get(0);
        assertEquals(0, p0.from);
        assertEquals(4, p0.to);
        Partition<Character> p1 = partitions.get(1);
        assertEquals(5, p1.from);
        assertEquals(9, p1.to);
        Partition<Character> p2 = partitions.get(2);
        assertEquals(10, p2.from);
        assertEquals(14, p2.to);
        char[] chars = new char[array.length];
        for (int i = 0; i < chars.length; i++) chars[i] = array[i];
        String partitionedString = new String(chars);
        assertEquals("CEAFKLMRQSZVYT", partitionedString);
    }

    @Test
    public void testSortDetailed() throws Exception {
        int k = 7;
        int N = (int) Math.pow(2, k);
        // NOTE this depends on the cutoff value for quick sort.
        int levels = k - 2;
        final Config config = Config.setupConfig("true", "0", "1", "", "");
        final BaseHelper<Integer> helper = (BaseHelper<Integer>) HelperFactory.create("quick sort dual pivot", N, config);
        System.out.println(helper);
        Sort<Integer> s = new QuickSort_DualPivot<>(helper);
        s.init(N);
        final Integer[] xs = helper.random(Integer.class, r -> r.nextInt(10000));
//        System.out.println(xs.length);
//        for(int ki=0;ki<xs.length;ki++)
//            System.out.print(xs[ki]+" ");
        assertEquals(Integer.valueOf(1360), xs[0]);
        helper.preProcess(xs);
        Integer[] ys = s.sort(xs);
        assertTrue(helper.sorted(ys));
        helper.postProcess(ys);
        final PrivateMethodTester privateMethodTester = new PrivateMethodTester(helper);
        final StatPack statPack = (StatPack) privateMethodTester.invokePrivate("getStatPack");
        System.out.println(statPack);
        final int compares = (int) statPack.getStatistics(InstrumentedHelper.COMPARES).mean();
        final int inversions = (int) statPack.getStatistics(InstrumentedHelper.INVERSIONS).mean();
        final int fixes = (int) statPack.getStatistics(InstrumentedHelper.FIXES).mean();
        final int swaps = (int) statPack.getStatistics(InstrumentedHelper.SWAPS).mean();
        final int copies = (int) statPack.getStatistics(InstrumentedHelper.COPIES).mean();
        final int worstCompares = round(2.0 * N * Math.log(N));
        System.out.println("compares: " + compares + ", worstCompares: " + worstCompares);
        assertTrue(compares <= worstCompares);
        assertTrue(inversions <= fixes);
    }

    @Test
    public void testPartitionWithSort() {
        String[] xs = new String[]{"g", "f", "e", "d", "c", "b", "a"};
        int n = xs.length;
        final Config config = Config.setupConfig("true", "0", "1", "", "");
        final BaseHelper<String> helper = new InstrumentedHelper<>("test", config);
        final PrivateMethodTester privateMethodTester = new PrivateMethodTester(helper);
        QuickSort<String> sorter = new QuickSort_DualPivot<>(helper);
        int inversions = n * (n - 1) / 2;
        assertEquals(inversions, helper.inversions(xs));
        Partitioner<String> partitioner = sorter.createPartitioner();
        List<Partition<String>> partitions = partitioner.partition(new Partition<>(xs, 0, xs.length));
        assertEquals(11, privateMethodTester.invokePrivate("getFixes"));
        Partition<String> p0 = partitions.get(0);
        sorter.sort(xs, 0, p0.to, 0);
        assertEquals(11, privateMethodTester.invokePrivate("getFixes"));
        Partition<String> p1 = partitions.get(1);
        sorter.sort(xs, p1.from, p1.to, 0);
        assertEquals(21, privateMethodTester.invokePrivate("getFixes"));
        Partition<String> p2 = partitions.get(2);
        sorter.sort(xs, p2.from, n, 0);
        int fixes = (int) privateMethodTester.invokePrivate("getFixes");
        // NOTE: there are at least as many fixes as inversions -- sort methods aren't necessarily perfectly efficient in terms of swaps.
        assertTrue(inversions <= fixes);
        assertEquals(0, helper.inversions(xs));
        assertEquals(13, privateMethodTester.invokePrivate("getSwaps"));
    }

    private static String[] setupWords(final int n) {
        if (n > 36) throw new RuntimeException("cannot have n > 36");
        String alphabet = "abcdefghijklmnopqrstuvwxyz0123456789";
        String[] words = new String[n * n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                words[i * n + j] = alphabet.substring(i, i + 1) + alphabet.substring(j, j + 1);
        return words;
    }

    final static LazyLogger logger = new LazyLogger(QuickSort_DualPivot.class);

    @BeforeClass
    public static void beforeClass() throws IOException {
        config = Config.load();
    }

    private static Config config;
}