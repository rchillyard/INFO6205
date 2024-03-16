package edu.neu.coe.info6205.graphs.dag;

import edu.neu.coe.info6205.SizedIterable;
import edu.neu.coe.info6205.SizedIterableImpl;
import edu.neu.coe.info6205.bqs.Bag;
import edu.neu.coe.info6205.bqs.Bag_Array;

import java.util.Random;
import java.util.TreeSet;
import java.util.function.Consumer;

/**
 * TODO this should extend AbstractGraph
 *
 * @param <V> the vertex type.
 * @param <E> the edge type.
 */
public class DAG_Impl<V, E> extends DiGraph<V, E> implements DAG<V, E> {

    public SizedIterable<V> vertices() {
        return SizedIterableImpl.create(adjacentEdges.keySet());
    }

    public Iterable<Edge<V, E>> adjacent(V vertex) {
        return adjacentEdges.get(vertex);
    }

    public void dfs(V vertex, Consumer<V> pre, Consumer<V> post) {
        new DepthFirstSearch(new TreeSet<>(), pre, post).innerDfs(vertex);
    }

    public Iterable<V> sorted() {
        return reversePostOrderDFS();
    }

    public SizedIterable<Edge<V, E>> edges() {
        Bag<Edge<V, E>> result = new Bag_Array<>(random);
        for (Bag<Edge<V, E>> b : adjacentEdges.values())
            for (Edge<V, E> e : b)
                result.add(e);
        return result;
    }

    public void addEdge(Edge<V, E> edge) {
        // First, we add the edge to the adjacency bag for the "from" vertex;
        getAdjacencyBag(edge.getFrom()).add(edge);
        // Then, we simply ensure that the "to" vertex has an adjacency bag (which might be empty)
        getAdjacencyBag(edge.getTo());
    }

    public void addEdge(V from, V to, E attributes) {
        addEdge(new Edge<>(from, to, attributes));
    }

    public String toString() {
        return adjacentEdges.toString();
    }

    protected Bag<Edge<V, E>> getAdjacencyBag(V vertex) {
        return adjacentEdges.computeIfAbsent(vertex, k -> new Bag_Array<>(random));
    }

    public DAG_Impl(Random random) {
        this.random = random;
    }

    public DAG_Impl() {
        this(new Random());
    }

    private final Random random;

    //    private final Map<V, Bag<Edge<V, E>>> adjacentEdges = new HashMap<>();

}