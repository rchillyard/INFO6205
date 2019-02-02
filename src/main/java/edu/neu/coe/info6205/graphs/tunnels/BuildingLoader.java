package edu.neu.coe.info6205.graphs.tunnels;

import java.util.ArrayList;

@SuppressWarnings("SpellCheckingInspection")
public class BuildingLoader {

    public static ArrayList<Building> createBuildings() {
        //can be replaced with the code to load the buildings into array list from excel file.
        ArrayList<Building> buildings = new ArrayList<>();
        buildings.add(new Building(6, "CU", "Fenway", 42.3416669, -71.0915419, false, "Cushing Hall"));
        buildings.add(new Building(7, "CA", "Fenway", 42.341477, -71.0936997, false, "Cahners Hall"));
        buildings.add(new Building(10, "HF", "Fenway", 42.3415995, -71.0897866, false, "Hillel-Frager"));
        buildings.add(new Building(17, "MC", "North", 42.3403227, -71.092524, false, "Marino Recreation Center"));
        buildings.add(new Building(23, "WV", "West Village", 42.3377807, -71.0950127, false, "West Village F, G, H"));
        buildings.add(new Building(24, "RY", "Centennial", 42.3365775, -71.0927975, false, "Ryder Hall"));
        buildings.add(new Building(26, "BK", "West Village", 42.3367181, -71.093842, false, "Behrakis Health Sciences Center"));
        buildings.add(new Building(27, "AF", "West Village", 42.33749, -71.0937956, false, "O’Bryant African American Institute"));
        buildings.add(new Building(29, "ME", "Centennial", 42.3378125, -71.0931276, false, "Meserve Hall"));
        buildings.add(new Building(30, "SH", "Centennial", 42.3375573, -71.0923795, false, "Shillman Hall"));
        buildings.add(new Building(31, "NI", "Centennial", 42.3381069, -71.0921763, false, "Nightingale Hall"));
        buildings.add(new Building(33, "HO", "Centennial", 42.3381791, -71.0928458, false, "Holmes Hall"));
        buildings.add(new Building(34, "LA", "Centennial", 42.3384215, -71.0930697, false, "Lake Hall"));
        buildings.add(new Building(35, "KA", "Plaza", 42.3386223, -71.0931943, false, "Kariotis Hall"));
        buildings.add(new Building(36, "CG", "Plaza", 42.3389432, -71.0938253, false, "Cargill Hall"));
        buildings.add(new Building(37, "ST", "Plaza", 42.339003, -71.0935624, false, "Stearns Center"));
        buildings.add(new Building(38, "KN", "Plaza", 42.3392027, -71.0930708, false, "Knowles Center"));
        buildings.add(new Building(39, "DK", "Plaza", 42.3386842, -71.0926833, false, "Dockser Hall"));
        buildings.add(new Building(40, "BN", "Center", 42.3390314, -71.0920404, true, "Barletta Natatorium"));
        buildings.add(new Building(41, "CB", "Center", 42.3394531, -71.0919062, true, "Cabot Physical Education Center (CB"));
        buildings.add(new Building(42, "RI", "Center", 42.3399696, -71.0908448, true, "Richards Hall"));
        buildings.add(new Building(43, "DG", "Center", 42.3403277, -71.0900382, true, "Dodge Hall"));
        buildings.add(new Building(44, "MA", "Matthews", 42.3412391, -71.0867117, false, "Matthews Arena"));
        buildings.add(new Building(46, "HT", "Center", 42.3397629, -71.0883481, false, "Hurtig Hall"));
        buildings.add(new Building(47, "CN", "Center", 42.340086, -71.0886964, false, "Cullinane Hall"));
        buildings.add(new Building(48, "MU", "Center", 42.3397752, -71.0893004, true, "Mugar Life Sciences Building"));
        buildings.add(new Building(49, "RB", "Center", 42.3392655, -71.0889125, false, "Robinson Hall"));
        buildings.add(new Building(50, "CSC", "Center", 42.3391489, -71.0897579, true, "Curry Student Center"));
        buildings.add(new Building(52, "EL", "Center", 42.3393659, -71.0899888, true, "Ell Hall"));
        buildings.add(new Building(53, "HA", "Center", 42.3394668, -71.0907173, true, "Hayden Hall"));
        buildings.add(new Building(54, "CH", "Center", 42.3387774, -71.0911122, true, "Churchill Hall"));
        buildings.add(new Building(55, "FR", "Center", 42.3385709, -71.0918029, true, "Forsyth Building"));
        buildings.add(new Building(56, "LC", "Center", 42.3381742, -71.0919376, false, "Latino/a Student Cultural Center"));
        buildings.add(new Building(57, "DA", "Center", 42.3380837, -71.0915343, false, "Dana Research Center"));
        buildings.add(new Building(58, "SN", "Center", 42.3382885, -71.0910495, true, "Snell Engineering Center"));
        buildings.add(new Building(59, "SL", "Center", 42.33854, -71.08826, true, "Snell Library"));
        buildings.add(new Building(60, "EC", "Center", 42.3376711, -71.0910815, false, "Egan Engineering/ Science Research Center"));
        buildings.add(new Building(61, "RG", "Center", 42.3370771, -71.0913416, false, "Architecture Studio"));
        buildings.add(new Building(63, "RP", "Columbus", 42.3355765, -71.0903964, false, "Renaissance Park"));
        buildings.add(new Building(66, "CP", "Columbus", 42.3376757, -71.0874819, false, "Alumni Center at Columbus Place"));
        buildings.add(new Building(68, "SB", "Strip", 42.3377009, -71.0940446, false, "Badger and Rosen SquashBusters Center"));
        buildings.add(new Building(70, "AC", "Fenway", 42.3431744, -71.092534, false, "Asian American Center"));
        buildings.add(new Building(71, "FC", "St. Stephens", 42.3420912, -71.0901576, false, "Fenway Center"));
        buildings.add(new Building(72, "CC", "St. Stephens", 42.3416307, -71.0897127, false, "Catholic Center"));
        buildings.add(new Building(73, "RO", "St. Stephens", 42.3407475, -71.0906917, false, "ROTC Office"));
        buildings.add(new Building(74, "BV", "Pool", 42.3453256, -71.0859921, false, "101 Belvidere"));
        buildings.add(new Building(77, "INV", "Strip", 42.3352532, -71.0915672, false, "International Village"));
        buildings.add(new Building(78, "YMC", "Center", 42.3407354, -71.0894811, false, "Hastings Hall at the YMCA"));
        buildings.add(new Building(79, "177", "Pool", 42.3449342, -71.0849756, false, "177 Huntington"));
        buildings.add(new Building(80, "TF", "Fenway", 42.3407946, -71.093952, false, "140 The Fenway (TF )"));
        buildings.add(new Building(81, "236", "Theater", 42.3429946, -71.0862284, false, "236 Huntington"));
        buildings.add(new Building(82, "EV", "Center", 42.3404407, -71.0890679, false, "East Village"));
        buildings.add(new Building(83, "ISEC", "Strip", 42.3377374, -71.0891008, false, "Interdisciplinary Science and Engineering Complex"));
        buildings.add(new Building(84, "271", "Symphony", 42.3421028, -71.0884667, false, "271 Huntington"));
        return buildings;
    }
}
