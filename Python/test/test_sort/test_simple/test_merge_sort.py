import unittest

from sort.simple.merge_sort_basic import MergeSortBasic


class TestMergeSort(unittest.TestCase):

    def test_sort(self):
        xs = [3, 4, 2, 1]
        sorter = MergeSortBasic()
        ys = sorter.sort(xs)
        self.assertSequenceEqual([1, 2, 3, 4], ys)


if __name__ == "__main__":
    unittest.main()
