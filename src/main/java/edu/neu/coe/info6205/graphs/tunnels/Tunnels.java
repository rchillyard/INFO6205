package edu.neu.coe.info6205.graphs.tunnels;

import edu.neu.coe.info6205.graphs.gis.*;
import edu.neu.coe.info6205.graphs.undirected.Edge;
import edu.neu.coe.info6205.graphs.undirected.EdgeGraph;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;

public class Tunnels implements Iterable<Edge> {

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

    public Tunnels(ArrayList<Building> buildings) {
        setupZones();
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
        if (b1.zone==b2.zone) return 1000;
        return crossZoneExpense(b1.zone, b2.zone);
    }

    private static int crossZoneExpense(String zone1, String zone2) {
        int i1 = zones.indexOf(zone1);
        int i2 = zones.indexOf(zone2);
        ZoneCross cross = new ZoneCross(i1, i2, 0);

        if (cross.equals(railroad)) return railroad.costFactor;
        if (cross.equals(huntAve1)) return huntAve1.costFactor;
        if (cross.equals(huntAve2)) return huntAve2.costFactor;
        if (cross.equals(massAve1)) return massAve1.costFactor;
        if (cross.equals(massAve2)) return massAve2.costFactor;
        if (cross.equals(forsyth1)) return forsyth1.costFactor;
        if (cross.equals(forsyth2)) return forsyth2.costFactor;
        if (cross.equals(leon1)) return leon1.costFactor;
        if (cross.equals(leon2)) return leon2.costFactor;
        if (cross.equals(leon3)) return leon3.costFactor;
        if (cross.equals(hemenway)) return hemenway.costFactor;
        return 10000;
    }

    static class ZoneCross {
        private final int zone1;
        private final int zone2;
        private final int costFactor;

        public ZoneCross(int zone1, int zone2, int costFactor) {
            this.zone1 = zone1;
            this.zone2 = zone2;
            this.costFactor = costFactor;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            ZoneCross zoneCross = (ZoneCross) o;
            return zone1 == zoneCross.zone1 &&
                    zone2 == zoneCross.zone2 || zone1 == zoneCross.zone2 &&
                    zone2 == zoneCross.zone1;
        }

        @Override
        public int hashCode() {
            return Objects.hash(zone1, zone2) + Objects.hash(zone2, zone1);
        }
    }

    private static final ZoneCross railroad = new ZoneCross(0, 8, 3000);
    private static final ZoneCross huntAve1 = new ZoneCross(0, 2, 2500);
    private static final ZoneCross huntAve2 = new ZoneCross(10, 11, 2500);
    private static final ZoneCross massAve1 = new ZoneCross(10, 12, 2500);
    private static final ZoneCross massAve2 = new ZoneCross(6, 11, 2500);
    private static final ZoneCross forsyth1 = new ZoneCross(0, 5, 1500);
    private static final ZoneCross forsyth2 = new ZoneCross(3, 0, 1500);
    private static final ZoneCross hemenway = new ZoneCross(1, 2, 1500);
    private static final ZoneCross leon1 = new ZoneCross(4, 3, 1200);
    private static final ZoneCross leon2 = new ZoneCross(4, 5, 1200);
    private static final ZoneCross leon3 = new ZoneCross(3, 5, 1200);

    private static void setupZones() {
        zones.add(0, "Central");
        zones.add(1, "Fenway");
        zones.add(2, "North");
        zones.add(3,"Plaza");
        zones.add(4,"West Village");
        zones.add(5,"Centennial");
        zones.add(6,"Matthews");
        zones.add(7,"Columbus");
        zones.add(8,"Strip");
        zones.add(9,"St. Stephens");
        zones.add(10,"Pool");
        zones.add(11,"Theater");
        zones.add(12,"Symphony");
    }

    private static ArrayList<String> zones = new ArrayList();
}
