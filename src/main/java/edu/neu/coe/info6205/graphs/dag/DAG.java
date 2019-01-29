package edu.neu.coe.info6205.graphs.dag;

import java.util.function.Consumer;

/**
 * DAG interface for directed acyclic graphs.
 *
 * @tparam <Vertex>
 */
public interface DAG<Vertex> {

    /**
     * Get the vertices of this DAG as an Iterable.
     * @return the vertices
     */
    Iterable<Vertex> vertices();

    /**
     * Get the edges which are adjacent to the given vertex.
     * @param vertex the vertex whose adjacent edges we want.
     * @return the adjacent edges as an Iterable.
     */
    Iterable<Edge<Vertex>> adjacent(Vertex vertex);

    /**
     * Implement depth-first-search on this DAG, starting at vertex.
     * @param vertex the starting point.
     * @param pre a function which takes the current vertex before recursively calling dfs.
     * @param post a function which takes the current vertex after recursively calling dfs.
     */
    void dfs(Vertex vertex, Consumer<Vertex> pre, Consumer<Vertex> post);

    /**
     * Get a topologically sorted list of vertex from this DAG.
     * @return the vertices as an Iterable.
     */
    Iterable<Vertex> sorted();

    /**
     * Reverse the sense of this DAG.
     *
     * @return a DAG whose edges all point in the opposite direction to those in this DAG.
     */
    DAG<Vertex> reverse();

    /**
     * Get the number of vertices.
     *
     * @return the number of vertices.
     */
    int V();

    /**
     * Get the number of edges.
     *
     * @return the number of edges.
     */
    int E();
}
