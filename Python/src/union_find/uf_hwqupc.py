from typing import List

from union_find.uf import UF


class UF_HWQUPC(UF):

    def __init__(self, n: int, path_compression: bool = True):
        """
        Initializes an empty union–find data structure with n sites
        0 through n-1. Each site is initially in its own
        component.

        :param n:                   n the number of sites
        :param path_compression:    whether to use path compression
        """
        self.count = n
        self.parent: List[int] = []
        self.height: List[int] = []
        for i in range(n):
            self.parent.append(i)
            self.height.append(1)
        self.path_compression = path_compression

    def connect(self, p: int, q: int) -> None:
        """
        Ensure that site p is connected to site q

        :param p:                   the integer representing one site
        :param q:                   the integer representing the other site
        """
        if not self.is_connected(p, q):
            self.union(p, q)

    def show(self) -> None:
        for i in range(len(self.parent)):
            print(f"{i}: {self.parent[i]}, {self.height[i]}")

    def components(self) -> int:
        """
        Returns the number of components.

        :return:                    the number of components (between 1 and n)
        """
        return self.count

    def find(self, p: int) -> int:
        """
        Returns the component identifier for the component containing site p.

        :param p:                   the integer representing one site
        :return:                    the component identifier for the component containing site p
        """
        self.validate(p)
        root = p
        # TO BE IMPLEMENTED ...
        
        # ... END IMPLEMENTATION
        return root

    def connected(self, p: int, q: int) -> bool:
        """
        Returns True if the the two sites are in the same component.

        :param p:                   the integer representing one site
        :param q:                   the integer representing the other site
        :return:                    True if the two sites p and q are in the same component;
                                    False otherwise
        """
        return self.find(p) == self.find(q)

    def union(self, p: int, q: int) -> None:
        """
        Merges the component containing site p with the
        the component containing site q.

        :param p:                   the integer representing one site
        :param q:                   the integer representing the other site
        """
        # CONSIDER can we avoid doing find again?
        self.merge_components(self.find(p), self.find(q))
        self.count -= 1

    def size(self) -> int:
        return len(self.parent)

    def set_path_compression(self, path_compression: bool) -> None:
        """
        Used only by testing code

        :param path_compression:    True if you want path compression
        """
        self.path_compression = path_compression

    def __str__(self):
        return f"UF_HWQUPC:" \
               f"\n  count: {self.count}" \
               f"\n  path compression? {self.path_compression}" \
               f"\n  parents: {self.parent}" \
               f"\n  heights: {self.height}"

    def validate(self, p: int) -> None:
        n = len(self.parent)
        if not (0 <= p < n):
            raise ValueError(f"index {p} is not between 0 and {n - 1}")

    def update_parent(self, p: int, x: int) -> None:
        self.parent[p] = x

    def update_height(self, p: int, x: int) -> None:
        self.height[p] += self.height[x]

    def get_parent(self, i: int) -> int:
        """
        Used only by testing code

        :param i:                   the component
        :return:                    the parent of the component
        """
        return self.parent[i]

    def merge_components(self, i: int, j: int) -> None:
        # TO BE IMPLEMENTED make shorter root point to taller one
        
        # ... END IMPLEMENTATION

    def do_path_compression(self, i) -> None:
        """
        This implements the single-pass path-halving mechanism of path compression

        :param i:                   the component
        """
        # TO BE IMPLEMENTED update parent to value of grandparent
        
        # ... END IMPLEMENTATION
