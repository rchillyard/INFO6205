package edu.neu.coe.info6205.bqs;

import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import static org.junit.Assert.*;

public class DLLQueueTest {
    @Test
    public void TestCase0() {
        DLLQueue<Integer> q = new DLLQueue<>();
        assertTrue(q.isEmpty());
        assertEquals(q.size(), 0);
        assertFalse(q.iterator().hasNext());
    }

    @Test
    public void TestCase1() {
        DLLQueue<Integer> q= new DLLQueue<>();
        q.enqueue(1);
        assertFalse(q.isEmpty());
        assertEquals(q.size(), 1);
        Iterator<Integer> data = q.iterator();
        assertTrue(data.hasNext());
        assertEquals(data.next(), Integer.valueOf(1));
    }

    @Test
    public void TestCase2() {
        DLLQueue<Integer> q= new DLLQueue<>(1);
        assertFalse(q.isEmpty());
        assertEquals(q.size(), 1);
        Iterator<Integer> data = q.iterator();
        assertTrue(data.hasNext());
        assertEquals(data.next(), Integer.valueOf(1));
    }

    @Test
    public void TestCase3() throws BQSException {
        DLLQueue<Integer> q= new DLLQueue<>();
        q.enqueue(1);
        assertFalse(q.isEmpty());
        assertEquals(q.toString(), "1, ");
        assertEquals(q.dequeue(), Integer.valueOf(1));
        assertEquals(q.size(), 0);
    }

    @Test
    public void TestCase4() throws BQSException {
        DLLQueue<Integer> q= new DLLQueue<>(1);
        q.dequeue();
        assertTrue(q.isEmpty());
    }

    @Test
    public void TestCase5() throws BQSException {
        DLLQueue<Integer> q= new DLLQueue<>(1);
        q.enqueue(2);
        q.enqueue(3);
        q.enqueue(4);
        q.enqueue(5);
        assertEquals(q.toString(), "1, 2, 3, 4, 5, ");
        q.dequeue();
        q.dequeue();
        assertEquals(q.toString(), "3, 4, 5, ");
        q.enqueue(6);
        q.enqueue(7);
        assertEquals(q.toString(), "3, 4, 5, 6, 7, ");
    }

    @Ignore //(expected = BQSException.class)
    public void TestCase6() throws BQSException {
        DLLQueue<Integer> q= new DLLQueue<>();
        q.dequeue();
    }

    @Test
    public void TestCase7() {
        DLLQueue<Integer> q= new DLLQueue<>(1);
        q.enqueue(2);
        q.enqueue(3);
        assertEquals(q.dequeue(), Integer.valueOf(1));
        assertEquals(q.dequeue(), Integer.valueOf(2));
        assertEquals(q.size(), 1);
        assertEquals(q.dequeue(), Integer.valueOf(3));
        assertTrue(q.isEmpty());
    }

    @Test
    public void JosephusProblem() throws BQSException {
        int m = 3;
        int n = 7;
        ArrayList<Integer> list = new ArrayList<>();
        // initialize the queue
        DLLQueue<Integer> queue = new DLLQueue<>();
        for (int i = 0; i < n; i++)
            queue.enqueue(i);

        while (!queue.isEmpty()) {
            for (int i = 0; i < m-1; i++)
                queue.enqueue(queue.dequeue());
            list.add(queue.dequeue());
        }
        System.out.println(list);
        assertEquals(list, new ArrayList<>(Arrays.asList(2, 5, 1, 6, 4, 0, 3)));
    }
}
