package edu.neu.coe.info6205.sort;

import edu.neu.coe.info6205.sort.linearithmic.MergeSort;
import edu.neu.coe.info6205.util.Config;
import edu.neu.coe.info6205.util.PrivateMethodTester;
import edu.neu.coe.info6205.util.StatPack;
import edu.neu.coe.info6205.util.Statistics;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class InstrumentedHelperTest {

    @Test
    public void testInstrumented() {
        assertTrue(new InstrumentedHelper<String>("test", config).instrumented());
    }

    @Test
    public void testLess() {
        final Helper<String> helper = new InstrumentedHelper<>("test", config);
        assertTrue(helper.less("a", "b"));
        final PrivateMethodTester privateMethodTester = new PrivateMethodTester(helper);
        assertEquals(1, privateMethodTester.invokePrivate("getCompares"));
        assertEquals(0, privateMethodTester.invokePrivate("getSwaps"));
        assertEquals(0, privateMethodTester.invokePrivate("getHits"));
    }

    @Test
    public void testCompare() {
        String[] xs = new String[]{"a", "b"};
        final Helper<String> helper = new InstrumentedHelper<>("test", config);
        final PrivateMethodTester privateMethodTester = new PrivateMethodTester(helper);
        assertEquals(-1, helper.compare(xs, 0, 1));
        assertEquals(1, privateMethodTester.invokePrivate("getCompares"));
        assertEquals(0, helper.compare(xs, 0, 0));
        assertEquals(2, privateMethodTester.invokePrivate("getCompares"));
        assertEquals(1, helper.compare(xs, 1, 0));
        assertEquals(3, privateMethodTester.invokePrivate("getCompares"));
        assertEquals(0, privateMethodTester.invokePrivate("getSwaps"));
        assertEquals(6, privateMethodTester.invokePrivate("getHits"));
    }

    @Test
    public void testSwap1() {
        String[] xs = new String[]{"b", "a"};
        final Helper<String> helper = new InstrumentedHelper<>("test", config);
        final PrivateMethodTester privateMethodTester = new PrivateMethodTester(helper);
        assertEquals(1, helper.inversions(xs));
        assertEquals(0, privateMethodTester.invokePrivate("getFixes"));
        helper.swap(xs, 0, 1);
        assertArrayEquals(new String[]{"a", "b"}, xs);
        assertEquals(0, helper.inversions(xs));
        assertEquals(1, privateMethodTester.invokePrivate("getFixes"));
        helper.swap(xs, 0, 1);
        assertEquals(1, helper.inversions(xs));
        assertArrayEquals(new String[]{"b", "a"}, xs);
        // NOTE that we do not check fixes here because we did a non-fixing swap which will have generated an incorrect total.
        assertEquals(0, privateMethodTester.invokePrivate("getCompares"));
        assertEquals(2, privateMethodTester.invokePrivate("getSwaps"));
        assertEquals(8, privateMethodTester.invokePrivate("getHits"));
    }

    @Test
    public void testSwap2() {
        String[] xs = new String[]{"c", "b", "a"};
        final Helper<String> helper = new InstrumentedHelper<>("test", config);
        final PrivateMethodTester privateMethodTester = new PrivateMethodTester(helper);
        assertEquals(3, helper.inversions(xs));
        assertEquals(0, privateMethodTester.invokePrivate("getFixes"));
        helper.swap(xs, 0, 2);
        assertArrayEquals(new String[]{"a", "b", "c"}, xs);
        assertEquals(0, helper.inversions(xs));
        assertEquals(3, privateMethodTester.invokePrivate("getFixes"));
        helper.swap(xs, 0, 1);
        assertArrayEquals(new String[]{"b", "a", "c"}, xs);
        // NOTE that we do not check fixes here because we did a non-fixing swap which will have generated an incorrect total.
        assertEquals(0, privateMethodTester.invokePrivate("getCompares"));
        assertEquals(2, privateMethodTester.invokePrivate("getSwaps"));
        assertEquals(8, privateMethodTester.invokePrivate("getHits"));
    }

    @Test
    public void testSwap3() {
        String[] xs = new String[]{"c", "b", "d", "a"};
        final Helper<String> helper = new InstrumentedHelper<>("test", config);
        final PrivateMethodTester privateMethodTester = new PrivateMethodTester(helper);
        assertEquals(4, helper.inversions(xs));
        assertEquals(0, privateMethodTester.invokePrivate("getFixes"));
        helper.swap(xs, 0, 3);
        assertArrayEquals(new String[]{"a", "b", "d", "c"}, xs);
        assertEquals(1, helper.inversions(xs));
        assertEquals(3, privateMethodTester.invokePrivate("getFixes"));
        helper.swap(xs, 2, 3);
        assertArrayEquals(new String[]{"a", "b", "c", "d"}, xs);
        assertEquals(0, helper.inversions(xs));
        assertEquals(4, privateMethodTester.invokePrivate("getFixes"));
        assertEquals(0, privateMethodTester.invokePrivate("getCompares"));
        assertEquals(2, privateMethodTester.invokePrivate("getSwaps"));
        assertEquals(8, privateMethodTester.invokePrivate("getHits"));
    }

    @Test
    public void testSwap4() {
        String[] xs = new String[]{"c", "e", "b", "d", "a"};
        final Helper<String> helper = new InstrumentedHelper<>("test", config);
        final PrivateMethodTester privateMethodTester = new PrivateMethodTester(helper);
        assertEquals(7, helper.inversions(xs));
        assertEquals(0, privateMethodTester.invokePrivate("getFixes"));
        helper.swap(xs, 0, 4);
        assertArrayEquals(new String[]{"a", "e", "b", "d", "c"}, xs);
        assertEquals(4, helper.inversions(xs));
        assertEquals(3, privateMethodTester.invokePrivate("getFixes"));
        helper.swap(xs, 1, 4);
        assertArrayEquals(new String[]{"a", "c", "b", "d", "e"}, xs);
        assertEquals(1, helper.inversions(xs));
        assertEquals(6, privateMethodTester.invokePrivate("getFixes"));
        helper.swap(xs, 1, 2);
        assertArrayEquals(new String[]{"a", "b", "c", "d", "e"}, xs);
        assertEquals(0, helper.inversions(xs));
        assertEquals(7, privateMethodTester.invokePrivate("getFixes"));
        assertEquals(0, privateMethodTester.invokePrivate("getCompares"));
        assertEquals(3, privateMethodTester.invokePrivate("getSwaps"));
        assertEquals(12, privateMethodTester.invokePrivate("getHits"));
    }

    @Test
    public void testSwap5() {
        String[] xs = new String[]{"f", "e", "d", "c", "b", "a"};
        int n = xs.length;
        final Helper<String> helper = new InstrumentedHelper<>("test", config);
        final PrivateMethodTester privateMethodTester = new PrivateMethodTester(helper);
        int inversions = n * (n - 1) / 2;
        assertEquals(inversions, helper.inversions(xs));
        assertEquals(0, privateMethodTester.invokePrivate("getFixes"));
        helper.swap(xs, 0, n - 1);
        assertArrayEquals(new String[]{"a", "e", "d", "c", "b", "f"}, xs);
        int fixes = 2 * n - 3;
        assertEquals(fixes, privateMethodTester.invokePrivate("getFixes"));
        assertEquals(inversions - fixes, helper.inversions(xs));
        assertEquals(1, privateMethodTester.invokePrivate("getSwaps"));
        assertEquals(4, privateMethodTester.invokePrivate("getHits"));
    }

    @Test
    public void testSwap6() {
        String[] xs = new String[]{"g", "f", "e", "d", "c", "b", "a"};
        int n = xs.length;
        final Helper<String> helper = new InstrumentedHelper<>("test", config);
        final PrivateMethodTester privateMethodTester = new PrivateMethodTester(helper);
        int inversions = n * (n - 1) / 2;
        assertEquals(inversions, helper.inversions(xs));
        assertEquals(0, privateMethodTester.invokePrivate("getFixes"));
        helper.swap(xs, 0, n - 1);
        assertArrayEquals(new String[]{"a", "f", "e", "d", "c", "b", "g"}, xs);
        int fixes = 2 * n - 3;
        assertEquals(fixes, privateMethodTester.invokePrivate("getFixes"));
        assertEquals(inversions - fixes, helper.inversions(xs));
        assertEquals(1, privateMethodTester.invokePrivate("getSwaps"));
        assertEquals(4, privateMethodTester.invokePrivate("getHits"));
    }

    @Test
    public void testSorted() {
        String[] xs = new String[]{"a", "b"};
        final Helper<String> helper = new InstrumentedHelper<>("test", config);
        assertTrue(helper.sorted(xs));
        helper.swap(xs, 0, 1);
        assertFalse(helper.sorted(xs));
        final PrivateMethodTester privateMethodTester = new PrivateMethodTester(helper);
        assertEquals(0, privateMethodTester.invokePrivate("getCompares"));
        assertEquals(1, privateMethodTester.invokePrivate("getSwaps"));
        assertEquals(4, privateMethodTester.invokePrivate("getHits"));
    }

    @Test
    public void testInversions() {
        String[] xs = new String[]{"a", "b"};
        final Helper<String> helper = new InstrumentedHelper<>("test", config);
        assertEquals(0, helper.inversions(xs));
        helper.swap(xs, 0, 1);
        assertEquals(1, helper.inversions(xs));
    }

    @Test
    public void testInversionsWithDuplicates() {
        String[] xs = new String[]{"a", "a", "b", "a"};
        final Helper<String> helper = new InstrumentedHelper<>("test", config);
        assertEquals(1, helper.inversions(xs));
        helper.swap(xs, 2, 3);
        assertEquals(0, helper.inversions(xs));
    }

    @Test
    public void testPostProcess1() {
        String[] xs = new String[]{"a", "b"};
        final Helper<String> helper = new InstrumentedHelper<>("test", config);
        helper.init(3);
        helper.postProcess(xs);
    }

    @Test(expected = BaseHelper.HelperException.class)
    public void testPostProcess2() {
        String[] xs = new String[]{"b", "a"};
        final Helper<String> helper = new InstrumentedHelper<>("test", config);
        helper.postProcess(xs);
    }

    @Test
    public void testRandom() {
        String[] words = new String[]{"Hello", "World"};
        final Helper<String> helper = new InstrumentedHelper<>("test", 3, 0L, config);
        final String[] strings = helper.random(String.class, r -> words[r.nextInt(2)]);
        assertArrayEquals(new String[]{"World", "World", "Hello"}, strings);
    }

    @Test
    public void testToString() {
        final Helper<String> helper = new InstrumentedHelper<>("test", 3, config);
        assertEquals("Instrumenting helper for test with 3 elements", helper.toString());
    }

    @Test
    public void testGetDescription() {
        final Helper<String> helper = new InstrumentedHelper<>("test", 3, config);
        assertEquals("test", helper.getDescription());
    }

    @Test(expected = RuntimeException.class)
    public void testGetSetN() {
        final Helper<String> helper = new InstrumentedHelper<>("test", 3, config);
        assertEquals(3, helper.getN());
        helper.init(4);
        assertEquals(4, helper.getN());
    }

    @Test
    public void testGetSetNBis() {
        final Helper<String> helper = new InstrumentedHelper<>("test", config);
        assertEquals(0, helper.getN());
        helper.init(4);
        assertEquals(4, helper.getN());
    }

    @Test
    public void testClose() {
        final Helper<String> helper = new InstrumentedHelper<>("test", config);
        helper.close();
    }

    @Test
    public void testSwapStable() {
        String[] xs = new String[]{"a", "b"};
        final Helper<String> helper = new InstrumentedHelper<>("test", config);
        helper.swapStable(xs, 1);
        assertArrayEquals(new String[]{"b", "a"}, xs);
        helper.swapStable(xs, 1);
        assertArrayEquals(new String[]{"a", "b"}, xs);
        final PrivateMethodTester privateMethodTester = new PrivateMethodTester(helper);
        assertEquals(0, privateMethodTester.invokePrivate("getCompares"));
        assertEquals(2, privateMethodTester.invokePrivate("getSwaps"));
        assertEquals(8, privateMethodTester.invokePrivate("getHits"));
    }

    @Test
    public void testFixInversion1() {
        String[] xs = new String[]{"a", "b"};
        final Helper<String> helper = new InstrumentedHelper<>("test", config);
        final PrivateMethodTester privateMethodTester = new PrivateMethodTester(helper);
        helper.fixInversion(xs, 1); // XXX Deprecated
        assertEquals(1, privateMethodTester.invokePrivate("getCompares"));
        assertEquals(0, privateMethodTester.invokePrivate("getSwaps"));
        assertEquals(2, privateMethodTester.invokePrivate("getHits"));
        assertArrayEquals(new String[]{"a", "b"}, xs);
        helper.swapStable(xs, 1);
        assertArrayEquals(new String[]{"b", "a"}, xs);
        helper.fixInversion(xs, 1); // XXX Deprecated
        assertArrayEquals(new String[]{"a", "b"}, xs);
        assertEquals(2, privateMethodTester.invokePrivate("getCompares"));
        assertEquals(2, privateMethodTester.invokePrivate("getSwaps"));
        assertEquals(10, privateMethodTester.invokePrivate("getHits"));
    }

    @Test
    public void testFixInversion2() {
        String[] xs = new String[]{"a", "b"};
        final Helper<String> helper = new InstrumentedHelper<>("test", config);
        final PrivateMethodTester privateMethodTester = new PrivateMethodTester(helper);
        helper.fixInversion(xs, 0, 1);
        assertEquals(1, privateMethodTester.invokePrivate("getCompares"));
        assertEquals(0, privateMethodTester.invokePrivate("getSwaps"));
        assertEquals(2, privateMethodTester.invokePrivate("getHits"));
        assertArrayEquals(new String[]{"a", "b"}, xs);
        helper.swap(xs, 0, 1);
        assertArrayEquals(new String[]{"b", "a"}, xs);
        helper.fixInversion(xs, 0, 1);
        assertArrayEquals(new String[]{"a", "b"}, xs);
        assertEquals(2, privateMethodTester.invokePrivate("getCompares"));
        assertEquals(2, privateMethodTester.invokePrivate("getSwaps"));
        assertEquals(10, privateMethodTester.invokePrivate("getHits"));
    }

    @Test
    public void testMergeSort() {
        int N = 8;
        final Helper<Integer> helper = new InstrumentedHelper<>("test", config);
        final PrivateMethodTester privateMethodTester = new PrivateMethodTester(helper);
        Sort<Integer> s = new MergeSort<>(helper);
        s.init(N);
        final Integer[] xs = helper.random(Integer.class, r -> r.nextInt(1000));
        s.sort(xs);
        final int compares = (Integer) privateMethodTester.invokePrivate("getCompares");
        assertTrue(compares <= 20 && compares >= 11);
    }

    @SuppressWarnings("unused")
    @Ignore // FIXME fix this test
    public void testMergeSortMany() {
        int N = 8;
        int m = 10;
        final Helper<Integer> helper = new InstrumentedHelper<>("test", config);
        final PrivateMethodTester privateMethodTester = new PrivateMethodTester(helper);
        Sort<Integer> s = new MergeSort<>(helper);
        s.init(N);
        for (int i = 0; i < m; i++) {
            final Integer[] xs = helper.random(Integer.class, r -> r.nextInt(1000));
            Integer[] ys = s.sort(xs);
            helper.postProcess(ys);
        }
        final StatPack statPack = (StatPack) privateMethodTester.invokePrivate("getStatPack");
        final Statistics statistics = statPack.getStatistics(InstrumentedHelper.COMPARES);
        System.out.println(statistics);
        final int compares = statPack.getCount(InstrumentedHelper.COMPARES);
        System.out.println(statPack);
        assertTrue(12 <= compares && compares <= 17);
    }

    @BeforeClass
    public static void beforeClass() {
        config = Config.setupConfig("true", "0", "10", "1", "");
    }

    private static Config config;

    @SuppressWarnings("EmptyMethod")
    @Test
    // TEST
    public void swapInto() {
    }

    @Test
    public void testSwapConditional1() {
        String[] xs = new String[]{"c", "b", "a"};
        final Helper<String> helper = new InstrumentedHelper<>("test", config);
        assertFalse(helper.sorted(xs));
        helper.swapConditional(xs, 0, 2);
        assertTrue(helper.sorted(xs));
        final PrivateMethodTester privateMethodTester = new PrivateMethodTester(helper);
        assertEquals(1, privateMethodTester.invokePrivate("getCompares"));
        assertEquals(1, privateMethodTester.invokePrivate("getSwaps"));
        assertEquals(4, privateMethodTester.invokePrivate("getHits"));
    }

    @Test
    // TEST
    public void swapStableConditional() {
    }

    @Test
    public void copy() {
        String[] xs = new String[]{"c", "b", "a"};
        String[] ys = new String[3];
        final Helper<String> helper = new InstrumentedHelper<>("test", config);
        helper.copy(xs, 2, ys, 0);
        final PrivateMethodTester privateMethodTester = new PrivateMethodTester(helper);
        assertEquals(1, privateMethodTester.invokePrivate("getCopies"));
        helper.copy(xs, 1, ys, 1);
        assertEquals(2, privateMethodTester.invokePrivate("getCopies"));
        helper.copy(xs, 0, ys, 2);
        assertEquals(3, privateMethodTester.invokePrivate("getCopies"));
        assertTrue(helper.sorted(ys));
    }

    @Test
    public void incrementCopies() {
        final Helper<String> helper = new InstrumentedHelper<>("test", config);
        helper.incrementCopies(3);
        final PrivateMethodTester privateMethodTester = new PrivateMethodTester(helper);
        assertEquals(3, privateMethodTester.invokePrivate("getCopies"));
    }

    @Test
    public void incrementFixes() {
        final Helper<String> helper = new InstrumentedHelper<>("test", config);
        helper.incrementFixes(42);
        final PrivateMethodTester privateMethodTester = new PrivateMethodTester(helper);
        assertEquals(42, privateMethodTester.invokePrivate("getFixes"));
    }

    @Test
    // TEST
    public void cutoff() {
    }

    @Test
    // TEST
    public void testToString1() {
    }

    @Test
    // TEST
    public void init() {
    }

    @Test
    // TEST
    public void preProcess() {
    }

    @SuppressWarnings("EmptyMethod")
    @Test
    // TEST
    public void postProcess() {
    }

    @Test
    public void testIncrementFixes0() {
        final List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(4);
        list.add(3);
        list.add(2);
        Integer[] xs = list.toArray(new Integer[0]);
        final Config config = Config.setupConfig("true", "0", "1", "", "");
        int n = xs.length;
        Helper<Integer> helper = HelperFactory.create("RandomSort", n, 0L, config);
        helper.init(n);
        int i0 = helper.inversions(xs);
        helper.swap(xs, 2, 3);
        assertEquals("fixes+inversions: " + i0, helper.showFixes(xs));
        helper.swap(xs, 1, 2);
        assertEquals("fixes+inversions: " + i0, helper.showFixes(xs));
    }

    @Test
    public void testIncrementFixes1() {
        final List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(6);
        list.add(4);
        list.add(3);
        list.add(2);
        Integer[] xs = list.toArray(new Integer[0]);
        final Config config = Config.setupConfig("true", "0", "1", "", "");
        int n = xs.length;
        Helper<Integer> helper = HelperFactory.create("RandomSort", n, 0L, config);
        helper.init(n);
        int i0 = helper.inversions(xs);
        helper.swap(xs, 1, 3);
        assertEquals("fixes+inversions: " + i0, helper.showFixes(xs));
        helper.swap(xs, 1, 4);
        assertEquals("fixes+inversions: " + i0, helper.showFixes(xs));
        helper.swap(xs, 2, 4);
        assertEquals("fixes+inversions: " + i0, helper.showFixes(xs));
        helper.swap(xs, 3, 4);
        assertEquals("fixes+inversions: " + i0, helper.showFixes(xs));
        assertTrue(helper.sorted(xs));
    }

    @Test
    public void testIncrementFixes2() {
        final Config config = Config.setupConfig("true", "0", "1", "", "");
        int n = 10;
        Helper<Integer> helper = HelperFactory.create("RandomSort", n, 0L, config);
        helper.init(n);
        Integer[] xs = helper.random(Integer.class, r -> r.nextInt(1000));
        helper.preProcess(xs);
        System.out.println(Arrays.toString(xs));
        int i0 = helper.inversions(xs);
        assertEquals(14, i0);
        assertFalse(helper.swapConditional(xs, 3, 8));
        assertEquals("fixes+inversions: " + i0, helper.showFixes(xs));
        assertFalse(helper.swapConditional(xs, 4, 7));
        assertEquals("fixes+inversions: " + i0, helper.showFixes(xs));
        assertFalse(helper.swapConditional(xs, 2, 4));
        assertEquals("fixes+inversions: " + i0, helper.showFixes(xs));
        assertTrue(helper.swapConditional(xs, 4, 5));
        assertEquals("fixes+inversions: " + i0, helper.showFixes(xs));
        assertFalse(helper.swapConditional(xs, 6, 4));
        // FIXME this fails because indexes are out of usual order
        assertEquals("fixes+inversions: " + i0, helper.showFixes(xs));
        System.out.println(Arrays.toString(xs));
    }

}