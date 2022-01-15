package edu.neu.coe.info6205.graphs.dag;

import edu.neu.coe.info6205.SizedIterable;
import edu.neu.coe.info6205.bqs.Bag;
import edu.neu.coe.info6205.bqs.Bag_Array;
import edu.neu.coe.info6205.graphs.undirected.AbstractGraph;

public class DiGraph<V, E> extends AbstractGraph<V, Edge<V, E>> {

    public void addEdge(Edge<V, E> edge) {
        getAdjacencyBag(edge.getFrom()).add(edge);
        getAdjacencyBag(edge.getTo());
    }

    public SizedIterable<Edge<V, E>> edges() {
        Bag<Edge<V, E>> result = new Bag_Array<>();
        for (Iterable<Edge<V, E>> b : adjacentEdges.values())
            for (Edge<V, E> e : b)
                result.add(e);
        return result;
    }

    @Override
    public String toString() {
        return adjacentEdges.toString();
    }

}
