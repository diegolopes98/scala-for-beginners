package exercises

trait MyPredicate[-T] {
  def test(value: T): Boolean
}

trait MyTransformer[-A, B] {
  def transform(value: A): B
}

class DoubleTransformer extends MyTransformer[Int, Int] {
  def transform(value: Int): Int = value * 2
}

class IntToStringTransformer extends MyTransformer[Int, String] {
  def transform(value: Int): String = s"$value"
}

class StrToUpperCaseTransformer extends MyTransformer[String, String] {
  def transform(value: String): String = value.toUpperCase()
}

class EvenPredicate extends MyPredicate[Int] {
  def test(value: Int): Boolean = value % 2 == 0
}

class FPLangPredicate(languages: List[String]) extends MyPredicate[String] {
  def test(value: String): Boolean = languages.map(lang => lang.toLowerCase).contains(value.toLowerCase)
}

abstract class MyGenericList[+A] {
  def head(): A
  def tail(): MyGenericList[A]
  def isEmpty(): Boolean
  def add[ B >: A ](elem: B): MyGenericList[B]
  def printElements(): String
  override def toString(): String = "[" + printElements + "]"

  def map[B](transformer: MyTransformer[A, B]): MyGenericList[B]

  def filter(predicate: MyPredicate[A]): MyGenericList[A]
}

object GEmpty extends MyGenericList[Nothing] {
  def head(): Nothing = throw new NoSuchElementException
  def tail(): MyGenericList[Nothing] = throw new NoSuchElementException
  def isEmpty(): Boolean = true
  def add[B >: Nothing](elem: B): MyGenericList[B] = new GCons(elem, GEmpty)
  override def printElements(): String = ""

  def map[B](transformer: MyTransformer[Nothing, B]): MyGenericList[Nothing] = this
  def filter(predicate: MyPredicate[Nothing]): MyGenericList[Nothing] = this

}

class GCons[+A](h: A, t: MyGenericList[A]) extends MyGenericList[A] {
  def head(): A = h
  def tail(): MyGenericList[A] = t
  def isEmpty(): Boolean = false
  def add[ B >: A ](elem: B): MyGenericList[B] = new GCons(elem, this)

  override def printElements(): String =
    if(t.isEmpty()) s"$h"
    else s"$h, ${t.printElements()}"


  def map[B](transformer: MyTransformer[A, B]): MyGenericList[B] =
    new GCons(
      transformer.transform(h),
      tail().map(transformer)
    )

  def filter(predicate: MyPredicate[A]): MyGenericList[A] = {
    val condition = predicate.test(h)
    if(condition) new GCons(h, t.filter(predicate))
    else t.filter(predicate)
  }
}

object GenericListTest extends App {
  val listInt: MyGenericList[Int] = new GCons(1, new GCons(2, new GCons(3, new GCons(4, GEmpty))))
  val listStr: MyGenericList[String] = new GCons("Hello", new GCons("Scala", GEmpty))
  val langsList: MyGenericList[String]  = new GCons("Elixir", new GCons("Scala", new GCons("Java", GEmpty)))

  val doubleTransformer = new DoubleTransformer
  val intToStringTransformer = new IntToStringTransformer
  val toUpperCaseTransformer = new StrToUpperCaseTransformer

  val evenPredicate = new EvenPredicate
  val fpLangPredicate = new FPLangPredicate(List("scala", "f#", "clojure", "elixir"))

  println(listInt)


  println("\nMapped int list\n")
  val mappedIntList = listInt.map(doubleTransformer)
  println(mappedIntList)
  println(GEmpty.map[Int](doubleTransformer))


  println("\nFiltered int list\n")
  val filteredIntList = listInt.filter(evenPredicate)
  println(filteredIntList)
  println(GEmpty.filter(evenPredicate))

  println("\nMapped int to str list\n")
  val mappedIntToStrList = listInt.map[String](intToStringTransformer)
  println(mappedIntToStrList)

  println("\nMapped str to upper case list\n")
  val mappedToUpperCaseList = listStr.map[String](toUpperCaseTransformer)
  println(mappedToUpperCaseList)

  println("\nFP languages list\n")
  println(langsList)
  println("\nFiltered fp languages list\n")
  val filteredFPLangList = langsList.filter(fpLangPredicate)
  println(filteredFPLangList)
}