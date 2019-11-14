package edu.neu.info6205;

/**
 * The SequentialSearchST class represents an (unordered) symbol table of generic key-value pairs.
 * This implementation uses a singly-linked list and sequential search.
 * @author Robert Sedgewick
 * @author Kevin Wayne
 **/
public class SequentialSearchST<Key, Value> {
  private int n;           // number of key-value pairs
  private Node first;      // the linked list of key-value pairs

  // a helper linked list data type
  private class Node {
    private Key key;
    private Value val;
    private Node next;

    public Node(Key key, Value val, Node next)  {
      this.key  = key;
      this.val  = val;
      this.next = next;
    }
  }

  /**
   * Returns the number of key-value pairs in this symbol table.
   *
   * @return the number of key-value pairs in this symbol table
   */
  public int size() {
    return n;
  }

  /**
   * Returns the value associated with the given key in this symbol table.
   *
   * @param  key the key
   * @return the value associated with the given key if the key is in the symbol table
   *     and null if the key is not in the symbol table
   */
  public Value get(Key key) 
  {
        for(Node x=first;x!=null;x=x.next)
        {
            if(x.key.equals(key))
            {
                return x.val;
            }
        }
        return null;
  }

  /**
   * Inserts the specified key-value pair into the symbol table, overwriting the old
   * value with the new value if the symbol table already contains the specified key.
   *
   * @param  key the key
   * @param  val the value
   */
  public void put(Key key, Value val) {
      for(Node x=first;x!)
  }
}
