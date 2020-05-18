import unittest

from bqs.queue_elements import QueueElements


class TestQueue(unittest.TestCase):

    def test_queue(self):
        queue = QueueElements()
        self.assertTrue(queue.is_empty())
        self.assertEqual(queue.dequeue(), None)
        self.assertTrue(queue.is_empty())

    def test_queue2(self):
        queue = QueueElements()
        self.assertTrue(queue.is_empty())
        queue.enqueue(1)
        self.assertFalse(queue.is_empty())
        self.assertEqual(queue.dequeue(), 1)
        self.assertTrue(queue.is_empty())

    def test_queue3(self):
        queue = QueueElements()
        self.assertTrue(queue.is_empty())
        queue.enqueue(1)
        queue.enqueue(2)
        self.assertFalse(queue.is_empty())
        self.assertEqual(queue.dequeue(), 1)
        self.assertEqual(queue.dequeue(), 2)
        self.assertTrue(queue.is_empty())

    def test_queue4(self):
        queue = QueueElements()
        self.assertTrue(queue.is_empty())
        queue.enqueue(1)
        queue.enqueue(2)
        self.assertFalse(queue.is_empty())
        self.assertEqual(queue.dequeue(), 1)
        queue.enqueue(3)
        queue.enqueue(4)
        self.assertEqual(queue.dequeue(), 2)
        self.assertEqual(queue.dequeue(), 3)
        self.assertEqual(queue.dequeue(), 4)
        self.assertTrue(queue.is_empty())


if __name__ == "__main__":
    unittest.main()
