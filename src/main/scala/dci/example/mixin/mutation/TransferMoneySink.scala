package dci.example.mixin.mutation

import java.time.Instant

trait TransferMoneySink  extends MoneySink { this: Account =>
  def transferFrom(amount: Long, src: MoneySource): Unit = {
    increaseBalance(amount)
    updateLong("Transfer in", Instant.now(), amount)
  }
}
