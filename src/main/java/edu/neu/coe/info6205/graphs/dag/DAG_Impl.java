package edu.neu.coe.info6205.graphs.dag;

import edu.neu.coe.info6205.SizedIterable;
import edu.neu.coe.info6205.SizedIterableImpl;
import edu.neu.coe.info6205.bqs.Bag;
import edu.neu.coe.info6205.bqs.Bag_Array;
import edu.neu.coe.info6205.bqs.Stack;
import edu.neu.coe.info6205.bqs.Stack_LinkedList;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;
import java.util.function.Consumer;

/**
 * TODO this should extend AbstractGraph
 *
 * @tparam V
 * @tparam E
 */
public class DAG_Impl<V extends Comparable<V>, E> implements DAG<V, E> {

    @Override
    public SizedIterable<V> vertices() {
        return SizedIterableImpl.create(adjacentEdges.keySet());
    }

    @Override
    public Iterable<Edge<V, E>> adjacent(V vertex) {
        return adjacentEdges.get(vertex);
    }

    @Override
    public void dfs(V vertex, Consumer<V> pre, Consumer<V> post) {
        new DepthFirstSearch(pre, post).innerDfs(vertex);
    }

    @Override
    public Iterable<V> sorted() {
        Stack<V> postOrderStack = new Stack_LinkedList<>();
        Consumer<V> pre = (v) -> {
        };
        Consumer<V> post = postOrderStack::push;
        new DepthFirstSearch(pre, post).innerDfs();
        return postOrderStack;
    }

    @Override
    public DAG<V, E> reverse() {
        DAG_Impl<V, E> result = new DAG_Impl<>();
        for (Edge<V, E> e : edges()) result.addEdge(e.reverse());
        return result;
    }

    public SizedIterable<Edge<V, E>> edges() {
        Bag<Edge<V, E>> result = new Bag_Array<>();
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

    @Override
    public String toString() {
        return adjacentEdges.toString();
    }

    private Bag<Edge<V, E>> getAdjacencyBag(V vertex) {
        return adjacentEdges.computeIfAbsent(vertex, k -> new Bag_Array<>());
    }

    private final Map<V, Bag<Edge<V, E>>> adjacentEdges = new HashMap<>();

    class DepthFirstSearch {

        DepthFirstSearch(Consumer<V> pre, Consumer<V> post) {
            this.pre = pre;
            this.post = post;
            this.marked = new TreeSet<>();
        }

        void innerDfs() {
            for (V v : vertices()) innerDfs(v);
        }

        void innerDfs(V v) {
            if (marked.contains(v)) return;
            marked.add(v);
            pre.accept(v);
            for (Edge<V, E> e : adjacentEdges.get(v)) {
                V v1 = e.getTo();
                if (!marked.contains(v1)) innerDfs(v1);
            }
            post.accept(v);
        }

        private final TreeSet<V> marked;
        private final Consumer<V> pre;
        private final Consumer<V> post;
    }
}
