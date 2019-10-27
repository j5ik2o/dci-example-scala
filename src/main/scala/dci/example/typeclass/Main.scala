package dci.example.typeclass

class SavingsAccount(value: Long) extends Account {
  override def toString: String = "Savings"
  override def availableBalance: Long = value
  override protected def createInstance(amount: Long): Account = new SavingsAccount(amount)
}

class CheckingAccount(value: Long) extends Account {
  override def toString: String = "Check"
  override def availableBalance: Long = value
  override protected def createInstance(amount: Long): Account =  new CheckingAccount(amount)
}

object Main extends App {

  implicit def toSource(self: Account): TransferMoneySourceOps = new TransferMoneySourceOps(self)

  val source0 = new SavingsAccount(0)
  val sink0 = new CheckingAccount(0)
  val source1 = source0.increaseBalance(100000)
  val (source2, sink1) = source1.transferTo(200, sink0)
  println(source2.availableBalance + ", " + sink1.availableBalance)

}
