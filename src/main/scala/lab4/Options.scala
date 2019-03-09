package lab4

object Options extends App{

  def calculator(operand1: String, operator: String, operand2: String):Unit = {


    val a1: Option[Int] = if(operand1 matches "\\d+") Some(operand1.toInt) else None
    val a2: Option[Int] = if(operand2 matches "\\d+") Some(operand2.toInt) else None


    val res = a1 match {
      case Some(a) =>{
        a2 match {
          case Some(b) => {
            operator match {
              case "+" => Option(a + b)
              case "-" => Option(a - b)
              case "*" => Option(a * b)
              case "/" => Option(a / b)
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
  //TODO WORK WITH ZERO
}
