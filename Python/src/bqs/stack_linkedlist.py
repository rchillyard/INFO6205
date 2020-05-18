from bqs.stack import Stack
from bqs.linkedlist_elements import LinkedListElements
from util.generic_type import T


class StackLinkedList(Stack[T]):

    def __init__(self):
        self.list = LinkedListElements()

    def push(self, item):
        self.list.add(item)

    def pop(self):
        return self.list.remove()

    def peek(self):
        return self.list.get_head()

    def is_empty(self) -> bool:
        return self.list.is_empty()

    def __print__(self):
        return self.list.__print__()
