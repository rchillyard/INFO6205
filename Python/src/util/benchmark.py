from random import Random
from typing import Callable, List, Generic, Union

import sys
import time

from sort.simple.insertion_sort import InsertionSort
from sort.simple.sort import Sort
from util.generic_type import C


class Benchmark(Generic[C]):

    def __init__(self, f_run: Callable[[C], None], f_pre: Callable[[C], C] = None, f_post: Callable[[C], None] = None):
        """
        Notice this Python version is different from the Java version.
        In the Java version, the order of parameters is fPre, fRun, fPost,
        while in the Python version, the order is f_run, f_pre, f_post.
        This is due to the fact that how Python handles polymorphism.

        :param f_run:       A Consumer function (i.e. a function of T => None).
                            Function f_run is the function whose timing you want to measure.
                            For example, you might create a function which sorts an array.
        :param f_pre:       A function of T => T.
                            Function f_pre is run before each invocation of fRun (but with the clock stopped).
                            The result of f_pre (if any) is passed to fRun.
        :param f_post:      A Consumer function (i.e. a function of T => None).
                            Function f_post is run after each invocation of f_run (but with the clock stopped).
        """
        self.f_run = f_run
        self.f_pre = f_pre
        self.f_post = f_post

    def run(self, t: Union[C, Callable[[], C]], _m: int):
        supplier: Callable[[], C]
        if not callable(t):
            def supplier():
                return t
        else:
            supplier = t

        warm_up_runs = max(10, _m // 10)
        for i in range(warm_up_runs):
            self.do_run(supplier(), True)

        total_time = 0
        for i in range(_m):
            total_time += self.do_run(supplier(), False)

        return total_time / _m / 1e6

    def do_run(self, t: C, warm_up: bool):

        # TO BE IMPLEMENTED: if fPre isn't None, then invoke it (using "apply") and memoize its result as "t1". Otherwise, assign "t" to "t1."

        # TO BE IMPLEMENTED: if warmup is true, simply invoke fRun with t1 (using "accept") and return 0.

        # TO BE IMPLEMENTED: start the timer, invoke fRun on t1 (using "accept"), stop the timer,
        # ... invoke fPost (if not-null) on t1 (using "accept").

        # TO BE IMPLEMENTED: return the number of nanoseconds elapsed.

        return nanos

    @staticmethod
    def benchmark_sort(_array: List[int], _name: str, _sorter: Sort[int], _m: int) -> None:
        helper = _sorter.get_helper()

        def pre_function(xs: List[int]):
            return helper.initialize(xs)

        def sort_function(xs: List[int]):
            _sorter.sort(xs, True)

        def cleanup_function(xs: List[int]):
            if not helper.cleanup(xs, sys.stdout):
                raise RuntimeError("Not sorted!")

        bm = Benchmark[List[int]](sort_function, pre_function, cleanup_function)
        x = bm.run(_array, _m)
        print(f"{_name}: {x} millisecs")


if __name__ == "__main__":
    random = Random()
    m = 50
    n = 1000
    for k in range(5):
        array = [random.randint(0, int(1e5)) for _ in range(n)]
        Benchmark.benchmark_sort(array, f"InsertionSort: {n}", InsertionSort(), m)
