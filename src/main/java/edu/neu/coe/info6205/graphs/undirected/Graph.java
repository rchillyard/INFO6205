package edu.neu.coe.info6205.graphs.undirected;

/**
 * Graph interface for undirected graphs.
 *
 * @tparam <Vertex> the type of a vertex.
 */
public interface Graph<Vertex> {

    /**
     * Get the vertices of this Graph as an Iterable.
     * @return the vertices
     */
    Iterable<Vertex> vertices();

    /**
     * Get the vertices which are adjacent to the given vertex.
     * @param vertex the vertex whose adjacent vertices we want.
     * @return the adjacent vertices as an Iterable.
     */
    Iterable<Edge<Vertex>> adjacent(Vertex vertex);

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
