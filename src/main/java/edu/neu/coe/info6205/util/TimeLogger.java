package edu.neu.coe.info6205.util;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.function.BiFunction;

public class TimeLogger {
    private final String prefix;
    private final BiFunction<Double, Integer, Double> normalizer;

    public TimeLogger(String prefix, BiFunction<Double, Integer, Double> normalizer) {
        this.prefix = prefix;
        this.normalizer = normalizer;
    }

    public void log(Double time, Integer N) {
        logger.info(prefix + " " + formatTime(normalizer.apply(time, N)));
    }

    private static String formatTime(double time) {
        decimalFormat.applyPattern(timePattern);
        return decimalFormat.format(time);
    }

    final static LazyLogger logger = new LazyLogger(TimeLogger.class);

    private static final Locale locale = new Locale("en", "US");
    private static final String timePattern = "######.00";
    private static final DecimalFormat decimalFormat = (DecimalFormat)
            NumberFormat.getNumberInstance(locale);

}
