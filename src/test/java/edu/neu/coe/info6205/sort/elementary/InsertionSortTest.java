/*
 * Copyright (c) 2017. Phasmid Software
 */

package edu.neu.coe.info6205.sort.elementary;

import edu.neu.coe.info6205.sort.*;
import edu.neu.coe.info6205.util.*;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@SuppressWarnings("ALL")
public class InsertionSortTest {

    @Test
    public void sort0() throws Exception {
        final List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        Integer[] xs = list.toArray(new Integer[0]);
        final Config config = Config.setupConfig("true", "0", "1", "", "");
        Helper<Integer> helper = HelperFactory.create("InsertionSort", list.size(), config);
        helper.init(list.size());
        final PrivateMethodTester privateMethodTester = new PrivateMethodTester(helper);
        final StatPack statPack = (StatPack) privateMethodTester.invokePrivate("getStatPack");
        SortWithHelper<Integer> sorter = new InsertionSort<Integer>(helper);
        sorter.preProcess(xs);
        Integer[] ys = sorter.sort(xs);
        assertTrue(helper.sorted(ys));
        sorter.postProcess(ys);
        final int compares = (int) statPack.getStatistics(InstrumentedHelper.COMPARES).mean();
        assertEquals(list.size() - 1, compares);
        final int inversions = (int) statPack.getStatistics(InstrumentedHelper.INVERSIONS).mean();
        assertEquals(0L, inversions);
        final int fixes = (int) statPack.getStatistics(InstrumentedHelper.FIXES).mean();
        assertEquals(inversions, fixes);
    }

    @Test
    public void sort1() throws Exception {
        final List<Integer> list = new ArrayList<>();
        list.add(3);
        list.add(4);
        list.add(2);
        list.add(1);
        Integer[] xs = list.toArray(new Integer[0]);
        BaseHelper<Integer> helper = new BaseHelper<>("InsertionSort", xs.length, Config.load(InsertionSortTest.class));
        GenericSort<Integer> sorter = new InsertionSort<Integer>(helper);
        Integer[] ys = sorter.sort(xs);
        assertTrue(helper.sorted(ys));
        System.out.println(sorter.toString());
    }

    @Test
    public void testMutatingInsertionSort() throws IOException {
        final List<Integer> list = new ArrayList<>();
        list.add(3);
        list.add(4);
        list.add(2);
        list.add(1);
        Integer[] xs = list.toArray(new Integer[0]);
        BaseHelper<Integer> helper = new BaseHelper<>("InsertionSort", xs.length, Config.load(InsertionSortTest.class));
        GenericSort<Integer> sorter = new InsertionSort<Integer>(helper);
        sorter.mutatingSort(xs);
        assertTrue(helper.sorted(xs));
    }

    @Test
    public void testStaticInsertionSort() throws IOException {
        final List<Integer> list = new ArrayList<>();
        list.add(3);
        list.add(4);
        list.add(2);
        list.add(1);
        Integer[] xs = list.toArray(new Integer[0]);
        InsertionSort.sort(xs);
        assertTrue(xs[0] < xs[1] && xs[1] < xs[2] && xs[2] < xs[3]);
    }

    @Test
    public void sort2() throws Exception {
        final Config config = Config.setupConfig("true", "0", "1", "", "");
        int n = 100;
        Helper<Integer> helper = HelperFactory.create("InsertionSort", n, config);
        helper.init(n);
        final PrivateMethodTester privateMethodTester = new PrivateMethodTester(helper);
        final StatPack statPack = (StatPack) privateMethodTester.invokePrivate("getStatPack");
        Integer[] xs = helper.random(Integer.class, r -> r.nextInt(1000));
        SortWithHelper<Integer> sorter = new InsertionSort<Integer>(helper);
        sorter.preProcess(xs);
        Integer[] ys = sorter.sort(xs);
        assertTrue(helper.sorted(ys));
        sorter.postProcess(ys);
        final int compares = (int) statPack.getStatistics(InstrumentedHelper.COMPARES).mean();
        // NOTE: these are suppoed to match within about 12%.
        // Since we set a specific seed, this should always succeed.
        // If we use true random seed and this test fails, just increase the delta a little.
        assertEquals(1.0, 4.0 * compares / n / (n - 1), 0.12);
        final int inversions = (int) statPack.getStatistics(InstrumentedHelper.INVERSIONS).mean();
        final int fixes = (int) statPack.getStatistics(InstrumentedHelper.FIXES).mean();
        System.out.println(statPack);
        assertEquals(inversions, fixes);
    }

    @Test
    public void sort3() throws Exception {
        final Config config = Config.setupConfig("true", "0", "1", "", "");
        int n = 100;
        Helper<Integer> helper = HelperFactory.create("InsertionSort", n, config);
        helper.init(n);
        final PrivateMethodTester privateMethodTester = new PrivateMethodTester(helper);
        final StatPack statPack = (StatPack) privateMethodTester.invokePrivate("getStatPack");
        Integer[] xs = new Integer[n];
        for (int i = 0; i < n; i++) xs[i] = n - i;
        SortWithHelper<Integer> sorter = new InsertionSort<Integer>(helper);
        sorter.preProcess(xs);
        Integer[] ys = sorter.sort(xs);
        assertTrue(helper.sorted(ys));
        sorter.postProcess(ys);
        final int compares = (int) statPack.getStatistics(InstrumentedHelper.COMPARES).mean();
        // NOTE: these are suppoed to match within about 12%.
        // Since we set a specific seed, this should always succeed.
        // If we use true random seed and this test fails, just increase the delta a little.
        assertEquals(4950, compares);
        final int inversions = (int) statPack.getStatistics(InstrumentedHelper.INVERSIONS).mean();
        final int fixes = (int) statPack.getStatistics(InstrumentedHelper.FIXES).mean();
        System.out.println(statPack);
        assertEquals(inversions, fixes);
    }

    @Test
    public void orderedTestCase() throws Exception{
        final Timer timer = new Timer();
        int n = 51200;
        final Config config = Config.setupConfig("true", "0", "1", "", "");
        Helper<Integer> helper = HelperFactory.create("InsertionSort", n, config);
        SortWithHelper<Integer> sorter = new InsertionSort<Integer>(helper);
        final double mean = timer.repeat(5,() -> {
                    helper.init(n);
                    Integer[] xs = new Integer[n];
                    return xs;
                },
                xs -> {
                    Integer[] ys = sorter.sort(xs);
                    return ys;
                },
                xs ->{
                    for (int i = 0; i < n; i++) xs[i] = i;
                    sorter.preProcess(xs);
                    return xs;
                },
                ys ->{
                    assertTrue(helper.sorted(ys));
                    sorter.postProcess(ys);
                }
        );
        System.out.println("orderedTestCase time : "+mean);
    }

    @Test
    public void revereseOrderTestCase() throws Exception{
        final Timer timer = new Timer();
        int n =51200;
        final Config config = Config.setupConfig("true", "0", "1", "", "");
        Helper<Integer> helper = HelperFactory.create("InsertionSort", n, config);
        SortWithHelper<Integer> sorter = new InsertionSort<Integer>(helper);
        final double mean = timer.repeat(5,() -> {
                    helper.init(n);
                    Integer[] xs = new Integer[n];
                    return xs;
                },

                //function
                xs -> {
                    Integer[] ys = sorter.sort(xs);
                    return ys;
                },
                //pre-function
                xs ->{
                    for (int i = 0; i < n; i++) xs[i] = n - i;
                    sorter.preProcess(xs);
                    return xs;
                },
                //post-function
                ys ->{
                    assertTrue(helper.sorted(ys));
                    sorter.postProcess(ys);
                }
        );
        System.out.println("revereseOrderTestCase time : "+mean);
    }

    @Test
    public void randomOrderTestCase() throws Exception{
        final Timer timer = new Timer();
        int n =51200;
        final Config config = Config.setupConfig("true", "0", "1", "", "");
        Helper<Integer> helper = HelperFactory.create("InsertionSort", n, config);
        SortWithHelper<Integer> sorter = new InsertionSort<Integer>(helper);
        final double mean = timer.repeat(5,() -> {
                    helper.init(n);
                    Integer[] xs = new Integer[n];
                    return xs;
                },

                //function
                xs -> {
                    Integer[] ys = sorter.sort(xs);
                    return ys;
                },
                xs ->{
                    xs = helper.random(Integer.class, r -> r.nextInt(1000));
                    sorter.preProcess(xs);
                    return xs;
                },
                ys ->{
                    assertTrue(helper.sorted(ys));
                    sorter.postProcess(ys);
                }
        );
        System.out.println("randomOrderTestCase time : "+mean);
    }

    @Test
    public void partiallyOrderedTestCase() throws Exception{
        final Timer timer = new Timer();
        int n = 51200;
        final Config config = Config.setupConfig("true", "0", "1", "", "");
        Helper<Integer> helper = HelperFactory.create("InsertionSort", n, config);
        SortWithHelper<Integer> sorter = new InsertionSort<Integer>(helper);
        final double mean = timer.repeat(5,() -> {
                    helper.init(n);
                    Integer[] xs = new Integer[n];
                    return xs;
                },
                xs -> {
                    Integer[] ys = sorter.sort(xs);
                    return ys;
                },
                xs ->{
                    Random rand = new Random();
                    int sortIndex = rand.nextInt(n);
                    for (int i = 0; i < n; i++) xs[i] = rand.nextInt(n);
                    for (int i = 0; i < sortIndex; i++) {
                        for (int j = i + 1; j < sortIndex; j++) {
                            if (xs[i] > xs[j]) {
                                int temp = xs[i];
                                xs[i] = xs[j];
                                xs[j] = temp;
                            }
                        }
                    }
                    sorter.preProcess(xs);
                    return xs;
                },
                ys ->{
                    assertTrue(helper.sorted(ys));
                    sorter.postProcess(ys);
                }
        );
        System.out.println("partiallyOrderedTestCase time : "+mean);
    }

    final static LazyLogger logger = new LazyLogger(InsertionSort.class);

}