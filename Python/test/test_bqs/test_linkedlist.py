import unittest

from bqs.linkedlist_elements import LinkedListElements


class TestBagArray(unittest.TestCase):

    def test_linkedlist1(self):
        _list = LinkedListElements()
        self.assertTrue(_list.is_empty())
        _list.add(1)
        self.assertEqual(1, _list.get_head())
        self.assertFalse(_list.is_empty())
        self.assertEqual(1, _list.remove())

    def test_linkedlist2(self):
        _list = LinkedListElements()
        self.assertTrue(_list.is_empty())
        _list.add(1)
        _list.add(2)
        _list.add(3)
        self.assertEqual(3, _list.get_head())
        self.assertEqual(3, _list.remove())
        self.assertEqual(2, _list.get_head())


if __name__ == "__main__":
    unittest.main()
