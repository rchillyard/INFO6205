package com.example;
/**
 *  @author Lingfeng
 */
public class BST<Key extends Comparable<Key>, Value> {
  private Node root; // root of BST

  public class Node {
    public Key key; // sorted by key
    public Value val; // associated data
    public Node left, right; // left and right subtrees
    public int size;          // number of nodes in subtree

    public Node(Key key, Value val,int size) {
      this.key = key;
      this.val = val;
      this.size = size;
    }

  }
  /**
   * Inserts the specified key-value pair into the BST, overwriting the
   * old value with the new value if the BST already contains the
   * specified key.
   *
   * @param key the key
   * @param val the value
   */
  public void put(Key key, Value val) {
    root = put(root, key, val);
  }

  public Node put(Node x, Key key, Value val) {
    //TODO: Inserts the specified key-value pair into the specified Node x
      if(x == null) return new Node(key, val,1);
      int cmp = key.compareTo(x.key);
      if (cmp < 0)
          x.left = put (x.left, key,val);
      else if (cmp > 0)
          x.right = put(x.right, key,val);
      else 
          x.val = val;
      x.size = size(x.left) + size(x.right) +1;
      return x;
      
  }

  public boolean isEmpty() {
    return size() == 0;
}
  /**
     * @return the number of nodes in this BST(the size of the root Node)
     */
  public int size() {
    if (root == null) return 0;
    else return root.size;
  }
  /**
     * @return size of Node x
     */
  private int size(Node x) {
    if (x == null) return 0;
    else return x.size;
}
  /**
     * @return the root of this BST
     */
  public Node get(){
    return root;
  }
  /**
     * @param key the key
     * @return the value associated with this key
     */
  public Value get(Key key) {
    Node x = getNode(key);
    if (x!=null) return  x.val;
    else return null;
  }
  /**
     * @param key the key
     * @return the Node associated with this key
     */
  public Node getNode(Key key) {
    Node x = root;
    while (x != null) {
      int cmp = key.compareTo(x.key);
      if (cmp < 0)
        x = x.left;
      else if (cmp > 0)
        x = x.right;
      else
        return x;
    }
    return null;
  }


}
