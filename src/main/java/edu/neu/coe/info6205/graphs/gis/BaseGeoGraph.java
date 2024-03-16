package edu.neu.coe.info6205.graphs.gis;

import edu.neu.coe.info6205.SizedIterable;
import edu.neu.coe.info6205.SizedIterableImpl;
import edu.neu.coe.info6205.graphs.undirected.Edge;
import edu.neu.coe.info6205.graphs.undirected.Graph_Edges;

import java.util.ArrayList;

public abstract class BaseGeoGraph<V extends GeoPoint, E> extends Graph_Edges<V, E> implements Geo<V, E> {

    public SizedIterable<Edge<V, E>> goeEdges() {
        ArrayList<Edge<V, E>> result = new ArrayList<>();
        for (Edge<V, E> edge : super.edges()) result.add(edge);
        return SizedIterableImpl.create(result);
    }

}