package lectures.oop

object MethodNotations extends App {

  class Person(val name: String, favoriteMovie: String, age: Int = 0) {
    def likes(movie: String): Boolean = favoriteMovie == movie
    def +(person: Person): String = s"${this.name} is hanging out with ${person.name}"
    def unary_! : String = s"$name, what the heck??"
    def isAlive: Boolean = true
    def apply(): String = s"Hi, my name is $name and I like $favoriteMovie"

    // 1 exercise
    def +(nickname: String): Person = new Person(s"$name ($nickname)", favoriteMovie)

    // 2 exercise
    def unary_+ : Person = new Person(name, favoriteMovie, age + 1)

    // 3 exercise

    def learns(anything: String): String = s"$name learns $anything"

    def learnsScala(): String = this learns "Scala"

    // 4 exercise
    def apply(times: Int): String = s"$name watched $favoriteMovie $times times"
  }

  val mary = new Person("Mary", "shrek")
  println(mary.likes("inception"))
  println(mary likes "inception") // equivalent
  // infix notation = operator notation (only work with methods with one parameter) - syntactic sugar

  // "operators" in Scala
  val tom = new Person("Tom", "Fight Club")
  println(mary + tom)
  println(mary.+(tom))

  println(1 + 2)
  println(1.+(2))

  // All operators are methods.

  // prefix notation

  val x = -1
  val y = 1.unary_- // equivalents
  // unary_ prefix only works with - + ~ !

  println(!mary)
  println(mary.unary_!)

  // postfix notation (fn without parameters can be used with postfix notation)
  println(mary.isAlive)
  println(mary isAlive)

  // apply
  println(mary.apply())
  println(mary()) //equivalent

  // 1 exercise overload + return a new person
  // 2 exercise add unary plus return a new person with age + 1
  // 3 exercise add "learns" method in the Person => "Mary learns Scala"
  //   Add learnsScala method, calls learns method with "Scala"
  //   Use in postfix notation
  // 4 exercise overload apply to receive a number and returns a string
  //  mary.apply(2) => s"$name watched $favoriteMovie $times times"

  println((mary + "scala beginner").name)
  println(+mary)
  println(mary learnsScala)
  println(mary(2))

}
