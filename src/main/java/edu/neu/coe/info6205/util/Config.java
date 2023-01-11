package edu.neu.coe.info6205.util;

import edu.neu.coe.info6205.sort.BaseHelper;
import org.ini4j.Ini;
import org.ini4j.Profile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.net.URL;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static edu.neu.coe.info6205.sort.InstrumentedHelper.*;
import static edu.neu.coe.info6205.sort.linearithmic.MergeSort.INSURANCE;
import static edu.neu.coe.info6205.sort.linearithmic.MergeSort.NOCOPY;

@SuppressWarnings("SuspiciousMethodCalls")
public class Config {

    /**
     * Method to copy this Config, but setting sectionName.optionName to be value.
     *
     * @param sectionName the section name.
     * @param optionName  the option name.
     * @param value       the new value.
     * @return a new Config as described.
     */
    public Config copy(String sectionName, String optionName, String value) {
        Config result = new Config(copyIni());
        Profile.Section section = result.ini.get(sectionName);
        section.put(optionName, value);
        result.ini.replace(sectionName, section);
        return result;
    }

    public String get(Object sectionName, Object optionName) {
        return get(sectionName, optionName, String.class);
    }

    public <T> T get(Object sectionName, Object optionName, Class<T> clazz) {
        final T t = ini.get(sectionName, optionName, clazz);
        if (unLogged(sectionName + "." + optionName))
            logger.debug(() -> "Config.get(" + sectionName + ", " + optionName + ") = " + t);
        return t;
    }

    public boolean getBoolean(String sectionName, String optionName) {
        return get(sectionName, optionName, boolean.class);
    }

    public int getInt(final String sectionName, final String optionName, final int defaultValue) {
        final String s = get(sectionName, optionName);
        if (s == null || s.isEmpty()) return defaultValue;
        return Integer.parseInt(s);
    }

    public long getLong(final String sectionName, final String optionName, final long defaultValue) {
        final String s = get(sectionName, optionName);
        if (s == null || s.isEmpty()) return defaultValue;
        return Long.parseLong(s);
    }

    public String getComment(String key) {
        final String comment = ini.getComment(key);
        if (unLogged(key))
            logger.debug(() -> "Config.getComment(" + key + ") = " + comment);
        return comment;
    }

    public Collection<Profile.Section> getAll(Object key) {
        return ini.getAll(key);
    }

    public Profile.Section get(Object key) {
        return ini.get(key);
    }

    public Profile.Section get(Object key, int index) {
        return ini.get(key, index);
    }

    public Profile.Section getOrDefault(Object key, Profile.Section defaultValue) {
        return ini.getOrDefault(key, defaultValue);
    }

    public Config(Ini ini) {
        this.ini = ini;
    }

    public Config(Reader reader) throws IOException {
        this(new Ini(reader));
    }

    public Config(InputStream stream) throws IOException {
        this(new Ini(stream));
    }

    public Config(URL resource) throws IOException {
        this(new Ini(resource));
    }

    public Config(File input) throws IOException {
        this(new Ini(input));
    }

    public Config(String file) throws IOException {
        this(new File(file));
    }

    /**
     * Method to determine if this configuration has an instrumented helper.
     * NOTE: we would prefer to place this logic in the Helper class but we put it here for now.
     *
     * @return true if helper is instrument
     */
    public boolean isInstrumented() {
        return getBoolean(HELPER, INSTRUMENT);
    }

    // CONSIDER: sort these out.
    public static final String HELPER = "helper";
    public static final String INSTRUMENT = BaseHelper.INSTRUMENT;

    /**
     * Method to load the appropriate configuration.
     * <p>
     * If clazz is not null, then we look for config.ini relative to the given class.
     * If clazz is null, or if the resource cannot be found relative to the class,
     * then we look in the root directory.
     *
     * @param clazz the Class in which to look for the config.ini file (may be null).
     * @return a new Config.
     * @throws IOException if config.ini cannot be found using the locations described above.
     */
    public static Config load(final Class<?> clazz) throws IOException {
        final String name = "config.ini";
        URL resource = null;
        if (clazz != null) resource = clazz.getResource(name);
        if (resource == null)
            resource = Config.class.getResource("/" + name);
        if (resource != null) return new Config(resource);
        throw new IOException("resource " + name + " not found for " + clazz);
    }

    public static Config load() throws IOException {
        return load(null);
    }

    public static final String SEED = "seed";
    public static final String CUTOFF = "cutoff";

    public static Config setupConfig(final String instrumenting, final String seed, final String inversions, String cutoff, String interimInversions) {
        final Ini ini = new Ini();
        final String sInstrumenting = INSTRUMENTING;
        ini.put(Config.HELPER, Config.INSTRUMENT, instrumenting);
        ini.put(Config.HELPER, SEED, seed);
        ini.put(Config.HELPER, CUTOFF, cutoff);
        ini.put(sInstrumenting, INVERSIONS, inversions);
        ini.put(sInstrumenting, SWAPS, instrumenting);
        ini.put(sInstrumenting, COMPARES, instrumenting);
        ini.put(sInstrumenting, COPIES, instrumenting);
        ini.put(sInstrumenting, FIXES, instrumenting);
        ini.put(sInstrumenting, HITS, instrumenting);
        ini.put("huskyhelper", "countinteriminversions", interimInversions);
        return new Config(ini);
    }


    public static Config setupConfig2(final String instrumenting, final String seed, final String inversions, String cutoff, String interimInversions, String insurance, String noCopy) {
        final Ini ini = new Ini();
        final String sInstrumenting = INSTRUMENTING;
        ini.put(Config.HELPER, Config.INSTRUMENT, instrumenting);
        ini.put(Config.HELPER, SEED, seed);
        ini.put(Config.HELPER, CUTOFF, cutoff);
        ini.put(sInstrumenting, INVERSIONS, inversions);
        ini.put(sInstrumenting, SWAPS, instrumenting);
        ini.put(sInstrumenting, COMPARES, instrumenting);
        ini.put(sInstrumenting, COPIES, instrumenting);
        ini.put(sInstrumenting, FIXES, instrumenting);
        ini.put("huskyhelper", "countinteriminversions", interimInversions);
        ini.put(Config.HELPER, INSURANCE, insurance);
        ini.put(Config.HELPER, NOCOPY, noCopy);
        return new Config(ini);
    }

    public static Config setupConfigFixes() {
        final Ini ini = new Ini();
        ini.put(Config.HELPER, Config.INSTRUMENT, true);
        ini.put(Config.HELPER, CUTOFF, 0);
        ini.put(INSTRUMENTING, FIXES, true);
        return new Config(ini);
    }

    private boolean unLogged(String s) {
        Boolean value = logged.get(s);
        if (value == null) {
            logged.put(s, true);
            return true;
        }
        return !value;
    }

    private Ini copyIni() {
        Ini result = new Ini();
        // CONSIDER using result.putAll(this.result)...
        // XXX ... but only if Ini4J fixes the bug (unlikely since it hasn't been touched in 6 years!)
        for (Map.Entry<String, Profile.Section> entry : this.ini.entrySet())
            for (Map.Entry<String, String> x : entry.getValue().entrySet())
                result.put(entry.getKey(), x.getKey(), x.getValue());
        return result;
    }

    final static LazyLogger logger = new LazyLogger(Config.class);

    // NOTE this is static because, otherwise, we get too much logging when we copy a Config that hasn't had all enquiries made yet.
    private static final Map<String, Boolean> logged = new HashMap<>();

    private final Ini ini;
}
