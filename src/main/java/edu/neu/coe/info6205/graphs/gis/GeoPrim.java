package edu.neu.coe.info6205.graphs.gis;

import edu.neu.coe.info6205.graphs.undirected.Edge;
import edu.neu.coe.info6205.graphs.undirected.EdgeGraph;

public class GeoPrim<V extends GeoPoint, X extends Comparable<X> & Sequenced> extends Prim<V, X> {
    public GeoPrim(EdgeGraph<V, X> graph) {
        super(graph);
    }

    /**
     * Method to generate a graph of the MST, given an empty BaseGeoGraph
     *
     * @param geoGraph an empty GeoGraph which will be filled with edges before being returned.
     * @return the geoGraph that was passed as the parameter, but filled with the MST edges.
     */
    public Geo<V, X> getGeoMST(Geo<V, X> geoGraph) {
        EdgeGraph<V, X> mst = super.getMST();
        for (Edge<V, X> e : mst.edges())
            geoGraph.addEdge(createEdge(e));
        return geoGraph;
    }

    public Edge<V, X> createEdge(Edge<V, X> edge) {
        V v = edge.get();
        return new GeoEdge<>(v, edge.getOther(v), edge.getAttribute());
    }

}