from bqs.bag import Bag
from util.generic_type import T


class BagArray(Bag[T]):

    def __init__(self):
        self.items = []

    def add(self, item):
        self.items.append(item)

    def is_empty(self) -> bool:
        return len(self.items) == 0

    def size(self) -> int:
        return len(self.items)

    def contains(self, item) -> bool:
        return item in self.items

    def multiplicity(self, item) -> int:
        result = 0
        if self.is_empty():
            return 0
        for i in self.items:
            if i is not None and i == item:
                result += 1
        return result
