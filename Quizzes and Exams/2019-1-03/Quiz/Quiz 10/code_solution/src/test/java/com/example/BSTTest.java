package com.example;

import org.junit.Test;
import static org.junit.Assert.*;
/**
 *  @author Lingfeng
 */
public class BSTTest {
    @Test
    public void testsize0() throws Exception {
        BST<String, Integer> bst = new BST<>();
        assertEquals(0, bst.size());
    }

    @Test
    public void testPutAndSize1() throws Exception {
        BST<String, Integer> bst = new BST<>();
        assertEquals(0, bst.size());
        bst.put("X", 42);
        assertEquals(1, bst.size());
    }
    @Test
    public void testPutAndSize3() throws Exception {
        BST<String, Integer> bst = new BST<>();
        bst.put("Hello", 3);
        bst.put("Goodbye", 5);
        bst.put("Ciao", 8);
        BST<String, Integer>.Node root = bst.get();
        assertEquals("Hello",root.key);
        assertEquals("Goodbye",root.left.key);
        assertEquals("Ciao",root.left.left.key);
        assertEquals(3, bst.size());
    }

    @Test
    public void testPutAndUpdate1() throws Exception {
        BST<String, Integer> bst = new BST<>();
        bst.put("X", 42);
        bst.put("X", 99);
        BST<String, Integer>.Node root = bst.get();
        assertEquals("X",root.key);
        assertEquals(99,(int)root.val);
    }
    @Test
    public void testPutUpdateSize1() throws Exception {
        BST<String, Integer> bst = new BST<>();
        bst.put("X", 42);
        bst.put("X", 99);
        BST<String, Integer>.Node root = bst.get();
        assertEquals("X",root.key);
        assertEquals(99,(int)root.val);
        assertEquals(1, bst.size());
    }

    @Test
    public void testPut3() throws Exception {
        BST<String, Integer> bst = new BST<>();
        bst.put("Y", 99);
        bst.put("X", 99);
        bst.put("Z", 37);
        BST<String, Integer>.Node root = bst.get();
        assertEquals("Y",root.key);
        assertEquals("X",root.left.key);
        assertEquals("Z",root.right.key);
    }

    @Test
    public void testPut2() throws Exception {
        BST<String, Integer> bst = new BST<>();
        bst.put("Y", 99);
        bst.put("X", 100);
        BST<String, Integer>.Node root = bst.get();
        assertEquals("Y",root.key);
        assertEquals("X",root.left.key);
    }

    @Test
    public void testPut5() throws Exception {
        BST<String, Integer> bst = new BST<>();
        bst.put("Y", 99);
        bst.put("X", 100);
        bst.put("Z", 37);
        BST<String, Integer>.Node root = bst.get();
        assertEquals("Y",root.key);
        assertEquals("X",root.left.key);
        assertEquals("Z",root.right.key);
        bst.put("K", 99);
        assertEquals("K", root.left.left.key);
        bst.put("A", 37);
        assertEquals("A", root.left.left.left.key);
    }

    @Test
    public void testPutAndSize5() throws Exception {
        BST<String, Integer> bst = new BST<>();
        bst.put("Y", 99);
        bst.put("X", 99);
        bst.put("Z", 37);
        BST<String, Integer>.Node root = bst.get();
        assertEquals("Y",root.key);
        assertEquals("X",root.left.key);
        assertEquals("Z",root.right.key);
        bst.put("A", 99);
        assertEquals("A", root.left.left.key);
        bst.put("K", 37);
        assertEquals("K", root.left.left.right.key);
        assertEquals(5, bst.size());
    }
}
