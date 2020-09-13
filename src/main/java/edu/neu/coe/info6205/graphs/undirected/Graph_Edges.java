package edu.neu.coe.info6205.graphs.undirected;

import edu.neu.coe.info6205.SizedIterable;
import edu.neu.coe.info6205.bqs.Bag;
import edu.neu.coe.info6205.bqs.Bag_Array;

import java.util.function.Predicate;

public class Graph_Edges<V, E> extends AbstractGraph<V, Edge<V, E>> implements EdgeGraph<V, E> {

    @Override
    public SizedIterable<Edge<V, E>> edges() {
        Bag<Edge<V, E>> result = new Bag_Array<>();
        for (Iterable<Edge<V, E>> b : adjacentEdges.values())
            for (Edge<V, E> e : b)
                result.add(e);
        return result;
    }

    @Override
    public void addEdge(Edge<V, E> edge, Predicate<Edge<V, E>> predicate) {
        if (predicate.test(edge)) {
            V v = edge.get();
            // First, we add the edge to the adjacency bag for the "from" vertex;
            getAdjacencyBag(v).add(edge);
            // Then, we simply ensure that the "to" vertex has an adjacency bag (which might be empty)
            getAdjacencyBag(edge.getOther(v));
        }
    }

    @Override
    public void addEdge(V from, V to, E attribute, Predicate<Edge<V, E>> predicate) {
        addEdge(new Edge<>(from, to, attribute), predicate);
    }

    @Override
    public String toString() {
        return adjacentEdges.toString();
    }

}
