from abc import abstractmethod, ABC
from typing import Generic

from util.generic_type import T


class LinkedList(ABC, Generic[T]):

    @abstractmethod
    def add(self, item):
        pass

    @abstractmethod
    def remove(self):
        pass

    @abstractmethod
    def get_head(self):
        pass

    @abstractmethod
    def is_empty(self) -> bool:
        pass
