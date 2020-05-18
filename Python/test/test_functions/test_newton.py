import math
import unittest

from functions.newton import Newton


class TestRandomWalk(unittest.TestCase):

    def test_newton1(self):
        # Build the Newton's Approximation problem to be solved: cos(x) = x
        newton = Newton("cos(x) - x", lambda x: math.cos(x) - x, lambda x: -math.sin(x) - 1)

        # Solve the problem starting with a value of x = 1;
        # requiring a precision of 10^-7;
        # and giving up after 200 tries.
        success, result = newton.solve(1, 200, 1e-7)

        self.assertTrue(success, "solved")
        self.assertAlmostEqual(0.73908513338528, result, 7)

    def test_newton2(self):
        # Build the Newton's Approximation problem to be solved: cos(x) = x
        newton = Newton("cos(x) - x", lambda x: math.cos(x) - x, lambda x: -math.sin(x) - 1)

        # Demonstrate that we cannot solve the problem starting with a value of x = -1;
        # requiring a precision of 10^-7;
        # and giving up after 2 tries.
        success, result = newton.solve(-1, 2, 1e-7)

        self.assertFalse(success, "solved")
        self.assertEqual("cos(x) - x=0 did not converge given x0=-1, max_tries=2, and tolerance=1e-07", result)

    def test_newton3(self):
        # Build the Newton's Approximation problem to be solved: cos(x) = x
        newton = Newton("cos(x) - x", lambda x: math.cos(x) - x, lambda x: -math.sin(x) - 1)

        # Demonstrate that we cannot solve the problem starting with a value of x = -1;
        # requiring a precision of 10^-7;
        # and giving up after 100 tries.
        success, result = newton.solve(-math.pi / 2, 100, 1e-7)

        self.assertFalse(success, "solved")
        self.assertEqual("Exception thrown solving cos(x) - x=0, given x0=-1.5707963267948966, max_tries=100, "
                         "and tolerance=1e-07 because float division by zero", result)
