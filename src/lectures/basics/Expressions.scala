package lectures.basics

object Expressions extends App {

  val x = 1 + 2 // Expression

  println(x)

  println(2 + 3 * 4)
  // + - * / & | ^ << >> >>> (right shift w zero extension)

  println(1 == x)
  // == != > >= < <=

  println(!(1==x))
  // ! && ||

  var aVariable = 2
  aVariable += 3 // -= *= /= only work with variables (side effects)
  println(aVariable)

  // Instructions (tell the computer to do) vs Expressions (returns an value)
  // fp thinks in expressions

  // If expression

  val aCondition = true

  val aConditionedValue = if(aCondition) 6 else 3

  println(aConditionedValue)

  print(if(aCondition) 6 else 3) //if returns an value

  // Loops (do not use, produces side effects)
  var i = 0
  while (i < 10) {
    println(i)
    i+=1
  }

  // Use recursion instead

  // note: everything is an expression**

  val aWeirdValue = (aVariable = 3)

  println(aWeirdValue) // type unit (similar to void)

  // side effects: println() , whiles, reassigning -> returns unit (void)

  // Code blocks

  val aCodeBlock = {
    val y = 2
    val z = y + 1

    if(z > 2) "hello" else "goodbye"
  } // the value of the block is the value of the last expression
}
