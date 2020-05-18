from typing import List

from sort.simple.helper import Helper
from sort.simple.sort import Sort
from util.generic_type import C


class SelectionSort(Sort[C]):

    def __init__(self, helper: Helper[C] = None):
        if helper is None:
            helper = Helper[C]("SelectionSort")
        self.helper = helper

    def _sort(self, xs: List[C], _from: int, _to: int) -> None:
        
        # TO BE IMPLEMENTED ...
        
        for i in range(_from, _to):
            # Invariant 1: xs[from..i] are in (final) ascending order
            # Invariant 2: no element xs[i+1..to-1] is smaller than any element xs[from..i]

        # ... END IMPLEMENTATION
           

    def get_helper(self) -> Helper[C]:
        return self.helper
