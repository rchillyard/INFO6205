package edu.neu.coe.info6205.sort;

import edu.neu.coe.info6205.util.Config;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class BaseHelperTest {

    @BeforeClass
    public static void setupClass() {
        try {
            config = Config.load(BaseHelperTest.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class BaseHelperWithSortedTest<X extends Comparable<X>> extends BaseHelper<X> {

        public BaseHelperWithSortedTest() {
            super("test", BaseHelperTest.config);
        }

        public BaseHelperWithSortedTest(int i, long l) {
            super("test", i, l, BaseHelperTest.config);
        }

        /**
         * Method to post-process the array xs after sorting.
         * By default, this method does nothing.
         *
         * @param xs the array to be tested.
         */
        @Override
        public void postProcess(X[] xs) {
            if (!sorted(xs)) throw new BaseHelper.HelperException("Array is not sorted");
        }
    }

    @Test
    public void instrumented() {
        assertFalse(new BaseHelperWithSortedTest<String>().instrumented());
    }

    @Test
    public void less() {
        assertTrue(new BaseHelperWithSortedTest<String>().less("a", "b"));
    }

    @Test
    public void compare() {
        String[] xs = new String[]{"a", "b"};
        final Helper<String> helper = new BaseHelperWithSortedTest<>();
        assertEquals(-1, helper.compare(xs, 0, 1));
        assertEquals(0, helper.compare(xs, 0, 0));
        assertEquals(1, helper.compare(xs, 1, 0));
    }

    @Test
    public void swap() {
        String[] xs = new String[]{"a", "b"};
        final Helper<String> helper = new BaseHelperWithSortedTest<>();
        helper.swap(xs, 0, 1);
        assertArrayEquals(new String[]{"b", "a"}, xs);
        helper.swap(xs, 0, 1);
        assertArrayEquals(new String[]{"a", "b"}, xs);
    }

    @Test
    public void sorted() {
        String[] xs = new String[]{"a", "b"};
        final Helper<String> helper = new BaseHelperWithSortedTest<>();
        assertTrue(helper.sorted(xs));
        helper.swap(xs, 0, 1);
        assertFalse(helper.sorted(xs));
    }

    @Test
    public void inversions() {
        String[] xs = new String[]{"a", "b"};
        final Helper<String> helper = new BaseHelperWithSortedTest<>();
        assertEquals(0, helper.inversions(xs));
        helper.swap(xs, 0, 1);
        assertEquals(1, helper.inversions(xs));
    }

    @Test
    public void postProcess1() {
        String[] xs = new String[]{"a", "b"};
        final Helper<String> helper = new BaseHelperWithSortedTest<>();
        helper.postProcess(xs);
    }

    @Test(expected = BaseHelper.HelperException.class)
    public void postProcess2() {
        String[] xs = new String[]{"b", "a"};
        final Helper<String> helper = new BaseHelperWithSortedTest<>();
        helper.postProcess(xs);
    }

    @Test
    public void random() {
        String[] words = new String[]{"Hello", "World"};
        final Helper<String> helper = new BaseHelperWithSortedTest<>(3, 0L);
        final String[] strings = helper.random(String.class, r -> words[r.nextInt(2)]);
        assertArrayEquals(new String[]{"World", "World", "Hello"}, strings);
    }

    @Test
    public void testToString() {
        final Helper<String> helper = new BaseHelper<>("test", 3, config);
        assertEquals("Helper for test with 3 elements", helper.toString());
    }

    @Test
    public void getDescription() {
        final Helper<String> helper = new BaseHelper<>("test", 3, config);
        assertEquals("test", helper.getDescription());
    }

    @Test(expected = RuntimeException.class)
    public void getSetN() {
        final Helper<String> helper = new BaseHelper<>("test", 3, config);
        assertEquals(3, helper.getN());
        helper.init(4);
        assertEquals(4, helper.getN());
    }

    @Test
    public void getSetNBis() {
        final Helper<String> helper = new BaseHelper<>("test", config);
        assertEquals(0, helper.getN());
        helper.init(4);
        assertEquals(4, helper.getN());
    }

    @Test
    public void close() {
        final Helper<String> helper = new BaseHelper<>("test", config);
        helper.close();
    }

    @Test
    public void swapStable() {
        String[] xs = new String[]{"a", "b"};
        final Helper<String> helper = new BaseHelper<>("test", config);
        helper.swapStable(xs, 1);
        assertArrayEquals(new String[]{"b", "a"}, xs);
        helper.swapStable(xs, 1);
        assertArrayEquals(new String[]{"a", "b"}, xs);
    }

    @Test
    public void fixInversion1() {
        String[] xs = new String[]{"a", "b"};
        final Helper<String> helper = new BaseHelper<>("test", config);
        helper.fixInversion(xs, 1); // XXX Deprecated
        assertArrayEquals(new String[]{"a", "b"}, xs);
        helper.swapStable(xs, 1);
        assertArrayEquals(new String[]{"b", "a"}, xs);
        helper.fixInversion(xs, 1); // XXX Deprecated
        assertArrayEquals(new String[]{"a", "b"}, xs);
    }

    @Test
    public void testFixInversion2() {
        String[] xs = new String[]{"a", "b"};
        final Helper<String> helper = new BaseHelper<>("test", config);
        helper.fixInversion(xs, 0, 1);
        assertArrayEquals(new String[]{"a", "b"}, xs);
        helper.swap(xs, 0, 1);
        assertArrayEquals(new String[]{"b", "a"}, xs);
        helper.fixInversion(xs, 0, 1);
        assertArrayEquals(new String[]{"a", "b"}, xs);
    }

    @Test
    public void testSwapInto() {
        String[] xs = new String[]{"a", "b", "c"};
        final Helper<String> helper = new BaseHelper<>("test", config);
        helper.swapInto(xs, 0, 2);
        assertArrayEquals(new String[]{"c", "a", "b"}, xs);
        helper.swapInto(xs, 0, 1);
        assertArrayEquals(new String[]{"a", "c", "b"}, xs);
        helper.swapInto(xs, 0, 0);
        assertArrayEquals(new String[]{"a", "c", "b"}, xs);
    }


    @Test
    public void testSwapIntoSorted0() {
        String[] xs = new String[]{"a", "b", "c"};
        final Helper<String> helper = new BaseHelper<>("test", config);
        helper.swapIntoSorted(xs, 2);
        assertArrayEquals(new String[]{"a", "b", "c"}, xs);
    }

    @Test
    public void testSwapIntoSorted1() {
        String[] xs = new String[]{"a", "c", "b"};
        final Helper<String> helper = new BaseHelper<>("test", config);
        helper.swapIntoSorted(xs, 2);
        assertArrayEquals(new String[]{"a", "b", "c"}, xs);
    }

    @Test
    public void testSwapIntoSorted2() {
        String[] xs = new String[]{"a", "c", "b"};
        final Helper<String> helper = new BaseHelper<>("test", config);
        helper.swapIntoSorted(xs, 1);
        assertArrayEquals(new String[]{"a", "c", "b"}, xs);
    }

    @Test
    public void testSwapIntoSorted3() {
        String[] xs = new String[]{"a", "c", "b"};
        final Helper<String> helper = new BaseHelper<>("test", config);
        helper.swapIntoSorted(xs, 0);
        assertArrayEquals(new String[]{"a", "c", "b"}, xs);
    }

    static Config config;

}