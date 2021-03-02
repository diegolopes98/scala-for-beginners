package lectures.oop

object Exceptions extends App {

  val x: String = null

  //println(x.length) // NullPointerException

  // 1. throwing exceptions

  //val aWeirdValue = throw new NullPointerException

  // throwable classes extend the Throwable class.
  // Exception and Error are the major Throwable subtypes.

  // 2. catching exceptions
  def getInt(withExceptions: Boolean): Int =
    if (withExceptions)throw new RuntimeException("Excepted")
    else 42

  val potentialFail = try {
    getInt(true)
  } catch {
    case _: RuntimeException => 43
  } finally {
    // code that will be executed no matter what (if threw exception or not)
    // optional
    // does not influence the return type of expression
    // hint: use finally for side effects
    println("finally")
  }

  // 3. how to define your own exceptions
  class MyException extends Exception

  val exception = new MyException

  //throw exception

  /*
    1. Crash the program with OutOfMemoryError
    2. Crash with StackOverflowError
    3. PocketCalculator
      - add
      - subtract
      - multiply
      - divide

      methods should throw customized exceptions
       - OverflowException if add(x, y) exceeds Int.MAX_VALUE
       - UnderflowException if subtract(x,y) exceeds Int.MIN_VALUE
       - MathCalculationException for division by 0
   */

  def outOfMemoryErrorFn: Unit = {
    val memory = Array.ofDim(Int.MaxValue)
  }

//  outOfMemoryErrorFn

  def stackOverflowErrorFn: Int = {
    2 * stackOverflowErrorFn
  }

//  stackOverflowErrorFn


  val calc = Calc
//  calc.add(2147483647, 1000000000)
  calc.subtract(Int.MinValue, 1)
}

class OverflowException extends Exception
class UnderflowException extends Exception
class MathCalculationException extends Exception

object Calc {
  def add(x: Int, y: Int): Int = {
    val result = x + y
    if(x > 0 && y > 0 && result < 0) throw new OverflowException
    else if (x < 0 && y < 0 && result > 0) throw new UnderflowException
    else result
  }

  def subtract(x: Int, y: Int): Int = {
    val result = x - y
    if(x > 0 && y < 0 && result < 0) throw new OverflowException
    else if (x < 0 && y > 0 && result > 0) throw new UnderflowException
    else result
  }

  def multiply(x: Int, y: Int): Int = {
    val result = x * y
    if (x > 0 && y > 0 && result < 0) throw new OverflowException
    else if (x < 0 && y < 0 && result < 0) throw new OverflowException
    else if (x > 0 && y < 0 && result > 0) throw new UnderflowException
    else if (x < 0 && y > 0 && result > 0) throw new UnderflowException
    else result

  }

  def divide(x: Int, y: Int): Int = {
    if(x == 0 || y == 0) throw new MathCalculationException
    x / y
  }
}

