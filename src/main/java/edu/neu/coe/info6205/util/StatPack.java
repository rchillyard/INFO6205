package edu.neu.coe.info6205.util;

import java.util.HashMap;
import java.util.function.Function;

public class StatPack {

    public StatPack(Function<Double, Double> normalizer, int N, String... keys) {
        map = new HashMap<>();
        for (String key : keys) map.put(key, new Statistics(key, normalizer, N));
    }

    public void add(String key, double x) {
        getStatistics(key).add(x);
    }

    public Statistics getStatistics(String key) {
        final Statistics statistics = map.get(key);
        if (statistics == null) throw new RuntimeException("StatPack.getStatistics(" + key + "): key not valid");
        return statistics;
    }

    public int getCount(String key) {
        return getStatistics(key).getCount();
    }

    public double total(String key) {
        return getStatistics(key).total();
    }

    public double mean(String key) {
        return getStatistics(key).mean();
    }

    public double stdDev(String key) {
        return getStatistics(key).stdDev();
    }

    @Override
    public String toString() {
        final StringBuilder stringBuilder = new StringBuilder("StatPack {");
        if (map.isEmpty()) stringBuilder.append("<empty>}");
        for (String key : map.keySet()) {
            final Statistics statistics = map.get(key);
            stringBuilder.append(statistics.toString()).append("; ");
        }
        return stringBuilder.toString().replaceAll("; $", "}");
    }

    private final HashMap<String, Statistics> map;
}