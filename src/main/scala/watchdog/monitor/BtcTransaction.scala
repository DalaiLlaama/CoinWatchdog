package watchdog.monitor


import scala.collection.mutable.MutableList
import spray.json._
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport

/*
 * Maps on to JSON responses for bitcoin transaction data
 */
case class Input (
      var prev_addresses: List[String],
      var prev_position: Int = 0,
      var prev_type: String,
      var prev_value: Long = 0,
      var sequence: Long = 0
    )
    
case class Output (
      var addresses: List[String],
      var value: Long = 0,
      var typ: String = ""
    )
    
case class Transaction (
      var confirmations: Long = 0,
      var block_height: Long = 0,
      var block_time: Long = 0,
      var size: Long = 0,
      var inputs: List[Input], 
      var outputs: List[Output]
    )

case class Data (
    var total_count: Long = 0,
    var page: Int = 0,
    var pagesize: Int = 0,
    var list: List[Transaction]
)
    
case class BtcTransaction (
  val data: Data
)

object InputJsonProtocol extends DefaultJsonProtocol {
    implicit val inputFormat = jsonFormat5(Input)
}

object OutputJsonProtocol extends DefaultJsonProtocol {
    implicit val outputFormat = jsonFormat(Output, "addresses", "value", "type")
}

object TransactionJsonProtocol extends DefaultJsonProtocol with SprayJsonSupport {
    import InputJsonProtocol._
    import OutputJsonProtocol._
    implicit val transactionFormat: RootJsonFormat[Transaction] = jsonFormat6(Transaction)
}

object DataJsonProtocol extends DefaultJsonProtocol with SprayJsonSupport {
    import TransactionJsonProtocol._
    implicit val dataJsonFormat: RootJsonFormat[Data] = jsonFormat4(Data)
}

object BtcTransactionJsonProtocol extends DefaultJsonProtocol with SprayJsonSupport {
    import DataJsonProtocol._
    implicit val btcTransactionJsonFormat: RootJsonFormat[BtcTransaction] = jsonFormat1(BtcTransaction)
}
