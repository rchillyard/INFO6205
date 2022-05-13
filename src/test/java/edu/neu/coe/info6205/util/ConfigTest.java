package edu.neu.coe.info6205.util;

import edu.neu.coe.info6205.sort.InstrumentedHelper;
import org.ini4j.Ini;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;

import static edu.neu.coe.info6205.sort.InstrumentedHelper.HITS;
import static org.junit.Assert.*;

public class ConfigTest {

    @Test
    public void testConfig() throws IOException {
        final Config config = Config.load();
        String name = config.get("main", "version");
        System.out.println("ConfigTest: " + name);
    }

    @Test
    public void testConfigFixed() {
        final Config config = setupConfig(TRUE, "0", "10", "", "");
        assertTrue(config.isInstrumented());
        assertEquals(0L, config.getLong(Config.HELPER, SEED, -1L));
        assertEquals(10, config.getInt(INSTRUMENTING, INVERSIONS, 0));
    }

    @Test
    public void testCopy1() {
        final Config config = setupConfig(FALSE, "", "", "", "");
        int originalSeed = config.getInt(Config.HELPER, SEED, -1);
        Config config1 = config.copy(Config.HELPER, SEED, "1");
        assertEquals(originalSeed, config.getInt(Config.HELPER, SEED, -1));
        assertEquals(1, config1.getInt(Config.HELPER, SEED, -1));
    }

    @Test
    public void testCopy2() {
        final Config config = setupConfig(FALSE, "", "", "", "");
        String junk = "junk";
        assertEquals(-1, config.getInt(Config.HELPER, junk, -1));
        Config config1 = config.copy(Config.HELPER, junk, "1");
        assertEquals(1, config1.getInt(Config.HELPER, junk, -1));
    }

    // NOTE: we ignore this for now, because this would need to run before any other tests in order to work as originally designed.
    @Ignore
    public void testUnLogged() throws IOException {
        final Config config = Config.load();
        final PrivateMethodTester privateMethodTester = new PrivateMethodTester(config);
        assertTrue((Boolean) privateMethodTester.invokePrivate("unLogged", Config.HELPER + "." + SEED));
        assertFalse((Boolean) privateMethodTester.invokePrivate("unLogged", Config.HELPER + "." + SEED));
    }

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

    public static Config setupConfig(final String instrumenting, final String seed, final String inversions, String cutoff, String interimInversions, String insurance, String noCopy) {
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
        ini.put(Config.HELPER,  INSURANCE, insurance);
        ini.put(Config.HELPER, NOCOPY, noCopy);
        return new Config(ini);
    }

    public static final String TRUE = "true";
    public static final String FALSE = "";
    public static final String INSTRUMENTING = InstrumentedHelper.INSTRUMENTING;
    public static final String INVERSIONS = InstrumentedHelper.INVERSIONS;
    public static final String SEED = "seed";
    public static final String CUTOFF = "cutoff";
    public static final String SWAPS = InstrumentedHelper.SWAPS;
    public static final String COMPARES = InstrumentedHelper.COMPARES;
    public static final String COPIES = InstrumentedHelper.COPIES;
    public static final String FIXES = InstrumentedHelper.FIXES;
    public static final String INSURANCE = "";
    public static final String NOCOPY = "";
;
}
