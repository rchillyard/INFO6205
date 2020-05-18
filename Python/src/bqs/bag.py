from abc import abstractmethod, ABC
from typing import Generic

from util.generic_type import T


class Bag(ABC, Generic[T]):

    @abstractmethod
    def add(self, item):
        pass

    @abstractmethod
    def is_empty(self) -> bool:
        pass

    @abstractmethod
    def contains(self, item) -> bool:
        pass

    @abstractmethod
    def multiplicity(self, item) -> int:
        pass
