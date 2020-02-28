package dci.example.typeclass.immutable

import java.time.Instant

class CheckingAccount(value: Long) extends Account {
  override type This = CheckingAccount
  override def toString: String = "Check"
  override def availableBalance: Long = value
  override protected def createInstance(amount: Long): CheckingAccount =  new CheckingAccount(amount)
}

object CheckingAccount {
  implicit object TransferSink extends TransferSink[CheckingAccount] {
    override def transferFrom(self: CheckingAccount)(amount: Long, src: Account): CheckingAccount = {
      val result = self.increaseBalance(amount)
      self.updateLong("Transfer in", Instant.now(), amount)
      result
    }
  }
}

class SavingsAccount(value: Long) extends Account {
  override type This = SavingsAccount
  override def toString: String = "Savings"
  override def availableBalance: Long = value
  override protected def createInstance(amount: Long): SavingsAccount = new SavingsAccount(amount)
}

object SavingsAccount {
  implicit object TransferSource extends TransferSource[SavingsAccount]  {
    override def transferTo(self: SavingsAccount)(amount: Long, recipient: CheckingAccount): (SavingsAccount, CheckingAccount) = {
      val newSource = self.decreaseBalance(amount)
      self.updateLong("Transfer out", Instant.now(), amount)
      // CheckingAccountにRoleをバインドする
      import TransferSink._
      val result: CheckingAccount = recipient.transferFrom(amount, self)
      (newSource, result)
    }
  }
}

object Main extends App {

  val source0: SavingsAccount = new SavingsAccount(0)
  val sink0: CheckingAccount = new CheckingAccount(0)
  val source1: SavingsAccount = source0.increaseBalance(100000)

  // SavingsAccountにRoleをバインドする
  import TransferSource._
  val (source2, sink1) = source1.transferTo(200, sink0)
  println(source2.availableBalance + ", " + sink1.availableBalance)

}
