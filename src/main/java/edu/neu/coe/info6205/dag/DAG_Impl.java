package edu.neu.coe.info6205.dag;

import edu.neu.coe.info6205.bqs.Bag;
import edu.neu.coe.info6205.bqs.Bag_Array;
import edu.neu.coe.info6205.bqs.Stack;
import edu.neu.coe.info6205.bqs.Stack_LinkedList;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;
import java.util.function.Function;

public class DAG_Impl<Vertex> implements DAG<Vertex> {

    @Override
    public Iterable<Vertex> vertices() {
        return adjacentEdges.keySet();
    }

    @Override
    public Iterable<Edge<Vertex>> adjacent(Vertex vertex) {
        return adjacentEdges.get(vertex);
    }

    @Override
    public void dfs(Vertex vertex, Function<Vertex, Void> pre, Function<Vertex, Void> post) {
        new DepthFirstSearch(pre, post).innerDfs(vertex);
    }

    @Override
    public Iterable<Vertex> sorted() {
        Stack<Vertex> postOrderStack = new Stack_LinkedList<>();
        Function<Vertex, Void> pre = (v) -> null;
        Function<Vertex, Void> post = (v) -> {
            postOrderStack.push(v);
            return null;
        };
        new DepthFirstSearch(pre, post).innerDfs();
        return postOrderStack;
    }

    @Override
    public DAG<Vertex> reverse() {
        DAG_Impl<Vertex> result = new DAG_Impl<>();
        for (Edge<Vertex> e : edges()) result.addEdge(e.reverse());
        return result;
    }

    @Override
    public int V() {
        return adjacentEdges.keySet().size();
    }

    @Override
    public int E() {
        int e = 0;
        for (Bag<Edge<Vertex>> b : adjacentEdges.values()) e += b.size();
        return e;
    }

    public Iterable<Edge<Vertex>> edges() {
        Bag<Edge<Vertex>> result = new Bag_Array<>();
        for (Bag<Edge<Vertex>> b : adjacentEdges.values())
            for (Edge<Vertex> e : b)
                result.add(e);
        return result;
    }

    public void addEdge(Edge<Vertex> edge) {
        // First, we add the edge to the adjacency bag for the "from" vertex;
        getAdjacencyBag(edge.getFrom()).add(edge);
        // Then, we simply ensure that the "to" vertex has an adjacency bag (which might be empty)
        getAdjacencyBag(edge.getTo());
    }

    public void addEdge(Vertex from, Vertex to) {
        addEdge(new Edge<>(from, to));
    }

    @Override
    public String toString() {
        return adjacentEdges.toString();
    }

    private Bag<Edge<Vertex>> getAdjacencyBag(Vertex vertex) {
        return adjacentEdges.computeIfAbsent(vertex, k -> new Bag_Array<>());
    }

    private final Map<Vertex, Bag<Edge<Vertex>>> adjacentEdges = new HashMap<>();

    class DepthFirstSearch {

        DepthFirstSearch(Function<Vertex, Void> pre, Function<Vertex, Void> post) {
            this.pre = pre;
            this.post = post;
            this.marked = new TreeSet<>();
        }

        void innerDfs() {
            for (Vertex v : vertices()) innerDfs(v);
        }

        void innerDfs(Vertex v) {
            if (marked.contains(v)) return;
            marked.add(v);
            pre.apply(v);
            for (Edge<Vertex> e : adjacentEdges.get(v)) {
                Vertex v1 = e.getTo();
                if (!marked.contains(v1)) innerDfs(v1);
            }
            post.apply(v);
        }

        private final TreeSet<Vertex> marked;
        private final Function<Vertex, Void> pre;
        private final Function<Vertex, Void> post;
    }
}
