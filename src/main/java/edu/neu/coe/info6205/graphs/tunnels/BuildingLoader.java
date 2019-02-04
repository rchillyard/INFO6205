package edu.neu.coe.info6205.graphs.tunnels;

import java.util.ArrayList;

@SuppressWarnings("SpellCheckingInspection")
public class BuildingLoader {

    public static ArrayList<Building> createBuildings() {
        //can be replaced with the code to load the buildings into array list from excel file.
        ArrayList<Building> buildings = new ArrayList<>();
        buildings.add(new Building(6, "CU", "Fenway", -71.091542,42.341667, false, "Cushing Hall"));
        buildings.add(new Building(7, "CA", "Fenway", -71.0916079,42.3414373, false, "Cahners Hall"));
        buildings.add(new Building(10, "HF", "St. Stephens", -71.087652,42.3416555, false, "Hillel-Frager"));
        buildings.add(new Building(17, "MC", "North", -71.0903085,42.3401168, false, "Marino Recreation Center"));
        buildings.add(new Building(23, "WV", "West Village", -71.0922342,42.3375312, false, "West Village F, G, H"));
        buildings.add(new Building(24, "RY", "Centennial", -71.090663,42.3366811, false, "Ryder Hall"));
        buildings.add(new Building(26, "BK", "West Village", -71.0914709,42.337067, false, "Behrakis Health Sciences Center"));
        buildings.add(new Building(27, "AF", "West Village", -71.0912372,42.3376446, false, "O’Bryant African American Institute"));
        buildings.add(new Building(29, "ME", "Centennial", -71.0909018,42.3376732, false, "Meserve Hall"));
        buildings.add(new Building(30, "SH", "Centennial", -71.090202,42.3375055, false, "Shillman Hall"));
        buildings.add(new Building(31, "NI", "Centennial", -71.0900088,42.3381149, false, "Nightingale Hall"));
        buildings.add(new Building(33, "HO", "Centennial", -71.0908826,42.3380244, false, "Holmes Hall"));
        buildings.add(new Building(34, "LA", "Centennial", -71.0908009,42.3383546, false, "Lake Hall"));
        buildings.add(new Building(35, "KA", "Plaza", -71.091,42.3386339, false, "Kariotis Hall"));
        buildings.add(new Building(36, "CG", "Plaza", -71.0916846,42.3389628, false, "Cargill Hall"));
        buildings.add(new Building(37, "ST", "Plaza", -71.0914001,42.3390863, false, "Stearns Center"));
        buildings.add(new Building(38, "KN", "Plaza", -71.0908662,42.3392942, false, "Knowles Center"));
        buildings.add(new Building(39, "DK", "Plaza", -71.092683,42.338684, false, "Dockser Hall"));
        buildings.add(new Building(40, "BN", "Center", -71.0900766,42.3390429, true, "Barletta Natatorium"));
        buildings.add(new Building(41, "CB", "Center", -71.0892023,42.3393182, true, "Cabot Physical Education Center (CB"));
        buildings.add(new Building(42, "RI", "Center", -71.0887314,42.3397321, true, "Richards Hall"));
        buildings.add(new Building(43, "DG", "Center", -71.0877206,42.3400901, true, "Dodge Hall"));
        buildings.add(new Building(44, "MA", "Matthews", -71.0844697,42.3411121, false, "Matthews Arena"));
        buildings.add(new Building(46, "HT", "Center", -71.08617,42.3397352, false, "Hurtig Hall"));
        buildings.add(new Building(47, "CN", "Center", -71.086609,42.340193, false, "Cullinane Hall"));
        buildings.add(new Building(48, "MU", "Center", -71.0874332,42.3398622, true, "Mugar Life Sciences Building"));
        buildings.add(new Building(49, "RB", "Center", -71.0867287,42.3392938, false, "Robinson Hall"));
        buildings.add(new Building(50, "CSC", "Center", -71.0879985,42.33949, true, "Curry Student Center"));
        buildings.add(new Building(52, "EL", "Center", -71.0880149,42.3398101, true, "Ell Hall"));
        buildings.add(new Building(53, "HA", "Center", -71.0885712,42.3395146, true, "Hayden Hall"));
        buildings.add(new Building(54, "CH", "Center", -71.0888053,42.3387453, true, "Churchill Hall"));
        buildings.add(new Building(55, "FR", "Center", -71.0893246,42.3385076, true, "Forsyth Building"));
        buildings.add(new Building(56, "LC", "Center", -71.0899532,42.3386181, false, "Latino/a Student Cultural Center"));
        buildings.add(new Building(57, "DA", "Center", -71.0898067,42.3384012, false, "Dana Research Center"));
        buildings.add(new Building(58, "SN", "Center", -71.0889364,42.3382166, true, "Snell Engineering Center"));
        buildings.add(new Building(59, "SL", "Center", -71.08826,42.33854, true, "Snell Library"));
        buildings.add(new Building(60, "EC", "Center", -71.0888596,42.337741, false, "Egan Engineering/ Science Research Center"));
        buildings.add(new Building(61, "RG", "Center", -71.0893142,42.337077, false, "Architecture Studio"));
        buildings.add(new Building(63, "RP", "Columbus", -71.0883361,42.3356077, false, "Renaissance Park"));
        buildings.add(new Building(66, "CP", "Columbus", -71.0853362,42.3376443, false, "Alumni Center at Columbus Place"));
        buildings.add(new Building(68, "SB", "Strip", -71.0861378,42.3380817, false, "Badger and Rosen SquashBusters Center"));
        buildings.add(new Building(70, "AC", "Fenway", -71.0903346,42.3433643, false, "Asian American Center"));
        buildings.add(new Building(71, "FC", "St. Stephens", -71.0877547,42.3419879, false, "Fenway Center"));
        buildings.add(new Building(72, "CC", "St. Stephens", -71.089713,42.341631, false, "Catholic Center"));
        buildings.add(new Building(73, "RO", "St. Stephens", -71.090692,42.340748, false, "ROTC Office"));
        buildings.add(new Building(74, "BV", "Pool", -71.0833205,42.3456273, false, "101 Belvidere"));
        buildings.add(new Building(77, "INV", "Strip", -71.089035,42.3352609, false, "International Village"));
        buildings.add(new Building(78, "YMC", "Center", -71.0894811, 42.3407354, false, "Hastings Hall at the YMCA"));
        buildings.add(new Building(79, "177", "Pool", -71.0828624,42.3448626, false, "177 Huntington"));
        buildings.add(new Building(80, "TF", "Fenway", -71.09178, 42.34062, false, "140 The Fenway"));
        buildings.add(new Building(81, "236", "Theater", -71.0842217,42.3425668, false, "236 Huntington"));
        buildings.add(new Building(82, "EV", "Center", -71.087,42.34039, false, "East Village"));
        buildings.add(new Building(83, "ISEC", "Strip", -71.08703, 42.33748, false, "Interdisciplinary Science and Engineering Complex"));
        buildings.add(new Building(84, "271", "Symphony", -71.0864607,42.341833, false, "271 Huntington"));
        return buildings;
    }
}
