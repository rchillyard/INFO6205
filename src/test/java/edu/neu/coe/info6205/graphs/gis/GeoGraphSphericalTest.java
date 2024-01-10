package edu.neu.coe.info6205.graphs.gis;

import edu.neu.coe.info6205.SizedIterable;
import edu.neu.coe.info6205.graphs.tunnels.Building;
import edu.neu.coe.info6205.graphs.tunnels.BuildingLoader;
import edu.neu.coe.info6205.graphs.tunnels.TunnelProperties;
import edu.neu.coe.info6205.graphs.tunnels.Tunnels_Kruskal;
import edu.neu.coe.info6205.graphs.undirected.Edge;
import edu.neu.coe.info6205.util.PrivateMethodTester;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class GeoGraphSphericalTest {

    private static final int oneNauticalMile = 1852; // (i.e. one minute of latitude) in meters
    private static final int oneDegreeLongAtEquator = 111321; // in meters
    public static final int londonToBoston = 5239657; // in metres (assuming spherical earth)
    private Building sn;
    private Tunnels_Kruskal ts;
    private GeoKruskal<Building, TunnelProperties> kruskal;
    private final Building la = new Building(34, "LA", "Centennial", -71.0930697, 42.3384215, false, "Lake Hall");
    private final Building ka = new Building(35, "KA", "Plaza", -71.0931943, 42.3386223, false, "Kariotis Hall");
    private final Edge<Building, TunnelProperties> laka = new GeoEdge<>(la, ka, new TunnelProperties(29510, 25, 1, 0));
    private final Building ri = new Building(42, "RI", "Center", -71.0887314, 42.3397321, true, "Richards Hall");
    private final Building ha = new Building(53, "HA", "Center", -71.0885712, 42.3395146, true, "Hayden Hall");
    private final Edge<Building, TunnelProperties> riha = new Edge<>(ri, ha, new TunnelProperties(276L, 28, 0, 0));
    final GeoPoint london = new MockGeoPoint("London", new Position_Spherical(51.5, -0.5)); // Heathrow (approx) 51°28′14″N, 0°27′42″W
    final GeoPoint boston = new MockGeoPoint("Boston", new Position_Spherical(42.35, -71)); // Logan (approx) 42°21′51″N, 71°0′18″W


    @Before
    public void setUp() {
        ts = new Tunnels_Kruskal(BuildingLoader.createBuildings());
        PrivateMethodTester tsTester = new PrivateMethodTester(ts);
        kruskal = (GeoKruskal<Building, TunnelProperties>) tsTester.invokePrivate("getKruskal");
    }


    @After
    public void tearDown() {
    }

    @Test
    public void goeEdges() {
        Geo<Building, TunnelProperties> mst = kruskal.getGeoMST(new GeoGraphSpherical<>());
        SizedIterable<Edge<Building, TunnelProperties>> edges = mst.goeEdges();
        assertEquals(79, edges.size());
        Iterator<Edge<Building, TunnelProperties>> iterator = kruskal.iterator();
        assertTrue(iterator.hasNext());
        assertEquals(riha, iterator.next());
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
    public void length0() {
        GeoGraphSpherical<GeoPoint, Object> graph = new GeoGraphSpherical<>();
        MockGeoPoint oneDegreeNorth = new MockGeoPoint("OneDegreeNorth", new Position_Spherical(1, 0));
        MockGeoPoint equator = new MockGeoPoint("EquatorialMeridian", new Position_Spherical(0, 0));
        double polarDistance = graph.getDistance(oneDegreeNorth, equator);
        assertEquals(oneNauticalMile * 60, polarDistance, 200);
    }

    @Test
    public void length1() {
        GeoGraphSpherical<GeoPoint, Object> graph = new GeoGraphSpherical<>();
        MockGeoPoint oneDegreeEast = new MockGeoPoint("OneDegreeEast", new Position_Spherical(0, 1));
        MockGeoPoint equator = new MockGeoPoint("EquatorialMeridian", new Position_Spherical(0, 0));
        double polarDistance = graph.getDistance(oneDegreeEast, equator);
        assertEquals(oneDegreeLongAtEquator, polarDistance, 4);
    }

    @Test
    public void length2() {
        GeoGraphSpherical<GeoPoint, Object> graph = new GeoGraphSpherical<>();
        MockGeoPoint north_pole = new MockGeoPoint("North Pole", new Position_Spherical(90, 0));
        MockGeoPoint south_pole = new MockGeoPoint("South Pole", new Position_Spherical(-90, 0));
        double polarDistance = graph.getDistance(north_pole, south_pole);
        assertEquals(20000000, polarDistance, 100000);
    }

    @Test
    public void length3() {
        Geo<Building, TunnelProperties> graph = kruskal.getGeoMST(new GeoGraphSpherical<>());
        Iterable<Edge<Building, TunnelProperties>> geoEdges = graph.goeEdges();
        assertEquals(25, graph.length(laka), 1);
    }

    @Test
    public void length4() {
        GeoGraphSpherical<GeoPoint, Object> graph = new GeoGraphSpherical<>();
        double bostonLondonApprox = graph.getDistance(boston, london);
        assertEquals(londonToBoston, bostonLondonApprox, 4000);
    }

    @Test
    public void getDistance() {
    }


}