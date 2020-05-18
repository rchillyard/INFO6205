import unittest

from union_find.uf_hwqupc import UF_HWQUPC


class Test_UF_HWQUPC(unittest.TestCase):
    def test_to_str(self):
        h = UF_HWQUPC(2)
        self.assertEqual("UF_HWQUPC:\n" +
                         "  count: 2\n" +
                         "  path compression? True\n" +
                         "  parents: [0, 1]\n" +
                         "  heights: [1, 1]", str(h))

    def test_is_connected_01(self):
        h = UF_HWQUPC(2)
        self.assertFalse(h.is_connected(0, 1))

    def test_is_connected_02(self):
        h = UF_HWQUPC(1)
        self.assertRaises(ValueError, h.is_connected, 0, 1)

    def test_is_connected_03(self):
        h = UF_HWQUPC(2)
        self.assertIsNone(h.update_parent(0, 1))
        self.assertTrue(h.is_connected(0, 1))

    def test_is_connected_04(self):
        h = UF_HWQUPC(10)
        self.assertFalse(h.is_connected(0, 1))

    def test_connect_01(self):
        h = UF_HWQUPC(2)
        h.connect(0, 1)
        self.assertTrue(h.is_connected(0, 1))

    def test_connect_02(self):
        h = UF_HWQUPC(2)
        h.connect(0, 1)
        h.connect(0, 1)
        self.assertTrue(h.is_connected(0, 1))

    def test_find_0(self):
        h = UF_HWQUPC(1)
        self.assertEqual(0, h.find(0))

    def test_find_1(self):
        h = UF_HWQUPC(2)
        h.connect(0, 1)
        self.assertEqual(0, h.find(0))
        self.assertEqual(0, h.find(1))

    def test_find_2(self):
        h = UF_HWQUPC(3, False)
        h.connect(0, 1)
        self.assertEqual(0, h.find(0))
        self.assertEqual(0, h.find(1))
        h.connect(2, 1)
        self.assertEqual(0, h.find(0))
        self.assertEqual(0, h.find(1))
        self.assertEqual(0, h.find(2))

    def test_find_3(self):
        h = UF_HWQUPC(6, False)
        h.connect(0, 1)
        h.connect(0, 2)
        h.connect(3, 4)
        h.connect(3, 5)
        self.assertEqual(0, h.find(0))
        self.assertEqual(0, h.find(1))
        self.assertEqual(0, h.find(2))
        self.assertEqual(3, h.find(3))
        self.assertEqual(3, h.find(4))
        self.assertEqual(3, h.find(5))
        h.connect(0, 3)
        self.assertEqual(0, h.find(0))
        self.assertEqual(0, h.find(1))
        self.assertEqual(0, h.find(2))
        self.assertEqual(0, h.find(3))
        self.assertEqual(0, h.find(4))
        self.assertEqual(0, h.find(5))
        self.assertEqual(3, h.get_parent(4))
        self.assertEqual(3, h.get_parent(5))

    def test_find_4(self):
        h = UF_HWQUPC(6)
        h.connect(0, 1)
        h.connect(0, 2)
        h.connect(3, 4)
        h.connect(3, 5)
        self.assertEqual(0, h.find(0))
        self.assertEqual(0, h.find(1))
        self.assertEqual(0, h.find(2))
        self.assertEqual(3, h.find(3))
        self.assertEqual(3, h.find(4))
        self.assertEqual(3, h.find(5))
        h.connect(0, 3)
        self.assertEqual(0, h.find(0))
        self.assertEqual(0, h.find(1))
        self.assertEqual(0, h.find(2))
        self.assertEqual(0, h.find(3))
        self.assertEqual(0, h.find(4))
        self.assertEqual(0, h.find(5))
        self.assertEqual(0, h.get_parent(4))
        self.assertEqual(0, h.get_parent(5))

    def test_find_5(self):
        h = UF_HWQUPC(1)
        self.assertRaises(ValueError, h.find, 1)


if __name__ == "__main__":
    unittest.main()
