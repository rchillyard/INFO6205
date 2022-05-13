package edu.neu.coe.info6205.pq;

import edu.neu.coe.info6205.util.PrivateMethodTester;
import org.junit.Test;

import java.util.Comparator;

import static org.junit.Assert.*;

@SuppressWarnings("ConstantConditions")
public class PriorityQueueTest {

    @Test
    public void testMax() {

        PriorityQueue<String> pq = new PriorityQueue<>(10, false, Comparator.comparing(String::toString));
        final PrivateMethodTester tester = new PrivateMethodTester(pq);
        assertEquals(false, tester.invokePrivate("getMax"));
    }

    @Test
    public void testUnordered1() {
        String[] binHeap = new String[3];
        binHeap[1] = "A";
        binHeap[2] = "B";
        boolean max = false;
        PriorityQueue<String> pq = new PriorityQueue<>(max, binHeap, 2, Comparator.comparing(String::toString), false);
        final PrivateMethodTester tester = new PrivateMethodTester(pq);
        assertEquals(max, tester.invokePrivate("unordered", 1, 2));
    }

    @Test
    public void testUnordered2() {
        String[] binHeap = new String[3];
        binHeap[1] = "A";
        binHeap[2] = "B";
        boolean max = true;
        PriorityQueue<String> pq = new PriorityQueue<>(max, binHeap, 2, Comparator.comparing(String::toString), false);
        final PrivateMethodTester tester = new PrivateMethodTester(pq);
        assertEquals(max, tester.invokePrivate("unordered", 1, 2));
    }

    @Test
    public void testSwimUp() {
        String[] binHeap = new String[3];
        String a = "A";
        String b = "B";
        binHeap[1] = a;
        binHeap[2] = b;
        PriorityQueue<String> pq = new PriorityQueue<>(true, binHeap, 2, Comparator.comparing(String::toString), false);
        final PrivateMethodTester tester = new PrivateMethodTester(pq);
        assertEquals(a, tester.invokePrivate("peek", 1));
        tester.invokePrivate("swimUp", 2);
        assertEquals(b, tester.invokePrivate("peek", 1));
    }

    @Test
    public void testSink() {
        String[] binHeap = new String[4];
        String a = "A";
        String b = "B";
        String c = "C";
        binHeap[1] = a;
        binHeap[2] = b;
        binHeap[3] = c;
        PriorityQueue<String> pq = new PriorityQueue<>(true, binHeap, 3, Comparator.comparing(String::toString), false);
        final PrivateMethodTester tester = new PrivateMethodTester(pq);
        tester.invokePrivate("sink", 1);
        assertEquals(c, tester.invokePrivate("peek", 1));
        assertEquals(a, tester.invokePrivate("peek", 3));
    }

    @Test
    public void testGive1() {

        PriorityQueue<String> pq = new PriorityQueue<>(10, Comparator.comparing(String::toString));
        String key = "A";
        pq.give(key);
        assertEquals(1, pq.size());
        final PrivateMethodTester tester = new PrivateMethodTester(pq);
        assertEquals(key, tester.invokePrivate("peek", 1));
    }

    @Test
    public void testGive2() {
        // Test that we can comfortably give more elements than the the PQ has capacity for
        PriorityQueue<String> pq = new PriorityQueue<>(1, Comparator.comparing(String::toString));
        final PrivateMethodTester tester = new PrivateMethodTester(pq);
        String key = "A";
        pq.give(null); // This will never survive so it might as well be null
        assertEquals(1, pq.size());
        assertNull(tester.invokePrivate("peek", 1));
        pq.give(key);
        assertEquals(1, pq.size());
        assertEquals(key, tester.invokePrivate("peek", 1));

    }

    @Test
    public void testTake1() throws PQException {

        PriorityQueue<String> pq = new PriorityQueue<>(10, Comparator.comparing(String::toString));
        String key = "A";
        pq.give(key);
        assertEquals(key, pq.take());
        assertTrue(pq.isEmpty());

    }

    @Test
    public void testTake2() throws PQException {

        PriorityQueue<String> pq = new PriorityQueue<>(10, Comparator.comparing(String::toString));
        String a = "A";
        String b = "B";
        pq.give(a);
        pq.give(b);
        final PrivateMethodTester tester = new PrivateMethodTester(pq);
        assertEquals(a, tester.invokePrivate("peek", 2));
        assertEquals(b, tester.invokePrivate("peek", 1));
        assertEquals(b, pq.take());
        assertEquals(a, pq.take());
        assertTrue(pq.isEmpty());

    }

    @Test(expected = PQException.class)
    public void testTake3() throws PQException {

        PriorityQueue<String> pq = new PriorityQueue<>(10, Comparator.comparing(String::toString));
        pq.give("A");
        pq.take();
        pq.take();
    }
}
