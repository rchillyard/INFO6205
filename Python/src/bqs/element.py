from typing import Generic

from util.generic_type import T


class Element(Generic[T]):

    def __init__(self, item, _next=None):
        self.item = item
        self.next = _next

    # Return a string representation self.
    def __str__(self):
        return f"{self.item}{' (last)' if self.next is None else ''}"
