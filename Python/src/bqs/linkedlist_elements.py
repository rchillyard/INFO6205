from bqs.element import Element
from bqs.bqs_exception import BqsException
from bqs.linkedlist import LinkedList
from util.generic_type import T


class LinkedListElements(LinkedList[T]):

    def __init__(self, head=None):
        self.head = head

    def add(self, item):
        tail = self.head
        self.head = Element(item, tail)

    def remove(self):
        if self.head is None:
            raise BqsException("collection is empty")
        result = self.head.item
        self.head = self.head.next
        return result

    def get_head(self):
        return None if self.is_empty() else self.head.item

    def is_empty(self):
        return self.head is None

    def __printlist__(self):
        temp = self.head
        while temp:
            print(temp.item)
            temp = temp.next
