package edu.neu.coe.info6205.symbolTable.hashtable;

import edu.neu.coe.info6205.symbolTable.ST;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class HashTable_SC<Key, Value> implements ST<Key, Value> {
    /**
     * Get the size of this ImmutableSymbolTable.
     *
     * @return the current size.
     */
    @Override
    public int size() {
        int result = 0;
        for (Object bucket : buckets) //noinspection unchecked
            result += nodesAsStream((Node) bucket).count();
        return result;
    }

    /**
     * Insert a key/value pair.
     * If the key already exists, then its value will simply be overwritten.
     * If the value provided is null, we should CONSIDER performing a deletion.
     *
     * @param key the key.
     * @param value the value.
     */
    @Override
    public void put(Key key, Value value) {
        int index = getIndex(key);
        @SuppressWarnings("unchecked") Node bucket = (Node) buckets[index];
        List<Node> matches = nodesAsStream(bucket).takeWhile(Objects::nonNull).filter(node -> node.key.equals(key)).collect(Collectors.toList());
        if (matches.size() == 1)
            matches.get(0).value = value;
        else if (matches.size() == 0)
            buckets[index] = new Node(key, value, bucket);
        else
            throw new HashTable_LP.HashTableException("HashTable_SC:put: logic error");
    }

    /**
     * Retrieve the value for a given key.
     *
     * @param key the key.
     * @return the value, if key is present, else null.
     */
    @Override
    public Value get(Key key) {
        Object bucket = buckets[getIndex(key)];
        if (bucket == null) return null;
        //noinspection unchecked
        return nodesAsStream((Node) bucket).filter(n -> n != null && n.key == key).findFirst().map(node -> node.value).orElse(null);
    }

    /**
     * Get the set of keys in this symbol table.
     *
     * @return the Set of keys.
     */
    @Override
    public Set<Key> keys() {
        Set<Key> result = new TreeSet<>();
        for (Object bucket : buckets)
            //noinspection unchecked
            result.addAll(nodesAsStream((Node) bucket).map(node -> node.key).collect(Collectors.toList()));
        return result;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < buckets.length; i++) {
            //noinspection unchecked
            Node bucket = (Node) buckets[i];
            if (bucket != null) {
                List<Node> nodes = nodesAsStream(bucket).collect(Collectors.toList());
                result.append(i).append(": ");
                result.append(nodes);
                result.append("\n");
            }
        }
        return result.toString();
    }

    /**
     * Construct a new HashTable_SC with m buckets.
     *
     * @param m the required number of buckets.
     */
    public HashTable_SC(int m) {
        this.m = m;
        this.buckets = new Object[m];
    }

    /**
     * Construct a new HashTable_SC with 16 buckets.
     */
    public HashTable_SC() {
        this(16);
    }

    private int getIndex(Key key) {
        return (key.hashCode() & 0x7FFFFFFF) % m;
    }

    private Stream<Node> nodesAsStream(Node bucket) {
        if (bucket == null)
            return Stream.empty();
        else
            return Stream.iterate(bucket, Objects::nonNull, node -> node.next);
    }

    private final int m;
    private final Object[] buckets;

    private class Node {
        public Node(Key key, Value value, Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }

        @Override
        public String toString() {
            return key + ":" + value;
        }

        private final Key key;
        private Value value;
        // NOTE: this will need to be mutable when we implement delete.
        @SuppressWarnings("FieldMayBeFinal")
        private Node next;
    }
}
