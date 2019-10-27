package dci.example.typeclass

import java.time.Instant

final class TransferMoneySourceOps(val self: Account) extends AnyVal {
  implicit def toSink(recipient: Account): TransferMoneySinkOps = new TransferMoneySinkOps(recipient)

  def transferTo(amount: Long, recipient: Account): (Account, Account) = {
    val newSource = self.decreaseBalance(amount)
    self.updateLong("Transfer out", Instant.now(), amount)
    (newSource, recipient.transferFrom(amount, self))
  }
}
