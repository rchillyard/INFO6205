from random import Random
from typing import Generic, List, TextIO, Callable

from util.generic_type import C


class Helper(Generic[C]):
    """
    Helper class for sorting methods.
    """

    def __init__(self, description: str, n: int = 0, seed: int = None):
        self.description = description
        self.n = n
        self._random = Random(seed)

        self.compares = 0
        self.swaps = 0

    def initialize(self, xs: List[C]) -> List[C]:
        self.compares = 0
        self.swaps = 0
        return xs[:]

    def cleanup(self, xs: List[C], ps: TextIO = None) -> bool:
        if ps is not None:
            print(self, file=ps)
        _sorted = self.sorted(xs)
        if not _sorted:
            if ps is not None:
                print(xs, file=ps)
        return _sorted

    def less(self, v: C, w: C) -> bool:
        """
        Method to determine if one T value is less than another.

        :param v:       the candidate element.
        :param w:       the comparand element.
        :return:        True only if v is less than w.
        """
        self.compares += 1
        return v < w

    def compare(self, v: C, w: C) -> int:
        """
        Method to determine if one X value is less than another.

        :param v:       the candidate element.
        :param w:       the comparand element.
        :return:        result in int
        """
        self.compares += 1
        return (v > w) - (v < w)

    def swap(self, xs: List[C], lo: int, hi: int, i: int, j: int = None) -> None:
        """
        Swap the adjacent elements of array a at indices i-1 and i.
        This type of swap is guaranteed to be stable.

        :param xs:      the array.
        :param lo:      the lowest index of interest (only used for checking).
        :param hi:      one more than the highest index of interest (only used for checking).
        :param i:       the index of the lower element to swap.
                        If j is None, then i, j = i - 1, i.
        :param j:       the index of the higher element to swap.
        :return:
        """
        if j is None:
            i, j = i - 1, i

        self.swaps += 1
        if i < lo:
            raise ValueError(f"i is out of range: i={i}; lo={lo}")

        if j > hi:
            raise ValueError(f"j is out of range: j={j}; hi={hi}")

        xs[i], xs[j] = xs[j], xs[i]

    def move_up(self, xs: List[C], j) -> None:
        self.swaps += 1
        xs[j] = xs[j - 1]

    @staticmethod
    def sorted(xs: List[C]) -> bool:
        for i in range(1, len(xs)):
            if xs[i - 1] > xs[i]:
                return False
        return True

    def random(self, f: Callable[[Random], C], n: int = None) -> List[C]:
        if n is None:
            n = self.n
        self.set_n(n)
        result: List[C] = []
        for i in range(n):
            result.append(f(self._random))
        return result

    def __str__(self):
        return f"Helper for {self.description} with {self.n} elements: compares={self.compares}, swaps={self.swaps}"

    def set_n(self, n: int):
        if self.n == 0 or self.n == n:
            self.n = n
        else:
            raise ValueError("Helper: n is already set to a different value")
