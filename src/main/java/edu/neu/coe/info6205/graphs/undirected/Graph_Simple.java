package edu.neu.coe.info6205.graphs.undirected;

public class Graph_Simple extends AbstractGraph<Integer, Integer> {
    public Iterable<Integer> adjacent(int vertex) {
        return super.adjacent(vertex);
    }

    public void addEdge(int v1, int v2) {
        getAdjacencyBag(v1).add(v2);
        getAdjacencyBag(v2).add(v1);
    }

    @Override
    public String toString() {
        return adjacentEdges.toString();
    }

    public Graph_Simple() {
    }
}
