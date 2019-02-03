package edu.neu.coe.info6205.graphs.tunnels;

import edu.neu.coe.info6205.graphs.gis.GeoEdge;

public class Tunnel extends GeoEdge<Building, TunnelProperties> {

    /**
     * Edge constructor.
     *
     * @param a         a vertex.
     * @param b         the other vertex.
     * @param attribute the attribute.
     */
    public Tunnel(Building a, Building b, TunnelProperties attribute) {
        super(a, b, attribute);
    }
}
