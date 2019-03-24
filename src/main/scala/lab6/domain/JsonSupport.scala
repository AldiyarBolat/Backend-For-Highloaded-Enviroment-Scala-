package lab6.domain


import lab6.actors.Agashka
import spray.json.DefaultJsonProtocol._
import spray.json.RootJsonFormat

trait JsonSupport {
  implicit val libraryResponseFormat: RootJsonFormat[Agashka.OtvetAgashki] = jsonFormat1(Agashka.OtvetAgashki)
  implicit val f1: RootJsonFormat[Agashka.CreateBusiness] = jsonFormat1(Agashka.CreateBusiness)
  implicit val f2: RootJsonFormat[Agashka.DelteBusiness] = jsonFormat1(Agashka.DelteBusiness)
  implicit val f3: RootJsonFormat[Agashka.GetData] = jsonFormat1(Agashka.GetData)
  implicit val f4: RootJsonFormat[Agashka.AddRodstvenik] = jsonFormat1(Agashka.AddRodstvenik)

}