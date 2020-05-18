from dataclasses import dataclass
from typing import List

from sort.simple.helper import Helper
from sort.simple.sort import Sort
from util.generic_type import C


class QuickSort3way(Sort[C]):

    def __init__(self, helper: Helper[C] = None):
        if helper is None:
            helper = Helper[C]("3-way QuickSort")
        self.helper = helper

    @dataclass
    class Partition:
        lt: int
        gt: int

    def _sort(self, xs: List[C], _from: int, _to: int) -> None:
        lo = _from
        hi = _to - 1
        if hi <= lo:
            return
        partition = self.partition(xs, lo, hi)
        self._sort(xs, lo, partition.lt - 1)
        self._sort(xs, partition.gt + 1, hi)

    def partition(self, xs: List[C], lo: int, hi: int) -> Partition:
        lt = lo
        gt = hi
        if self.helper.less(xs[hi], xs[lo]):
            self.swap(xs, lo, hi)
        v = xs[lo]
        i = lo + 1
        while i <= gt:
            cmp = self.helper.compare(xs[i], v)
            if cmp < 0:
                self.helper.swap(xs, lo, hi, lt, i)
                lt += 1
                i += 1
            elif cmp > 0:
                self.helper.swap(xs, lo, hi, i, gt)
                gt -= 1
            else:
                i += 1
        return self.Partition(lt, gt)

    @staticmethod
    def swap(xs: List[C], i: int, j: int):
        """
        Used when we don't want to check or count swaps
        """
        xs[i], xs[j] = xs[j], xs[i]

    def get_helper(self) -> Helper[C]:
        return self.helper
