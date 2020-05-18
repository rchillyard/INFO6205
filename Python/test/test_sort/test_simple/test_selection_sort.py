import unittest

from sort.simple.selection_sort import SelectionSort


class TestSelectionSort(unittest.TestCase):

    def test_sort(self):
        xs = [3, 4, 2, 1]
        sorter = SelectionSort()
        ys = sorter.sort(xs)
        self.assertSequenceEqual([1, 2, 3, 4], ys)


if __name__ == "__main__":
    unittest.main()
