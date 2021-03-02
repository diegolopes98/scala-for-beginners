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

class CurrentAndDoubleFlatMapTransformer extends MyTransformer[Int, MyGenericList[Int]] {
  def transform(value: Int): MyGenericList[Int] = new GCons[Int](value, new GCons[Int](value * 2, new GCons[Int](value * 3, GEmpty)))
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

  def flatMap[B](transformer: MyTransformer[A, MyGenericList[B]]): MyGenericList[B]

  // concatenation
  def ++[ B >: A](list: MyGenericList[B]): MyGenericList[B]
}

case object GEmpty extends MyGenericList[Nothing] {
  def head(): Nothing = throw new NoSuchElementException
  def tail(): MyGenericList[Nothing] = throw new NoSuchElementException
  def isEmpty(): Boolean = true
  def add[B >: Nothing](elem: B): MyGenericList[B] = new GCons(elem, GEmpty)
  override def printElements(): String = ""

  def map[B](transformer: MyTransformer[Nothing, B]): MyGenericList[Nothing] = this
  def filter(predicate: MyPredicate[Nothing]): MyGenericList[Nothing] = this
  def flatMap[B](transformer: MyTransformer[Nothing, MyGenericList[B]]): MyGenericList[B] = this
  def ++[ B >: Nothing](list: MyGenericList[B]): MyGenericList[B] = list
}

case class GCons[+A](h: A, t: MyGenericList[A]) extends MyGenericList[A] {
  def head(): A = h
  def tail(): MyGenericList[A] = t
  def isEmpty(): Boolean = false
  def add[ B >: A ](elem: B): MyGenericList[B] = GCons(elem, this)

  override def printElements(): String =
    if(t.isEmpty()) s"$h"
    else s"$h, ${t.printElements()}"


  def map[B](transformer: MyTransformer[A, B]): MyGenericList[B] =
    GCons(
      transformer.transform(h),
      t.map(transformer)
    )

  def filter(predicate: MyPredicate[A]): MyGenericList[A] = {
    if(predicate.test(h)) GCons(h, t.filter(predicate))
    else t.filter(predicate)
  }

  def flatMap[B](transformer: MyTransformer[A, MyGenericList[B]]): MyGenericList[B] = {
    transformer.transform(h) ++ t.flatMap(transformer)
  }

  def ++[B >: A](list: MyGenericList[B]): MyGenericList[B] = {
    GCons(
      h,
      t ++ list
    )
  }
}

object GenericListTest extends App {
  val listInt: MyGenericList[Int] = GCons(1, GCons(2, GCons(3, GCons(4, GEmpty))))
  val listStr: MyGenericList[String] = GCons("Hello", GCons("Scala", GEmpty))
  val langsList: MyGenericList[String]  = GCons("Elixir", GCons("Scala", GCons("Java", GEmpty)))

  val doubleTransformer = new DoubleTransformer
  val intToStringTransformer = new IntToStringTransformer
  val toUpperCaseTransformer = new StrToUpperCaseTransformer
  val currentAndDoubleFlatMapTransformer = new CurrentAndDoubleFlatMapTransformer

  val evenPredicate = new EvenPredicate
  val fpLangPredicate = new FPLangPredicate(List("scala", "f#", "clojure", "elixir"))

  println(listInt.toString)


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

  println("\nMapped current and double transformer list\n")
  val currAndDoubleListFlatMapped = listInt.flatMap(currentAndDoubleFlatMapTransformer)
  val currAndDoubleListMapped = listInt.map(currentAndDoubleFlatMapTransformer)
  println(listInt)
  println(currAndDoubleListFlatMapped)
  println(currAndDoubleListMapped)
}