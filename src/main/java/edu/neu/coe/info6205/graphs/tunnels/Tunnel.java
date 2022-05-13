package edu.neu.coe.info6205.graphs.tunnels;

import edu.neu.coe.info6205.graphs.gis.GeoEdge;
import edu.neu.coe.info6205.graphs.gis.GeoPoint;

public class Tunnel extends GeoEdge<GeoPoint, TunnelProperties> {

    /**
     * Edge constructor.
     *
     * @param a          a vertex.
     * @param b          the other vertex.
     * @param properties the properties.
     */
    public Tunnel(GeoPoint a, GeoPoint b, TunnelProperties properties) {
        super(a, b, properties);
    }

    @Override
    public String toString() {
        return "From " + a + " to " + b + " (" + attribute + ")";
    }
}
