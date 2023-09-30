package edu.neu.coe.info6205.life.library;

import java.util.HashMap;
import java.util.Map;

public class Library {

    final public static String Blip = "0 0";

    final public static String Blip2 = "0 0, 1 0";

    final public static String Block = "1 1, 1 2, 2 2, 2 1";

    final public static String Beehive = "1 2, 2 1, 3 1, 4 2, 3 3, 2 3";

    final public static String Loaf = "1 3, 2 4, 3 4, 4 3, 4 2, 3 1, 2 2";

    final public static String Blinker = "0 -1, 0 0, 0 1";

    final public static String Glider1 = "0 0, 1 0, 2 0, 2 -1, 1 -2";

    final public static String Glider2 = "2 0, 1 0, 0 0, 0 -1, 1 -2";

    final public static String Glider3 = "0 0, 1 0, 2 0, 2 1, 1 2";

    final public static Map<String, String> map = new HashMap<>();

    public static String get(String key) {
        return map.get(key.toLowerCase());
    }

    public static String put(String key, String value) {
        return map.put(key.toLowerCase(), value);
    }

    static {
        put("Blip", Blip);
        put("Blip2", Blip2);
        put("Block", Block);
        put("Beehive", Beehive);
        put("Loaf", Loaf);
        put("Blinker", Blinker);
        put("Glider1", Glider1);
        put("Glider2", Glider2);
        put("Glider3", Glider3);
    }
}