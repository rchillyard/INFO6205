import unittest

from bqs.d_list import DList


class TestQueue(unittest.TestCase):

    def test_dlist(self):
        dlist = DList()
        self.assertTrue(dlist.is_empty())
        self.assertEqual(dlist.size(), 0)
        self.assertTrue(dlist.is_empty())

    def test_dlist2(self):
        dlist = DList()
        dlist.add_before_element(1, None)
        self.assertFalse(dlist.is_empty())
        self.assertEqual(dlist.size(), 1)

    def test_dlist3(self):
        dlist = DList()
        self.assertTrue(dlist.is_empty())

    def test_dlist4(self):
        dlist = DList()
        dlist.add_before_element(1, None)
        self.assertFalse(dlist.is_empty())
        first = dlist.find_first(1)
        self.assertEqual(first.item, 1)
        dlist.remove(first)
        self.assertEqual(dlist.size(), 0)

    def test_dlist5(self):
        dlist = DList()
        dlist.add_before_element(1, None)
        self.assertFalse(dlist.is_empty())
        first = dlist.find_first(1)
        self.assertEqual(first.item, 1)
        dlist.add_after_element(2, first)
        self.assertEqual(dlist.size(), 2)

    def test_dlist6(self):
        dlist = DList(1)
        self.assertFalse(dlist.is_empty())
        self.assertEqual(dlist.size(), 1)

    def test_dlist7(self):
        dlist = DList(1)
        dlist.remove_item(1)
        self.assertTrue(dlist.is_empty())


if __name__ == "__main__":
    unittest.main()
