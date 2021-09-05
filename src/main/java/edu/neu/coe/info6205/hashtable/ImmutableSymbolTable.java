package edu.neu.coe.info6205.hashtable;

import java.util.Set;

/**
 * Interface to model an immutable symbol table.
 *
 * @param <Key>   key type.
 * @param <Value> value type.
 */
public interface ImmutableSymbolTable<Key, Value> {
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
}
