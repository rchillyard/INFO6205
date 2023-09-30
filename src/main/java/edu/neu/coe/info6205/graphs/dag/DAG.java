package edu.neu.coe.info6205.graphs.dag;

import edu.neu.coe.info6205.SizedIterable;

import java.util.function.Consumer;

/**
 * DAG interface for directed acyclic graphs.
 *
 * @param <V> type of each vertex;
 * @param <E> type of edge attribute;
 */
public interface DAG<V, E> {

    /**
     * Get the vertices of this DAG as an Iterable.
     *
     * @return the vertices
     */
    SizedIterable<V> vertices();

    /**
     * Get the edges of this DAG as a SizedIterable.
     *
     * @return the vertices
     */
    SizedIterable<Edge<V, E>> edges();

    /**
     * Get the edges which are adjacent to the given vertex.
     *
     * @param vertex the vertex whose adjacent edges we want.
     * @return the adjacent edges as an Iterable.
     */
    Iterable<Edge<V, E>> adjacent(V vertex);

    /**
     * Implement depth-first-search on this DAG, starting at vertex.
     *
     * @param vertex the starting point.
     * @param pre    a function which takes the current vertex before recursively calling dfs.
     * @param post   a function which takes the current vertex after recursively calling dfs.
     */
    void dfs(V vertex, Consumer<V> pre, Consumer<V> post);

    /**
     * Get a topologically sorted list of vertex from this DAG.
     *
     * @return the vertices as an Iterable.
     */
    Iterable<V> sorted();

}