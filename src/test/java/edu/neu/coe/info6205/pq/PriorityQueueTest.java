package edu.neu.coe.info6205.pq;

import edu.neu.coe.info6205.util.PrivateMethodTester;
import org.junit.Test;

import java.util.Comparator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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
        PriorityQueue<String> pq = new PriorityQueue<>(max, binHeap, 2, Comparator.comparing(String::toString));
        final PrivateMethodTester tester = new PrivateMethodTester(pq);
        assertEquals(max, tester.invokePrivate("unordered", 1, 2));
    }

    @Test
    public void testUnordered2() {
        String[] binHeap = new String[3];
        binHeap[1] = "A";
        binHeap[2] = "B";
        boolean max = true;
        PriorityQueue<String> pq = new PriorityQueue<>(max, binHeap, 2, Comparator.comparing(String::toString));
        final PrivateMethodTester tester = new PrivateMethodTester(pq);
        assertEquals(max, tester.invokePrivate("unordered", 1, 2));
    }

    @Test
    public void testSwimUp() {
        String[] binHeap = new String[3];
        binHeap[1] = "A";
        binHeap[2] = "B";
        PriorityQueue<String> pq = new PriorityQueue<>(true, binHeap, 2, Comparator.comparing(String::toString));
        final PrivateMethodTester tester = new PrivateMethodTester(pq);
        assertEquals("A", tester.invokePrivate("peek", 1));
        tester.invokePrivate("swimUp", 2);
        assertEquals("B", tester.invokePrivate("peek", 1));
    }

    @Test
    public void testSink() {
        String[] binHeap = new String[4];
        binHeap[1] = "A";
        binHeap[2] = "B";
        binHeap[3] = "C";
        PriorityQueue<String> pq = new PriorityQueue<>(true, binHeap, 3, Comparator.comparing(String::toString));
        final PrivateMethodTester tester = new PrivateMethodTester(pq);
        tester.invokePrivate("sink", 1);
        assertEquals("C", tester.invokePrivate("peek", 1));
        assertEquals("A", tester.invokePrivate("peek", 3));
    }

    @Test
    public void testInsert() {

        PriorityQueue<String> pq = new PriorityQueue<>(10, Comparator.comparing(String::toString));
        String key = "A";
        pq.insert(key);
        assertEquals(1,pq.size());
        final PrivateMethodTester tester = new PrivateMethodTester(pq);
        assertEquals(key, tester.invokePrivate("peek", 1));

    }
    @Test
    public void testTake1() {

        PriorityQueue<String> pq = new PriorityQueue<>(10, Comparator.comparing(String::toString));
        String key = "A";
        pq.insert(key);
        assertEquals(key,pq.take());
        assertTrue(pq.isEmpty());

    }
    @Test
    public void testTake2() {

        PriorityQueue<String> pq = new PriorityQueue<>(10, Comparator.comparing(String::toString));
        String key1 = "A";
        String key2 = "B";
        pq.insert(key1);
        pq.insert(key2);
        final PrivateMethodTester tester = new PrivateMethodTester(pq);
        assertEquals(key1, tester.invokePrivate("peek", 2));
        assertEquals(key2, tester.invokePrivate("peek", 1));
        assertEquals(key2,pq.take());
        assertEquals(key1,pq.take());
        assertTrue(pq.isEmpty());

    }
}
