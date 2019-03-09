package lab3.ShapingUp.Task2



trait Shape {

  def numOfSides(): Int
  def perimeter(): Double
  def area(): Double

}

class Circle(radius: Int) extends Shape{

  def numOfSides(): Int = 0
  def perimeter(): Double = 2 * math.Pi * radius
  def area(): Double = 4 * math.Pi * radius * radius

}

trait Rectangular extends Shape

class Rectangle(side: Int, height: Int) extends Rectangular{

  def numOfSides(): Int = 4
  def perimeter(): Double = 2 * (side + height)
  def area(): Double = side * height

}

class Square(side: Int) extends Rectangular{

  def numOfSides(): Int = 4
  def perimeter(): Double = 4 * side
  def area(): Double = side * side

}

object task2 extends App {

  val circle = new Circle(3)
  val rectangle = new Rectangle(2, 3)
  val square = new Square(2)

  println(circle.numOfSides())
  println(circle.perimeter())
  println(circle.area())

  println(rectangle.numOfSides())
  println(rectangle.perimeter())
  println(rectangle.area())

  println(square.numOfSides())
  println(square.perimeter())
  println(square.area())

}
