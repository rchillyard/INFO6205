import math
import unittest
from randomwalk.random_walk import RandomWalk


class TestRandomWalk(unittest.TestCase):

    def test_move0(self):
        rw = RandomWalk()
        rw.move(1, 0)
        self.assertAlmostEqual(1, rw.distance(), 7)

    def test_move1(self):
        rw = RandomWalk()

        rw.move(1, 0)
        self.assertAlmostEqual(1, rw.distance(), 7)

        rw.move(1, 0)
        self.assertAlmostEqual(2, rw.distance(), 7)

        rw.move(-1, 0)
        self.assertAlmostEqual(1, rw.distance(), 7)

        rw.move(-1, 0)
        self.assertAlmostEqual(0, rw.distance(), 7)

    def test_move2(self):
        rw = RandomWalk()

        rw.move(0, 1)
        self.assertAlmostEqual(1, rw.distance(), 7)

        rw.move(0, 1)
        self.assertAlmostEqual(2, rw.distance(), 7)

        rw.move(0, -1)
        self.assertAlmostEqual(1, rw.distance(), 7)

        rw.move(0, -1)
        self.assertAlmostEqual(0, rw.distance(), 7)

    def test_move3(self):
        rw = RandomWalk()
        root2 = math.sqrt(2)

        rw.move(1, 1)
        self.assertAlmostEqual(root2, rw.distance(), 7)

        rw.move(1, 1)
        self.assertAlmostEqual(2 * root2, rw.distance(), 7)

        rw.move(0, -2)
        self.assertAlmostEqual(2, rw.distance(), 7)

        rw.move(-2, 0)
        self.assertAlmostEqual(0, rw.distance(), 7)

    def test_random_walk(self):
        for i in range(5000):
            self.assertAlmostEqual(10, RandomWalk.random_walk_multi(100, 100), delta=4)

    def test_random_walk2(self):
        for i in range(5000):
            self.assertNotEqual(0, RandomWalk.random_walk_multi(1, 1))


if __name__ == "__main__":
    unittest.main()
