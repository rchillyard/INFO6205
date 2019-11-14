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
				return map.get(key);
		}

		static {
				map.put("blip", Blip);
				map.put("blip2", Blip2);
				map.put("block", Block);
				map.put("beehive", Beehive);
				map.put("loaf", Loaf);
				map.put("blinker", Blinker);
				map.put("glider1", Glider1);
				map.put("glider2", Glider2);
				map.put("glider3", Glider3);
		}
}
