package lab4

object Options extends App{

  def calculator(operand1: String, operator: String, operand2: String):Unit = {

    def sum(opt1: Option[Int], opt2: Option[Int]): Option[Int] =  {
      for {
        value1 <- opt1
        value2 <- opt2
      } yield value1 + value2
    }

    def div(opt1: Option[Int], opt2: Option[Int]): Option[Int] =  {
      opt2 match {
        case Some(0) => return None
      }

      for {
        value1 <- opt1
        value2 <- opt2
      } yield value1 / value2
    }

    def minus(opt1: Option[Int], opt2: Option[Int]): Option[Int] =  {
      for {
        value1 <- opt1
        value2 <- opt2
      } yield value1 - value2
    }

    def mult(opt1: Option[Int], opt2: Option[Int]): Option[Int] =  {
      for {
        value1 <- opt1
        value2 <- opt2
      } yield value1 * value2
    }

    val a1: Option[Int] = if(operand1 matches "\\d+") Some(operand1.toInt) else None
    val a2: Option[Int] = if(operand2 matches "\\d+") Some(operand2.toInt) else None


    val res = a1 match {
      case Some(a) =>{
        a2 match {
          case Some(b) => {
            operator match {
              case "+" => sum(a1, a2)
              case "-" => minus(a1, a2)
              case "*" => mult(a1, a2)
              case "/" => div(a1, a2)
            }
          }
          case None => println("cant convert op2 into int")
        }
      }
      case None =>  println("cant convert op1 into int")
    }


    res match {
      case Some(r) => println(r)
      case None => println("cant div to zero")
    }

  }



  calculator("1", "/", "0")
}
