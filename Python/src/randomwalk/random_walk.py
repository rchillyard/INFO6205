import argparse
import math
import sys
from random import Random


class RandomWalk:
    x = 0
    y = 0
    random = Random()

    def move(self, dx: int, dy: int) -> None:
        """
        Method to move the current position, that's to say the drunkard moves

        :param dx:  the distance he moves in the x direction
        :param dy:  the distance he moves in the y direction
        """
        # TO BE IMPLEMENTED ...
        
        # ... END IMPLEMENTATION

    def random_walk(self, m: int) -> None:
        """
        Perform a random walk of m steps

        :param m:   the number of steps the drunkard takes
        """
        # TO BE IMPLEMENTED ...
        
        # ... END IMPLEMENTATION

    def random_move(self) -> None:
        """
        Method to generate a random move according to the rules of the situation.

        That's to say, moves can be (+-1, 0) or (0, +-1).
        """
        ns: bool = bool(self.random.getrandbits(1))
        step: int = 1 if bool(self.random.getrandbits(1)) else -1
        self.move(step if ns else 0, 0 if ns else step)

    def distance(self) -> float:
        """
        Method to compute the distance from the origin (the lamp-post where the drunkard starts)
        to his current position.

        :return:    the (Euclidean) distance from the origin to the current position.
        """
        # TO BE IMPLEMENTED ...
        
        # ... END IMPLEMENTATION

    @staticmethod
    def random_walk_multi(m: int, n: int) -> float:
        """
        Perform multiple random walk experiments, returning the mean distance.

        :param m:   the number of steps for each experiment
        :param n:   the number of experiments to run
        :return:    the mean distance
        """
        total_distance: float = 0
        for i in range(n):
            walk = RandomWalk()
            walk.random_walk(m)
            total_distance = total_distance + walk.distance()
        return total_distance / n


if __name__ == "__main__":
    parser = argparse.ArgumentParser()
    parser.add_argument('m', nargs="?", type=int)
    parser.add_argument('n', nargs="?", type=int, default=30)

    args = parser.parse_args()
    _m = args.m
    if _m is None:
        parser.print_help(sys.stderr)
        sys.exit(1)
    _n = args.n
    mean_distance = RandomWalk.random_walk_multi(_m, _n)
    print(f"{_m} steps: {mean_distance} over {_n} experiments")
