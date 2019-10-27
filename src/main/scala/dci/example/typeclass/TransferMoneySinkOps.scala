package dci.example.typeclass

import java.time.Instant

final class TransferMoneySinkOps(val self: Account) extends AnyVal {
  def transferFrom(amount: Long, src: Account): Account = {
    val result = self.increaseBalance(amount)
    self.updateLong("Transfer in", Instant.now(), amount)
    result
  }
}
