package main.scala
import watchdog.monitor.Bitcoin
import watchdog.monitor.BitcoinParser
import akka.actor._

object Main extends App {
  val actorSystem = ActorSystem("Bitcoin")
  val bitcoinReq = actorSystem.actorOf(Props[Bitcoin], name = "BitcoinReq")
    
 
  Thread.sleep(10000)
  actorSystem.terminate()
}