package dci.example.typeclass

import java.time.Instant

trait Account {

  protected def createInstance(amount: Long): Account

  def availableBalance: Long

  def decreaseBalance(amount: Long): Account = {
    require(amount >= 0)
    createInstance(availableBalance - amount)
  }

  def increaseBalance(amount: Long): Account =  {
    require(amount >= 0)
    createInstance(availableBalance + amount)
  }

  def updateLong(msg: String, date: Instant, amount: Long): Unit =
    println(s"Account $toString, $msg, $date, $amount")
}
