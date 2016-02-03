package akka.viz.serialization.serializers

import akka.actor.ActorRef
import akka.viz.serialization.AkkaVizSerializer
import upickle.Js

case object ActorRefSerializer extends AkkaVizSerializer {
  override def serialize(obj: Any): Js.Value = {
    obj match {
      case a: ActorRef =>
        Js.Obj("path" -> Js.Str(a.path.toSerializationFormat))
    }
  }

  override def canSerialize(obj: Any): Boolean = obj match {
    case t: ActorRef => true
    case _ => false
  }
}


