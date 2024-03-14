package edu.neu.coe.info6205.bqs;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * This is an implementation of Dictionary which delegates to HashMap.
 *
 * @param <K> the key type
 * @param <V> the value type
 */
public class Dictionary_Hash<K, V> implements Dictionary<K, V> {

    public void put(K k, V v) {
        map.put(k, v);
    }

    public V get(K k) {
        return map.get(k);
    }

    public int size() {
        return map.size();
    }

    public boolean isEmpty() {
        return map.isEmpty();
    }

    public boolean containsKey(Object key) {
        //noinspection SuspiciousMethodCalls
        return map.containsKey(key);
    }

    public void clear() {
        map.clear();
    }

    public Set<K> keySet() {
        return map.keySet();
    }

    private final Map<K, V> map = new HashMap<>();
}