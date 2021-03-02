package lectures.fp

object FunctionDefinition extends App {

  // Dream: use functions as first class elements (as values)
  // problem: oop

  val doubler = new MyFunction[Int, Int] { // type = Function1[A, B] param 1 type A return type B
    override def apply(elem: Int): Int = elem * 2
  }

  println(doubler(2))
  // function types = Function1[A, B]

  // Function1 example
  val stringToIntConverter = new Function[String, Int] {
    override def apply(string: String): Int = string.toInt
  }

  println(stringToIntConverter("4") + 3)

  // Function2 example
  val adder = new ((Int, Int) => Int) { // syntactic sugar to: val adder = new Function2[Int, Int, Int] {
    override def apply(a: Int, b: Int): Int = a + b
  }

  // ALL SCALA FUNCTIONS ARE OBJECTS

  // exercise 1 concat 2 string

  val concatString = new ((String, String) => String) {
    override def apply(str1: String, str2: String): String = str1 + str2
  }

  println(concatString("Scala", " yeah"))

  // exercise 2: transform MyPredicate and MyTransformer into function types

  // exercise 3: define a fn which takes an int an returns a fn which takes an int an returns an int

  // high-order curried function
  val addHOF = new (Int => (Int => Int)) {
    override def apply(f1Value: Int): Int => Int = (f2Value: Int) => f1Value + f2Value
  }
  /*

  syntactic sugar for:

  val addHOF = new Function[Int, Int => Int] {
    override def apply(f1Value: Int): Int => Int = new Function1[Int, Int] {
      override def apply(f2Value: Int): Int = f1Value + f2Value
    }
  }
   */

  println(addHOF(2)(4))
}

trait MyFunction[A, B] {
  def apply(elem: A): B
}
