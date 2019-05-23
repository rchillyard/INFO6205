package edu.neu.coe.info6205.lab_1;

import java.util.Objects;
import java.util.Random;

public class WheelOfFortune<T> {

    public WheelOfFortune(Random random, Event<T> ...events) {
        this.random = random;
        this.events = events;
        this.total = getTotal();
    }

    public WheelOfFortune(long seed, Event<T> ...events) {
        this(new Random(seed), events);
    }

    public WheelOfFortune(Event<T> ...events) {
        this(new Random(), events);
    }

    public T get() {
        // TO BE IMPLEMENTED ...
        int r = random.nextInt(total);
        for (Event<T> event : events) {
            if (r < event.frequency) return event.event;
            r -= event.frequency;
        }
        // ... END IMPLEMENTATION
        throw new RuntimeException("logic error: "+r);
    }

    // Private stuff...
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
