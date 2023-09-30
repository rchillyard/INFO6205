package edu.neu.coe.info6205.bqs;

import java.util.Set;

/**
 * The purpose of defining this interface is really just to illustrate the use of an interface for encapsulation purposes.
 *
 * @param <K> the key type
 * @param <V> the value type
 */
public interface Dictionary<K, V> {

    void put(K k, V v);

    V get(K k);

    int size();

    boolean isEmpty();

    boolean containsKey(Object key);

    void clear();

    Set<K> keySet();
}