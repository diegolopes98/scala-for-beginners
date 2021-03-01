package lectures.oop

object AnonymousClasses extends App {

  abstract class Animal {
    def eat: Unit
  }

  // anonymous class
  val funnyAnimal: Animal = new Animal {
    override def eat: Unit = println("eating")
  }
  /*
  * The compiler actually instantiates the class:
  class AnonymousClasses$$anon$1 extends Animal {
    override def eat: Unit = println("eating")
  }
   */

  class Person(name: String) {
    def sayHi: Unit = println(s"Hi, my name is $name, how can I help?")
  }

  val jim = new Person("jim") {
    override def sayHi: Unit = println("Hi, my name is Jim, how can i be of service?")
  }
}
