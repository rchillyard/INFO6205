import unittest

from bqs.bag_array import BagArray


class TestBagArray(unittest.TestCase):

    def test_bag_array(self):
        # initialize an object
        s = BagArray()
        self.assertEqual(s.is_empty(), True)

    def test_bag_array2(self):
        # initialize an object
        s = BagArray()
        s.add(1)
        self.assertEqual(s.is_empty(), False)
        self.assertEqual(s.size(), 1)

    def test_bag_array3(self):
        bag = BagArray()
        self.assertEqual(0, bag.size())
        self.assertTrue(bag.is_empty())
        bag.add(1)
        self.assertEqual(1, bag.size())
        self.assertFalse(bag.is_empty())

    def test_bag_array4(self):
        bag = BagArray()
        self.assertEqual(0, bag.size())
        self.assertTrue(bag.is_empty())
        for i in range(32):
            bag.add(i)
        bag.add(32)
        self.assertEqual(33, bag.size())
        self.assertFalse(bag.is_empty())


if __name__ == "__main__":
    unittest.main()
