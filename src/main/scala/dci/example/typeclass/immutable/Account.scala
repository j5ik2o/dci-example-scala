package dci.example.typeclass.immutable

import java.time.Instant

trait Account {

  type This <: Account

  protected def createInstance(amount: Long): This

  def availableBalance: Long

  def decreaseBalance(amount: Long): This = {
    require(amount >= 0)
    createInstance(availableBalance - amount)
  }

  def increaseBalance(amount: Long): This =  {
    require(amount >= 0)
    createInstance(availableBalance + amount)
  }

  def updateLong(msg: String, date: Instant, amount: Long): Unit =
    println(s"Account $toString, $msg, $date, $amount")
}
