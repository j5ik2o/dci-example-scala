package dci.example.mixin.mutation

trait MoneySink {
  def transferFrom(amount: Long, src: MoneySource): Unit
}
