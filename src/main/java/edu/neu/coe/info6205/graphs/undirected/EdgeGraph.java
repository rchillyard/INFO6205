package edu.neu.coe.info6205.graphs.undirected;

import edu.neu.coe.info6205.SizedIterable;

/**
 * Interface to model the behavior of an EdgeGraph.
 *
 * @tparam V the vertex type
 * @tparam E the edge-attribute type
 */
public interface EdgeGraph<V, E> extends Graph<V, Edge<V, E>> {

    /**
     * Method to get the edges as an iterable.
     *
     * @return a SizedIterable
     */
    SizedIterable<Edge<V,E>> edges();

    /**
     * Method to add an edge to this EdgeGraph.
     *
     * @param edge the edge to be added.
     */
    void addEdge(Edge<V, E> edge);

    /**
     * Method to add an edge in the form of two vertices and an attribute.
     * NOTE: Questionable whether this is a proper method for this interface.
     *
     * @param from the from vertex.
     * @param to the to vertex.
     * @param attribute the attribute.
     */
    void addEdge(V from, V to, E attribute);
}
