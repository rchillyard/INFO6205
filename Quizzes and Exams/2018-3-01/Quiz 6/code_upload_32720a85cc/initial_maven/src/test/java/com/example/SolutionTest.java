package com.example;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static junit.framework.TestCase.assertFalse;

public class SolutionTest {

     @Test
    public void testInsert() {
        Solution s = new Solution(5);
        s.insert(1);
        assertEquals(null,s.pq[0]);
        assertEquals(1,s.pq[1]);
    }
    
    @Test
    public void testSwim() {
        Solution s = new Solution(5);
        s.insert(4);
        s.insert(1);
        s.insert(5);
        s.insert(3);
        assertEquals(null,s.pq[0]);
        assertEquals(5,s.pq[1]);
    }
    
    @Test
     public void testSink() {
        Solution s = new Solution(5);
        s.insert(4);
        s.insert(1);
        s.insert(5);
        s.insert(3);
        assertEquals(5,s.delMax());
        assertEquals("New max is 4",4,s.pq[1]);
    }
    
    @Test
     public void testDelMax() {
        Solution s = new Solution(5);
        s.insert(4);
        s.insert(1);
        s.insert(5);
        s.insert(3);
        assertEquals(5,s.delMax());
        assertEquals(4,s.delMax());
        assertEquals(3,s.delMax());
        
    }
    
    @Test
    public void testEmpty() {
        Solution s = new Solution(5);
        s.insert(4);
        s.insert(1);
        s.insert(5);
        s.insert(3);
        assertEquals(5,s.delMax());
        assertEquals(4,s.pq[1]);
        assertEquals(4,s.delMax());
        assertEquals(3,s.pq[1]);
        assertEquals(3,s.delMax());
        assertEquals(1,s.pq[1]);
        assertEquals(1,s.delMax());
        assertEquals(null,s.pq[1]);
        assertEquals(null,s.pq[0]);
        assertTrue(s.isEmpty());
    }
}
