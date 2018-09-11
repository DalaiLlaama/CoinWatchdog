package watchdog.monitor

import scala.concurrent.Future
import scala.util.{ Failure, Success }
import akka.actor.ActorSystem
import akka.actor.Actor
import akka.http.scaladsl.model._
import akka.stream.{ ActorMaterializer, ActorMaterializerSettings }
import akka.util.ByteString
import spray.json._
import DefaultJsonProtocol._


class BitcoinParser extends Actor {
  
  import akka.pattern.pipe
  import context.dispatcher
  
  implicit val materializer: ActorMaterializer = ActorMaterializer(ActorMaterializerSettings(context.system))
    
  override def preStart() = {
  }
    
  def receive = {
    case msg: ByteString => {
          println("Parser recvd msg")
          // TODO: Parse it
      	  import BtcTransactionJsonProtocol._
      		val jsonAst = msg.utf8String.parseJson
      		val tx: BtcTransaction = jsonAst.convertTo[BtcTransaction]
      		println("txcount: " + tx.data.total_count.toString())
      		val tx0: Transaction = tx.data.list.head
      		println("tx0 confs: " + tx0.confirmations)
      	  val outAddr1 = tx0.outputs.head.addresses.head
      	  println("first out address: " + outAddr1) 
        
        }
    case _ => {
        println("Unknown message received ")
    }
  }
}