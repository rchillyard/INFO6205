package edu.neu.coe.info6205.bqs;

/**
 * Interface to model the behavior of generic list-like object.
 * This interface does not specify where elements should be added or removed.
 *
 * @param <Item> the underlying type of this list.
 */
public interface ListLike<Item> {
    /**
     * Method to add an element to this list.
     *
     * @param item an item.
     */
    void add(Item item);

    /**
     * Method to remove an element from this list
     *
     * @return the item removed from the list.
     * @throws BQSException the list was empty or the item to remove was otherwise undefined.
     */
    Item remove() throws BQSException;

    /**
     * Method to determine if this list is empty.
     *
     * @return true if this list is empty.
     */
    boolean isEmpty();
}