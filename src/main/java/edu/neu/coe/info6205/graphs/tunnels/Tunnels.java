package edu.neu.coe.info6205.graphs.tunnels;

import edu.neu.coe.info6205.bqs.Queue;
import edu.neu.coe.info6205.graphs.undirected.Edge;
import edu.neu.coe.info6205.graphs.undirected.Graph_Edges;

import java.util.ArrayList;
import java.util.Iterator;

public class Tunnels implements Iterable<Edge> {

    public Tunnels(ArrayList<Building> buildings) {
        kruskal = new Kruskal(createGraph(buildings));
    }

    public Queue<Edge> getMst() {
        return kruskal.getMst();
    }

    public Iterator<Edge> iterator() {
        return kruskal.iterator();
    }

    private final Kruskal kruskal;

    public static Graph_Edges<String, Double> createGraph(ArrayList<Building> buildings) {
        Graph_Edges<String, Double> graph = new Graph_Edges<>();
        int len = buildings.size();
        for (int i = 0; i < len; i++) {
            Building v1 = buildings.get(i);
            for (int j = 0; j < len; j++) {
                if (i == j) {
                    continue;
                }
                Building v2 = buildings.get(j);
                double dist = distance(v1.lat, v2.lat, v1.lon, v2.lon) * 1000;
                double attribute = 0;

                if (v1.isAlreadyTunneled && v2.isAlreadyTunneled) {
                    attribute = 0.1 * dist;
                } else if (v1.isHuntAve || v2.isHuntAve) {
                    attribute = 3 * dist;
                } else if (v1.isMassAveT ^ v2.isMassAveT) {
                    attribute = 2 * dist;
                } else if (v1.isRuggleT ^ v2.isRuggleT) {
                    attribute = 2 * dist;
                } else {
                    attribute = dist;
                }
                graph.addEdge(v1.name, v2.name, attribute);

            }
        }
        return graph;

    }
    
    // method to calculate distance between given longitudes and latitudes

    public static double distance(double lat1, double lat2, double lon1, double lon2) {
        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        return R * 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
    }

    public static void main(String args[]) {
        Tunnels ts = new Tunnels(BuildingLoader.createBuildings());
        Graph_Edges<String, Double> graph = ts.createGraph(BuildingLoader.createBuildings());
        for (Edge t : ts) System.out.println(t);
        while (ts.getMst().isEmpty() == false) {
            System.out.println(ts.getMst().dequeue());
        }

    }

    final static int R = 6371000; // Radius of the earth (millimeters)
}
