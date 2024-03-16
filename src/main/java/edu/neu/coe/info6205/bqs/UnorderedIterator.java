package edu.neu.coe.info6205.bqs;

import java.util.*;

/**
 * Class to implement an Iterator of T based on a Collection or Array of T.
 * The order of elements in the iterator is random.
 *
 * @param <T> the underlying type.
 */
public class UnorderedIterator<T> implements Iterator<T> {

    /**
     * Returns {@code true} if the iteration has more elements.
     * (In other words, returns {@code true} if {@link #next} would
     * return an element rather than throwing an exception.)
     *
     * @return {@code true} if the iteration has more elements
     */
    public boolean hasNext() {
        return !list.isEmpty();
    }

    /**
     * Returns the next element in the iteration.
     *
     * @return the next element in the iteration
     * @throws NoSuchElementException if the iteration has no more elements
     */
    public T next() {
        if (list.isEmpty()) throw new NoSuchElementException();
        int i = random.nextInt(list.size());
        try {
            return list.remove(i);
        } catch (UnsupportedOperationException e) {
            throw new RuntimeException("UnorderedIterator: cannot remove element " + i + " from list of type: " + list.getClass());
        }
    }

    /**
     * Secondary constructor which takes a collection.
     *
     * @param collection the collection of T over which to iterate.
     * @param random     an explicit random source.
     */
    public UnorderedIterator(Collection<T> collection, Random random) {
        this(copyCollection(collection), random);
    }

    /**
     * Secondary constructor which takes a collection.
     *
     * @param collection the collection of T over which to iterate.
     */
    public UnorderedIterator(Collection<T> collection) {
        this(collection, new Random());
    }

    /**
     * Secondary constructor which takes an array.
     *
     * @param array  an array of T.
     * @param random an explicit random source.
     */
    public UnorderedIterator(T[] array, Random random) {
        this(copyCollection(Arrays.asList(array)), random);
    }

    /**
     * Secondary constructor which takes an array.
     *
     * @param array an array of T.
     */
    public UnorderedIterator(T[] array) {
        this(array, new Random());
    }

    /**
     * Primary (private) constructor.
     *
     * @param list   a mutable list of T which will be mutated, ending as empty.
     * @param random a Random source.
     */
    private UnorderedIterator(List<T> list, Random random) {
        this.list = list;
        this.random = random;
    }

    public static <X> UnorderedIterator<X> createDeterministic(Collection<X> collection, Random random) {
        return new UnorderedIterator<>(copyCollection(collection), random);
    }

    public static <X> UnorderedIterator<X> createDeterministic(Collection<X> collection, long seed) {
        return createDeterministic(collection, new Random(seed));
    }

    private static <X> List<X> copyCollection(Collection<X> collection) {
        if (collection instanceof ArrayList) {
            ArrayList<X> result = new ArrayList<>(collection);
            Collections.copy(result, (ArrayList<X>) collection);
            return result;
        } else return new ArrayList<>(collection);
    }

    private final List<T> list;
    private final Random random;
}