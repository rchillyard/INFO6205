package edu.neu.coe.info6205.graphs.gis;

import edu.neu.coe.info6205.SizedIterable;
import edu.neu.coe.info6205.SizedIterableImpl;
import edu.neu.coe.info6205.graphs.undirected.Edge;
import edu.neu.coe.info6205.graphs.undirected.Graph_Edges;

import java.util.ArrayList;

public abstract class BaseGeoGraph<V extends GeoPoint, E> extends Graph_Edges<V, E> implements Geo<V, E> {

    @Override
    public SizedIterable<GeoEdge<V, E>> goeEdges() {
        ArrayList<GeoEdge<V, E>> result = new ArrayList<>();
        for (Edge<V, E> edge : super.edges()) result.add((GeoEdge<V, E>)edge);
        return SizedIterableImpl.create(result);
    }

}
