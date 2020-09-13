/*
 * Copyright (c) 2017. Phasmid Software
 */

package edu.neu.coe.info6205.hashtable;

/**
 * Interface to model a symbol table.
 *
 * @param <Key>   key type.
 * @param <Value> value type.
 */
public interface ST<Key, Value> extends ImmutableSymbolTable<Key, Value> {

    /**
     * Insert a key/value pair.
     * If the key already exists, then its value will simply be overwritten.
     *
     * @param key the key.
     * @param val the value.
     */
    void put(Key key, Value val);

}