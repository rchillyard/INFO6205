from abc import abstractmethod, ABC
from typing import List, Generic

from sort.simple.helper import Helper
from util.generic_type import C


class Sort(Generic[C], ABC):

    def sort(self, xs: List[C], inplace: bool = False) -> List[C]:
        """
        Generic, non-mutating sort method which allows for inplace modification of the inplace option.

        :param xs:          The array to be sorted
        :param inplace:     if set to True, we make modification inplace.
        :return:            The sorted result, by default leaving xs unchanged.
        """
        self.get_helper().set_n(len(xs))
        result = xs if inplace else xs[:]
        self._sort(result, 0, len(result))
        return result

    @abstractmethod
    def _sort(self, xs: List[C], _from: int, _to: int) -> None:
        """
        Generic, mutating sort method which operates on a sub-array.

        :param xs:          Sort the array xs from "from" to "to".
        :param _from:       the index of the first element to sort
        :param _to:         the index of the first element not to sort
        """
        pass

    @abstractmethod
    def get_helper(self) -> Helper[C]:
        """
        Get the Helper associated with this Sort.

        :return:            the Helper
        """
        pass

    def __str__(self):
        return str(self.get_helper())
