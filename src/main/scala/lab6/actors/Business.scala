package lab6.actors

import akka.actor.{Actor, ActorLogging, ActorRef, Props, ReceiveTimeout}
import lab6.actors.Agashka.AddRodstvenik
import lab6.actors.Business.Rodsvenik
import scala.collection.mutable.ListBuffer


object Business{
  case class Rodsvenik(name: String)

  def props(name: String) = Props(new Business(name))
}

class Business(name: String) extends Actor with ActorLogging  {
  var team = new ListBuffer[Rodsvenik]()

  override def receive: Receive = {
    case msg @ Agashka.GetData =>
      sender() ! Agashka.OtvetAgashki(name)
    case msg @ AddRodstvenik(busineesName, rodstvenikName) =>
      team += Rodsvenik(rodstvenikName)
      sender() ! Agashka.OtvetAgashki("rodstvenik successfully added to the business")
  }

}
