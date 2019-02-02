package edu.neu.coe.info6205.graphs.tunnels;

import edu.neu.coe.info6205.graphs.undirected.Edge;
import edu.neu.coe.info6205.graphs.undirected.EdgeGraph;
import edu.neu.coe.info6205.graphs.undirected.Position;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class Tunnels implements Iterable<Edge> {

    public Tunnels(ArrayList<Building> buildings) {
        kruskal = new GeoKruskal<>(createGraph(buildings));
    }

    public EdgeGraph<Building, Double> getMst() {
        return kruskal.getMST();
    }

    public Iterator<Edge> iterator() {
        return kruskal.iterator();
    }

    private final Kruskal<Building> kruskal;

    public static GeoGraph<Building, Double> createGraph(ArrayList<Building> buildings) {
        GeoGraph<Building, Double> graph = new GeoGraph<>();
        int len = buildings.size();
        for (int i = 0; i < len; i++) {
            Building v1 = buildings.get(i);
            for (int j = 0; j < len; j++) {
                if (i == j) {
                    continue;
                }
                Building v2 = buildings.get(j);
                double length = distance(v1.position, v2.position) * 1000;
                Double attribute = 0.;

                if (v1.isAlreadyTunneled && v2.isAlreadyTunneled) attribute = 0.1 * length;
                else if (v1.isHuntAve || v2.isHuntAve) attribute = 3 * length;
                else if (v1.isMassAveT ^ v2.isMassAveT) attribute = 2 * length;
                else if (v1.isRuggleT ^ v2.isRuggleT) attribute = 2 * length;
                else attribute = length;

                graph.addEdge(v1, v2, attribute);
            }
        }
        return graph;

    }
    
    // method to calculate distance between given longitudes and latitudes

    public static double distance(Position p1, Position p2) {
        double latDistance = Math.toRadians(p2.x - p1.x);
        double lonDistance = Math.toRadians(p2.y - p1.y);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(p1.x)) * Math.cos(Math.toRadians(p2.x))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        return R * 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
    }

    public static void main(String args[]) throws IOException {
        Tunnels ts = new Tunnels(BuildingLoader.createBuildings());
        for (Edge t : ts) System.out.println(t);
        GeoKruskal<Building> kruskal = (GeoKruskal<Building>) ts.kruskal;
        GeoGraph<Building, Double> mst = kruskal.getGeoMST();
        Kml<Building, Double> kml = new Kml<>(mst);
        String filename = "tunnels.kml";
        kml.createKML(new File(filename));
        System.out.println("Tunnels output to KML file: "+ filename);
    }

    final static int R = 6378100; // Radius of the earth (millimeters)
}
