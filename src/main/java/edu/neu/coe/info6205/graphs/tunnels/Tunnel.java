package edu.neu.coe.info6205.graphs.tunnels;

import edu.neu.coe.info6205.graphs.gis.GeoEdge;

public class Tunnel extends GeoEdge<Building, TunnelProperties> {

    /**
     * Edge constructor.
     *
     * @param a         a vertex.
     * @param b         the other vertex.
     * @param properties the properties.
     */
    public Tunnel(Building a, Building b, TunnelProperties properties) {
        super(a, b, properties);
    }

    @Override
    public String toString() {
        return "From " + a + " to " + b + " (" + attribute + ")";
    }
}
