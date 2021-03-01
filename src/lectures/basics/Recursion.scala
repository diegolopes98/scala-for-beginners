package lectures.basics

import scala.annotation.tailrec

object Recursion extends App {
  def factorialWStack(n: Int): Int = {
    if (n <=1 ) 1
    else n * factorialWStack(n - 1)
  }

  // Tail recursive = last expression of fn statements
  @tailrec
  def factorialTailRecursion(n: Int, acc: Int = 1): Int =
    if(n <= 1) acc
    else factorialTailRecursion(n - 1, acc * n )

  // When need to use loops, use TAIL RECURSION instead ***

  // tail recursion exercises

  // Exercise 1 (string concat n times)

  def stringConcat(string: String, times: Int): String = {
    @tailrec
    def stringConcatHelper(s: String, t: Int, stringAcc: String): String =
      if (t == 0) stringAcc
      else stringConcatHelper(s, t - 1, stringAcc + s)

    stringConcatHelper(string, times, "")
  }

  println(stringConcat("Tail", 2))

  // Exercise 2 (check prime)

  def isPrime(number: Int): Boolean = {
    @tailrec
    def isPrimeHelper(n: Int, d: Int): Boolean =
      if(n == d) true
      else n > 1 && n % d != 0 && isPrimeHelper(n, d + 1)

    isPrimeHelper(number, 2)
  }

  println(isPrime(2003))
  println(isPrime(629))

  // Exercise 3 (fibonacci)

  def fibonacci(number: Int): Int = {
    @tailrec
    def fib(n: Int, cur: Int, acc: Int): Int =
      if (n == 0) acc
      else fib(n - 1, cur + acc, cur)

    fib(number, 1, 0)
  }

  println(fibonacci(4))
}
