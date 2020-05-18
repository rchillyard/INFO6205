import unittest

from sort.simple.helper import Helper
from sort.simple.insertion_sort import InsertionSort


class TestInsertionSort(unittest.TestCase):

    def test_sort(self):
        xs = [3, 4, 2, 1]
        helper = Helper("InsertionSort", len(xs))
        sorter = InsertionSort(helper)
        ys = sorter.sort(xs)
        self.assertTrue(helper.sorted(ys))
        print(sorter)

    def test_sort_quiz6(self):
        self.assertEqual("epTy", self.sort_word("Type"))
        self.assertEqual("aABb", self.sort_word("BabA"))

    def sort_word(self, word: str) -> str:
        xs = list(word)
        helper = self.Quiz6Helper("InsertionSort", len(xs))
        sorter = InsertionSort[str](helper)
        ys = sorter.sort(xs)
        return "".join(ys)

    class Quiz6Helper(Helper[str]):

        def less(self, v: str, w: str) -> bool:
            if str.isalpha(v) and str.isalpha(w):
                return super().less(v.lower(), w.lower())
            return False


if __name__ == "__main__":
    unittest.main()
