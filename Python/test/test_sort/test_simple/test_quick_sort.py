import unittest

from sort.simple.quick_sort import QuickSort


class TestQuicksort(unittest.TestCase):

    def test_sort1(self):
        # initialize an object
        ys = QuickSort([3, 4, 2, 1])
        # sort the list
        ys.sort()
        # get the list after sorting
        ys1 = ys.get_array()
        # testing
        self.assertEqual(1, ys1[0])
        self.assertEqual(2, ys1[1])
        self.assertEqual(3, ys1[2])
        self.assertEqual(4, ys1[3])

    def test_sort2(self):
        # initialize an object
        ys = QuickSort([3, 4, 5, 6, 1, 7, 2])
        # sort the list
        ys.sort()
        # get the list after sorting
        ys1 = ys.get_array()
        # testing
        self.assertEqual(1, ys1[0])
        self.assertEqual(2, ys1[1])
        self.assertEqual(3, ys1[2])
        self.assertEqual(4, ys1[3])


if __name__ == "__main__":
    unittest.main()
