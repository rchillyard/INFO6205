package edu.neu.coe.info6205.graphs.tunnels;

import edu.neu.coe.info6205.graphs.undirected.Edge;
import edu.neu.coe.info6205.graphs.undirected.GeoPoint;

public class GeoEdge<V extends GeoPoint, E> extends Edge<V, E> {
    /**
     * Edge constructor.
     *
     * @param a         a vertex.
     * @param b         the other vertex.
     * @param attribute the attribute.
     */
    public GeoEdge(V a, V b, E attribute) {
        super(a, b, attribute);
    }

    public String asLineSegment() {
        StringBuilder sb = new StringBuilder();
        sb.append("      <Placemark>\n");
        sb.append("      <LineString>\n");
        sb.append("        <tessellate>1</tessellate>\n");
        sb.append("        <coordinates>\n");
        sb.append(a.getPosition());
        sb.append("\n");
        sb.append(b.getPosition());
        sb.append("\n");
        sb.append("        </coordinates>\n");
        sb.append("      </LineString>\n");
        sb.append("      </Placemark>\n");
        return sb.toString();
    }

    public static <V extends GeoPoint, E> GeoEdge<V, E> create(Edge<V, E> edge) {
        V v = edge.get();
        return new GeoEdge(v, edge.getOther(v), edge.getAttribute());
    }
}
