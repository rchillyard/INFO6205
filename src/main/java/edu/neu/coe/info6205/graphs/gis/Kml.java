package edu.neu.coe.info6205.graphs.gis;

import edu.neu.coe.info6205.SizedIterable;
import edu.neu.coe.info6205.graphs.undirected.Edge;
import edu.neu.coe.info6205.graphs.undirected.EdgeGraph;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Kml<V extends GeoPoint,E> {

    private final EdgeGraph<V, E> graph;

    public Kml(Geo<V,E> graph) {
        this.graph = graph;
    }

    public void createKML(File file) throws IOException {
        boolean x = file.createNewFile();
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        writer.write(preamble);
        SizedIterable<V> vertices = graph.vertices();
        for (V vertex : vertices) writer.write(asPoint(vertex));

        for (Edge<V, E> edge : graph.edges()) writer.write(asLine(edge));
        writer.write(colophon);
        writer.close();

    }

    private String asPoint(V vertex) {
        return "      <Placemark>\n" +"      <name>" + vertex.getName() +
                "</name>\n" +
                "      <description>" + vertex.toString() +
                "</description>\n" +
//                "      <styleUrl>#icon-1899-0288D1-nodesc</styleUrl>\n" +
                "      <Point>\n" +
                "        <coordinates>\n" + vertex.getPosition() +
                "         \n" +
                "        </coordinates>\n" +
                "      </Point>\n" +
                "      </Placemark>\n";
    }

    private String asLine(Edge<V, E> edge) {
        GeoEdge<V, E> e = (GeoEdge<V, E>) edge;
        V v1 = e.get();
        V v2 = e.getOther(v1);

        // TODO understand why this doesn't work
//        Tunnel e = (Tunnel) edge;
//        Building v1 = e.get();
//        Building v2 = e.getOther(v1);

        return "      <Placemark>\n" + "      <name>" + v1.getName() + "--" + v2.getName() +
        "</name>\n" +
                "      <description>" + edge.toString() +
                "</description>\n" +
                "      <LineString>\n" +
                "        <tessellate>1</tessellate>\n" +
                "        <coordinates>\n" +
                v1.getPosition() +
                "\n" +
                v2.getPosition() +
                "\n" +
                "        </coordinates>\n" +
                "      </LineString>\n" +
                "      </Placemark>\n";
    }

    private final static String preamble = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
            "<kml xmlns=\"http://www.opengis.net/kml/2.2\">\n" +
            "  <Document>\n" +
            "    <name>NEU Tunnel System</name>\n";

    private final static String colophon = "  </Document>\n" +
            "</kml>\n";
}


