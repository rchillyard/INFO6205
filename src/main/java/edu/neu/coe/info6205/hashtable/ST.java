/*
 * Copyright (c) 2017. Phasmid Software
 */

package edu.neu.coe.info6205.hashtable;

public interface ST<Key, Value> {

    void put(Key key, Value val);

    Value get(Key key);

}