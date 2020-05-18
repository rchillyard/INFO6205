from abc import abstractmethod, ABC
from typing import Generic

from util.generic_type import T


class Stack(ABC, Generic[T]):

    @abstractmethod
    def push(self, item):
        pass

    @abstractmethod
    def pop(self):
        pass

    @abstractmethod
    def peek(self):
        pass

    @abstractmethod
    def is_empty(self):
        pass
