package lab5

import akka.actor.{ Actor, ActorLogging, Props }

object Device {
  def props(groupId: String, deviceId: String): Props = Props(new Device(groupId, deviceId))

  final case class RecordTemperature(requestId: Long, value: Double)
  final case class TemperatureRecorded(requestId: Long)

  final case class ReadTemperature(requestId: Long)
  final case class RespondTemperature(requestId: Long, value: Option[Double])
}

class Device(groupId: String, deviceId: String) extends Actor with ActorLogging {
  import Device._
  var lastTemperatureReading: Option[Double] = None

  override def preStart(): Unit = log.info("Device actor {}-{} started", groupId, deviceId)
  override def postStop(): Unit = log.info("Device actor {}-{} stopped", groupId, deviceId)

  override def receive: Receive = {
    case RecordTemperature(id, value) ⇒
      log.info("Recorded temperature reading {} with {}", value, id)
      lastTemperatureReading = Some(value)
      sender() ! TemperatureRecorded(id)

    case ReadTemperature(id) ⇒
      sender() ! RespondTemperature(id, lastTemperatureReading)
  }
}


/*
"reply with latest temperature reading" in {
  val probe = TestProbe()
  val deviceActor = system.actorOf(Device.props("group", "device"))

  deviceActor.tell(Device.RecordTemperature(requestId = 1, 24.0), probe.ref)
  probe.expectMsg(Device.TemperatureRecorded(requestId = 1))

  deviceActor.tell(Device.ReadTemperature(requestId = 2), probe.ref)
  val response1 = probe.expectMsgType[Device.RespondTemperature]
  response1.requestId should ===(2L)
  response1.value should ===(Some(24.0))

  deviceActor.tell(Device.RecordTemperature(requestId = 3, 55.0), probe.ref)
  probe.expectMsg(Device.TemperatureRecorded(requestId = 3))

  deviceActor.tell(Device.ReadTemperature(requestId = 4), probe.ref)
  val response2 = probe.expectMsgType[Device.RespondTemperature]
  response2.requestId should ===(4L)
  response2.value should ===(Some(55.0))
}*/