package lectures.fp

object HOFsAndCurries extends App {

   val superFunction: (Int, (String, (Int => Boolean)) => Int) => (Int => Int) = null
  // higher order function (hof)

  // fn that applies a function n times over a value x
  // nTimes(f, n, x)

  def nTimes(f: Int => Int, n: Int, x: Int): Int =
    if (n <= 0) x
    else nTimes(f, n - 1, f(x))

  val plusOne: Int => Int = _ + 1
  println(nTimes(plusOne, 10, 1))

  def nTimesBetter(f: Int => Int, n: Int): Int => Int =
    if (n <= 0) (x: Int) => x
    else (x: Int) => nTimesBetter(f, n - 1)(f(x))

//  def nTimesBetter2(f: Int => Int, n: Int): Int => Int =
//    if (n <= 0) (x: Int) => x
//    else (x: Int) => nTimesBetter(f, n - 1)(f(x))

  val plus10 = nTimesBetter(plusOne, 10)
  println(plus10 (1))

  // curried functions
  val superAdder: Int => (Int => Int) = (x: Int) => (y: Int) => x + y
  val add3 = superAdder(3)
  println(add3(10))
  println(superAdder(3)(10))

  // functions with multiple parameter lists
  def curriedFormatter(c: String)(x: Double): String = c.format(x)
  val standardFormat: (Double => String) = curriedFormatter("%4.2f")
  val preciseFormat: (Double => String) = curriedFormatter("%10.8f")

  println(standardFormat(Math.PI))
  println(preciseFormat(Math.PI))

  /*
    1. Expand MyGenericList
      - foreach method A => Unit
      [1, 2, 3].foreach(x => println(x))

      - sort function ((A, A) => Int) => MyGenericList
       [1, 2, 3].sort((x, y) => y - x ) => [3, 2, 1]

      - zipWith (list, (A, A) => B) => MyGenericList[B]
      [1, 2, 3].zipWith([4, 5, 6], x * y) => [1*4, 2 * 5, 3 * 6] = [4, 10, 18]

      - fold (start)(function) => a value
      [1, 2, 3].fold(0)(x + y) = 6

     2. toCurry(f: (Int, Int) => Int) => (Int => Int => Int )
        fromCurry (Int => Int => Int ) = (Int, Int) => Int)

     3. compose(f,g) => x => f(g(x))
        andThen(f,g) => x => g(f(x))
   */


  // 2

  val toCurry : ((Int, Int) => Int) => Int => Int => Int  = { fn =>
      (a: Int) => (b: Int) => fn(a,b)
  }

  val curriedFn = toCurry(_+_)
  println(curriedFn(1)(2))

  val fromCurry: (Int => Int => Int) => (Int, Int) => Int = { fn =>
    (a: Int, b: Int) => fn(a)(b)
  }

  val notCurriedFn = fromCurry(curriedFn)
  println(notCurriedFn(1, 2))

  // 3

  val compose = { (f: Int => Int, g: Int => Int) =>
    (x: Int) => f(g(x))
  }

  val composedFn = compose(a => a * 2, b => b - 5)
  println(composedFn(10))

  val andThen = { (f: Int => Int, g: Int => Int) =>
    (x: Int) => g(f(x))
  }

  val andThenFn = andThen(a => a * 2, b => b - 5)
  println(andThenFn(10))

  // Function X
  // generic compose and andThen
  def gCompose[A, B, C](f: A => B, g: C => A): C => B = x => f(g(x))
  def gAndThen[A, B, C](f: A => B, g: B => C): A => C = x => g(f(x))
}
