/*
 * Copyright (c) 2017. Phasmid Software
 */

package edu.neu.coe.info6205.hashtable;

import java.util.Map;

public interface ST<Key, Value>{

    public void put(Key key, Value val);

    public Value get(Key key);

}