package com.example;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static junit.framework.TestCase.assertFalse;

public class BagTest {

    @Test
    public void testBag1()
    {
         Bag<Integer> bag = new Bag<>();
         assertTrue(bag.size()==0);
         assertTrue(bag.isEmpty());
         assertFalse((bag.iterator()).hasNext());    
    }
    @Test
    public void testBag2()
    {
        Bag<Integer> bag = new Bag<>();
        bag.add(1);
        assertTrue(bag.size()==1);
        assertFalse(bag.isEmpty());
    }
    @Test
    public void testBag3()
    {
        Bag<Integer> bag = new Bag<>();
        bag.add(1);
        bag.add(2);
        bag.add(3);
        bag.add(4);
        bag.add(5);
        bag.add(6);
        bag.add(7);
        assertTrue(bag.size()==7);
        assertTrue(bag.capacity()==10);
    }
    @Test
    public void testBag4()
    {
        Bag<Integer> bag = new Bag<>();
        assertFalse((bag.iterator()).hasNext());
        bag.add(1);
        assertTrue(( bag.iterator()).hasNext());
        assertEquals( bag.iterator().next(), new Integer(1));
    }
}
