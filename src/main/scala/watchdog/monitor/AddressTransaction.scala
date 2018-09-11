package watchdog.monitor

/**
 * Transaction for a single address
 */
case class AddressTransaction (
  var address: String,
  var network: String,
  var block: Long,
  var transaction: Long,
  var value: Long
)