package dci.example.mixin.mutation

trait MoneySource {
  def transferTo(amount: Long, recipient: MoneySink): Unit
}
