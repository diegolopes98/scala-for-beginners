package exercises

abstract class MyList {
  def head(): Int
  def tail(): MyList
  def isEmpty(): Boolean
  def add(number: Int): MyList
  def printElements(): String
  override def toString(): String = "[" + printElements + "]"

}

object Empty extends MyList {
  def head(): Int = throw new NoSuchElementException
  def tail(): MyList = throw new NoSuchElementException
  def isEmpty(): Boolean = true
  def add(number: Int): MyList = new Cons(number, Empty)

  override def printElements(): String = ""

}

class Cons(h: Int, t: MyList) extends MyList {
  def head(): Int = h
  def tail(): MyList = t
  def isEmpty(): Boolean = false
  def add(number: Int): MyList = new Cons(number, this)

  override def printElements(): String =
    if(t.isEmpty()) s"$h"
    else s"$h, ${t.printElements()}"
}

object ListTest extends App {
  val list = new Cons(1, new Cons(2, new Cons(3, Empty)))
  println(list.toString())
}


