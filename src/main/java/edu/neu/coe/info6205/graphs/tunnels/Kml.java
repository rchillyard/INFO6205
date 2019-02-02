package edu.neu.coe.info6205.graphs.tunnels;

import edu.neu.coe.info6205.SizedIterable;
import edu.neu.coe.info6205.graphs.undirected.Edge;
import edu.neu.coe.info6205.graphs.undirected.EdgeGraph;
import edu.neu.coe.info6205.graphs.undirected.GeoPoint;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Kml<V extends GeoPoint,E> {

    private final EdgeGraph<V, E> graph;

    public Kml(GeoGraph<V,E> graph) {
        this.graph = graph;
    }

    public void createKML(File file) throws IOException {
        boolean x = file.createNewFile();
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        writer.write(preamble);
        SizedIterable<Edge<V, E>> edges = graph.edges();
//        for (Edge<V, E> edge : edges) System.out.println("edge: "+edge.toString());
        for (Edge<V, E> edge : edges) writer.write(asLine(edge));
        writer.write(colophon);
        writer.close();

    }

    private String asLine(Edge<V, E> edge) {
        GeoEdge<V, E> geoEdge = (GeoEdge<V, E>) edge;
        return geoEdge.asLineSegment();
    }

    public final static String preamble = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
            "<kml xmlns=\"http://www.opengis.net/kml/2.2\">\n" +
            "  <Document>\n" +
            "    <name>NEU Tunnel System</name>\n";

    public final static String colophon = "  </Document>\n" +
            "</kml>\n";
}


