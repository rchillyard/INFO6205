/*
 * Copyright (c) 2017. Phasmid Software
 */

package edu.neu.coe.info6205.hashtable;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class STMap<Key, Value> implements ST<Key, Value> {
    /**
     * Retrieve the value for a given key.
     *
     * @param key the key.
     * @return the value, if key is present, else null.
     */
    public Value get(Key key) {
        return map.get(key);
    }

    /**
     * Get the set of keys in this symbol table.
     *
     * @return the Set of keys.
     */
    public Set<Key> keys() {
        return map.keySet();
    }

    /**
     * Insert a key/value pair.
     * If the key already exists, then its value will simply be overwritten.
     *
     * @param key the key.
     * @param val the value.
     */
    public void put(Key key, Value val) {
        map.put(key, val);
    }

    public STMap(Map<Key, Value> map) {
        this.map = map;
    }

    public STMap() {
        this(new HashMap<>());
    }

    @Override
    public String toString() {
        return map.toString();
    }

    private final Map<Key, Value> map;
}
