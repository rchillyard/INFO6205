package edu.neu.sort.minpq;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class UnorderedArrayMinPQTest {

    @Test
    public void testMinInsert() {
        UnorderedArrayMinPQ<String> pq_str = new UnorderedArrayMinPQ<>(10);
        pq_str.insert("first_word");
        pq_str.insert("second_word");
        pq_str.insert("third_word");
        assertEquals(pq_str.size(), 3);
    }

    @Test
    public void testMinPQString() {
        UnorderedArrayMinPQ<String> pq_str = new UnorderedArrayMinPQ<>(10);
        pq_str.insert("this");
        pq_str.insert("is");
        assertEquals(pq_str.size(), 2);
        pq_str.insert("a");
        pq_str.insert("test");
        assertEquals(pq_str.size(), 4);
        assertEquals(pq_str.delMin(), "a");
        assertEquals(pq_str.delMin(), "is");
        assertEquals(pq_str.size(), 2);
        assertEquals(pq_str.delMin(), "test");
        assertEquals(pq_str.delMin(),"this");
        assertEquals(pq_str.size(), 0);
    }

    @Test
    public void testMinPQInteger() {
        UnorderedArrayMinPQ<Integer> pq_int = new UnorderedArrayMinPQ<>(10);
        pq_int.insert(34);
        pq_int.insert(5);
        assertEquals(pq_int.size(), 2);
        pq_int.insert(-8);
        pq_int.insert(4);
        assertEquals(pq_int.size(), 4);
        assertEquals(pq_int.delMin(), new Integer(-8));
        assertEquals(pq_int.delMin(), new Integer(4));
        assertEquals(pq_int.size(), 2);
        assertEquals(pq_int.delMin(), new Integer(5));
        assertEquals(pq_int.delMin(), new Integer(34));
        assertEquals(pq_int.size(), 0);
    }

}
