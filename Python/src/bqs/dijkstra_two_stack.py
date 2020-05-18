from bqs.stack_linkedlist import StackLinkedList
from bqs.bqs_exception import BqsException


class DijkstraTwoStack:

    def __init__(self, infix):
        self.tokens = infix.split()
        self.opStack = StackLinkedList()
        self.valStack = StackLinkedList()
        self.parentheses = 0

    def process_token(self, s):
        if s == "(":
            self.parentheses += 1
        elif s == ")":
            self.parentheses -= 1
            self.operate()
        elif s in "+ - * / ^ %".split():
            self.opStack.push(s)
        else:
            n = int(s)
            self.valStack.push(n)

    def operate(self):
        y = self.valStack.pop()
        x = self.valStack.pop()

        if self.opStack.peek() == "+":
            self.valStack.push(x + y)
            self.opStack.pop()
            return
        elif self.opStack.peek() == "-":
            self.valStack.push(x - y)
            self.opStack.pop()
            return
        elif self.opStack.peek() == "*":
            self.valStack.push(x * y)
            self.opStack.pop()
            return
        elif self.opStack.peek() == "/":
            self.valStack.push(x / y)
            self.opStack.pop()
            return
        else:
            raise BqsException(f"operator not recognized: {self.opStack.peek()}")

    def evaluate(self):
        for i in self.tokens:
            self.process_token(i)

        if self.parentheses != 0:
            raise BqsException(f"there are {self.parentheses} superfluous parentheses (net)")

        while not self.opStack.is_empty():
            self.operate()

        result = self.valStack.pop()

        if not self.valStack.is_empty():
            raise BqsException("there are superfluous values")

        return result
