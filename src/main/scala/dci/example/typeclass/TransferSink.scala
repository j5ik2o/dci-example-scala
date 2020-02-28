package dci.example.typeclass

trait TransferSink[A] {
  def transferFrom(self: A)(amount: Long, src: Account): A
}

object TransferSink {
  implicit class TransferSinkOps[A: TransferSink](val self: A) {
    def transferFrom(amount: Long, src: Account): A =
      implicitly[TransferSink[A]].transferFrom(self)(amount, src)
  }
}
