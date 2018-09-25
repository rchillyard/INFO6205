import java.util.*;

public class RedBlackBST<Key extends Comparable<Key>, Value> {

    private static final boolean RED   = true;
    private static final boolean BLACK = false;

    private Node root;     // root of the BST

    // BST helper node data type
    private class Node {
        private Key key;           // key
        private Value val;         // associated data
        private Node left, right;  // links to left and right subtrees
        private boolean color;     // color of parent link

        public Node(Key key, Value val, boolean color) {
            this.key = key;
            this.val = val;
            this.color = color;
        }
    }

    private boolean isRed(Node x) {
        if (x == null) return false;
        return x.color == RED;
    }

    public boolean isEmpty() {
        return root == null;
    }

    /***************************************************************************
     *  Standard BST search.
     ***************************************************************************/

    /**
     * Returns the value associated with the given key.
     * @param key the key
     * @return the value associated with the given key if the key is in the symbol table
     *     and {@code null} if the key is not in the symbol table
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public Value get(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to get() is null");
        //Todo
        // Returns the value associated with the given key.
        Node x = root;
        while(x != null){
            int cmp = key.compareTo(x.key);
            if(cmp < 0) x = x.left;
            else if(cmp > 0) x = x.right;
            else return x.val
        }
        // Returns null if cannot find
        return null;
    }

    /***************************************************************************
     *  Red-black tree insertion.
     ***************************************************************************/

    /**
     * Inserts the specified key-value pair into the symbol table,
     * overwriting the old value with the new value if the symbol table already contains the specified key.
     * @param key the key
     * @param val the value
     * @throws IllegalArgumentException if {@code key} or {@code val} is {@code null}
     */
    public void put(Node h, Key key, Value val) {
        if (key == null) throw new IllegalArgumentException("first argument to put() is null");
        if (val == null) throw new IllegalArgumentException("second argument to put() is null");
        //Todo
        // insert the key-value pair
        root = put(root, key, val);
        // fix-up any right-leaning links
        // After every put, make sure it still a LLRB Tree
    }
    
    private Node put(Node x, Key key, Value val){
        if(x == null) return new Node(key, val, RED);
        int compare = key.compareTo(x.key);
        if(compare < 0){
            x.left = put(x.left, key, val);
        }else if(compare >  0){
            x.right = put(x.right, key, val);
        }else{
            x.val = val;
        }
        
        if(isRed(x.right) && !isRed(x.left)) x = rotateLeft(x);
        if(isRed(x.left) && !isRed(x.left.left)) x = rotateRight(x);
        if(isRed(x.left) && isRed(x.right)) flipColors(x);
        
        return x
    }
    private Node rotateLeft(Node h){
        Node x = h.right;
        h.right = x.left;
        x.left =h;
        x.color = h.color;
        h.color = RED;
        return x;
    }
    private Node rotateRight(Node h){
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = h.color;
        h.color = RED;
        return x;
    }
    private void flipColors(Node h){
        h.color = RED;
        h.left.color = BLACK;
        h.right.color = BLACK;
    }
    

    /***************************************************************************
     *  Nothing you need to implement below, just used for LLRBT check
     ***************************************************************************/
    public boolean check() {
        if (!isBST())            System.out.println("Not in symmetric order");
        if (!is23())             System.out.println("Not a 2-3 tree");
        if (!isBalanced())       System.out.println("Not balanced");
        return isBST() && is23() && isBalanced(); //&& isSizeConsistent() && isRankConsistent()
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
