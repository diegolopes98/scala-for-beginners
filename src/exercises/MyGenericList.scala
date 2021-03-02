package exercises

abstract class MyGenericList[+A] {
  def head(): A
  def tail(): MyGenericList[A]
  def isEmpty(): Boolean
  def add[ B >: A ](elem: B): MyGenericList[B]
  def printElements(): String
  override def toString(): String = "[" + printElements + "]"

  def map[B](transformer: A => B): MyGenericList[B]

  def filter(predicate: A => Boolean): MyGenericList[A]

  def flatMap[B](transformer: A => MyGenericList[B]): MyGenericList[B]

  // concatenation
  def ++[ B >: A](list: MyGenericList[B]): MyGenericList[B]
}

case object GEmpty extends MyGenericList[Nothing] {
  def head(): Nothing = throw new NoSuchElementException
  def tail(): MyGenericList[Nothing] = throw new NoSuchElementException
  def isEmpty(): Boolean = true
  def add[B >: Nothing](elem: B): MyGenericList[B] = new GCons(elem, GEmpty)
  override def printElements(): String = ""

  def map[B](transformer: Nothing => B): MyGenericList[Nothing] = this
  def filter(predicate: Nothing => Boolean): MyGenericList[Nothing] = this
  def flatMap[B](transformer: Nothing => MyGenericList[B]): MyGenericList[B] = this
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


  def map[B](transformer: A => B): MyGenericList[B] =
    GCons(
      transformer(h),
      t.map(transformer)
    )

  def filter(predicate: A => Boolean): MyGenericList[A] = {
    if(predicate(h)) GCons(h, t.filter(predicate))
    else t.filter(predicate)
  }

  def flatMap[B](transformer: A => MyGenericList[B]): MyGenericList[B] = {
    transformer(h) ++ t.flatMap(transformer)
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

  val doubler = (n: Int) => 2 * n

  val evenCheck = (n: Int) => n % 2 == 0

  val doublerList = (n: Int) => GCons(n, GCons(n * 2, GEmpty))

  println(listInt)
  println(listInt.filter(evenCheck))
  println(listInt.map(doubler))
  println(listInt.map(doublerList))
  println(listInt.flatMap(doublerList))
}