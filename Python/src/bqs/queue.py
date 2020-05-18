from abc import abstractmethod, ABC
from typing import Generic

from util.generic_type import T


class Queue(ABC, Generic[T]):

    @abstractmethod
    def enqueue(self, item):
        pass

    @abstractmethod
    def dequeue(self):
        pass

    @abstractmethod
    def is_empty(self):
        pass
