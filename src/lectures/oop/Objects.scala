package lectures.oop

object Objects extends App {
  // Scala does not have class-level functionality ("static")

  object Person {
    // class-level functionalities (statics)
    val N_EYES = 2
    def canFly: Boolean = false

    // factory method
    def apply(mother: Person, father: Person): Person = new Person("Bobby")
  }

  class Person (val name:String){
    // instance-level functionalities
  }

  // Companions pattern => write object and class in the same scope to use class and instance level functionalities

  println(Person.N_EYES)
  println(Person.canFly)

  // scala object = singleton instance
  val person1 = Person
  val person2 = Person
  println(person1 == person2) //True  // they are the same

  val mary = new Person ("Mary")
  val john = new Person ("John")

  val bobby = Person(mary, john)

  // Scala Applications = Scala objects with
  // def main(args: Array[String]): Unit
  // To compile to java main method

}
