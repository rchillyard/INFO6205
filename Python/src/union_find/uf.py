from abc import abstractmethod

from union_find.connections import Connections


class UF(Connections):

    @abstractmethod
    def components(self) -> int:
        """
        Returns the number of components.

        :return:    the number of components (between 1 and n)
        """
        pass

    @abstractmethod
    def find(self, p: int) -> int:
        """
        Returns the component identifier for the component containing site p.

        :param p:   the integer representing one site
        :return:    the component identifier for the component containing site p
        """
        pass

    @abstractmethod
    def union(self, p: int, q: int) -> None:
        """
        Merges the component containing site p with the
        the component containing site q.

        :param p:   the integer representing one site
        :param q:   the integer representing the other site
        """
        pass

    def is_connected(self, p: int, q: int) -> bool:
        """
        Returns True if the the two sites are in the same component.

        :param p:   the integer representing one site
        :param q:   the integer representing the other site
        :return:    True if the two sites p and q are in the same component;
                    False otherwise
        """
        return self.find(p) == self.find(q)

    @abstractmethod
    def size(self) -> int:
        """
        Returns the number of sites (objects) in this UF object.

        :return:    the number of sites.
        """
        pass
