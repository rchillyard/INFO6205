package edu.neu.coe.info6205.graphs.tunnels;

import edu.neu.coe.info6205.SizedIterable;
import edu.neu.coe.info6205.graphs.gis.*;
import edu.neu.coe.info6205.graphs.undirected.Edge;
import edu.neu.coe.info6205.graphs.undirected.EdgeGraph;
import edu.neu.coe.info6205.graphs.undirected.Graph;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

public class Tunnels_Boruvka implements Iterable<Edge<Building, TunnelProperties>> {

    public static void main(String[] args) throws IOException {
        Tunnels_Boruvka ts = new Tunnels_Boruvka(BuildingLoader.createBuildings());
        double totalCost = 0.;
        double totalLength = 0.;
        GeoBoruvka<Building, TunnelProperties> Boruvka = (GeoBoruvka<Building, TunnelProperties>) ts.getBoruvka();
        Geo<Building, TunnelProperties> mst = Boruvka.getGeoMST(new GeoGraphSpherical<>());
        for (Edge<Building, TunnelProperties> e : mst.goeEdges()) {
            totalCost += e.getAttribute().cost;
            totalLength += e.getAttribute().length;
            System.out.println(e);
        }
        Kml<Building, TunnelProperties> kml = new Kml<>(mst);
        String filename = "tunnels.kml";
        kml.createKML(new File(filename));
        System.out.println("Tunnels output to KML file: " + filename);
        System.out.println("Total cost: " + totalCost + ", total length: " + totalLength);
    }

    public Tunnels_Boruvka(List<Building> buildings) {
        setupZones();
        setupTunnels();
        // We arbitrarily limit the the length of any tunnel to 250m
        Boruvka = new GeoBoruvka<>(createGraph(buildings, e -> e.getAttribute().length <= 250));
    }

    public Graph<Building, Edge<Building, TunnelProperties>> getMst() {
        return getBoruvka().getMST();
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<Edge<Building, TunnelProperties>> iterator() {
        return getBoruvka().iterator();
    }

    private Boruvka<Building, TunnelProperties> getBoruvka() {
        return Boruvka;
    }

    private final Boruvka<Building, TunnelProperties> Boruvka;

    /**
     * Create a graph consisting of all possible edges connection the buildings.
     * The resulting graph should contain N(N-1)/2 edges where N is length of the list buildings.
     *
     * @param buildings a list of Buildings.
     * @param predicate the predicate: only edges satisfying this predicate will be added to the graph.
     * @return a Geo&lt;Building, TunnelProperties&gt;
     */
    private static EdgeGraph<Building, TunnelProperties> createGraph(List<Building> buildings, Predicate<Edge<Building, TunnelProperties>> predicate) {
        GeoGraphSpherical<Building, TunnelProperties> graph = new GeoGraphSpherical<>();
        int len = buildings.size();
        for (int i = 0; i < len; i++) {
            Building b1 = buildings.get(i);
            for (int j = i + 1; j < len; j++) {
                Building b2 = buildings.get(j);
                double length = graph.getDistance(b1, b2);
                graph.addEdge(b1, b2, getTunnelProperties(b1, b2, length), predicate);
            }
        }
        SizedIterable<Edge<Building, TunnelProperties>> edges = graph.edges();
        System.out.println("created " + edges.size() + " edges");
        return graph;
    }

    private static TunnelProperties getTunnelProperties(Building b1, Building b2, double length) {
        return new TunnelProperties(Math.round(getCostFactor(b1, b2) * length), (int) Math.round(length), getPhase(b1, b2), 0);
    }

    /**
     * Determine when the tunnel should be built (0 implies existing).
     *
     * @param b1 building at one end.
     * @param b2 building at other end.
     * @return 0 if the tunnel is existing
     */
    private static int getPhase(Building b1, Building b2) {
        if (b1.isAlreadyTunneled && b2.isAlreadyTunneled && connected(b1, b2)) return 0;
        return 1; // TODO create later phases
    }

    private static boolean connected(Building b1, Building b2) {
        for (ExistingTunnel tunnel : tunnels) if (tunnel.matches(b1, b2)) return true;
        return false;
    }

    /**
     * Get the cost factor in $ per meter.
     *
     * @param b1 building at one end.
     * @param b2 building at other end.
     * @return the cost factor.
     */
    private static int getCostFactor(Building b1, Building b2) {
        if (getPhase(b1, b2) == 0) return 10;
        if (Objects.equals(b1.zone, b2.zone)) return 1000;
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
        if (cross.equals(columbus)) return columbus.costFactor;
        if (cross.equals(gainsboro1)) return gainsboro1.costFactor;
        return 10000;
    }

    static class ZoneCross {
        private final int zone1;
        private final int zone2;
        private final int costFactor;

        ZoneCross(int zone1, int zone2, int costFactor) {
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
    private static final ZoneCross gainsboro1 = new ZoneCross(6, 0, 1100);
    private static final ZoneCross forsyth1 = new ZoneCross(0, 5, 1500);
    private static final ZoneCross forsyth2 = new ZoneCross(3, 0, 1500);
    private static final ZoneCross hemenway = new ZoneCross(1, 2, 1500);
    private static final ZoneCross leon1 = new ZoneCross(4, 3, 1200);
    private static final ZoneCross leon2 = new ZoneCross(4, 5, 1200);
    private static final ZoneCross leon3 = new ZoneCross(3, 5, 1200);
    private static final ZoneCross columbus = new ZoneCross(7, 8, 1750);

    private static void setupZones() {
        zones.add(0, "Center");
        zones.add(1, "Fenway");
        zones.add(2, "North");
        zones.add(3, "Plaza");
        zones.add(4, "West Village");
        zones.add(5, "Centennial");
        zones.add(6, "Matthews");
        zones.add(7, "Columbus");
        zones.add(8, "Strip");
        zones.add(9, "St. Stephens");
        zones.add(10, "Pool");
        zones.add(11, "Theater");
        zones.add(12, "Symphony");
    }

    private static void setupTunnels() {
        tunnels.add(new ExistingTunnel(55, 58));
        tunnels.add(new ExistingTunnel(55, 54));
        tunnels.add(new ExistingTunnel(53, 54));
        tunnels.add(new ExistingTunnel(53, 59));
        tunnels.add(new ExistingTunnel(53, 55));
        tunnels.add(new ExistingTunnel(53, 42));
        tunnels.add(new ExistingTunnel(53, 41));
        tunnels.add(new ExistingTunnel(53, 52));
        tunnels.add(new ExistingTunnel(52, 50));
        tunnels.add(new ExistingTunnel(52, 43));
        tunnels.add(new ExistingTunnel(52, 48));
    }

    static class ExistingTunnel {
        private final int map1;
        private final int map2;

        ExistingTunnel(int map1, int map2) {
            this.map1 = map1;
            this.map2 = map2;
        }

        boolean matches(Building b1, Building b2) {
            return b1.getMap() == map1 && b2.getMap() == map2 || b1.getMap() == map2 && b2.getMap() == map1;
        }
    }

    private static final ArrayList<ExistingTunnel> tunnels = new ArrayList<>();
    private static final ArrayList<String> zones = new ArrayList<>();
}