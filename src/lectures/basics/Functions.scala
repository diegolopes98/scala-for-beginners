package lectures.basics

object Functions extends App {

  def aFunction(a: String, b: Int): String = {
    a + " " + b
  }

  println(aFunction("Hello", 100))

  def aNoParamFn(): Int = 42

  println(aNoParamFn) // can be called without parenthesis

  def aRepeatedFunction(aString: String, n: Int): String = {
    if (n <= 1) aString
    else aString + aRepeatedFunction(aString, n-1)
  }

  println(aRepeatedFunction("hello", 3))

  // When need loops use recursion instead

  def aFunctionWSideEffects(s: String): Unit = {
    println(s)
  }

  def aBigFn(n: Int): Int = {
    def aSmallerFn(a: Int, b: Int): Int = a + b

    aSmallerFn(n, n - 1)
  }

  println(aBigFn(10))


  // Exercise 1

  def greeting(name: String, age: Int): String =
    s"Hi, my name is $name and I am $age years old"

  println(greeting("Diego", 22))

  // Exercise 2 (factorial)

  def factorial(n: Int, acc: Int = 1): Int = {
    if (n == 1) acc
    else factorial(n - 1, n * acc)
  }

  println(factorial(5))

  // Exercise 3 (fibonacci)

  def fibonacci(n: Int, crr: Int = 1, acc: Int = 0): Int = {
    if (n == 0) acc
    else fibonacci(n - 1, crr + acc, crr)
  }

  println(fibonacci(8))

  // Exercise 4 (check prime number)

  def isPrime(n: Int, crr: Int = 2): Boolean = {
    if (n == 1) false
    else if (n == crr) n > 1
    else if(n % crr == 0) false
    else isPrime(n, crr + 1)
  }

  println(isPrime(9))

  // Exercise 4 instructor example

  def isPrimeInstructor(n: Int): Boolean = {
    def isPrimeUntil(t: Int): Boolean =
      if (t <= 1) true
      else n % t != 0 && isPrimeUntil((t - 1))

    isPrimeUntil(n / 2)
  }
}
