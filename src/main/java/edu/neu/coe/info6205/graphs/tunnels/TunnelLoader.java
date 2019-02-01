package edu.neu.coe.info6205.graphs.tunnels;

import edu.neu.coe.info6205.graphs.undirected.Graph_Edges;
import java.util.ArrayList;

public class TunnelLoader {

// create class vertex to contain all the attributes of a vertex
    class Vertex {

        String name;
        double lon;
        double lat;
        boolean isRuggleT;
        boolean isMassAveT;
        boolean isAlreadyTunneled;
        boolean isHuntAve;

        public Vertex(String name, double lon, double lat, boolean isRuggleT, boolean isMassAveT, boolean isAlreadyTunneled, boolean isHuntAve) {
            this.name = name;
            this.lon = lon;
            this.lat = lat;
            this.isRuggleT = isRuggleT;
            this.isMassAveT = isMassAveT;
            this.isAlreadyTunneled = isAlreadyTunneled;
            this.isHuntAve = isHuntAve;
        }

        @Override
        public String toString() {
            return name;
        }

    }
    ArrayList<Vertex> vertices = new ArrayList<>();
    Graph_Edges<String, Double> graph = new Graph_Edges<>();

    public void populateVertices() {

        //can be replaced with the code to load the vertices into array list from excel file.
        vertices.add(new Vertex("Cushing Hall (CU)", 42.3416669, -71.0915419, false, true, false, false));
        vertices.add(new Vertex("Cahners Hall (CA)", 42.341477, -71.0936997, false, true, false, false));
        vertices.add(new Vertex("Hillel-Frager (HF)", 42.3415995, -71.0897866, false, true, false, false));
        vertices.add(new Vertex("Marino Recreation Center (MC)", 42.3403227, -71.092524, false, true, false, false));
        vertices.add(new Vertex("West Village F, G, H (WV)", 42.3377807, -71.0950127, false, false, false, false));
        vertices.add(new Vertex("Ryder Hall (RY)", 42.3365775, -71.0927975, false, false, false, false));
        vertices.add(new Vertex("Behrakis Health Sciences Center (BK)", 42.3367181, -71.093842, false, false, false, false));
        vertices.add(new Vertex("O’Bryant African American Institute (AF)", 42.33749, -71.0937956, false, false, false, false));
        vertices.add(new Vertex("Meserve Hall (ME)", 42.3378125, -71.0931276, false, false, false, false));
        vertices.add(new Vertex("Shillman Hall (SH)", 42.3375573, -71.0923795, false, false, false, false));
        vertices.add(new Vertex("Nightingale Hall (NI)", 42.3381069, -71.0921763, false, false, false, false));
        vertices.add(new Vertex("Holmes Hall (HO)", 42.3381791, -71.0928458, false, false, false, false));
        vertices.add(new Vertex("Lake Hall (LA)", 42.3384215, -71.0930697, false, false, false, false));
        vertices.add(new Vertex("Kariotis Hall (KA)", 42.3386223, -71.0931943, false, false, false, false));
        vertices.add(new Vertex("Cargill Hall (CG)", 42.3389432, -71.0938253, false, false, false, false));
        vertices.add(new Vertex("Stearns Center (ST)", 42.339003, -71.0935624, false, false, false, false));
        vertices.add(new Vertex("Knowles Center (KN)", 42.3392027, -71.0930708, false, false, false, false));
        vertices.add(new Vertex("Dockser Hall (DK)", 42.3386842, -71.0926833, false, false, false, false));
        vertices.add(new Vertex("Barletta Natatorium (BN)", 42.3390314, -71.0920404, false, false, true, false));
        vertices.add(new Vertex("Cabot Physical Education Center (CB", 42.3394531, -71.0919062, false, false, true, false));
        vertices.add(new Vertex("Richards Hall (RI)", 42.3399696, -71.0908448, false, false, true, false));
        vertices.add(new Vertex("Dodge Hall (DG)", 42.3403277, -71.0900382, false, false, true, false));
        vertices.add(new Vertex("Matthews Arena (MA)", 42.3412391, -71.0867117, false, false, false, false));
        vertices.add(new Vertex("Hurtig Hall (HT)", 42.3397629, -71.0883481, false, false, false, false));
        vertices.add(new Vertex("Cullinane Hall (CN)", 42.340086, -71.0886964, false, false, false, false));
        vertices.add(new Vertex("Mugar Life Sciences Building (MU)", 42.3397752, -71.0893004, false, false, true, false));
        vertices.add(new Vertex("Robinson Hall (RB)", 42.3392655, -71.0889125, false, false, false, false));
        vertices.add(new Vertex("Curry Student Center (CSC)", 42.3391489, -71.0897579, false, false, true, false));
        vertices.add(new Vertex("Blackman Auditorium (AUDL)", 42.3395942, -71.0901278, false, false, true, false));
        vertices.add(new Vertex("Ell Hall (EL)", 42.3393659, -71.0899888, false, false, true, false));
        vertices.add(new Vertex("Hayden Hall (HA)", 42.3394668, -71.0907173, false, false, true, false));
        vertices.add(new Vertex("Churchill Hall (CH)", 42.3387774, -71.0911122, false, false, false, false));
        vertices.add(new Vertex("Forsyth Building (FR)", 42.3385709, -71.0918029, false, false, true, false));
        vertices.add(new Vertex("Latino/a Student Cultural Center (LC)", 42.3381742, -71.0919376, false, false, false, false));
        vertices.add(new Vertex("Dana Research Center (DA)", 42.3380837, -71.0915343, false, false, true, false));
        vertices.add(new Vertex("Snell Engineering Center (SN)", 42.3382885, -71.0910495, false, false, true, false));
        vertices.add(new Vertex("Snell Library (SL)", 42.3384001, -71.09026, false, false, true, false));
        vertices.add(new Vertex("Egan Engineering/ Science Research Center (EC)", 42.3376711, -71.0910815, false, false, false, false));
        vertices.add(new Vertex("Architecture Studio (RG)", 42.3370771, -71.0913416, false, false, false, false));
        vertices.add(new Vertex("Renaissance Park (RP)", 42.3355765, -71.0903964, true, false, false, false));
        vertices.add(new Vertex("Alumni Center at Columbus Place (CP)", 42.3376757, -71.0874819, true, false, false, false));
        vertices.add(new Vertex("Columbus Place and Alumni Center (CP)", 42.3376814, -71.0874785, false, false, false, false));
        vertices.add(new Vertex("Badger & Rosen SquashBusters Center (SB)", 42.3377009, -71.0940446, true, false, false, false));
        vertices.add(new Vertex("Asian American Center (AC)", 42.3431744, -71.092534, false, true, false, false));
        vertices.add(new Vertex("Fenway Center (FC)", 42.3420912, -71.0901576, false, true, false, false));
        vertices.add(new Vertex("Catholic Center (CC)", 42.3416307, -71.0897127, false, true, false, false));
        vertices.add(new Vertex("ROTC Office (RO)", 42.3407475, -71.0906917, false, true, false, false));
        vertices.add(new Vertex("101 Belvidere (BV)", 42.3453256, -71.0859921, false, false, false, false));
        vertices.add(new Vertex("International Village (INV)", 42.3352532, -71.0915672, false, false, false, false));
        vertices.add(new Vertex("Hastings Hall at the YMCA (YMC)", 42.3407354, -71.0894811, false, false, false, false));
        vertices.add(new Vertex("177 Huntington (177)", 42.3449342, -71.0849756, false, false, false, true));
        vertices.add(new Vertex("140 The Fenway (TF )", 42.3407946, -71.093952, false, true, false, false));
        vertices.add(new Vertex("236 Huntington (236)", 42.3429946,-71.0862284, false, false, false, true));
        vertices.add(new Vertex("East Village (EV)", 42.3404407, -71.0890679, false, false, false, false));
        vertices.add(new Vertex("Interdisciplinary Science and Engineering Complex (ISEC)", 42.3377374, -71.0891008, true, false, false, false));
        vertices.add(new Vertex("271 Huntington (271)", 42.3421028, -71.0884667, false, false, false, true));

    }

    public void createGraph() {
        int len = vertices.size();

        for (int i = 0; i < len; i++) {
            Vertex v1 = vertices.get(i);
            for (int j = 0; j < len; j++) {
                if (i == j) {
                    continue;
                }
                Vertex v2 = vertices.get(j);
                double dist = distance(v1.lat, v2.lat, v1.lon, v2.lon) * 1000;
                double attribute = 0;

                if (v1.isAlreadyTunneled && v2.isAlreadyTunneled) {
                    attribute = 0 * dist;
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

    }
    
    // method to calculate distance between given longitudes and latitudes

    public static double distance(double lat1, double lat2, double lon1, double lon2) {

        final int R = 6371; // Radius of the earth

        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c * 1000; // convert to meters

        

        return distance;
    }

    public static void main(String args[]) {
        TunnelLoader ts = new TunnelLoader();
        ts.populateVertices();
        ts.createGraph();

        Kruskal kr = new Kruskal(ts.graph);

        System.out.println(kr.iterator());
        while (kr.getMst().isEmpty() == false) {

            System.out.println(kr.getMst().dequeue());
        }

    }

}
