package lectures.oop

object InheritanceAndTraits extends App {

  // single class inheritance (only extends one class at time)

  class Animal {
    val creatureType = "wild"
    protected def eat = println("nhamnhamnham")
  }

  class Cat extends Animal {
    def crunch = {
      eat
      println("crunch")
    }
  }

  val cat = new Cat()
  cat.crunch

  // constructors
  class Person (name: String, age: Int)
  class Adult (name: String, age: Int, idCard: String) extends Person(name, age)

  // overriding
  class Dog(override val creatureType: String) extends Animal {
//    override val creatureType: String = "domestic"
    override def eat = println("crunch, crunch")
  }

  val dog = new Dog("domestic")
  dog.eat
  println(dog.creatureType)

  // type substitution (polymorphism)
  val unknownAnimal: Animal = new Dog("K9")

  // preventing override
  // - declare method with final
  // - declare class with final prevents to be extended
  // - seal the class = extend classes in THIS FILE, but prevent in other files (sealed class CLASSNAME)



}
