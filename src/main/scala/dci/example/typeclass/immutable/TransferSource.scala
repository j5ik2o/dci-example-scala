package dci.example.typeclass.immutable

trait TransferSource[A] {
  def transferTo(self: A)(amount: Long, recipient: CheckingAccount): (A, CheckingAccount)
}

object TransferSource {
  implicit class TransferSourceOps[A: TransferSource](val self: A) {
    def transferTo(amount: Long, recipient: CheckingAccount): (A, CheckingAccount) =
      implicitly[TransferSource[A]].transferTo(self)(amount, recipient)
  }
}
