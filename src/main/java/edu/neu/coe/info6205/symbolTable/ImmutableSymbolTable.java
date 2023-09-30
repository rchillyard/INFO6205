package edu.neu.coe.info6205.symbolTable;

import java.util.Set;

/**
 * Interface to model an immutable symbol table.
 *
 * @param <Key>   key type.
 * @param <Value> value type.
 */
public interface ImmutableSymbolTable<Key, Value> {

    /**
     * Tests if this ImmutableSymbolTable maps no keys to value. In other words, its size is 0.
     *
     * @return {@code true} if this ImmutableSymbolTable maps no keys to values;
     * {@code false} otherwise.
     */
    default boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Retrieve the value for a given key.
     *
     * @param key the key.
     * @return the value, if key is present, else null.
     */
    Value get(Key key);

    /**
     * Get the set of keys in this symbol table.
     *
     * @return the Set of keys.
     */
    Set<Key> keys();

    /**
     * Get the size of this ImmutableSymbolTable.
     *
     * @return the current size.
     */
    int size();
}