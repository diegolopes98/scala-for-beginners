package lectures.oop

//import playground._ //{ScalaImportExample, ScalaImportExample2} // the import _ imports everything
import playground.{ScalaImportExample, ScalaImportExample2 => Example2} // Alias import

object PackagingAndImports extends App {

  // package members are accessible by their name
  val writer = new Writer("George", "Orwell", 1998)

  // if not in the same package, you should import the package
  val playgroundPackageClass = new ScalaImportExample
  // or you can use the full package name
  // val playgroundPackageClass2 = new playground.ScalaImportExample
  // its the fully qualified name

  // packages are in hierarchy matching folder structure

  // package object
  sayHello // accessible by the package object

  // imports
  val example = new ScalaImportExample
  val example2 = new Example2

  // default imports
  // java.lang - String, Object, Exception
  // scala - Int, Nothing, Function
  // scala.Predef - println, ???
}
