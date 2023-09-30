package edu.neu.coe.info6205.util;


import edu.neu.coe.info6205.sort.SortException;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static edu.neu.coe.info6205.util.Utilities.formatWhole;


/**
 * Singleton class HuskySortBenchmarkHelper
 */
public class SortBenchmarkHelper {

    final static LazyLogger logger = new LazyLogger(SortBenchmarkHelper.class);

    public static LocalDateTime[] generateRandomLocalDateTimeArray(int number) {
        LocalDateTime[] result = new LocalDateTime[number];
        ThreadLocalRandom random = ThreadLocalRandom.current();
        for (int i = 0; i < number; i++) {
            result[i] = LocalDateTime.ofEpochSecond(random.nextLong(new Date().getTime()), random.nextInt(0, 1000000000), ZoneOffset.UTC);
        }
        return result;
    }

    // TEST
    public static String[] getWords(String resource, Function<String, Collection<String>> getStrings) throws FileNotFoundException {
        List<String> words = new ArrayList<>();
        final FileReader fr = new FileReader(getFile(resource, SortBenchmarkHelper.class));
        for (Object line : new BufferedReader(fr).lines().toArray()) words.addAll(getStrings.apply((String) line));
        words = words.stream().distinct().filter(new Predicate<String>() {
            private static final int MINIMUM_LENGTH = 2;

            public boolean test(String s) {
                return s.length() >= MINIMUM_LENGTH;
            }
        }).collect(Collectors.toList());
        logger.info("Testing with words: " + formatWhole(words.size()) + " from " + resource);
        String[] result = new String[words.size()];
        result = words.toArray(result);
        return result;
    }

    static Collection<String> getWords(Pattern regex, String line) {
        final Matcher matcher = regex.matcher(line);
        if (matcher.find()) {
            final String word = matcher.group(1);
            final String[] strings = word.split("[\\s\\p{Punct}\\uFF0C]");
            return Arrays.asList(strings);
        } else
            return new ArrayList<>();
    }

    static void logNormalizedTime(double time, String prefix, Function<Double, Double> normalizer) {
        logger.info(prefix + normalizer.apply(time));
    }

    // TEST
    static String[] generateRandomStringArray(String[] lookupArray, int number) {
        if (lookupArray.length == 0) throw new SortException("lookupArray is empty");
        Random r = new Random();
        String[] result = new String[number];
        for (int i = 0; i < number; i++) result[i] = getRandomElement(lookupArray, r);
        return result;
    }

    // TEST
    private static String getFile(String resource, @SuppressWarnings("SameParameterValue") Class<?> clazz) throws FileNotFoundException {
        final URL url = clazz.getClassLoader().getResource(resource);
        if (url != null) return url.getFile();
        throw new FileNotFoundException(resource + " in " + clazz);
    }

    private static String getRandomElement(String[] strings, int length, Random r) {
        return strings[r.nextInt(length)];
    }

    private static String getRandomElement(String[] strings, Random r) {
        return getRandomElement(strings, strings.length, r);
    }

    // NOTE private constructor (singleton pattern)
    private SortBenchmarkHelper() {
    }
}