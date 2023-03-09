package edu.neu.coe.info6205.symbolTable.tree;

import java.util.*;
import java.util.function.BiFunction;

/**
 * Binary Search Tree that is not simple and which has optimized deletion mechanism.
 *
 * @param <Key>   the key type (which must be comparable).
 * @param <Value> the value type.
 * @author Robin Hillyard
 * @author Abhishek Ravindra Satbhai;
 */
public class BSTOptimisedDeletion<Key extends Comparable<Key>, Value> implements BstDetail<Key, Value> {

    @Override
    public Value get(Key key) {
        BiFunction<Node, Node, Node> doGet = (node1, node2) -> node2;
        Node result = root.navigate(key, doGet, doGet);
        return result != null ? result.value : null;
    }

    @Override
    public Value put(final Key key, final Value value) {
        if (root != null) {
            Node result = root.navigate(key, doPut(key, value), Node::updateCount);
            assert result != null;
            return result.value;
        } else {
            root = makeNode(key, value, 0);
            return value;
        }
    }

    public void delete(Key key) {
        // CONSIDER using navigate
        root = root.delete(key);
    }

    public int size(Node node) {
        if (node == null)
            return 0;
        else
            return(size(node.smaller) + 1 + size(node.larger));
    }

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
        Collections.shuffle(ks);
        for (Key k : ks) put(k, map.get(k));
    }

    @Override
    public int size() {
        return root != null ? root.count : 0;
    }

    @Override
    public void inOrderTraverse(BiFunction<Key, Value, Void> f) {
        // CONSIDER using navigate
        doTraverse(0, root, f);
    }

    @Override
    public void deleteMin() {
        // CONSIDER removing this method.
        root = deleteMin(root);
    }

    /**
     * TODO implement this method properly.
     *
     * @return a set of keys.
     */
    @Override
    public Set<Key> keySet() {
        return null;
    }

    /**
     * Yield the total depth of this BST. If root is null, then depth will be 0.
     *
     * @return the total number of levels in this BST.
     */
    @Override
    public int depth() {
        return depth(root);
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

    static class Depth {
        long nodes = 0;
        long totalDepth = 0;

        void increment(int depth) {
            nodes++;
            totalDepth += depth;
        }

        double getMeanDepth() {
            return totalDepth * 1.0 / nodes;
        }
    }

    @Override
    public double meanDepth() {
        final Depth depth = new Depth();
        root.navigate(null, (node1, node2) -> {
            depth.increment(node1.depth);
            return null;
        }, (node1, node2) -> null);
        return depth.getMeanDepth();
    }

    void validate() {
        root.validate(0);
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        show(root, sb, 0);
        return sb.toString();
    }

    /**
     * Primary constructor for BSTOptimisedDeletion.
     *
     * @param map  an existing collection of key-value pairs.
     * @param mode the deletion mode:
     *             0: regular Hibbard deletion;
     *             1: random Hibbard deletion;
     *             2: weight-dependent Hibbard deletion.
     */
    public BSTOptimisedDeletion(Map<Key, Value> map, int mode) {
        this.mode = mode;
        if (map != null) putAll(map);
    }

    /**
     * Secondary constructor which yields an empty BST.
     *
     * @param mode the deletion mode: see constructor BSTOptimisedDeletion(Map&lt;Key, Value&gt; map, int mode).
     */
    public BSTOptimisedDeletion(int mode) {
        this(null, mode);
    }

    /**
     * Secondary constructor which yields an empty BST and unoptimized for deletion.
     */
    public BSTOptimisedDeletion() {
        this(0);
    }

    /**
     * Class to represent a node in the BST.
     */
    class Node {

        /**
         * Primary Constructor.
         *
         * @param key   the key.
         * @param value the value.
         * @param depth the depth.
         */
        Node(Key key, Value value, int depth) {
            this.key = key;
            this.value = value;
            this.depth = depth;
            this.count = 1;
        }


        final Key key;
        Value value;
        int depth;
        Node smaller = null;
        Node larger = null;
        int count;

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder("Node: " + key + ":" + value + " @ " + depth + " with count=" + count);
            if (smaller != null) sb.append(", smaller: ").append(smaller.key);
            if (larger != null) sb.append(", larger: ").append(larger.key);
            return sb.toString();
        }

        private Node navigate(Key k, BiFunction<Node, Node, Node> function1, BiFunction<Node, Node, Node> function2) {
            if (k != null) {
                int cf = k.compareTo(this.key);
                return cf == 0 ? function1.apply(this, this) : navigateSubtree(cf < 0 ? smaller : larger, k, function1, function2);
            } else {
                navigateSubtree(smaller, null, function1, function2);
                navigateSubtree(larger, null, function1, function2);
                return null;
            }
        }

        private Node navigateSubtree(Node subtree, Key k, BiFunction<Node, Node, Node> function1, BiFunction<Node, Node, Node> function2) {
            Node node = subtree != null ? subtree.navigate(k, function1, function2) : function1.apply(this, null);
            return function2.apply(this, node);
        }

        /**
         * Update the count of this Node by doing a shallow update.
         * We assume that the counts of the children, if any, are accurate.
         *
         * @param other another Node.
         * @return other.
         */
        private Node updateCount(Node other) {
            count = 1 + (smaller != null ? smaller.count : 0) + (larger != null ? larger.count : 0);
            return other;
        }

        private Node delete(Key k) {
            // CONSIDER using navigate
            // FIXME by replacing the following code
             return null;
            // END 
        }

        private Node getHibbardResult() {
            Node result;
            if (getDiscriminator()) {
                result = min(larger);
                result.larger = deleteMin(larger);
                result.smaller = smaller;
            } else {
                result = max(smaller);
                result.smaller = deleteMax(smaller);
                result.larger = larger;
            }
            result.depth--;
            result.count = size(result.smaller) + size(result.larger) + 1;
            return result;
        }

        private boolean getDiscriminator() {
            switch (mode) {
                case 1:
                    return random.nextBoolean();
                case 2:
                    return size(larger) >= size(smaller);
                default:
                    return true;
            }
        }

        private Node reduceDepth() {
            // CONSIDER using navigate
            this.depth--;
            if (smaller != null) smaller.reduceDepth();
            if (larger != null) larger.reduceDepth();
            return this;
        }

        private void validate(int d) {
            // CONSIDER using navigate
            assert (this.depth == d) : "At node " + key + ": incorrect depth value: " + depth + " but should be " + d;
            if (smaller != null) {
                assert smaller.key.compareTo(key) < 0 : "Symmetric order violation";
                smaller.validate(d + 1);
            }
            if (larger != null) {
                assert larger.key.compareTo(key) > 0 : "Symmetric order violation";
                larger.validate(d + 1);
            }
        }
    }

    private Node root = null;
    private final int mode;

    private BiFunction<Node, Node, Node> doPut(final Key key, final Value value) {
        return (node1, node2) -> {
            if (node2 != null) {
                node2.value = value;
                return node2;
            } else {
                if (node1 != null) {
                    node1.count++;
                    int cf = key.compareTo(node1.key);
                    Node node = makeNode(key, value, node1.depth + 1);
                    if (cf < 0) node1.smaller = node;
                    else if (cf > 0) node1.larger = node;
                    else throw new RuntimeException("put: Logic error");
                    return node;
                } else {
                    assert false : "this is impossible";
                    return null;
                }
            }
        };
    }

    private Node deleteMin(Node x) {
        // CONSIDER using navigate
        if (x.smaller == null) {
            if (x.larger != null) x.larger.reduceDepth();
            return x.larger;
        } else {
            x.smaller = deleteMin(x.smaller);
            x.count = 1 + size(x.smaller) + size(x.larger);
            return x;
        }
    }
    private Node deleteMax(Node x) {
        // CONSIDER using navigate
        if (x.larger == null) {
            if (x.smaller != null) x.smaller.reduceDepth();
            return x.smaller;
        } else {
            x.larger = deleteMax(x.larger);
            x.count = 1 + size(x.smaller) + size(x.larger);
            return x;
        }
    }

    private Node min(Node x) {
        // CONSIDER using navigate
        if (x == null) throw new RuntimeException("min not implemented for null");
        else if (x.smaller == null) return x;
        else return min(x.smaller);
    }
    private Node max(Node x) {
        // CONSIDER using navigate
        if (x == null) throw new RuntimeException("max not implemented for null");
        else if (x.larger == null) return x;
        else return max(x.larger);
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

    private int depth(Node node) {
        // CONSIDER making this an instance method of Node
        if (node == null) return 0;
        int depthS = depth(node.smaller);
        int depthL = depth(node.larger);
        return 1 + Math.max(depthL, depthS);
    }

    /**
     * Create a new Node from its key, value and depth.
     *
     * @param key   the key.
     * @param value the value.
     * @param depth the depth.
     * @return a new Node.
     */
    private Node makeNode(Key key, Value value, int depth) {
        return new Node(key, value, depth);
    }

    /**
     * NOTE: This method is called through reflection in unit tests.
     *
     * @return the root of this BST.
     */
    private Node getRoot() {
        return root;
    }

    /**
     * NOTE: This method is called through reflection in unit tests.
     *
     * @param node the new root of this BST.
     */
    private void setRoot(Node node) {
        if (root == null) {
            root = node;
            root.count++;
        } else
            root = node;
    }

    private void show(Node node, StringBuffer sb, int indent) {
        // CONSIDER using navigate
        if (node == null) return;
        for (int i = 0; i < indent; i++) sb.append("  ");
//        sb.append(node.toString());
        sb.append(node.key);
        sb.append(": ");
        sb.append(node.value);
        sb.append(" @ depth ").append(node.depth);
        sb.append(" with count ").append(node.count);
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

    private int depth(Node node, Key key) throws DepthException {
        if (node == null) throw new DepthException();
        int cf = key.compareTo(node.key);
        if (cf < 0) return 1 + depth(node.smaller, key);
        else if (cf > 0) return 1 + depth(node.larger, key);
        else return 0;
    }

    static final Random random = new Random();

    private static class DepthException extends Exception {
        public DepthException() {
        }
    }
}


