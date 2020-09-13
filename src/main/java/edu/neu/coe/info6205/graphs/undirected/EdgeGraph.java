package edu.neu.coe.info6205.graphs.undirected;

import edu.neu.coe.info6205.SizedIterable;

import java.util.function.Predicate;

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
    SizedIterable<Edge<V, E>> edges();

    /**
     * Method to add an edge to this EdgeGraph.
     *
     * @param edge      the edge to be added.
     * @param predicate the predicate which determines whether the edge should be added.
     */
    void addEdge(Edge<V, E> edge, Predicate<Edge<V, E>> predicate);

    /**
     * Method to add an edge to this EdgeGraph.
     *
     * @param edge the edge to be added.
     */
    default void addEdge(Edge<V, E> edge) {
        addEdge(edge, e -> true);
    }

    /**
     * Method to add an edge in the form of two vertices and an attribute.
     * NOTE: Questionable whether this is a proper method for this interface.
     *
     * @param from      the from vertex.
     * @param to        the to vertex.
     * @param attribute the attribute.
     * @param predicate the predicate which determines whether we add this element or not.
     */
    void addEdge(V from, V to, E attribute, Predicate<Edge<V, E>> predicate);

    /**
     * Method to add an edge in the form of two vertices and an attribute.
     * NOTE: Questionable whether this is a proper method for this interface.
     *
     * @param from      the from vertex.
     * @param to        the to vertex.
     * @param attribute the attribute.
     */
    default void addEdge(V from, V to, E attribute) {
        addEdge(from, to, attribute, e -> true);
    }
}
