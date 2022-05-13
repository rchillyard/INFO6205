#Author: INFO 6205 Program Structures and Algorithms
#Keywords Summary : Radix Sort for Integers
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios

Feature: Radix Sort for Integers
  Radix sort is an integer sorting algorithm that sorts data with integer keys by grouping
  the keys by individual digits that share the same significant position and value (place value).
  Radix sort uses counting sort as a subroutine to sort an array of numbers.
  Because radix sort is not comparison based, it is not bounded by \Omega(n \log n)Ω(nlogn) for running time — in fact,
  radix sort can perform in linear time.



  Scenario Outline: Positive Test: Given the integer array and sorting range, test should satisfy sorting invariance on integer array within range
    Given The "<scenario>" integer array "<Integer Array>"
    When radix sort is performed over the "<Range>" range from <Range Start> to <Range End>
    Then validate if the element of array within <Range Start> and <Range End> are sorted

    Examples:
      | scenario              | Integer Array                                           | Range Start | Range End | Range       |
      | sorted                |                                  0,1,2,3,4,5,6,7,8,9,10 |           0 |        10 | full        |
      | reverse sorted        |                                  10,9,8,7,6,5,4,3,2,1,0 |           0 |        10 | full        |
      | partially sorted      |                                  0,5,2,9,4,3,6,1,8,7,10 |           0 |        10 | full        |
      | sorted                |                                  0,1,2,3,4,5,6,7,8,9,10 |           2 |         7 | partial     |
      | reverse sorted        |                                  10,9,8,7,6,5,4,3,2,1,0 |           3 |         8 | partial     |
      | partially sorted      |                                  0,5,2,9,4,3,6,1,8,7,10 |           0 |         5 | partial     |
      | random                |                      0,11,95,52,78,65,65,87,51,87,87,50 |           0 |        11 | full        |
      | random                |                      0,11,95,52,78,65,65,87,51,87,87,50 |           1 |         9 | partial     |
      | same elements         |                                 95,95,95,95,95,95,95,95 |           0 |         7 | full        |
      | same elements         |                                 95,95,95,95,95,95,95,95 |           1 |         4 | partial     |
      | similar elements      |                           75,75,101,804,75,75,958,10000 |           0 |         7 | full        |
      | similar elements      |                           75,75,101,804,75,75,958,10000 |           1 |         6 | full        |
      | large value           |       100050,108080,99,1578654,984654650,855,4950,98445 |           0 |         7 | full        |
      | large value           |       100050,108080,99,1578654,984654650,855,4950,98445 |           1 |         5 | full        |
      | extremely large value | 0,2147483647,214748364,2147481647,1147483647,2047483647 |           0 |         5 | full        |
      | extremely large value | 0,2147483647,214748364,2147481647,1147483647,2047483647 |           1 |         4 | full        |
      | random                |                      0,11,95,52,78,65,65,87,51,87,87,50 |           0 |         1 | consecutive |
      | random                |                      0,11,95,52,78,65,65,87,51,87,87,50 |           4 |         5 | consecutive |
      | random                |                      0,11,95,52,78,65,65,87,51,87,87,50 |          10 |        11 | consecutive |
      | extremely large value | 0,2147483647,214748364,2147481647,1147483647,2047483647 |           3 |         4 | consecutive |
      | random                |                      0,11,95,52,78,65,65,87,51,87,87,50 |           0 |         0 | zero        |
      | random                |                      0,11,95,52,78,65,65,87,51,87,87,50 |           4 |         4 | zero        |
      | random                |                      0,11,95,52,78,65,65,87,51,87,87,50 |          11 |        11 | zero        |
      | random                |0                                                        |0            |0          |zero         |

  Scenario Outline: Negative Test: Given the integer array and out of bounds sorting range, test should generate ArrayIndexOutOfBounds exception
    Given The integer array "<Integer Array>"
    When radix sort is performed over the range from <Range Start> to <Range End> where "<Scenario>"
    Then validate if ArrayIndexOutOfBounds exception is raised

    Examples:
      | Integer Array                                         | Range Start | Range End | Scenario                                  |
      | 0,101,9045,52,78,65,65456,847,51,87,870,50,2147483647 |           0 |        13 | upper bound is violated                   |
      | 0,101,9045,52,78,65,65456,847,51,87,870,50,2147483647 |           4 |        13 | upper bound is violated                   |
      | 0,101,9045,52,78,65,65456,847,51,87,870,50,2147483647 |          12 |        13 | upper bound is violated                   |
      | 0,101,9045,52,78,65,65456,847,51,87,870,50,2147483647 |          14 |        16 | both bounds are violated beyond upper cap |
      | 0,101,9045,52,78,65,65456,847,51,87,870,50,2147483647 |          -1 |        11 | lower bound is violated                   |
      | 0,101,9045,52,78,65,65456,847,51,87,870,50,2147483647 |          -1 |         5 | lower bound is violated                   |
      | 0,101,9045,52,78,65,65456,847,51,87,870,50,2147483647 |          -1 |         0 | lower bound is violated                   |
      | 0,101,9045,52,78,65,65456,847,51,87,870,50,2147483647 |          -3 |        -1 | both bounds are violated below lower cap  |
      | 0,101,9045,52,78,65,65456,847,51,87,870,50,2147483647 |        -101 |       999 | both bounds are violated                  |


  Scenario Outline: Negative Test: Given the integer array and inapt bound in sorting range, test should generate Exception
    Given an integer array "<Integer Array>"
    When radix sort is performed over the range <Range Start> to <Range End> where "<Scenario>"
    Then validate if Exception is raised

    Examples:
      | Integer Array                                         | Range Start | Range End | Scenario                                                             |
      | 0,101,9045,52,78,65,65456,847,51,87,870,50,2147483647 |           3 |         0 | start index is higher than end index                                 |
      | 0,101,9045,52,78,65,65456,847,51,87,870,50,2147483647 |          12 |         0 | start index is higher than end index                                 |
      | 0,101,9045,52,78,65,65456,847,51,87,870,50,2147483647 |          15 |         0 | start index is beyond upper cap and higher than end index            |
      | 0,101,9045,52,78,65,65456,847,51,87,870,50,2147483647 |           3 |        -1 | start index is higher than out of bound end index                    |
      | 0,101,9045,52,78,65,65456,847,51,87,870,50,2147483647 |          15 |        -3 | start index is higher than end index and both index are out of bound |

  Scenario Outline: Positive Test: Given the integer array, sort elements based on certain radix.
    Given an int array "<Integer Array>"
    When counting sort is performed over the range <Range Start> to <Range End> at <Radix> radix
    Then validate if the element at of array within <Range Start> and <Range End> are sorted at <Radix> radix

    Examples:
      | Integer Array                                         | Range Start | Range End | Radix |
      | 0,101,9045,52,78,65,65456,847,51,87,870,50,2147483647 |           0 |        12 |     1 |
      | 0,101,9045,52,78,65,65456,847,51,87,870,50,2147483647 |           0 |        12 |    10 |
      | 0,101,9045,52,78,65,65456,847,51,87,870,50,2147483647 |           0 |        12 |   100 |
      | 0,101,9045,52,78,65,65456,847,51,87,870,50,2147483647 |           0 |        12 |  1000 |
      | 0,101,9045,52,78,65,65456,847,51,87,870,50,2147483647 |           0 |        12 | 10000 |
      |                                0,1,2,3,4,5,6,7,8,9,10 |           0 |        10 |  1000 |
      | 0,101,9045,52,78,65,65456,847,51,87,870,50,2147483647 |           3 |         8 |     1 |
      | 0,101,9045,52,78,65,65456,847,51,87,870,50,2147483647 |           3 |         8 |    10 |
      | 0,101,9045,52,78,65,65456,847,51,87,870,50,2147483647 |           3 |         8 |   100 |
      | 0,101,9045,52,78,65,65456,847,51,87,870,50,2147483647 |           3 |         8 |  1000 |
      | 0,101,9045,52,78,65,65456,847,51,87,870,50,2147483647 |           4 |         7 | 10000 |
      |                                0,1,2,3,4,5,6,7,8,9,10 |           2 |         6 |  1000 |
      |                                                     0 |           0 |         0 |     1 |
      |                                                     0 |           0 |         0 |    10 |

   Scenario Outline: Positive Test: Given the integer array find the max element within the range
     Given an Int array "<Integer Array>"
     Then maximum integer within <Range Start> and <Range End> should be <Output>

     Examples:
       | Integer Array                                         | Range Start | Range End | Output     |
       |                                0,1,2,3,4,5,6,7,8,9,10 |           0 |        10 |         10 |
       |                                0,1,2,3,4,5,6,7,8,9,10 |           3 |         7 |          7 |
       | 0,101,9045,52,78,65,65456,847,51,87,870,50,2147483647 |           0 |        12 | 2147483647 |
       | 0,101,9045,52,78,65,65456,847,51,87,870,50,2147483647 |           3 |         9 |      65456 |





