from bqs.queue import Queue
from bqs.element import Element
from util.generic_type import T


class QueueElements(Queue[T]):

    def __init__(self):
        self.oldest = None
        self.newest = None

    def enqueue(self, item):
        # TO BE IMPLEMENTED
        
        # END IMPLEMENTATION

    def dequeue(self):
        if self.is_empty():
            return None
        else:
            # TO BE IMPLEMENTED ...
            
            # ... END IMPLEMENTATION

    def is_empty(self) -> bool:
        return self.oldest is None
