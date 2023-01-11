package edu.neu.coe.info6205.sort;

import edu.neu.coe.info6205.sort.linearithmic.MergeSortBasic;
import edu.neu.coe.info6205.util.Config;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class HelperTest {

    private static Helper<Integer> helper;

    @BeforeClass
    public static void setup() throws IOException {
        Config config = Config.load();
        helper = new InstrumentedHelper<>("test helper", 0, 0L, config);
    }

    @Test
    public void testCompare() {
        for (int i = 0; i < 100; i++) {
            Integer[] pair = helper.randomPair(Integer.class, r -> r.nextInt(10));
            int cf0 = Integer.compare(pair[0], pair[1]);
            int cf1 = helper.compare(pair[0], pair[1]);
            int cf2 = helper.compare(pair, 0, 1);
            int cf3 = helper.compare(pair, 0, pair[1]);
            assertEquals(cf0, cf1);
            assertEquals(cf0, cf2);
            assertEquals(cf0, cf3);
        }
    }

    @Test
    public void testLess() {
        for (int i = 0; i < 100; i++) {
            Integer[] pair = helper.randomPair(Integer.class, r -> r.nextInt(10));
            boolean cf0 = pair[0] < pair[1];
            boolean cf1 = helper.less(pair[0], pair[1]);
            boolean cf2 = helper.less(pair, 0, 1);
            boolean cf3 = helper.less(pair, 0, pair[1]);
            assertEquals(cf0, cf1);
            assertEquals(cf0, cf2);
            assertEquals(cf0, cf3);
        }
    }

    @Test
    public void swapConditional() {
        for (int i = 0; i < 100; i++) {
            Integer[] pair = helper.randomPair(Integer.class, r -> r.nextInt(10));
            int cf0 = Integer.compare(pair[0], pair[1]);
            boolean swapped = helper.swapConditional(pair, 0, 1);
            int cf1 = Integer.compare(pair[0], pair[1]);
            assertEquals(cf0 > 0, swapped);
            assertTrue(cf1 <= 0);
        }
    }

    @Test
    public void swapStableConditional() {
        for (int i = 0; i < 100; i++) {
            Integer[] pair = helper.randomPair(Integer.class, r -> r.nextInt(10));
            int cf0 = Integer.compare(pair[0], pair[1]);
            boolean swapped = helper.swapStableConditional(pair, 1);
            int cf1 = Integer.compare(pair[0], pair[1]);
            assertEquals(cf0 > 0, swapped);
            assertTrue(cf1 <= 0);
        }
    }

    @Test
    public void swapInto() {
    }

    @Test
    public void swapIntoSorted() {
    }

    @Test
    public void fixInversion() {
    }

    @Test
    public void testFixInversion() {
    }

    @Test
    public void sorted() {
    }

    @Test
    public void inversions0() {
        assertEquals(0, MergeSortBasic.countInversions(new Integer[]{1, 2, 3}));
        assertEquals(1, MergeSortBasic.countInversions(new Integer[]{1, 3, 2}));
        assertEquals(1, MergeSortBasic.countInversions(new Integer[]{2, 1, 3}));
        assertEquals(2, MergeSortBasic.countInversions(new Integer[]{2, 3, 1}));
        assertEquals(2, MergeSortBasic.countInversions(new Integer[]{3, 1, 2}));
        assertEquals(3, MergeSortBasic.countInversions(new Integer[]{3, 2, 1}));
    }

    @Test
    public void postProcess() {
    }

    @Test
    public void cutoff() {
    }

    @Test
    public void init() {
    }

    @Test
    public void incrementCopies() {
    }

    @Test
    public void incrementFixes() {
    }

    @Test
    public void incrementHits() {
    }

    @Test
    public void preProcess() {
    }

    @Test
    public void registerDepth() {
    }

    @Test
    public void maxDepth() {
    }

    @Test
    public void showStats() {
    }

    @Test
    public void showFixes() {
    }
}