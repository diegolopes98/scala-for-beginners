package lectures.oop

object Generics extends App {

  class MyList[+A] {
    // use the type A inside
    def add[B >: A](element: B): MyList[B] = ???
  }

  val listOfIntegers = new MyList[Int]
  val listOfStrings = new MyList[String]

  // generic methods
  object MyList {
    def empty[A]():MyList[A] = ???

  }
  val emptyListOfInts = MyList.empty[Int]()

  // variance problem

  class Animal
  class Cat extends Animal
  class Dog extends Animal

  // a list of animals can be a list of cats?

  // 1. yes, List[Cat] extends List[Animal] = Covariance
  class CovariantList[+A]
  val covariantAnimalList: CovariantList[Animal] = new CovariantList[Cat]
        // any implementation of animal is valid
  // animalList.add(new Dog) => it should turn animalList into a list of animals instead of cats or dogs

  // 2. No = invariance
  class InvariantList[A]
  val invariantAnimalList: InvariantList[Animal] = new InvariantList[Animal]
        // only animal implementations are valid

  // 3. Definitely, NO = Contra variance
  class ContraVariantList[-A]
  val contraVariantAnimalList: ContraVariantList[Cat] = new ContraVariantList[Animal]
        // any implementation of super classes of cat are valid


  // bounded types
  class CageSub[A <: Animal] (animal: A) // reads: class A only accepts sub type of animal
  class CageSup[A >: Animal] (animal: A) // reads: class A only accepts super type of animal
  val cage = new CageSub(new Dog)

  //class Car
  //val newCage = new Cage(new Car) // gives error

  // exercise, expand MyList to generic

}

