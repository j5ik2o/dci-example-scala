package dci.example.mixin.mutation

class SavingsAccount extends Account {
  override def toString: String = "Savings"
}

class CheckingAccount extends Account {
  override def toString: String = "Check"
}

// Context
object Main extends App {
  val source = new SavingsAccount with TransferMoneySource
  val sink = new CheckingAccount with TransferMoneySink
  source.increaseBalance(100000)
  source.transferTo(200, sink)
  println(source.availableBalance + ", " + sink.availableBalance)

}