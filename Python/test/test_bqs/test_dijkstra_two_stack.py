import unittest

from bqs.dijkstra_two_stack import DijkstraTwoStack
from bqs.bqs_exception import BqsException


class TestDijkstraTwoStack(unittest.TestCase):
    def testTwoStack(self):
        self.assertEqual(1, DijkstraTwoStack("1").evaluate())
        self.assertEqual(3, DijkstraTwoStack("1 + 2").evaluate())
        self.assertEqual(14, DijkstraTwoStack("2 * ( 4 + 3 )").evaluate())
        self.assertEqual(2, DijkstraTwoStack("2 * ( 4 - 3 )").evaluate())
        self.assertEqual(101, DijkstraTwoStack("1 + ( ( 2 + 3 ) * ( 4 * 5 ) )").evaluate())

    def testTwoStack2(self):
        self.assertRaises(BqsException, lambda: DijkstraTwoStack("").evaluate())

    def testTwoStack3(self):
        self.assertRaises(BqsException, lambda: DijkstraTwoStack("(").evaluate())

    def testTwoStackFail(self):
        self.assertRaises(ZeroDivisionError, lambda: DijkstraTwoStack("1 / 0").evaluate())


if __name__ == "__main__":
    unittest.main()
