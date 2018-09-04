package main.scala.watchdog.monitor

import scala.concurrent.Future
import scala.util.{ Failure, Success }
import akka.actor.ActorSystem
import akka.actor.Actor
import akka.http.scaladsl.Http
import akka.http.scaladsl.model._
import akka.stream.{ ActorMaterializer, ActorMaterializerSettings }
import akka.util.ByteString

class Bitcoin extends Actor {
  
  import akka.pattern.pipe
  import context.dispatcher
  
  val endpoint = "https://chain.api.btc.com/v3/block/latest/tx"
  
  implicit val materializer: ActorMaterializer = ActorMaterializer(ActorMaterializerSettings(context.system))
  
  val http = Http(context.system)
  
  private case class Example(var1: Int, var2: String)
  
  private def createRequest(): HttpRequest = 
    HttpRequest(
        //method = HttpMethod..HttpMethods.GET,
        uri = endpoint,
        //entity = HttpEntity(ContentTypes.`application/json`, example.toJson.toString),

        //headers = Seq()
    )
    
    
  override def preStart() = {
    http.singleRequest(createRequest())
      .pipeTo(self)
  }
    
  def receive = {
    case HttpResponse(StatusCodes.OK, headers, entity, _) => {
          println("Response OK")
          println(entity.toString())
          entity.dataBytes.runFold(ByteString(""))(_ ++ _).foreach (body => println(body.utf8String))
        
        }
    case resp @ HttpResponse(code, _, _, _) => {
        println("Error response " ++ code.toString())
        resp.discardEntityBytes()
    }
  }
}