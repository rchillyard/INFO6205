package com.example;

import org.junit.Test;
import static org.junit.Assert.*;

public class LinearProbingHashSTTest
{
    // Simple deletion without loitering check
    @Test
    public void testDelete1() {
        LinearProbingHashST<Integer, Integer> l = new LinearProbingHashST<>();
        l.put(1, 1);
        l.put(10, 10);
        l.delete(1);
        assertEquals(l.getKeys(), "(_)(_)(_)(_)(_)(_)(_)(_)(_)(_)(10)(_)(_)(_)(_)(_)");
    }
    
    // Simple deletion with loitering check
    @Test
    public void testDelete2() {
        LinearProbingHashST<Integer, Integer> l = new LinearProbingHashST<>();
        l.put(1, 1);
        l.put(10, 10);
        l.delete(1);
        assertEquals(l.getKeys(), "(_)(_)(_)(_)(_)(_)(_)(_)(_)(_)(10)(_)(_)(_)(_)(_)");
        assertEquals(l.getVals(), "(_)(_)(_)(_)(_)(_)(_)(_)(_)(_)(10)(_)(_)(_)(_)(_)");
    }
    
    // Linear Probe Deletion
    @Test
    public void testDelete3() {
        LinearProbingHashST<Integer, Integer> l = new LinearProbingHashST<>();
        l.put(1, 1);
        l.put(17, 17);
        l.delete(1);
        assertEquals(l.getKeys(), "(_)(17)(_)(_)(_)(_)(_)(_)(_)(_)(_)(_)(_)(_)(_)(_)");
        assertEquals(l.getVals(), "(_)(17)(_)(_)(_)(_)(_)(_)(_)(_)(_)(_)(_)(_)(_)(_)");
    }
    
    // Linear Probe Deletion with Wrapping
    @Test
    public void testDelete4() {
        LinearProbingHashST<Integer, Integer> l = new LinearProbingHashST<>();
        l.put(15, 15);
        l.put(31, 31);
        l.delete(15);
        assertEquals(l.getKeys(), "(_)(_)(_)(_)(_)(_)(_)(_)(_)(_)(_)(_)(_)(_)(_)(31)");
        assertEquals(l.getVals(), "(_)(_)(_)(_)(_)(_)(_)(_)(_)(_)(_)(_)(_)(_)(_)(31)");
    }
    
    // Complex Linear Probe Deletion
    @Test
    public void testDelete5() {
        LinearProbingHashST<Integer, Integer> l = new LinearProbingHashST<>();
        l.put(15, 15);
        l.put(0, 0);
        l.put(31, 31);
        l.delete(15);
        assertEquals(l.getKeys(), "(0)(_)(_)(_)(_)(_)(_)(_)(_)(_)(_)(_)(_)(_)(_)(31)");
        assertEquals(l.getVals(), "(0)(_)(_)(_)(_)(_)(_)(_)(_)(_)(_)(_)(_)(_)(_)(31)");
    }
}