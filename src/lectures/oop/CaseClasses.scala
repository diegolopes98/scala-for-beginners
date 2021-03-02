package lectures.oop

object CaseClasses extends App {

  /*
    equals, hashCode, toString
   */
  case class Person(name: String, age: Int)
  // 1. class parameters are fields
  val jim = new Person("Jim", 34)
  println(jim.name)

  // 2. sensible toString
  println(jim.toString()) //Person(Jim, 34)
  println(jim) // equivalent to jim.toString()

  // 3. equals and hashCode implemented out of the box

  val jim2 = new Person("Jim", 34)

  println(jim == jim2) // True

  // 4. CCs have handy copy methods
  val jim3 = jim.copy(age = 45)

  println(jim3)

  // 5. CCs have companion objects

  val thePerson = Person
  val mary = thePerson("Mary", 23)

  // 6. CCs are serializable (akka useful)

  // 7. CCs have extractor patterns => CCs can be used in Pattern Matching

  case object UnitedKingdom {
    def name: String = "The UK of GB and NI"
  }

  /*
    Expand MyGenericList to use case classes / objects
   */

}
