package edu.neu.coe.info6205.graphs.gis;

import edu.neu.coe.info6205.graphs.undirected.Edge;

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
        String sb = "      <Placemark>\n" +
                "      <LineString>\n" +
                "        <tessellate>1</tessellate>\n" +
                "        <coordinates>\n" +
                a.getPosition() +
                "\n" +
                b.getPosition() +
                "\n" +
                "        </coordinates>\n" +
                "      </LineString>\n" +
                "      </Placemark>\n";
        return sb;
    }

    public static <V extends GeoPoint, E> GeoEdge<V, E> create(Edge<V, E> edge) {
        V v = edge.get();
        //noinspection unchecked
        return new GeoEdge(v, edge.getOther(v), edge.getAttribute());
    }
}
