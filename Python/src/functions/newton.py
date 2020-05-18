import math
from typing import Callable, Union, Tuple


class Newton:
    """
    This class models the Newton-Raphson Approximation algorithm.
    See https://en.wikipedia.org/wiki/Newton%27s_method

    It is an example of a non-deterministic algorithm inasmuch as the convergence (or lack thereof) is very dependent
    on the value of the initial guess x0 to the solve method.

    However, if you run it with identical starting conditions, it will always come out the same:
    it does not use any random elements.
    """
    def __init__(self, equation: str, f: Callable, dfbydx: Callable):
        """
        Constructor to create a problem to be solved by Newton's method.
        In particular, the problem is to find a root of the equation f(x) = 0.

        :param equation:    a String rendition of the function in the form f(x).
                            There is no need to add the "= 0" part of the equation.
        :param f:           the function f(x)
        :param dfbydx:      the first derivative of f(x) with respect to the variable x
        """
        self.equation = equation
        self.f = f
        self.dfbydx = dfbydx

    def solve(self, x0: float, max_tries: int, tolerance: float) -> Tuple[bool, Union[str, float]]:
        """
        Method to solve this Newton problem.

        :param x0:          the initial estimate of x.
                            If this is too far from any root, the solution may not converge.
        :param max_tries:   the maximum number of tries before admitting defeat due to non-convergence.
        :param tolerance:   the required precision for the value of f(x) to be considered equal to zero.
        :return:            a tuple of (bool, Union[str, float]), either (True, result) or (False, reason)
        """
        x = x0
        for tries in range(max_tries):
            try:
                y = self.f(x)
                if abs(y) < tolerance:
                    return True, x
                x = x - y / self.dfbydx(x)
            except Exception as e:
                return False, f"Exception thrown solving {self.equation}=0, given x0={x0}, max_tries={max_tries}, " \
                              f"and tolerance={tolerance} because {e}"
        return False, f"{self.equation}=0 did not converge given x0={x0}, max_tries={max_tries}, " \
                      f"and tolerance={tolerance}"


def main():
    newton = Newton("cos(x) - x", lambda x: math.cos(x) - x, lambda x: -math.sin(x) - 1)
    success, result = newton.solve(1.0, 200, 1e-7)
    if success:
        print(f"Good news! {newton.equation} was solved: {result}")
    else:
        print(result)


if __name__ == "__main__":
    main()
