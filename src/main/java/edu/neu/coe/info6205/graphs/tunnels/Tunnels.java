package edu.neu.coe.info6205.graphs.tunnels;

import edu.neu.coe.info6205.graphs.gis.*;
import edu.neu.coe.info6205.graphs.undirected.Edge;
import edu.neu.coe.info6205.graphs.undirected.EdgeGraph;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class Tunnels implements Iterable<Edge> {

    public Tunnels(ArrayList<Building> buildings) {
        kruskal = new GeoKruskal<>(createGraph(buildings));
    }

    public EdgeGraph<Building, Double> getMst() {
        return getKruskal().getMST();
    }

    public Iterator<Edge> iterator() {
        return getKruskal().iterator();
    }

    private Kruskal<Building> getKruskal() {
        return kruskal;
    }

    private final Kruskal<Building> kruskal;

    private static Geo<Building, Double> createGraph(ArrayList<Building> buildings) {
        GeoGraphSpherical<Building, Double> graph = new GeoGraphSpherical<>();
        int len = buildings.size();
        for (int i = 0; i < len; i++) {
            Building b1 = buildings.get(i);
            for (int j = 0; j < len; j++)
                if (i != j) {
                    Building b2 = buildings.get(j);
                    double length = graph.getDistance(b1, b2);
                    graph.addEdge(b1, b2, getCostFactor(b1, b2) * length);
                }
        }
        return graph;
    }

    /**
     * Get the cost factor in $ per meter.
     * @param b1 building at one end.
     * @param b2 building at other end.
     * @return the cost factor.
     */
    private static double getCostFactor(Building b1, Building b2) {
        if (b1.isAlreadyTunneled && b2.isAlreadyTunneled) return 10;
        else if (b1.isHuntAve || b2.isHuntAve) return 3000;
        else if (b1.isMassAveT ^ b2.isMassAveT) return 2000;
        else if (b1.isRuggleT ^ b2.isRuggleT) return 2000;
        else return 1000;
    }

    public static void main(String[] args) throws IOException {
        Tunnels ts = new Tunnels(BuildingLoader.createBuildings());
        for (Edge t : ts) System.out.println(t);
        GeoKruskal<Building> kruskal = (GeoKruskal<Building>) ts.getKruskal();
        Geo<Building, Double> mst = kruskal.getGeoMST(new GeoGraphSpherical<>());
        Kml<Building, Double> kml = new Kml<>(mst);
        String filename = "tunnels.kml";
        kml.createKML(new File(filename));
        System.out.println("Tunnels output to KML file: "+ filename);
    }

}
