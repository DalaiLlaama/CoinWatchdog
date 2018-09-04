package main.scala
import watchdog.monitor.Bitcoin
import akka.actor._

object Main extends App {
  val actorSystem = ActorSystem("Bitcoin")
  val bitcoin = actorSystem.actorOf(Props[Bitcoin], name = "Bitcoin")
 
  Thread.sleep(10000)
  actorSystem.terminate()
}