package com.example;

import org.junit.Test;
import static org.junit.Assert.*;

public class RedBlackBSTTest {
    @Test
    public void testRotateLeft1() {
        RedBlackBST.Node node1 = new RedBlackBST().new Node(0, 'a', RedBlackBST.BLACK);
        RedBlackBST.Node node2 = new RedBlackBST().new Node(1, 'b', RedBlackBST.BLACK);
        RedBlackBST.Node node3 = new RedBlackBST().new Node(2, 'c', RedBlackBST.RED);
        RedBlackBST.Node node4 = new RedBlackBST().new Node(3, 'd', RedBlackBST.BLACK);
        node2.left = node1;
        node2.right = node3;
        node3.left = node4;
        RedBlackBST.Node node = RedBlackBST.rotateLeft(node2);
        assertEquals(node, node3);
        assertEquals(RedBlackBST.BLACK, node1.color);
        assertEquals(RedBlackBST.RED, node2.color);
        assertEquals(RedBlackBST.BLACK, node3.color);
        assertEquals(RedBlackBST.BLACK, node4.color);
        assertEquals(node2.right, node4);
    }

    @Test
    public void testRotateLeft2() {
        RedBlackBST.Node node1 = new RedBlackBST().new Node(0, 'a', RedBlackBST.BLACK);
        RedBlackBST.Node node2 = new RedBlackBST().new Node(1, 'b', RedBlackBST.RED);
        RedBlackBST.Node node3 = new RedBlackBST().new Node(2, 'c', RedBlackBST.RED);
        RedBlackBST.Node node4 = new RedBlackBST().new Node(3, 'd', RedBlackBST.BLACK);
        node2.left = node1;
        node2.right = node3;
        node3.left = node4;
        RedBlackBST.Node node = RedBlackBST.rotateLeft(node2);
        assertEquals(node, node3);
        assertEquals(RedBlackBST.BLACK, node1.color);
        assertEquals(RedBlackBST.RED, node2.color);
        assertEquals(RedBlackBST.RED, node3.color);
        assertEquals(RedBlackBST.BLACK, node4.color);
        assertEquals(node2.right, node4);
    }

    @Test
    public void testPut0() {
        RedBlackBST<Integer, Character> st = new RedBlackBST<>();
        st.put(8, 'a');
        assertTrue(st.check());
        assertTrue(st.isRBTree());
        assertEquals('a', (char)st.inorderTraverse().get(0));
    }

    @Test
    public void testPut1() {
        RedBlackBST<Integer, Character> st = new RedBlackBST<>();
        st.put(8, 'a');
        st.put(4, 'c');
        assertTrue(st.check());
        assertTrue(st.isRBTree());
        assertEquals('c', (char)st.inorderTraverse().get(0));
        assertEquals('a', (char)st.inorderTraverse().get(1));
    }

    @Test
    public void testPut2() {
        RedBlackBST<Integer, Character> st = new RedBlackBST<>();
        st.put(8, 'a');
        st.put(4, 'c');
        st.put(12, 'e');
        st.put(5, 'h');
        st.put(11, 'l');
        st.put(9, 'm');
        st.put(10, 'p');
        st.put(3, 'r');
        st.put(0, 's');
        st.put(7, 'x');
        assertTrue(st.check());
        assertTrue(st.isRBTree());
        assertEquals('s', (char)st.inorderTraverse().get(0));
        assertEquals('r', (char)st.inorderTraverse().get(1));
        assertEquals('c', (char)st.inorderTraverse().get(2));
        assertEquals('h', (char)st.inorderTraverse().get(3));
        assertEquals('x', (char)st.inorderTraverse().get(4));
        assertEquals('a', (char)st.inorderTraverse().get(5));
        assertEquals('m', (char)st.inorderTraverse().get(6));
        assertEquals('p', (char)st.inorderTraverse().get(7));
        assertEquals('l', (char)st.inorderTraverse().get(8));
        assertEquals('e', (char)st.inorderTraverse().get(9));
    }

    @Test
    public void testPut3() {
        RedBlackBST<Integer, Character> st = new RedBlackBST<>();
        st.put(8, 'a');
        st.put(4, 'c');
        st.put(12, 'e');
        st.put(5, 'h');
        st.put(11, 'l');
        st.put(9, 'm');
        st.put(10, 'p');
        st.put(3, 'r');
        st.put(0, 's');
        st.put(7, 'x');
        st.put(0, 'z');
        assertTrue(st.check());
        assertTrue(st.isRBTree());
        assertEquals('z', (char)st.inorderTraverse().get(0));
        assertEquals('r', (char)st.inorderTraverse().get(1));
        assertEquals('c', (char)st.inorderTraverse().get(2));
        assertEquals('h', (char)st.inorderTraverse().get(3));
        assertEquals('x', (char)st.inorderTraverse().get(4));
        assertEquals('a', (char)st.inorderTraverse().get(5));
        assertEquals('m', (char)st.inorderTraverse().get(6));
        assertEquals('p', (char)st.inorderTraverse().get(7));
        assertEquals('l', (char)st.inorderTraverse().get(8));
        assertEquals('e', (char)st.inorderTraverse().get(9));
    }
}
