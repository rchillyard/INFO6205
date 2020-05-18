from abc import abstractmethod
from typing import TypeVar, Protocol, Any

C = TypeVar("C", bound="Comparable")

T = TypeVar("T")


class Comparable(Protocol):
    """
    As there is no Comparable interface in Python,
    here we define a help class to support this feature.

    Notice the whole type annotation system can be removed including this help class.
    We just want to make static type checking happy.
    """

    @abstractmethod
    def __eq__(self, other: Any) -> bool:
        pass

    @abstractmethod
    def __lt__(self: C, other: C) -> bool:
        pass

    def __gt__(self: C, other: C) -> bool:
        return (not self < other) and self != other

    def __le__(self: C, other: C) -> bool:
        return self < other or self == other

    def __ge__(self: C, other: C) -> bool:
        return not self < other
