class BqsException(Exception):

    def __init__(self, msg):
        self.msg = msg

    def __str__(self):
        return f"Error {self.msg}"
