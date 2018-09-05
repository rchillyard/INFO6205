/*
 * Copyright (c) 2017. Phasmid Software
 */

package edu.neu.coe.info6205.hashtable;

import java.util.HashMap;
import java.util.Map;

public class FrequencyCounter<K> {

    public FrequencyCounter(Map<K,Integer> map) {
        this.map = map;
    }

    public FrequencyCounter() {
        this(new HashMap<>());
    }

    public int get(Object key) {
        Integer value = map.get(key);
        if (value==null) value = 0;
        return value;
    }

    public void increment(K s) {
        int x = get(s);
        map.put(s,x+1);
    }

    public String toString() {
        return map.toString();
    }

    private final Map<K,Integer> map;

}
