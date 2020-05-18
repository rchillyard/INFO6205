from abc import abstractmethod, ABC


class Connections(ABC):

    @abstractmethod
    def is_connected(self, p: int, q: int) -> bool:
        """
        Returns True if site p is connected to site q.

        :param p:       the integer representing one site
        :param q:       the integer representing the other site
        :return:        if there is a connection (direct or indirect) between p and q
        """
        pass

    @abstractmethod
    def connect(self, p: int, q: int) -> None:
        pass
