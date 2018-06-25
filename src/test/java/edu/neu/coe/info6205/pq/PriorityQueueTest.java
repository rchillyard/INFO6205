package edu.neu.coe.info6205.pq;

import edu.neu.coe.info6205.util.PrivateMethodTester;
import org.junit.Test;

import java.util.Comparator;

import static org.junit.Assert.assertEquals;

public class PriorityQueueTest {

    @Test
    public void testInsert() {

        PriorityQueue<String> pq = new PriorityQueue<>(10, Comparator.comparing(String::toString));
        String key = "A";
        pq.insert(key);
        assertEquals(1,pq.size());
        final PrivateMethodTester tester = new PrivateMethodTester(pq);
        assertEquals(key, tester.invokePrivate("peek", 1));

    }
}
