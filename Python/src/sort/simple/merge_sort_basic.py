from typing import List

from sort.simple.helper import Helper
from sort.simple.sort import Sort
from util.generic_type import C


class MergeSortBasic(Sort[C]):

    def __init__(self, helper: Helper[C] = None):
        if helper is None:
            helper = Helper[C]("MergeSort")
        self.helper = helper

        self.aux: List[C] = []

    def sort(self, xs: List[C], inplace: bool = False) -> List[C]:
        self.get_helper().set_n(len(xs))
        result = xs if inplace else xs[:]
        self.aux = result[:]
        self._sort(result, 0, len(result))
        return result

    def _sort(self, xs: List[C], _from: int, _to: int) -> None:
        lo = _from
        hi = _to
        if hi <= lo + 1:
            return
        mid = _from + (_to - _from) // 2
        self._sort(xs, lo, mid)
        self._sort(xs, mid, hi)
        for i in range(_from, _to):
            self.aux[i] = xs[i]
        self.merge(self.aux, xs, lo, mid, hi)

    def merge(self, aux: List[C], xs: List[C], lo: int, mid: int, hi: int) -> None:
        i = lo
        j = mid
        for k in range(lo, hi):
            if i >= mid:
                xs[k] = aux[j]
                j += 1
            elif j >= hi:
                xs[k] = aux[i]
                i += 1
            elif self.helper.less(aux[j], aux[i]):
                xs[k] = aux[j]
                j += 1
            else:
                xs[k] = aux[i]
                i += 1

    def get_helper(self) -> Helper[C]:
        return self.helper
