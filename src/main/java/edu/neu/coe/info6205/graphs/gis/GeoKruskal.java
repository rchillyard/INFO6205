package edu.neu.coe.info6205.graphs.gis;

import edu.neu.coe.info6205.graphs.undirected.Edge;
import edu.neu.coe.info6205.graphs.undirected.EdgeGraph;

public class GeoKruskal<V extends GeoPoint> extends Kruskal<V> {
    public GeoKruskal(EdgeGraph<V, Double> graph) {
        super(graph);
    }

    /**
     * Method to generate a graph of the MST, given an empty BaseGeoGraph
     * @param geoGraph an empty GeoGraph which will be filled with edges before being returned.
     * @return the geoGraph that was passed as the parameter, but filled with the MST edges.
     */
    public Geo<V, Double> getGeoMST(BaseGeoGraph<V, Double> geoGraph) {
        EdgeGraph<V, Double> mst = super.getMST();
        for (Edge e : mst.edges()) //noinspection unchecked
            geoGraph.addEdge(GeoEdge.create(e));
        return geoGraph;
    }
}
