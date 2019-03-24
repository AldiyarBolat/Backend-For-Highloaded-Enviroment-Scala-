package lab6.boot

import akka.actor.{ActorRef, ActorSystem}
import lab6.domain.JsonSupport
import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.stream.ActorMaterializer
import akka.http.scaladsl.model._
import akka.http.scaladsl.server.Directives._
import akka.pattern.ask
import akka.util.Timeout
import spray.json.DefaultJsonProtocol._
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
import lab6.actors.Agashka
import lab6.actors.Agashka.{CreateBusiness, DelteBusiness, GetData}
import org.slf4j.LoggerFactory

import scala.concurrent.Future
import scala.concurrent.duration._

object Boot extends App with JsonSupport {

  val log = LoggerFactory.getLogger("Boot")
  implicit val system = ActorSystem()
  implicit val materializer = ActorMaterializer()
  implicit val executionContext = system.dispatcher
  implicit val timeout = Timeout(30.seconds)



  val aga = system.actorOf(Agashka.props("Bake-Sake-Aleke"), "Agaska1")

  val route = {
    pathPrefix("business") {
      (path("create") & post) {
        entity(as[CreateBusiness]) { createBusiness =>
          complete {
            (aga ? CreateBusiness(createBusiness.businessname)).mapTo[Agashka.OtvetAgashki]
          }
        }
      } ~
        (path("delete") & delete){
          entity(as[DelteBusiness]) { deleteBusiness =>
            complete {
              (aga ? DelteBusiness(deleteBusiness.businessname)).mapTo[Agashka.OtvetAgashki]
            }
          }
        } ~
          (path("info") & get){
            entity(as[GetData]) { getBusiness =>
              complete {
                (aga ? GetData(getBusiness.businessname)).mapTo[Agashka.OtvetAgashki]
              }
            }
          }
    }

  }

  val bindingFuture = Http().bindAndHandle(route, "localhost", 8080)
  log.info("Listening on port 8080...")

}
