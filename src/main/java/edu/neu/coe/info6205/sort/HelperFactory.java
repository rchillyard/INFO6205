package edu.neu.coe.info6205.sort;

import edu.neu.coe.info6205.util.Config;

public class HelperFactory {

    /**
     * Factory method to create a Helper.
     *
     * @param description the description of the Helper.
     * @param nElements   the number of elements to be sorted.
     * @param config      the configuration.
     * @param <X>         the underlying type.
     * @return a Helper<X></X>
     */
    public static <X extends Comparable<X>> Helper<X> create(String description, int nElements, Config config) {
        return create(description, nElements, config.isInstrumented(), config);
    }

    /**
     * CONSIDER eliminating this signature.
     *
     * @param description  the description of the Helper.
     * @param nElements    the number of elements to be sorted.
     * @param instrumented an explicit value of instrumented, not derived from the config.
     * @param config       the configuration.
     * @param <X>          the underlying type.
     * @return a Helper<X></X>
     */
    public static <X extends Comparable<X>> Helper<X> create(String description, int nElements, boolean instrumented, Config config) {
        return instrumented ? new InstrumentedHelper<>(description, nElements, config) : new BaseHelper<>(description, nElements);
    }

}
