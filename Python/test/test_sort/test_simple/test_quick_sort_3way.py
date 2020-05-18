import unittest

from sort.simple.quick_sort_3way import QuickSort3way


class TestQuickSort(unittest.TestCase):

    def test_sort(self):
        xs = [3, 4, 2, 1]
        sorter = QuickSort3way()
        ys = sorter.sort(xs)
        self.assertSequenceEqual([1, 2, 3, 4], ys)

    def test_partition(self):
        xs = list("PABXWPPVPDPCYZ")
        sorter = QuickSort3way()
        p = sorter.partition(xs, 0, len(xs) - 1)
        self.assertEqual(4, p.lt)
        self.assertEqual(8, p.gt)
        self.assertEqual("A", xs[0])
        self.assertEqual("X", xs[-1])


if __name__ == "__main__":
    unittest.main()
