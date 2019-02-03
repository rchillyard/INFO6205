package edu.neu.coe.info6205.graphs.tunnels;

import java.util.ArrayList;

@SuppressWarnings("SpellCheckingInspection")
public class BuildingLoader {

    public static ArrayList<Building> createBuildings() {
        //can be replaced with the code to load the buildings into array list from excel file.
        ArrayList<Building> buildings = new ArrayList<>();
        buildings.add(new Building(6, "CU", "Fenway", -71.0915419, 42.3416669, false, "Cushing Hall"));
        buildings.add(new Building(7, "CA", "Fenway", -71.0936997, 42.341477, false, "Cahners Hall"));
        buildings.add(new Building(10, "HF", "Fenway", -71.0897866, 42.3415995, false, "Hillel-Frager"));
        buildings.add(new Building(17, "MC", "North", -71.092524, 42.3403227, false, "Marino Recreation Center"));
        buildings.add(new Building(23, "WV", "West Village", -71.0950127, 42.3377807, false, "West Village F, G, H"));
        buildings.add(new Building(24, "RY", "Centennial", -71.0927975, 42.3365775, false, "Ryder Hall"));
        buildings.add(new Building(26, "BK", "West Village", -71.093842, 42.3367181, false, "Behrakis Health Sciences Center"));
        buildings.add(new Building(27, "AF", "West Village", -71.0937956, 42.33749, false, "O’Bryant African American Institute"));
        buildings.add(new Building(29, "ME", "Centennial", -71.0931276, 42.3378125, false, "Meserve Hall"));
        buildings.add(new Building(30, "SH", "Centennial", -71.0923795, 42.3375573, false, "Shillman Hall"));
        buildings.add(new Building(31, "NI", "Centennial", -71.0921763, 42.3381069, false, "Nightingale Hall"));
        buildings.add(new Building(33, "HO", "Centennial", -71.0928458, 42.3381791, false, "Holmes Hall"));
        buildings.add(new Building(34, "LA", "Centennial", -71.0930697, 42.3384215, false, "Lake Hall"));
        buildings.add(new Building(35, "KA", "Plaza", -71.0931943, 42.3386223, false, "Kariotis Hall"));
        buildings.add(new Building(36, "CG", "Plaza", -71.0938253, 42.3389432, false, "Cargill Hall"));
        buildings.add(new Building(37, "ST", "Plaza", -71.0935624, 42.339003, false, "Stearns Center"));
        buildings.add(new Building(38, "KN", "Plaza", -71.0930708, 42.3392027, false, "Knowles Center"));
        buildings.add(new Building(39, "DK", "Plaza", -71.0926833, 42.3386842, false, "Dockser Hall"));
        buildings.add(new Building(40, "BN", "Center", -71.0920404, 42.3390314, true, "Barletta Natatorium"));
        buildings.add(new Building(41, "CB", "Center", -71.0892023,42.3393182, true, "Cabot Physical Education Center (CB"));
        buildings.add(new Building(42, "RI", "Center", -71.0887314,42.3397321, true, "Richards Hall"));
        buildings.add(new Building(43, "DG", "Center", -71.0877206,42.3400901, true, "Dodge Hall"));
        buildings.add(new Building(44, "MA", "Matthews", -71.0867117, 42.3412391, false, "Matthews Arena"));
        buildings.add(new Building(46, "HT", "Center", -71.08617,42.3397352, false, "Hurtig Hall"));
        buildings.add(new Building(47, "CN", "Center", -71.0866092,42.3401931, false, "Cullinane Hall"));
        buildings.add(new Building(48, "MU", "Center", -71.0874332,42.3398622, true, "Mugar Life Sciences Building"));
        buildings.add(new Building(49, "RB", "Center", -71.0867287,42.3392938, false, "Robinson Hall"));
        buildings.add(new Building(50, "CSC", "Center", -71.0879985,42.33949, true, "Curry Student Center"));
        buildings.add(new Building(52, "EL", "Center", -71.0880149,42.3398101, true, "Ell Hall"));
        buildings.add(new Building(53, "HA", "Center", -71.0885712,42.3395146, true, "Hayden Hall"));
        buildings.add(new Building(54, "CH", "Center", -71.0888053,42.3387453, true, "Churchill Hall"));
        buildings.add(new Building(55, "FR", "Center", -71.0893246,42.3385076, true, "Forsyth Building"));
        buildings.add(new Building(56, "LC", "Center", -71.0919376, 42.3381742, false, "Latino/a Student Cultural Center"));
        buildings.add(new Building(57, "DA", "Center", -71.0915343, 42.3380837, false, "Dana Research Center"));
        buildings.add(new Building(58, "SN", "Center", -71.0889364,42.3382166, true, "Snell Engineering Center"));
        buildings.add(new Building(59, "SL", "Center", -71.08826,42.33854, true, "Snell Library"));
        buildings.add(new Building(60, "EC", "Center", -71.0888596,42.337741, false, "Egan Engineering/ Science Research Center"));
        buildings.add(new Building(61, "RG", "Center", -71.0913416, 42.3370771, false, "Architecture Studio"));
        buildings.add(new Building(63, "RP", "Columbus", -71.0903964, 42.3355765, false, "Renaissance Park"));
        buildings.add(new Building(66, "CP", "Columbus", -71.0853362,42.3376443, false, "Alumni Center at Columbus Place"));
        buildings.add(new Building(68, "SB", "Strip", -71.0861378,42.3380817, false, "Badger and Rosen SquashBusters Center"));
        buildings.add(new Building(70, "AC", "Fenway", -71.092534, 42.3431744, false, "Asian American Center"));
        buildings.add(new Building(71, "FC", "St. Stephens", -71.0901576, 42.3420912, false, "Fenway Center"));
        buildings.add(new Building(72, "CC", "St. Stephens", -71.0897127, 42.3416307, false, "Catholic Center"));
        buildings.add(new Building(73, "RO", "St. Stephens", -71.0906917, 42.3407475, false, "ROTC Office"));
        buildings.add(new Building(74, "BV", "Pool", -71.0859921, 42.3453256, false, "101 Belvidere"));
        buildings.add(new Building(77, "INV", "Strip", -71.0915672, 42.3352532, false, "International Village"));
        buildings.add(new Building(78, "YMC", "Center", -71.0894811, 42.3407354, false, "Hastings Hall at the YMCA"));
        buildings.add(new Building(79, "177", "Pool", -71.0849756, 42.3449342, false, "177 Huntington"));
        buildings.add(new Building(80, "TF", "Fenway", -71.093952, 42.3407946, false, "140 The Fenway (TF )"));
        buildings.add(new Building(81, "236", "Theater", -71.0862284, 42.3429946, false, "236 Huntington"));
        buildings.add(new Building(82, "EV", "Center", -71.0890679, 42.3404407, false, "East Village"));
        buildings.add(new Building(83, "ISEC", "Strip", -71.08703, 42.33748, false, "Interdisciplinary Science and Engineering Complex"));
        buildings.add(new Building(84, "271", "Symphony", -71.0884667, 42.3421028, false, "271 Huntington"));
        return buildings;
    }
}
