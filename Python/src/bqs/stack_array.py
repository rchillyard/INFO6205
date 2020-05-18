class Stack:

    # Construct an empty Stack object.
    def __init__(self):
        self._a = []  # Items

    # Return True if self is empty, and False otherwise.

    def is_empty(self):
        return len(self._a) == 0

    # Push object item onto the top of self.
    def push(self, item):
        self._a += [item]

    # Pop the top object from self and return it.
    def pop(self):
        return self._a.pop()
