package edu.neu.coe.info6205.symbolTable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiFunction;

public class BSTOptimisedDeletion<Key extends Comparable<Key>, Value> implements BstDetail<Key, Value> {
    @Override
    public Boolean contains(Key key) {
        return get(key) != null;
    }

    /**
     * This implementation of putAll ensures that the keys are inserted into this BST in random order.
     *
     * @param map a map of key value pairs
     */
    @Override
    public void putAll(Map<Key, Value> map) {
        List<Key> ks = new ArrayList<>(map.keySet());
//        Collections.shuffle(ks);
        for (Key k : ks) put(k, map.get(k));
    }

    @Override
    public int size() {
        return root != null ? root.count : 0;
    }

    @Override
    public void inOrderTraverse(BiFunction<Key, Value, Void> f) {
        doTraverse(0, root, f);
    }

    @Override
    public Value get(Key key) {
        return get(root, key);
    }

    @Override
    public Value put(Key key, Value value) {
        NodeValue nodeValue = put(root, key, value);
        if (root == null) root = nodeValue.node;
        if (nodeValue.value == null) root.count++;
        return nodeValue.value;
    }

    public void delete(Key key) {
        root = delete(root, key);
    }

    @Override
    public void deleteMin() {
        root = deleteMin(root);
    }

    @Override
    public Set<Key> keySet() {
        return null;
    }

    /**
     * Method to yield the depth of a key, relative to the root.
     *
     * @param key the key whose depth we are interested in.
     * @return the depth of the key (root: 0) otherwise -1 if key is not found.
     */
    public int depth(Key key) {
        try {
            return depth(root, key);
        } catch (DepthException e) {
            return -1;
        }
    }

    public BSTOptimisedDeletion() {
    }

    public BSTOptimisedDeletion(Map<Key, Value> map) {
        this();
        putAll(map);
    }

    Node root = null;

    private Value get(Node node, Key key) {
        Node result = getNode(node, key);
        return result != null ? result.value : null;
    }

    private Node getNode(Node node, Key key) {
        if (node == null) return null;
        int cf = key.compareTo(node.key);
        if (cf < 0) return getNode(node.smaller, key);
        else if (cf > 0) return getNode(node.larger, key);
        else return node;
    }

    /**
     * Method to put the key/value pair into the subtree whose root is node.
     *
     * @param node  the root of a subtree
     * @param key   the key to insert
     * @param value the value to associate with the key
     * @return a tuple of Node and Value: Node is the
     */
    private NodeValue put(Node node, Key key, Value value) {
        // If node is null, then we return the newly constructed Node, and value=null
        if (node == null) return new NodeValue(new Node(key, value, 0), null);
        int cf = key.compareTo(node.key);
        if (cf == 0) {
            // If keys match, then we return the node and its value
            NodeValue result = new NodeValue(node, node.value);
            node.value = value;
            return result;
        } else if (cf < 0) {
            // if key is less than node's key, we recursively invoke put in the smaller subtree
            NodeValue result = put(node.smaller, key, value);
            if (node.smaller == null)
                node.smaller = result.node;
            if (result.value == null)
                result.node.count++;
            return result;
        } else {
            // if key is greater than node's key, we recursively invoke put in the larger subtree
            NodeValue result = put(node.larger, key, value);
            if (node.larger == null)
                node.larger = result.node;
            if (result.value == null)
                result.node.count++;
            return result;
        }
    }

    public Node getMinimumKey(Node curr)
    {
        while (curr.smaller != null) {
            curr = curr.larger;
        }
        return curr;
    }


    // Helper function to find the maximum value node in the subtree rooted at `ptr`
    public Node findMaximumKey(Node ptr)
    {
        while (ptr.larger != null) {
            ptr = ptr.larger;
        }
        return ptr;
    }

    // Function to delete a node from a BST
    private Node delete(Node x, Key key)
    {
        // pointer to store the parent of the current node
        Node parent = null;
        // start with the root node
        Node curr = x;

        // search key in the BST and set its parent pointer
        while (curr != null && curr.key != key)
        {
            // update the parent to the current node
            parent = curr;
            // if the given key is less than the current node, go to the left subtree;
            // otherwise, go to the right subtree
            if (key.compareTo(curr.key)<0) {
                curr = curr.smaller;
            }
            else {
                curr = curr.larger;
            }
        }
        // return if the key is not found in the tree
        if (curr == null) {
            return x;
        }
        // Case 1: node to be deleted has no children, i.e., it is a leaf node
        if (curr.smaller == null && curr.larger == null)
        {
            // if the node to be deleted is not a root node, then set its
            // parent left/right child to null
            if (curr != x)
            {
                if (parent.smaller == curr) {
                    parent.smaller = null;
                }
                else {
                    parent.larger = null;
                }
            }
            // if the tree has only a root node, set it to null
            else {
                x = null;
            }
        }

        // Case 2: node to be deleted has two children
        else if (curr.smaller != null && curr.larger != null)
        {
            if (size(curr.larger) >= size(curr.smaller)){
                // find its inorder successor node
                Node successor = getMinimumKey(curr.larger);

                // store successor value
                Value val = successor.value;

                // recursively delete the successor. Note that the successor
                // will have at most one child (right child)
                delete(x, successor.key);
                // copy value of the successor to the current node
                curr.value = val;
            }
            else if (size(curr.larger) < size(curr.smaller)){

                // find its inorder predecessor node
                Node predecessor = findMaximumKey(curr.smaller);

                // copy value of the predecessor to the current node
                curr.value = predecessor.value;

                // recursively delete the predecessor. Note that the
                // predecessor will have at most one child (left child)
                curr.smaller = delete(curr.smaller, predecessor.key);
        }

        }

        // Case 3: node to be deleted has only one child
        else {
            // choose a child node
            Node child = (curr.smaller != null)? curr.smaller: curr.larger;

            // if the node to be deleted is not a root node, set its parent
            // to its child
            if (curr != x)
            {
                if (curr == parent.smaller) {
                    parent.smaller = child;
                }
                else {
                    parent.larger = child;
                }
            }

            // if the node to be deleted is a root node, then set the root to the child
            else {
                x = child;
            }
        }

        return x;
    }


    private Node deleteMin(Node x) {
        if (x.smaller == null) return x.larger;
        x.smaller = deleteMin(x.smaller);
        x.count = 1 + size(x.smaller) + size(x.larger);
        return x;
    }

    public int size(Node node) {
        if (node == null)
            return 0;
        else
            return(size(node.smaller) + 1 + size(node.larger));
    }

    private Node min(Node x) {
        if (x == null) throw new RuntimeException("min not implemented for null");
        else if (x.smaller == null) return x;
        else return min(x.smaller);
    }

    /**
     * Do a generic traverse of the binary tree starting with node
     *
     * @param q    determines when the function f is invoked ( lt 0: pre, ==0: in, gt 0: post)
     * @param node the node
     * @param f    the function to be invoked
     */
    private void doTraverse(int q, Node node, BiFunction<Key, Value, Void> f) {
        if (node == null) return;
        if (q < 0) f.apply(node.key, node.value);
        doTraverse(q, node.smaller, f);
        if (q == 0) f.apply(node.key, node.value);
        doTraverse(q, node.larger, f);
        if (q > 0) f.apply(node.key, node.value);
    }

    /**
     * Yield the total depth of this BST. If root is null, then depth will be 0.
     *
     * @return the total number of levels in this BST.
     */
    public int depth() {
        return depth(root);
    }

    private int depth(Node node) {
        if (node == null) return 0;
        int depthS = depth(node.smaller);
        int depthL = depth(node.larger);
        return 1 + Math.max(depthL, depthS);
    }

    private class NodeValue {
        private final Node node;
        private final Value value;

        NodeValue(Node node, Value value) {
            this.node = node;
            this.value = value;
        }

        @Override
        public String toString() {
            return node + "<->" + value;
        }
    }

    class Node {
        Node(Key key, Value value, int depth) {
            this.key = key;
            this.value = value;
            this.depth = depth;
        }

        final Key key;
        Value value;
        final int depth;
        Node smaller = null;
        Node larger = null;
        int count = 0;

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder("Node: " + key + ":" + value);
            if (smaller != null) sb.append(", smaller: ").append(smaller.key);
            if (larger != null) sb.append(", larger: ").append(larger.key);
            return sb.toString();
        }

    }

    private Node makeNode(Key key, Value value, int depth) {
        return new Node(key, value, depth);
    }

    private Node getRoot() {
        return root;
    }

    private void setRoot(Node node) {
        if (root == null) {
            root = node;
            root.count++;
        } else
            root = node;
    }

    private void show(Node node, StringBuffer sb, int indent) {
        if (node == null) return;
        for (int i = 0; i < indent; i++) sb.append("  ");
        sb.append(node.key);
        sb.append(": ");
        sb.append(node.value);
        sb.append("\n");
        if (node.smaller != null) {
            for (int i = 0; i <= indent; i++) sb.append("  ");
            sb.append("smaller: ");
            show(node.smaller, sb, indent + 1);
        }
        if (node.larger != null) {
            for (int i = 0; i <= indent; i++) sb.append("  ");
            sb.append("larger: ");
            show(node.larger, sb, indent + 1);
        }
    }

    public String toString() {
        StringBuffer sb = new StringBuffer();
        show(root, sb, 0);
        return sb.toString();
    }

    private int depth(Node node, Key key) throws DepthException {
        if (node == null) throw new DepthException();
        int cf = key.compareTo(node.key);
        if (cf < 0) return 1 + depth(node.smaller, key);
        else if (cf > 0) return 1 + depth(node.larger, key);
        else return 0;
    }

    private static class DepthException extends Exception {
        public DepthException() {
        }
    }

    public void inorder(Node root)
    {
        if (root == null) {
            return;
        }

        inorder(root.smaller);
        System.out.print(root.value + " ");
        inorder(root.larger);
    }
}

class Main {
    public static void main(String[] args)
    {

        BSTOptimisedDeletion bst = new BSTOptimisedDeletion();
        bst.put("15",15);
        bst.put("10",10);
        bst.put("20",20);
        bst.put("08",8);
        bst.put("12",12);
        bst.put("18",18);
        bst.put("16",16);
        bst.put("30",30);
        bst.put("19",19);
        System.out.println( " ");
        bst.inorder (bst.root);
        bst.delete("12");
        bst.delete("30");
        bst.delete("08");
        System.out.println( " ");
        bst.inorder(bst.root);

    }
}

