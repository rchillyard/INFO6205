#Author: INFO 6205 Program Structures and Algorithms
#Keywords Summary : LSD String Sort
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios

Feature: LSD String Sort
  LSD String sort is an string sorting algorithm that sorts string with character keys by grouping
  the keys by individual characters that share the same significant position and value (place value).
  LSD String sort uses similar to counting sort method as a subroutine to sort an array of characters.
  Since LSD String sort is similar to radix sort and is not comparison based, it is not bounded by
  \Omega(n \log n)Ω(nlogn) for running time — in fact, it can perform sorting in linear time.

Scenario Outline: Given the array of String, sort the strings based on their lexicographical value
  Given the "<Scenario>" array of String "<String Array>"
  When LSD Radix sort is performed within <Range Start> and <Range End>
  Then validate if the elements within <Range Start> and <Range End> statisfy sorting invariance

  Examples:
    | Scenario                         | String Array                                                                    | Range Start | Range End |
    | random equal length              | EIwFiIP,Uc6uBNo,pPTaBKK,mt47xaN,r7SRIz0,F8tSwit,49wHEkZ,mHhHoKr,52WT8yx,qcgaXDW |           0 |         9 |
    | random equal length              | EIwFiIP,Uc6uBNo,pPTaBKK,mt47xaN,r7SRIz0,F8tSwit,49wHEkZ,mHhHoKr,52WT8yx,qcgaXDW |           3 |         7 |
    | random equal length              | EIwFiIP,Uc6uBNo,pPTaBKK,mt47xaN,r7SRIz0,F8tSwit,49wHEkZ,mHhHoKr,52WT8yx,qcgaXDW |           3 |         4 |
    | random equal length              | EIwFiIP,Uc6uBNo,pPTaBKK,mt47xaN,r7SRIz0,F8tSwit,49wHEkZ,mHhHoKr,52WT8yx,qcgaXDW |           3 |         3 |
    | sorted equal length              | 49wHEkZ,52WT8yx,EIwFiIP,F8tSwit,Uc6uBNo,mHhHoKr,mt47xaN,pPTaBKK,qcgaXDW,r7SRIz0 |           0 |         9 |
    | sorted equal length              | 49wHEkZ,52WT8yx,EIwFiIP,F8tSwit,Uc6uBNo,mHhHoKr,mt47xaN,pPTaBKK,qcgaXDW,r7SRIz0 |           3 |         7 |
    | sorted equal length              | 49wHEkZ,52WT8yx,EIwFiIP,F8tSwit,Uc6uBNo,mHhHoKr,mt47xaN,pPTaBKK,qcgaXDW,r7SRIz0 |           3 |         4 |
    | sorted equal length              | 49wHEkZ,52WT8yx,EIwFiIP,F8tSwit,Uc6uBNo,mHhHoKr,mt47xaN,pPTaBKK,qcgaXDW,r7SRIz0 |           3 |         3 |
    | reverse sorted equal length      | r7SRIz0,qcgaXDW,pPTaBKK,mt47xaN,mHhHoKr,Uc6uBNo,F8tSwit,EIwFiIP,52WT8yx,49wHEkZ |           0 |         9 |
    | reverse sorted equal length      | r7SRIz0,qcgaXDW,pPTaBKK,mt47xaN,mHhHoKr,Uc6uBNo,F8tSwit,EIwFiIP,52WT8yx,49wHEkZ |           3 |         7 |
    | reverse sorted equal length      | r7SRIz0,qcgaXDW,pPTaBKK,mt47xaN,mHhHoKr,Uc6uBNo,F8tSwit,EIwFiIP,52WT8yx,49wHEkZ |           3 |         4 |
    | reverse sorted equal length      | r7SRIz0,qcgaXDW,pPTaBKK,mt47xaN,mHhHoKr,Uc6uBNo,F8tSwit,EIwFiIP,52WT8yx,49wHEkZ |           3 |         3 |
    | random unequal length            | EIw,Uc6uBNo,pK,mt47x,r7SRIz,F8tSwit,49wH,mHKr,52WT8ysdfsdx,qcgaX23423DW         |           0 |         9 |
    | random unequal length            | EIw,Uc6uBNo,pK,mt47x,r7SRIz,F8tSwit,49wH,mHKr,52WT8ysdfsdx,qcgaX23423DW         |           3 |         7 |
    | random unequal length            | EIw,Uc6uBNo,pK,mt47x,r7SRIz,F8tSwit,49wH,mHKr,52WT8ysdfsdx,qcgaX23423DW         |           3 |         4 |
    | random unequal length            | EIw,Uc6uBNo,pK,mt47x,r7SRIz,F8tSwit,49wH,mHKr,52WT8ysdfsdx,qcgaX23423DW         |           6 |         6 |
    | sorted unequal length            | 49wH,52WT8ysdfsdx,EIw,F8tSwit,Uc6uBNo,mHKr,mt47x,pK,qcgaX23423DW,r7SRIz         |           0 |         9 |
    | sorted unequal length            | 49wH,52WT8ysdfsdx,EIw,F8tSwit,Uc6uBNo,mHKr,mt47x,pK,qcgaX23423DW,r7SRIz         |           3 |         7 |
    | sorted unequal length            | 49wH,52WT8ysdfsdx,EIw,F8tSwit,Uc6uBNo,mHKr,mt47x,pK,qcgaX23423DW,r7SRIz         |           3 |         4 |
    | sorted unequal length            | 49wH,52WT8ysdfsdx,EIw,F8tSwit,Uc6uBNo,mHKr,mt47x,pK,qcgaX23423DW,r7SRIz         |           3 |         3 |
    | reverse sorted unequal length    | r7SRIz,qcgaX23423DW,pK,mt47x,mHKr,Uc6uBNo,F8tSwit,EIw,52WT8ysdfsdx,49wH         |           0 |         9 |
    | reverse sorted unequal length    | r7SRIz,qcgaX23423DW,pK,mt47x,mHKr,Uc6uBNo,F8tSwit,EIw,52WT8ysdfsdx,49wH         |           3 |         7 |
    | reverse sorted unequal length    | r7SRIz,qcgaX23423DW,pK,mt47x,mHKr,Uc6uBNo,F8tSwit,EIw,52WT8ysdfsdx,49wH         |           3 |         4 |
    | reverse sorted unequal length    | r7SRIz,qcgaX23423DW,pK,mt47x,mHKr,Uc6uBNo,F8tSwit,EIw,52WT8ysdfsdx,49wH         |           3 |         3 |
    | special character random         | @#Abc,GKSsd,?sdfs,/sdfsd,^&%,IUYsdf123,%234<,786sdfskj^,234*&^8,~wer3           |           0 |         9 |
    | special character random         | @#Abc,GKSsd,?sdfs,/sdfsd,^&%,IUYsdf123,%234<,786sdfskj^,234*&^8,~wer3           |           3 |         7 |
    | special character random         | @#Abc,GKSsd,?sdfs,/sdfsd,^&%,IUYsdf123,%234<,786sdfskj^,234*&^8,~wer3           |           3 |         4 |
    | special character random         | @#Abc,GKSsd,?sdfs,/sdfsd,^&%,IUYsdf123,%234<,786sdfskj^,234*&^8,~wer3           |           3 |         3 |
    | special character sorted         | %234<,/sdfsd,234*&^8,786sdfskj^,?sdfs,@#Abc,GKSsd,IUYsdf123,^&%,~wer3           |           0 |         9 |
    | special character sorted         | %234<,/sdfsd,234*&^8,786sdfskj^,?sdfs,@#Abc,GKSsd,IUYsdf123,^&%,~wer3           |           3 |         7 |
    | special character sorted         | %234<,/sdfsd,234*&^8,786sdfskj^,?sdfs,@#Abc,GKSsd,IUYsdf123,^&%,~wer3           |           3 |         4 |
    | special character sorted         | %234<,/sdfsd,234*&^8,786sdfskj^,?sdfs,@#Abc,GKSsd,IUYsdf123,^&%,~wer3           |           3 |         3 |
    | special character reverse sorted | ~wer3,^&%,IUYsdf123,GKSsd,@#Abc,?sdfs,786sdfskj^,234*&^8,/sdfsd,%234<           |           0 |         9 |
    | special character reverse sorted | ~wer3,^&%,IUYsdf123,GKSsd,@#Abc,?sdfs,786sdfskj^,234*&^8,/sdfsd,%234<           |           3 |         7 |
    | special character reverse sorted | ~wer3,^&%,IUYsdf123,GKSsd,@#Abc,?sdfs,786sdfskj^,234*&^8,/sdfsd,%234<           |           3 |         4 |
    | special character reverse sorted | ~wer3,^&%,IUYsdf123,GKSsd,@#Abc,?sdfs,786sdfskj^,234*&^8,/sdfsd,%234<           |           3 |         3 |
    | numerical random length          |             102,1005,508,456,980656,9851651981650,9875165,65,008,6549161,123655 |           0 |        10 |
    | numerical random length          |             102,1005,508,456,980656,9851651981650,9875165,65,008,6549161,123655 |           2 |         8 |
    | numerical random length          |             102,1005,508,456,980656,9851651981650,9875165,65,008,6549161,123655 |           3 |         4 |
    | numerical random length          |             102,1005,508,456,980656,9851651981650,9875165,65,008,6549161,123655 |           5 |         5 |
    | numerical same length            |                                 102,105,103,108,101,100,104,106,109,107,111,110 |           0 |        11 |
    | numerical same length            |                                 102,105,103,108,101,100,104,106,109,107,111,110 |           3 |         7 |
    | numerical same length            |                                 102,105,103,108,101,100,104,106,109,107,111,110 |           7 |         8 |
    | numerical same length            |                                 102,105,103,108,101,100,104,106,109,107,111,110 |           3 |         3 |
    | uppercase character              | A,G,M,B,H,N,C,I,O,D,J,P,E,K,Q                                                   |           0 |        14 |
    | uppercase character              | A,G,M,B,H,N,C,I,O,D,J,P,E,K,Q                                                   |           7 |        14 |
    | uppercase character              | A,G,M,B,H,N,C,I,O,D,J,P,E,K,Q                                                   |           3 |         4 |
    | uppercase character              | A,G,M,B,H,N,C,I,O,D,J,P,E,K,Q                                                   |           8 |         8 |
    | lowercase character              | a,g,m,b,h,n,c,i,o,d,j,p,e,k,q                                                   |           0 |        14 |
    | lowercase character              | a,g,m,b,h,n,c,i,o,d,j,p,e,k,q                                                   |           7 |        14 |
    | lowercase character              | a,g,m,b,h,n,c,i,o,d,j,p,e,k,q                                                   |           3 |         4 |
    | lowercase character              | a,g,m,b,h,n,c,i,o,d,j,p,e,k,q                                                   |           0 |         0 |
    | character                        | A,g,M,b,H,N,C,i,O,d,J,p,E,k,Q                                                   |           0 |        14 |
    | character                        | A,g,M,b,H,N,C,i,O,d,J,p,E,k,Q                                                   |           7 |        14 |
    | character                        | A,g,M,b,H,N,C,i,O,d,J,p,E,k,Q                                                   |           3 |         4 |
    | character                        | A,g,M,b,H,N,C,i,O,d,J,p,E,k,Q                                                   |           0 |         0 |

