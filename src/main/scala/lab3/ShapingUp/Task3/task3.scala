package lab3.ShapingUp.Task3

sealed trait Shape {

  def numOfSides(): Int
  def perimeter(): Double
  def area(): Double

}

case class Circle(radius: Int) extends Shape{

  def numOfSides(): Int = 0
  def perimeter(): Double = 2 * math.Pi * radius
  def area(): Double = 4 * math.Pi * radius * radius

}

trait Rectangular extends Shape {}

case class Rectangle(side: Int, height: Int) extends Rectangular{

  def numOfSides(): Int = 4
  def perimeter(): Double = 2 * (side + height)
  def area(): Double = side * height

}

case class Square(side: Int) extends Rectangular{

  def numOfSides(): Int = 4
  def perimeter(): Double = 4 * side
  def area(): Double = side * side

}

object Draw{
  def apply(shape: Shape): Unit = {
    shape match {
      case x: Circle => println(s"A circle of radius ${x.radius}cm")
      case x: Rectangle => println(s"A rectangle with side: ${x.side}cm and height: ${x.height}cm")
      case x: Square => println(s"A rectangle with side: ${x.side}cm")
      case _ => println("Can't find the type")
    }
  }
}

object task3 extends App{

  Draw(Circle(10))
  Draw(Rectangle(3, 4))
  Draw(Square(4))

}
