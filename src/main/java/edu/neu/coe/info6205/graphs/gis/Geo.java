package edu.neu.coe.info6205.graphs.gis;

import edu.neu.coe.info6205.SizedIterable;
import edu.neu.coe.info6205.graphs.undirected.Edge;
import edu.neu.coe.info6205.graphs.undirected.EdgeGraph;

public interface Geo<V extends GeoPoint, E> extends EdgeGraph<V, E> {
    /**
     * Get the edges of this Geo instance as GeoEdges
     *
     * @return an iterable of GeoEdge
     */
    SizedIterable<Edge<V, E>> goeEdges();

    /**
     * Get the length of an edge
     *
     * @param edge the edge
     * @return the length of the edge
     */
    double length(Edge<V, E> edge);
}
