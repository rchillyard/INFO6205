package edu.neu.coe.info6205.symbolTable;

import java.util.Set;

public interface BST<Key extends Comparable<Key>, Value> {

    Value get(Key key);

    Value put(Key key, Value value);

    Set<Key> keySet();

    void delete(Key key);
}
