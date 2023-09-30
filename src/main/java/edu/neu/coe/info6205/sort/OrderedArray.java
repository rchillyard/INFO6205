package edu.neu.coe.info6205.sort;

import java.util.*;

/**
 * Abstract Data Type for an ordered array.
 * Regardless of the elements that are passed to the constructor, or to the addElements method,
 * this ordered array will always be in order.
 * <p>
 * There are some peculiarities of Java which prevent us from declaring the internal array as a K[].
 *
 * @param <K> the underlying type of the ordered array.
 */
public class OrderedArray<K extends Comparable<K>> implements Iterable<K> {

    /**
     * Class method to create a new OrderedArray from a varargs list of T elements.
     *
     * @param args a comma-separated list of Ts.
     * @param <T>  the underlying type of the args and the result.
     * @return an OrderedArray of T.
     */
    @SafeVarargs
    public static <T extends Comparable<T>> OrderedArray<T> from(T... args) {
        return new OrderedArray<>(args, false, getComparator());
    }

    /**
     * Method to get the ith element in order.
     *
     * @param i the index of the desired element (from 0 through length-1).
     * @return the element as a K.
     */
    public K get(int i) {
        //noinspection unchecked
        return (K) array[i];
    }

    /**
     * Method to add additional elements to this OrderedArray.
     *
     * @param addition an array of additional elements.
     */
    public void addElements(K[] addition) {
        doAddElements(addition);
    }

    /**
     * Method to add additional elements to this OrderedArray.
     *
     * @param addition a collection of additional elements.
     */
    public void addElements(Collection<K> addition) {
        doAddElements(addition.toArray());
    }

    /**
     * Returns an iterator over elements of type {@code K} (defined by Iterable).
     * Since there is no Java iterator of an array, this implementation adds elements of array individually to the (temporary) list.
     * This is necessary because Java doesn't allow casting of generic arrays.
     *
     * @return an Iterator.
     */
    public Iterator<K> iterator() {
        List<K> list = new ArrayList<>();
        for (Object k : array) //noinspection unchecked
            list.add((K) k);
        return list.iterator();
    }

    /**
     * Method to get the index of a K element.
     * Since the array is always ordered, we can use binary search.
     *
     * @param k the element to find.
     * @return the index of the element (if found), otherwise -1.
     */
    public int indexOf(K k) {
        return Arrays.binarySearch(array, k);
    }

    /**
     * Method to get the size of this OrderedArray.
     *
     * @return the value of size.
     */
    public int getSize() {
        return size;
    }

    /**
     * Primary constructor.
     *
     * @param array      the input array.
     * @param makeCopy   true if the constructor should make a copy of the input array rather than simply referencing the input array.
     * @param comparator the comparator for ordering the array of Ks--by default, use getComparator().
     */
    public OrderedArray(K[] array, boolean makeCopy, Comparator<Object> comparator) {
        this.comparator = comparator;
        int length = array.length;
        if (makeCopy) {
            this.array = new Object[length];
            System.arraycopy(array, 0, this.array, 0, length);
        } else
            this.array = array;
        update(length);
    }

    /**
     * Secondary constructor where only parameter array is explicit.
     * makeCopy is set to true; comparator is set to OrderedArray.getComparator().
     *
     * @param array the original array, which will NOT be affected by the sort.
     */
    public OrderedArray(K[] array) {
        this(array, true, OrderedArray.getComparator());
    }

    /**
     * Secondary constructor where only parameter array and makeCopy are explicit.
     * comparator is set to OrderedArray.getComparator().
     *
     * @param makeCopy true if the constructor should make a copy of the input array rather than simply referencing the input array.
     * @param array    the original array, which will NOT be affected by the sort.
     */
    public OrderedArray(K[] array, boolean makeCopy) {
        this(array, makeCopy, OrderedArray.getComparator());
    }

    /**
     * Secondary constructor where only parameter input is explicit.
     * comparator is set to OrderedArray.getComparator() for the generic type K.
     * The input collection will never be affected by the ordering.
     *
     * @param input a Collection of K elements.
     */
    public OrderedArray(Collection<K> input) {
        comparator = OrderedArray.getComparator();
        array = input.toArray();
        update(array.length);
    }

    /**
     * Package-scope method to get the underlying array for this OrderedArray
     * <p>
     * Internally, Object[] can be cast as an Item[] but it is not valid externally.
     * Hence, the arraycopy.
     * This is a quirk of Java Generics.
     *
     * @return the array as a K[].
     */
    K[] getArray() {
        @SuppressWarnings({"unchecked", "ConstantConditions"}) K[] result = (K[]) new Object[size];
        //noinspection SuspiciousSystemArraycopy
        System.arraycopy(this.array, 0, result, 0, size);
        return result;
    }

    private void doAddElements(Object[] addition) {
        Object[] combined = new Object[size + addition.length];
        System.arraycopy(array, 0, combined, 0, array.length);
        System.arraycopy(addition, 0, combined, size, addition.length);
        array = combined;
        update(combined.length); // Could use insertion sort, assuming that addition.length is small compared with array.length
    }

    private void update(int length) {
        this.size = length;
        Arrays.sort(array, comparator);
    }

    private static <T extends Comparable<T>> Comparator<Object> getComparator() {
        //noinspection unchecked
        return Comparator.comparing(o -> ((T) o));
    }

    private final Comparator<Object> comparator;

    private Object[] array;

    private int size;
}