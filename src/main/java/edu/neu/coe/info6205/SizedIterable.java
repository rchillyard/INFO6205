package edu.neu.coe.info6205;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public interface SizedIterable<T> extends Iterable<T> {

    /**
     * Method to yield the size of this iterable.
     *
     * @return the size.
     */
    int size();

    /**
     * Method to yield a List from this Iterable.
     * The result is always a copy of the original (typically of a different shape).
     * The order of the result is the same as the order of the iterator.
     *
     * @return a List&lt;T&gt; formed from the iterator of this Iterable.
     */
    default List<T> toList() {
        final Iterator<T> iterator = iterator();
        final List<T> result = new ArrayList<>();
        while (iterator.hasNext()) result.add(iterator.next());
        return result;
    }
}
