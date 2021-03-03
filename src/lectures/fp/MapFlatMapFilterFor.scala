package lectures.fp

object MapFlatMapFilterFor extends App{

  val list = List(1,2,3)
  println(list.head)
  println(list.tail)

  // map
  println(list.map(_ + 1))
  println(list.map(_ + "is a number"))

  // filter
  println(list.filter(_ % 2 == 0))

  // flatmap
  val toPair = (x: Int) => List(x, x + 1)
  println(list.map(toPair))
  println(list.flatMap(toPair))

  // Example 1: print all combinations between two lists
  val numbers = List(1, 2, 3, 4)
  val chars = List('a', 'b', 'c', 'd')
  // expected return: List("a1", "a2" ... "d4")
  // result:
  println(chars.flatMap(char => numbers.map(number => s"$char$number")))

  // forEach
  list.foreach(println)

  // for-comprehensions
  val forCombinations = for {
    n <- numbers if n % 2 == 0 // if guard
    c <- chars
  } yield s"$c$n"

  println(forCombinations)

  // side-effects for-comprehensions
  for {
    n <- numbers
  } println(n)

  // syntax overload
  list.map { x =>
    x * 2
  }

  /*
    1. MyGenericList supports for-comprehensions?
    2. A small collection of at most ONE element - Maybe[+T]
       - map, flatMap, filter
   */
}
