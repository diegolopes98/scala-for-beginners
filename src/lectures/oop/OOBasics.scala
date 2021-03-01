package lectures.oop

object OOBasics extends App {

  val person = new Person("Diego", 22)
  println(person)
  person.greet("Daniel")
  person.greet()
}

class Person(name: String, val age: Int) {
  // body
  val x = 2

  def greet(name: String): Unit = println(s"${this.name} says: Hi, $name")

  // overload
  def greet(): Unit = println(s"Hi, I am $name")

  // overload constructor
  def this(name: String) = this(name, 0)
}

// to convert class parameter to field should put val or var before