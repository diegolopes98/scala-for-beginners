package lectures.fp

import scala.util.Random

object Sequences extends App {

  /*
    Seq has head and tail, a well defined order and can be indexed

    operations:
      - apply, iterator, length, reverse, for indexing and iterating
      - concatenation, appending, prepending for sizing
      - grouping, sorting, zipping, searching, slicing and others for other stuff
   */

  val aSeq = Seq(2, 1, 4, 3) // actually is a List
  println(aSeq)
  println(aSeq.reverse)
  println(aSeq(2))
  println(aSeq ++ Seq(5, 6, 7))
  println(aSeq.sorted)

  // Ranges
  val aRange: Seq[Int] = 1 to 10
  aRange.foreach(print)
  println()
  (1 until 10).foreach(print)
  println()

  // Lists
  val aList = List(2,1,3)
  val prepended = 43 :: aList // or 43 +: aList
  val appended = aList :+ 90

  val apples5 = List.fill(5)("apple") // list with 5 apples string in it

  println(aList.mkString("-|-"))

  /*
    Arrays
      can be manually constructed with predefined lengths
      mutable (update in place)
      indexing is fast
      seq ???
   */

  val numbers = Array(1,2,4)
  val threeElements = Array.ofDim[Int](3)
  // fill with defaults (int double boolean) => 0, false, for object types are null
  println(numbers)
  println(threeElements)

  // mutation
  numbers(2) = 0 // syntax sugar for numbers.update(2, 0)
  println(numbers.mkString(" "))

  // arrays and seq

  val numbersSeq: Seq[Int] = numbers // implicit conversion to WrappedArray
  println(numbersSeq)

  /*
    Vectors

    - effectively constant indexed read and write O(log32(n))
    - fast element addition: append/ prepend
    - implemented as a fixed-branched trie (branch factor 32) => 32 elements at any level
    - good performance for very large size
   */

  val vector: Vector[Int] = Vector(1,2,3)
  println(vector)

  // vector vs list

  val maxRuns = 1000
  val maxCapacity = 1000000
  def getWriteTime(collection: Seq[Int]): Double = {
    val r = new Random
    val times = for {
      it <- 1 to maxRuns
    } yield {
      val currentTime = System.nanoTime()
      collection.updated(r.nextInt(maxCapacity), 0)
      System.nanoTime() - currentTime
    }
    times.sum * 1.0 / maxRuns
  }

  val numbersList = (1 to maxCapacity).toList
  val numbersVector = (1 to maxCapacity).toVector

  println(getWriteTime(numbersList))
  /*
    List

    advantages:
    - keeps reference to tail
    - update head or tail is really fast o(1)

    disadvantages:
    - update a element in the middle is really slow
   */
  println(getWriteTime(numbersVector))
  /*
    Vector

    advantages:
    - depth of the tree is small

    disadvantages:
    - needs to replace an entire 32-element chunk
   */
}
