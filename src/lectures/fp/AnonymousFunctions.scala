package lectures.fp

object AnonymousFunctions extends App {

  // anonymous function (lambda), syntactic sugar to new Function1[a, b]
  val doubler = (x: Int) => x * 2
  // or
  val doubler2: Int => Int = x => x * 2

  // multiple params in lambda

  val adder = (a: Int, b: Int) => a + b
  // or
  val adder2: (Int, Int) => Int = (a, b) => a + b

  // no params in lambda
  val justDoSomething = () => "something"
  // or
  val justDoSomething2: () => String = () => "something"

  // careful lambdas must have parenthesis in call
  println(justDoSomething) // prints memory reference
  println(justDoSomething()) // actually prints "something"

  // lambdas with curly braces

  val stringToInt = { (str: String) =>
    str.toInt
  }

  // More syntactic sugar
  val increment: Int => Int = _ + 1 // equivalent to x => x + 1
  val adderSugar: (Int, Int) => Int = _ + _  // equivalent to (a, b) => a + b


  /*
    1. MyGenericList: replace all FunctionX calls with lambdas
    2. Rewrite the special adder from the last class as lambda
   */

  // 2

  val specialAdder = (x: Int) => (y: Int) => y + x

  val add3 = specialAdder(3)
  println(add3(7))
  println(specialAdder(3)(3))


}
