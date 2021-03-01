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

/*
* Novel and Write
*
* Writer: first name, surname, year
* - method: fullname
*
* Novel: name, release year, author
* - authorAge
* - isWrittenBy(author)
* - copy (new year of release) = new instance of novel
* */

class Writer(firstName: String, surname: String, val year: Int) {
  def fullName(): String = s"$firstName $surname"
}

class Novel(name: String, releaseYear: Int, author: Writer) {
  def authorAge(): Int = 2021 - author.year
  def isWrittenBy(author: Writer): Boolean = this.author == author
  def copy(year: Int): Novel = new Novel(name, year, author)
}

/*
  Counter class
    - receives an int value
    - method current count
    - method to increment/decrement => new Counter
    - overload inc/dec to receive an amount
 */

class Counter(val cur: Int = 0) {
  def inc(): Counter = new Counter(cur + 1)
  def dec(): Counter = new Counter(cur - 1)

  def inc(amount:Int): Counter =
    if (amount <= 0 ) this
    else inc.inc(amount - 1)

  def dec(amount:Int): Counter =
    if (amount <= 0 ) this
    else dec.dec(amount - 1)
}
