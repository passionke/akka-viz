package akkaviz.events

import akka.actor._
import akkaviz.events.types.EventActorRef

import scala.util.Try

object FilteringRule {

  def isUserActor(actorRef: ActorRef): Boolean = actorRef.path.elements.headOption.contains("user")
}

case class AllowedClasses(names: Set[String]) extends (Any => Boolean) {
  @transient private val allowedClasses = names.flatMap { name =>
    val loaded: Try[Class[_]] = Try(getClass.getClassLoader.loadClass(name))
    loaded.failed.foreach(thr => println(s"class loading failed: $thr"))
    loaded.toOption
  }

  override def apply(payload: Any): Boolean = {
    allowedClasses.exists(c => c.isAssignableFrom(payload.getClass))
  }
}

case class ActorRefFilter(actors: Set[String]) extends (EventActorRef => Boolean) {
  override def apply(v1: EventActorRef): Boolean = {
    actors(v1.path)
  }
}