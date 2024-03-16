package edu.neu.coe.info6205;

import java.util.Collection;
import java.util.Iterator;

public class SizedIterableImpl<T> implements SizedIterable<T> {
    public SizedIterableImpl(Collection<T> collection) {
        this.iterable = collection;
        size = collection.size();
    }

    public SizedIterableImpl(Iterable<T> iterable) {
        this.iterable = iterable;
        size = getSize(iterable);
    }

    public int size() {
        return size;
    }

    public Iterator<T> iterator() {
        return iterable.iterator();
    }

    public static <T> SizedIterable<T> create(Collection<T> collection) {
        return new SizedIterableImpl<>(collection);
    }

    public static <T> SizedIterable<T> create(Iterable<T> iterable) {
        return new SizedIterableImpl<>(iterable);
    }

    public static <T> int getSize(Iterator<T> iterator) {
        int size = 0;
        while (iterator.hasNext()) {
            size++;
            iterator.next();
        }
        return size;
    }

    private final Iterable<T> iterable;
    private final int size;

    private static <T> int getSize(Iterable<T> iterable) {
        int size = 0;
        for (T t : iterable) size++;
        return size;
    }
}