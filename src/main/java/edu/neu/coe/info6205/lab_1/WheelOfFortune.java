package edu.neu.coe.info6205.lab_1;

import java.util.Objects;
import java.util.Random;

/**
 * Wheel of Fortune class which will yield a value of T according to a probability distribution
 * given by the set of events.
 * <p>
 * In order to pass all of the unit tests, you will also have to implement code in FrequencyCounter.
 * That bit of code is very simple.
 *
 * @param <T> the type yielded by get().
 */
public class WheelOfFortune<T> {

    /**
     * Primary constructor.
     *
     * @param random an instance of Random.
     * @param events a variable number of Event instances.
     */
    @SafeVarargs
    public WheelOfFortune(Random random, Event<T>... events) {
        this.random = random;
        this.events = events;
        this.total = getTotal();
    }

    /**
     * Secondary constructor that uses a Random based on the given seed.
     *
     * @param seed   a long value which will be used as the seed for the random number generator.
     * @param events a variable number of Event instances.
     */
    @SafeVarargs
    public WheelOfFortune(long seed, Event<T>... events) {
        this(new Random(seed), events);
    }

    /**
     * Secondary constructor that uses a randomly-seeded Random.
     *
     * @param events a variable number of Event instances.
     */
    @SafeVarargs
    public WheelOfFortune(Event<T>... events) {
        this(new Random(), events);
    }

    /**
     * Method to get a randomly chosen value of T.
     * <p>
     * The result depends on the events and the next value of random.
     *
     * @return a T from one of the events, whose probability of being selected
     * is the quotient of the corresponding frequency and the value of total.
     */
    public T get() {
        int r = random.nextInt(total);
        // FIXME by replacing the following code
        // END 
        throw new RuntimeException("logic error: " + r);
    }

    // Private stuff...

    /**
     * Get the total of the frequencies of all the events.
     *
     * @return the total of all frequencies.
     */
    private int getTotal() {
        int total = 0;
        for (Event<T> event : events) total += event.frequency;
        return total;
    }

    private final Random random;
    private final Event<T>[] events;
    private final int total;

    // Class (static) stuff...
    static class Event<E> {
        final E event;
        final int frequency;

        Event(E event, int frequency) {
            this.event = event;
            this.frequency = frequency;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Event<?> event1 = (Event<?>) o;
            return frequency == event1.frequency &&
                    Objects.equals(event, event1.event);
        }

        @Override
        public int hashCode() {
            return Objects.hash(event, frequency);
        }
    }

    public static <E> Event<E> valueOf(E event, int frequency) {
        return new Event<>(event, frequency);
    }

}
