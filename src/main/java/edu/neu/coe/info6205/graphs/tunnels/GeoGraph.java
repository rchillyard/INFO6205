package edu.neu.coe.info6205.graphs.tunnels;

import edu.neu.coe.info6205.SizedIterable;
import edu.neu.coe.info6205.SizedIterableImpl;
import edu.neu.coe.info6205.graphs.undirected.Edge;
import edu.neu.coe.info6205.graphs.undirected.GeoPoint;
import edu.neu.coe.info6205.graphs.undirected.Graph_Edges;

import java.util.ArrayList;

public class GeoGraph<V extends GeoPoint, E> extends Graph_Edges<V, E> {

    public SizedIterable<GeoEdge<V, E>> goeEdges() {
        ArrayList<GeoEdge<V, E>> result = new ArrayList<>();
        for (Edge<V, E> edge : super.edges()) result.add((GeoEdge<V, E>)edge);
        return SizedIterableImpl.create(result);
    }
}
