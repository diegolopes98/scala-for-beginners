package lectures.basics

import scala.annotation.tailrec

object DefaultArgs extends App {

  @tailrec
  def trFact(n: Int, acc: Int = 1): Int = {
    if(n <= 1 ) acc
    else trFact(n - 1, n * acc)
  }

  val fact10 = trFact(10)

  def savePicture(format: String = "jpg", width: Int = 1920, height: Int = 1080): Unit = println("picture saved")

  savePicture(width = 800) // name the arg
  savePicture(width = 800, format = "png", height = 10) // reorder the args with named args
}
