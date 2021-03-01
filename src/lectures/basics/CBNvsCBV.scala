package lectures.basics

object CBNvsCBV extends App {

  def calledByValue(x: Long): Unit = {
    println("by value:" + x)
    println("by value:" + x)
  }

  def calledByName(x: => Long): Unit = { // lazy evaluation ??
    println("by name:" + x)
    println("by name:" + x)
  }

  calledByValue(System.nanoTime())
  calledByName(System.nanoTime())
}
