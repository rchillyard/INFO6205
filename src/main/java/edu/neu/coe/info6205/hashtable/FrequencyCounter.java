/*
 * Copyright (c) 2017. Phasmid Software
 */

package edu.neu.coe.info6205.hashtable;

import java.util.Set;

/**
 * This class defines a specialized type of symbol table where the value corresponding to a key
 * is the count of the number of times increment has been called for that key.
 *
 * @param <Key> the key type.
 */
public class FrequencyCounter<Key> implements ImmutableSymbolTable<Key, Integer> {

    public FrequencyCounter(ST<Key, Integer> map) {
        this.map = map;
    }

    public FrequencyCounter() {
        this(new STMap<>());
    }

    public Integer get(Key key) {
        Integer value = map.get(key);
        if (value == null) value = 0;
        return value;
    }

    public double relativeFrequency(Key key) {
        return 1.0 * get(key) / total;
    }

    public double relativeFrequencyAsPercentage(Key key) {
        return 100.0 * relativeFrequency(key);
    }

    /**
     * Get the set of keys in this symbol table.
     *
     * @return the Set of keys.
     */
    public Set<Key> keys() {
        return map.keys();
    }

    public void increment(Key s) {
        // FIXME
        // END 
    }

    /**
     * Method to get the total number of increments over all existing keys.
     *
     * @return the total number of times increment has been called.
     */
    public long total() {
        return total;
    }

    public String toString() {
        return map.toString();
    }

    private final ST<Key, Integer> map;
    @SuppressWarnings("CanBeFinal")
    private long total = 0L;

    public static void main(String[] args) {
        FrequencyCounter<String> counter = new FrequencyCounter<>();

        for (String arg : args) counter.increment(arg);

        System.out.println(counter.toString());
    }
}
