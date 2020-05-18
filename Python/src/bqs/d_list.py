from bqs.bqs_exception import BqsException


class DList:
    """
    Implementation of a doubly-linked list
    """

    def __init__(self, item=None):
        """
        Constructor which creates an empty DList if no item is given or seeds the DList with one item

        :param item:    the first item
        """
        self.head = None
        self.tail = None
        self.count = 0
        if item is not None:
            self.add_before_element(item, None)

    def add_before(self, item, _next):
        """
        Add an item immediately before the given element

        :param item:    the item to be added.
        :param _next:   may be None, in which case the item will be the only item on the list
        """
        if _next is None:
            self.add_before_element(item, None)
        else:
            first = self.find_first(_next)
            if first is not None:
                self.add_before_element(item, first)
            else:
                raise BqsException("item not found: ")

    def add_after(self, item, prev):
        """
        Add an item immediately before the given element

        :param item:    the item to be added.
        :param prev:    may NOT be None
        """
        last = self.find_last(prev)
        if last is not None:
            self.add_after_element(item, last)
        else:
            raise BqsException("item not found: ")

    def remove_item(self, item):
        """
        Remove the first element matching item from this DList

        :param item:    the item to be removed.
        """
        last = self.find_last(item)
        if last is not None:
            self.remove(last)
        else:
            raise BqsException("item not found: ")

    def add_before_element(self, item, _next):
        """
        Add an item immediately before the given element

        :param item:    the item to be added.
        :param _next:   may be None, in which case the item will be the only item on the list
        """
        # TO BE IMPLEMENTED ...
        
        # END IMPLEMENTATION

    def add_after_element(self, item, prev):
        """
        Add an item immediately before the given element

        :param item:    the item to be added.
        :param prev:    may NOT be None
        """
        # TO BE IMPLEMENTED ...
        
        # ... END IMPLEMENTATION

    def remove(self, element):
        """
        Remove the element given from this DList

        :param element:     the element to be removed.
        """
        # TO BE IMPLEMENTED ...
        
        # ... END IMPLEMENTATION

    def find_first(self, item):
        # TO BE IMPLEMENTED ...
        
        # ... END IMPLEMENTATION

    def find_last(self, item):
        # TO BE IMPLEMENTED ...
        
        # ... END IMPLEMENTATION

    def is_empty(self):
        return self.head is None

    def size(self):
        return self.count


class D_Element:

    def __init__(self, x, p, n):
        self.item = x
        self.prev = p
        self.next = n
