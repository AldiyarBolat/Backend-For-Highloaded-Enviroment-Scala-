package lab3.AlgebraicDtypes



sealed trait Calculator
case object Integer extends Calculator
case object _String extends Calculator


case class BottledWater(size: Int, source: String, carbonated: String)

object adt {

}
