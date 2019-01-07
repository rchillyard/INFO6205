package com.example;

public class Solution<Key,Value> {


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

    public Solution() {
    }

    public int size() {
        return n;
    }

    public boolean isEmpty() {
        return size() == 0;
    }


    public boolean contains(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to contains() is null");
        return get(key) != null;
    }

    public Value get(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to get() is null"); 
        for (Node x = first; x != null; x = x.next) {
            if (key.equals(x.key))
                return x.val;
        }
        return null;
    }

    /**
     * Inserts the specified key-value pair into the symbol table, overwriting the old 
     * value with the new value if the symbol table already contains the specified key.
     * Deletes the specified key (and its associated value) from this symbol table
     */
    
    public void put(Key key, Value val) {
        // TODO implement the put method
        for(Node x = first; x != null; x = x.next){
            if(key.equals(x.key)){
                x.val = val;
                return;
            }
        }
        first = new Node(key, val, first);
        n++;
    }

    /**
     * Removes the specified key and its associated value from this symbol table     
     * (if the key is in this symbol table).    
     *
     */
    public void delete(Key key) {
        //TODO delete() method
        Node next;
        if(key.equals(first.key)){
            first = first.next;
            n--;
            return;
        }
        for(Node x = first; x.next != null; x = x.next){
            next = x.next;
            if(key.equals(next.key)){
                x.next = next.next;
                n--;
                return;
            }
        }    
    }
}
