package edu.neu.coe.info6205.graphs.undirected;

import edu.neu.coe.info6205.bqs.Bag;
import edu.neu.coe.info6205.bqs.Bag_Array;
import edu.neu.coe.info6205.graphs.dag.DAG;
import edu.neu.coe.info6205.graphs.dag.Edge;

import java.util.HashMap;
import java.util.Map;

public class Graph_Impl<Vertex> implements Graph<Vertex> {

    @Override
    public Iterable<Vertex> vertices() {
        return adjacentEdges.keySet();
    }

    @Override
    public Iterable<Edge<Vertex>> adjacent(Vertex vertex) {
        return adjacentEdges.get(vertex);
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

}
