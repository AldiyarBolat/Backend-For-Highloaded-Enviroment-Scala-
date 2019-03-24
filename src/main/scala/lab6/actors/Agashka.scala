package lab6.actors

import akka.actor.{Actor, ActorLogging, ActorRef, PoisonPill, Props, ReceiveTimeout}
import language.postfixOps




object Agashka{
  case class CreateBusiness(businessname: String)
  case class DelteBusiness(businessname: String)
  case class GetData(businessname: String)
  case class OtvetAgashki(txt: String)
  case object GetBusinessData
  case class AddRodstvenik(busineesName: String, rodstvenikName: String)

  def props(name: String) = Props(new Agashka(name))
}

class Agashka(name: String) extends Actor with ActorLogging {
  import lab6.actors.Agashka._

  var businesses = Map.empty[String, ActorRef]

  override def receive: Receive = {
    case msg @ CreateBusiness(businessname: String) =>
      log.info(s"Creating business $businessname")
      val business: ActorRef = context.actorOf(Business.props(businessname), businessname)
      businesses += (businessname -> business)
      sender() ! OtvetAgashki(s"$businessname created")

    case msg @ DelteBusiness(businessname: String) =>
      log.info(s"Deleting business $businessname")
      businesses.foreach{ b =>
        if(b._1 == businessname) {
          b._2 ! PoisonPill
          businesses -= b._1
        }
      }
      sender() ! OtvetAgashki(s"$name deleted")

    case msg @ GetData(businessname: String) =>
      log.info(s"Getting data from business $businessname")
      businesses.foreach{ b =>
        if(b._1 == businessname)
          b._2 ! GetBusinessData
      }
      context.become(nextStage(sender))

    case msg @ AddRodstvenik(busineesName, rodstvenikName) =>
      log.info(s"Adding $rodstvenikName to business $busineesName")
      businesses.foreach{ b =>
        if(b._1 == busineesName) {
          b._2 ! AddRodstvenik(busineesName, rodstvenikName)
        }
      }
      context.become(nextStage(sender))
  }

  def nextStage(replyTo: ActorRef): Receive = {
    case  msg @ OtvetAgashki =>
      replyTo ! msg
  }
}
