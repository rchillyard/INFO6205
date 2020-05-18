from typing import List

from sort.simple.helper import Helper
from sort.simple.sort import Sort
from util.generic_type import C


class InsertionSort(Sort[C]):

    def __init__(self, helper: Helper[C] = None):
        if helper is None:
            helper = Helper[C]("InsertionSort")
        self.helper = helper

    def _sort(self, xs: List[C], _from: int, _to: int) -> None:
        for i in range(_from, _to):
            # // Invariant 1: elements xs[from..i] are in order
            # // TO BE IMPLEMENTED ...
            # // ... END IMPLEMENTATION
            

    def get_helper(self) -> Helper[C]:
        return self.helper
