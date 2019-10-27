package dci.example.mixin.mutation

import java.time.Instant

trait Account {
  protected var balance: Long = 0

  def availableBalance: Long = balance

  // Unitを返すのは避けたい
  def decreaseBalance(amount: Long): Unit = {
    require(amount >= 0)
    balance -= amount
  }

  // Unitを返すのは避けたい
  def increaseBalance(amount: Long): Unit = {
    require(amount >= 0)
    balance += amount
  }

  // ↓ こういうのもドメインの外側でやるべきでしょう
  def updateLong(msg: String, date: Instant, amount: Long): Unit =
    println(s"Account $toString, $msg, $date, $amount")
}
