package com.example;

import java.util.ArrayList;
import java.util.List;

public class RedBlackBST<Key extends Comparable<Key>, Value> {

    public static final boolean RED   = true;
    public static final boolean BLACK = false;

    private Node root;     // root of the BST

    // BST helper node data type
    public class Node {
        public Key key;           // key
        public Value val;         // associated data
        public Node left, right;  // links to left and right subtrees
        public boolean color;     // color of parent link

        public Node(Key key, Value val, boolean color) {
            this.key = key;
            this.val = val;
            this.color = color;
        }
    }

    public RedBlackBST() {
    }

   // is node x red; false if x is null ?
    private boolean isRed(Node x) {
        if (x == null) return false;
        return x.color == RED;
    }

    public boolean isEmpty() {
        return root == null;
    }


    public Value get(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to get() is null");
        return get(root, key);
    }

    // value associated with the given key in subtree rooted at x; null if no such key
    private Value get(Node x, Key key) {
        while (x != null) {
            int cmp = key.compareTo(x.key);
            if      (cmp < 0) x = x.left;
            else if (cmp > 0) x = x.right;
            else              return x.val;
        }
        return null;
    }

    public boolean contains(Key key) {
        return get(key) != null;
    }

    public void put(Key key, Value val) {
        if (key == null) throw new IllegalArgumentException("first argument to put() is null");

        root = put(root, key, val);
        root.color = BLACK;
        // assert check();
    }

    // insert the key-value pair in the subtree rooted at h
    private Node put(Node h, Key key, Value val) {
        if(h==null) return new Node(key,val,RED);
        int cmp=key.compareTo(h.key);
        if(cmp<0) h.left=put(h.left,key,val);
        else if(cmp>0) h.right=put(h.right,key,val);
        else h.val=val;
        if(isRed(h.right)&&!isRed(h.left)) h=rotateLeft(h);
        if(isRed(h.left)&&isRed(h.left.left)) h=rotateRight(h);
        if(isRed(h.left)&&isRed(h.right)) flipColors(h);
        return h;// TO-DO:

    }

    /***************************************************************************
     *  Red-black tree helper functions.
     ***************************************************************************/

    // make a left-leaning link lean to the right
    public static RedBlackBST.Node rotateRight(RedBlackBST.Node h) {
        // assert (h != null) && isRed(h.left);
        RedBlackBST.Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = x.right.color;
        x.right.color = RED;
        return x;
    }

    // make a right-leaning link lean to the left
    public static RedBlackBST.Node rotateLeft(RedBlackBST.Node h) {
        // assert (h != null) && isRed(h.right);
        RedBlackBST.Node x = h.right;
        h.right = x.left;
        x.left = h;
        // x.color = h.color;
        // h.color = RED;
        x.color = x.left.color;
        x.left.color = RED;
        return x;
        // TO-DO:

    }

    // flip the colors of a node and its two children
    public void flipColors(Node h) {
        // h must have opposite color of its two children
        // assert (h != null) && (h.left != null) && (h.right != null);
        // assert (!isRed(h) &&  isRed(h.left) &&  isRed(h.right))
        //    || (isRed(h)  && !isRed(h.left) && !isRed(h.right));
        h.color = !h.color;
        h.left.color = !h.left.color;
        h.right.color = !h.right.color;
    }

    // Assuming that h is red and both h.left and h.left.left
    // are black, make h.left or one of its children red.
    private Node moveRedLeft(Node h) {
        // assert (h != null);
        // assert isRed(h) && !isRed(h.left) && !isRed(h.left.left);

        flipColors(h);
        if (isRed(h.right.left)) {
            h.right = rotateRight(h.right);
            h = rotateLeft(h);
            flipColors(h);
        }
        return h;
    }

    // Assuming that h is red and both h.right and h.right.left
    // are black, make h.right or one of its children red.
    private Node moveRedRight(Node h) {
        // assert (h != null);
        // assert isRed(h) && !isRed(h.right) && !isRed(h.right.left);
        flipColors(h);
        if (isRed(h.left.left)) {
            h = rotateRight(h);
            flipColors(h);
        }
        return h;
    }

    // restore red-black tree invariant
    private Node balance(Node h) {
        // assert (h != null);

        if (isRed(h.right))                      h = rotateLeft(h);
        if (isRed(h.left) && isRed(h.left.left)) h = rotateRight(h);
        if (isRed(h.left) && isRed(h.right))     flipColors(h);

        return h;
    }


    /***************************************************************************
     *  Check integrity of red-black tree data structure.
     ***************************************************************************/
    public boolean check() {
        if (!isBST())            System.out.println("Not in symmetric order");
        if (!is23())             System.out.println("Not a 2-3 tree");
        if (!isBalanced())       System.out.println("Not balanced");
        return isBST() &&  is23() && isBalanced();
    }

    // does this binary tree satisfy symmetric order?
    // Note: this test also ensures that data structure is a binary tree since order is strict
    private boolean isBST() {
        return isBST(root, null, null);
    }

    // is the tree rooted at x a BST with all keys strictly between min and max
    // (if min or max is null, treat as empty constraint)
    // Credit: Bob Dondero's elegant solution
    private boolean isBST(Node x, Key min, Key max) {
        if (x == null) return true;
        if (min != null && x.key.compareTo(min) <= 0) return false;
        if (max != null && x.key.compareTo(max) >= 0) return false;
        return isBST(x.left, min, x.key) && isBST(x.right, x.key, max);
    }

    // Does the tree have no red right links, and at most one (left)
    // red links in a row on any path?
    private boolean is23() { return is23(root); }
    private boolean is23(Node x) {
        if (x == null) return true;
        if (isRed(x.right)) return false;
        if (x != root && isRed(x) && isRed(x.left))
            return false;
        return is23(x.left) && is23(x.right);
    }

    // do all paths from root to leaf have same number of black edges?
    private boolean isBalanced() {
        int black = 0;     // number of black links on path from root to min
        Node x = root;
        while (x != null) {
            if (!isRed(x)) black++;
            x = x.left;
        }
        return isBalanced(root, black);
    }

    // does every path from the root to a leaf have the given number of black links?
    private boolean isBalanced(Node x, int black) {
        if (x == null) return black == 0;
        if (!isRed(x)) black--;
        return isBalanced(x.left, black) && isBalanced(x.right, black);
    }


    public boolean isRBTree() {
        return isRBTree(root);
    }

    private boolean isRBTree(Node node) {
        if(node == null) {
            return true;
        } else if(node.color == RED) {
            return false;
        } else {
            Node x = node;
            int count = 0;

            for(; x != null; x = x.left) {
                if(x.color == BLACK) {
                    ++count;
                }
            }

            return isRBTree(node, count, 0);
        }
    }

    private boolean isRBTree(Node node, int count, int k) {
        if(node == null) {
            return count == k;
        } else if((isRed(node.left) && isRed(node.left.left))
                ||(isRed(node.left) && isRed(node.left.right))
                ||(isRed(node.right) && isRed(node.right.right))
                ||(isRed(node.right) && isRed(node.right.left))) {
            return false;
        } else {
            if(node.color == BLACK) {
                ++k;
            }
            return node.left == null && node.right == null ? k == count:isRBTree(node.left, count, k) && isRBTree(node.right, count, k);
        }
    }

    public List<Value> inorderTraverse(){
        List<Value> values = new ArrayList<>();
        inorderTraverse(root, values);
        return values;
    }

    private void inorderTraverse(Node node, List<Value> values){
        if (node == null)
            return;
        inorderTraverse(node.left, values);
        //System.out.print(node.key + ": "+node.val+" ,");
        values.add(node.val);
        inorderTraverse(node.right, values);
    }


}
