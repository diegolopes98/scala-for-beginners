package exercises

import scala.annotation.tailrec

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

  def forEach(fn: A => Unit): Unit

  def sort(fn: (A, A) => Int): MyGenericList[A]

  def zipWith[B >: A](anotherList: MyGenericList[B], zipFn: (A, B) => B): MyGenericList[B]

  def fold[B >: A](acc: B)(function: (B, B) => B): B
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

  def forEach(fn: Nothing => Unit): Unit = ()
  def sort(fn: (Nothing, Nothing) => Int): MyGenericList[Nothing] = GEmpty
  def zipWith[B >: Nothing](anotherList: MyGenericList[B], zipFn: (Nothing, B) => B): MyGenericList[B] = GEmpty
  def fold[B >: Nothing](acc: B)(function: (B, B) => B): B = acc
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

  def forEach(fn: A => Unit): Unit = {
    fn(h)
    t.forEach(fn)
  }

  def sort(sortFn: (A, A) => Int): MyGenericList[A] = this

  def zipWith[B >: A](list: MyGenericList[B], zipFn: (A, B) => B): MyGenericList[B] = {
    GCons(
      zipFn(head(), list.head()),
      tail().zipWith(list.tail(), zipFn)
    )
  }

  def fold[B >: A](acc: B)(function: (B, B) => B): B = {
    t.fold(function(acc, h))(function)
  }
}

object GenericListTest extends App {
  val listInt: MyGenericList[Int] = GCons(1, GCons(2, GCons(3, GCons(4, GEmpty))))
  val listInt2: MyGenericList[Int] = GCons(2, GCons(3, GCons(4, GCons(5, GEmpty))))

  val doubler = (n: Int) => 2 * n

  val evenCheck = (n: Int) => n % 2 == 0

  val doublerList = (n: Int) => GCons(n, GCons(n * 2, GEmpty))

  println(listInt.toString())
  println(listInt2.toString())
  println(listInt2.zipWith(listInt, (x: Int, y: Int) => x * y))

  println(listInt2.fold(0)(_ + _))

}