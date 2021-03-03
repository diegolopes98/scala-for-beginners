package exercises

abstract class Maybe[+T] {
  def isEmpty(): Boolean
  def map[K](mapper: T => K): Maybe[K]
  def filter(predicate: T => Boolean): Maybe[T]
  def flatMap[K](mapper: T => Maybe[K]): Maybe[K]
  def foreach(fn: T => Unit): Unit
}

case object MyCollection extends Maybe[Nothing] {
  def apply[K >: Nothing](elem: K): Maybe[K] =
    if (elem == null) MyCollection else new MyCollection(elem)

  override def isEmpty(): Boolean = true

  override def map[K](mapper: Nothing => K): Maybe[K] = MyCollection

  override def filter(predicate: Nothing => Boolean): Maybe[Nothing] = MyCollection

  override def flatMap[K](mapper: Nothing => Maybe[K]): Maybe[K] = MyCollection

  override def foreach(fn: Nothing => Unit): Unit = ()

  override def toString(): String = "Maybe(Empty)"
}

case class MyCollection[T](elem: T) extends Maybe[T] {
  override def isEmpty(): Boolean = false

  override def map[K](mapper: T => K): Maybe[K] = MyCollection(mapper(elem))

  override def filter(predicate: T => Boolean): Maybe[T] = if(predicate(elem)) this else MyCollection

  override def flatMap[K](mapper: T => Maybe[K]): Maybe[K] = mapper(elem)

  override def foreach(fn: T => Unit): Unit = fn(elem)

  override def toString(): String = s"Maybe($elem)"

}

object MyCollectionTest extends App {
  val filled = MyCollection[Int](10)
  val empty = MyCollection

  // check if is empty
  println(filled.isEmpty()) // false
  println(empty.isEmpty()) // true

  // map
  println(filled.map(value => value * 2).toString())
  println(empty.map(value => value).toString())

  // flatMap
  println(filled.flatMap(value => MyCollection(value * 2)).toString())
  println(empty.map(value => value).toString())
}

