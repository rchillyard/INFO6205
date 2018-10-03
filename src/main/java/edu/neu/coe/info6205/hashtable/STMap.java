/*
 * Copyright (c) 2017. Phasmid Software
 */

package edu.neu.coe.info6205.hashtable;

import java.util.HashMap;
import java.util.Map;

public class STMap<Key, Value> implements ST<Key, Value> {

    @SuppressWarnings("SuspiciousMethodCalls")
    public Value get(Object key) {
        return map.get(key);
    }

    public void put(Key key, Value value) {
        map.put(key, value);
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
