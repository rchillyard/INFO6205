package edu.neu.coe.info6205.graphs.tunnels;

import edu.neu.coe.info6205.graphs.undirected.Edge;
import edu.neu.coe.info6205.graphs.undirected.EdgeGraph;
import edu.neu.coe.info6205.graphs.undirected.GeoPoint;

public class GeoKruskal<V extends GeoPoint> extends Kruskal<V> {
    public GeoKruskal(EdgeGraph<V, Double> graph) {
        super(graph);
    }

    public GeoGraph<V, Double> getGeoMST() {
        GeoGraph<V, Double> geoGraph = new GeoGraph<>();
        EdgeGraph<V, Double> mst = super.getMST();
        for (Edge e : mst.edges()) geoGraph.addEdge(GeoEdge.create(e));
        return geoGraph;
    }
}
