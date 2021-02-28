package lectures.basics

object ValueVariablesTypes extends App {
  val x: Int = 42
  println(x)
  // vals are immutable (const, final similar), types are inferred
  // semicolons are not necessary (only in multiple exp in a line)

  val aString: String = "Hello"
  val anotherString: String = "goodbye"
  val aBoolean: Boolean = false
  val aChar: Char = 'a'
  val aInt: Int = x
  val aShort: Short = 4613
  val aLong: Long = 931029312123123L
  val aFloat: Float = 2.0f
  val aDouble: Double = 2.0

  // variables

  var aVariable: Int = 4
  aVariable = 5 // reassign


}
