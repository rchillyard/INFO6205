package edu.neu.coe.info6205.graphs.gis;

import edu.neu.coe.info6205.graphs.tunnels.Building;
import edu.neu.coe.info6205.graphs.tunnels.BuildingLoader;
import edu.neu.coe.info6205.graphs.tunnels.Tunnels;
import edu.neu.coe.info6205.graphs.undirected.Edge;
import edu.neu.coe.info6205.util.PrivateMethodTester;
import org.junit.Before;
import org.junit.Test;

public class GeoGraphSphericalTest {


    private Building sn;
    private Tunnels ts;

    @Before
    public void setUp() throws Exception {
        ts = new Tunnels(BuildingLoader.createBuildings());
        for (Edge t : ts) System.out.println(t);
        PrivateMethodTester tsTester = new PrivateMethodTester(ts);
        GeoKruskal<Building> kruskal = (GeoKruskal<Building>) tsTester.invokePrivate("getKruskal");

        Geo<Building, Double> mst = kruskal.getGeoMST(new GeoGraphSpherical<>());
    }


    @Test
    public void goeEdges() {
    }

    @Test
    public void edges() {
    }

    @Test
    public void addEdge() {
    }

    @Test
    public void toStringTest() {
    }

    @Test
    public void vertices() {
    }

    @Test
    public void length() {
    }

    @Test
    public void getDistance() {
    }
}