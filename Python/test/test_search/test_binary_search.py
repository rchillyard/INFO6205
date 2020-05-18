import unittest
from search.binary_search import BinarySearch


class TestSearch(unittest.TestCase):
    def test_sequence(self):
        ar = [1, 2, 3, 4, 5, 6, 7, 8, 9]
        self.assertEqual(2, BinarySearch.binary_search(ar, 0, len(ar) - 1, 3))

    def test_missing_too_large(self):
        ar = [1, 2, 3, 4, 5, 6, 7, 8, 9]
        self.assertEqual(-1, BinarySearch.binary_search(ar, 0, len(ar) - 1, 11))

    def test_singleton_array(self):
        ar = [1]
        self.assertEqual(0, BinarySearch.binary_search(ar, 0, len(ar) - 1, 1))

    def test_empty_array(self):
        ar = []
        self.assertEqual(-1, BinarySearch.binary_search(ar, 0, len(ar) - 1, 1))

    def testSequenceOutOfOrder(self):
        ar = [9, 8, 7, 6, 5, 4, 3, 2, 1]
        self.assertEqual(-1, BinarySearch.binary_search(ar, 0, len(ar) - 1, 3))
