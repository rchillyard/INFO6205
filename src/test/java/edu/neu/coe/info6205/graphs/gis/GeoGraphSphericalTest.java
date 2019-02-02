package edu.neu.coe.info6205.graphs.gis;

import edu.neu.coe.info6205.SizedIterable;
import edu.neu.coe.info6205.graphs.tunnels.Building;
import edu.neu.coe.info6205.graphs.tunnels.BuildingLoader;
import edu.neu.coe.info6205.graphs.tunnels.Tunnels;
import edu.neu.coe.info6205.graphs.undirected.Edge;
import edu.neu.coe.info6205.util.PrivateMethodTester;
import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class GeoGraphSphericalTest {


    private Building sn;
    private Tunnels ts;
    private GeoKruskal<Building> kruskal;
    private Building la = new Building("Lake Hall (LA)", 42.3384215, -71.0930697, false, false, false, false);
    private Building ka = new Building("Kariotis Hall (KA)", 42.3386223, -71.0931943, false, false, false, false);
    private GeoEdge<Building, Double> laka = new GeoEdge<>(la, ka, 24591.98089386682);
    private Building ri = new Building("Richards Hall (RI)", 42.3399696, -71.0908448, false, false, true, false);
    private Building ha = new Building("Hayden Hall (HA)", 42.3394668, -71.0907173, false, false, true, false);
    private GeoEdge<Building, Double> riha = new GeoEdge<>(ri, ha, 569.4583703646161);
    private Building csc = new Building("Curry Student Center (CSC)", 42.3391489, -71.0897579, false, false, true, false);
    private Building el = new Building("Ell Hall (EL)", 42.3393659, -71.0899888, false, false, true, false);
    private Edge cheapest = new Edge(csc, el, 307.3261481523301);

    @Before
    public void setUp() throws Exception {
        ts = new Tunnels(BuildingLoader.createBuildings());
        PrivateMethodTester tsTester = new PrivateMethodTester(ts);
        kruskal = (GeoKruskal<Building>) tsTester.invokePrivate("getKruskal");
    }


    @Test
    public void goeEdges() {
        Geo<Building, Double> mst = kruskal.getGeoMST(new GeoGraphSpherical<>());
        SizedIterable<GeoEdge<Building, Double>> edges = mst.goeEdges();
        assertEquals(53, edges.size());
        Iterator<Edge> iterator = kruskal.iterator();
        assertTrue(iterator.hasNext());
        Edge actual = iterator.next();
        assertEquals(cheapest, actual);
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