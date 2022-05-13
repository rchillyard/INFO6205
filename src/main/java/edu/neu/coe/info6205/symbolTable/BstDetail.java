package edu.neu.coe.info6205.symbolTable;

import java.util.Map;
import java.util.function.BiFunction;

public interface BstDetail<Key extends Comparable<Key>, Value> extends BST<Key, Value> {

    /**
     * Determine if this BST contains key.
     *
     * @param key the key to find.
     * @return true if this contains key.
     */
    Boolean contains(Key key);

    /**
     * Method to input a Map of key-value pairs.
     *
     * @param map the given map.
     */
    void putAll(Map<Key, Value> map);

    /**
     * @return the size of this BST.
     */
    int size();

    /**
     * Method to visit all keys based on the inorder form of traverse.
     * CONSIDER returning a Map of key-value pairs.
     *
     * @param f the function to invoke for each node.
     */
    void inOrderTraverse(BiFunction<Key, Value, Void> f);

    /**
     * Delete the minimum element of the BST.
     */
    void deleteMin();
}
