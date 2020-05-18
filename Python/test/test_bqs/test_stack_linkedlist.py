import unittest

from bqs.stack_linkedlist import StackLinkedList


class TestStackLinkedlist(unittest.TestCase):

    def test_stack(self):
        # initialize an object
        s = StackLinkedList()
        self.assertEqual(s.is_empty(), True)

    def test_stack2(self):
        # initialize an object
        s = StackLinkedList()
        s.push(1)
        self.assertEqual(s.is_empty(), False)

    def test_stack3(self):
        stack = StackLinkedList()
        self.assertTrue(stack.is_empty())
        stack.push(1)
        self.assertFalse(stack.is_empty())
        item = stack.pop()
        self.assertEqual(item, 1)


if __name__ == "__main__":
    unittest.main()
