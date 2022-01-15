/*
 * Copyright (c) 2017. Phasmid Software
 */

package edu.neu.coe.info6205.balsearchtree;

public class TwoFourTree<Key extends Comparable<Key>, Value> {

    private Node root;

    private class Node {
        private final Value value;
        private final Key key1;
        private final Key key2;
        private final Key key3;
        private Node left, middle, right;

        public Node(Value value, Key key1, Key key2, Key key3) {
            this.value = value;
            this.key1 = key1;
            this.key2 = key2;
            this.key3 = key3;
        }
    }

    public Value get(Key key) {
        return null;
//        return get(key, root);
    }

    private Node cf(Key key, Node node, Key k, Node n) {
        if (key.compareTo(k) == 0) return node;
        else if (key.compareTo(k) < 0) return n;
        else return null;
    }

//    private Value get(Key key, Node node) {
//        if (node == null) return null;
//        Node n = cf(key, node, node.key1, node.left);
//        if (n)
//    }
}
