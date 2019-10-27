package dci.example.mixin.mutation

import java.time.Instant

trait TransferMoneySource extends MoneySource {
  this: Account =>
  override def transferTo(amount: Long, recipient: MoneySink): Unit = {
    decreaseBalance(amount)
    updateLong("Transfer out", Instant.now(), amount)
    recipient.transferFrom(amount, this)
  }
}