package lectures.basics

object StringOps extends App {

  val str: String = "Hello, I am learning Scala"

  // Java functions
  println(str.charAt(2))
  println(str.substring(7,11))
  println(str.split(" ").toList)
  println(str.replace("Hello", "Hi"))
  println(str)

  // Scala functions

  val aNumberString = "2"
  val aNumber = aNumberString.toInt
  println('a' +: aNumberString :+ 'z')

  println(str.reverse)
  println(str.take(2))

  // Scala-specific: String interpolator.

  // S-interpolator

  val name = "Diego"
  val age = 22
  val greeting = s"Hi, my name is $name and I'm $age years old"
  val anotherGreeting = s"Hi, my name is $name and I will be turning ${age + 1} years old"

  println(greeting)
  println(anotherGreeting)

  // F-interpolator

  val speed = 1.2f
  val myth = f"$name%s can eat $speed%2.2f burguers per minute"

  println(myth)

  // raw-interpolator

  println(raw"This is a \n new line")
  val escaped = "This is a \n new line"
  println(raw"$escaped") // raw interpolator ignores escapes but interpolated values with escape are escaped
}
