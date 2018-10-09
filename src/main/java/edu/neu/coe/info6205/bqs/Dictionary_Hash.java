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

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        //noinspection SuspiciousMethodCalls
        return map.containsKey(key);
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public Set<K> keySet() {
        return map.keySet();
    }

    private final Map<K, V> map = new HashMap<>();
}
