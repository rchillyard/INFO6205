package edu.neu.coe.info6205.graphs.undirected;

import edu.neu.coe.info6205.SizedIterable;
import edu.neu.coe.info6205.SizedIterableImpl;
import edu.neu.coe.info6205.bqs.Bag;
import edu.neu.coe.info6205.bqs.Bag_Array;

import java.util.HashMap;
import java.util.Map;

/**
 * Abstract class for Graphs.
 * This should be extended by both directed and undirected graphs.
 *
 * @param <V>   the vertex type.
 * @param <Adj> the adjacency type: can be vertex or edge.
 */
abstract public class AbstractGraph<V, Adj> implements Graph<V, Adj> {

    /**
     * Method to add a vertex (without having to add an edge).
     *
     * @param vertex the vertex to be added.
     */
    public void addVertex(V vertex) {
        adjacentEdges.put(vertex, new Bag_Array<>());
    }

    public SizedIterable<V> vertices() {
        return SizedIterableImpl.create(adjacentEdges.keySet());
    }

    public Iterable<Adj> adjacent(V v) {
        return adjacentEdges.get(v);
    }

    protected Bag<Adj> getAdjacencyBag(V vertex) {
        return adjacentEdges.computeIfAbsent(vertex, k -> new Bag_Array<>());
    }

    protected final Map<V, Bag<Adj>> adjacentEdges = new HashMap<>();
}