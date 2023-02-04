package edu.neu.coe.info6205.graphs.undirected;

import edu.neu.coe.info6205.SizedIterable;

/**
 * Graph interface for undirected graphs.
 *
 * @param <Vertex> the type of a vertex.
 */
public interface Graph<Vertex, Adjacent> {

    /**
     * Get the vertices of this Graph as an Iterable.
     *
     * @return the vertices
     */
    SizedIterable<Vertex> vertices();

    /**
     * Get the entities which are adjacent to the given vertex.
     *
     * @param vertex the vertex whose adjacent entities we want.
     * @return the adjacent entities as an Iterable.
     */
    Iterable<Adjacent> adjacent(Vertex vertex);

}
