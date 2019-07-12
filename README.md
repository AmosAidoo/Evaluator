# Evaluator
## Evaluate your functions with this simple Evaluator implementaion

The purpose of this is to help one perform simple computations such as evaluating a function, differenting a function and integrating a function. Instead of explicitly defining the function, you can simply type the function as a string and evaluate it.

### Example:
I can create a program that uses bisection method to find the root of the function `f(x) = 4*cos(x) - 10`. Instead of embedding the function in the code, the user can simply type the string, "4*cos(x) - 10" as input and get the desired output.

### How to use
1. Import the Evaluator implementation into your project.
2. Call `Evaluator.eval("f(x)", x)` to evaluate f(x). It returns a double.

Examples:
For f(x) = 4*cos(2x)*sin(x).

To find f(2): `var value = Evaluator.eval("4*cos(2x)*sin(x)", 2);`
The Main.java has some driving code you can use to test the implementation

### This project is in working progress
Currently, it can only evaluate linear functions, polynomials of power n,simple trig functions containing cos, sin and tan and exponential functions.
