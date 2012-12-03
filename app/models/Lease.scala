package models

import anorm._
import anorm.SqlParser._
import play.api.db._
import play.api.Play.current

case class Lease(id: Int,
				 product: Int,
				 account: Account,
				 installments: Int)
				 
object Lease {
  val lease = {
    get[Int]("id") ~
    get[Int]("product") ~
    get[Int]("account") ~ 
    get[Int]("installments") map {
      case id~product~account~installments => 
        Lease(id, product, Account.account(account).get, installments)
    }
  }
  
  def all: List[Lease] = DB.withConnection {
    implicit c => 
      SQL("select * from leases").as(lease *)
  }
  
  def product(id: Int): Option[Lease] = DB.withConnection {
    implicit c =>
      val res: List[Lease] = SQL("select * from leases where id = {id}").on('id -> id).as(lease *)
      res.headOption
  } 
  
  def byAccount(accountId: Int): List[Lease] = DB.withConnection {
    implicit c =>
      SQL("select * from leases where account = {account_id}").on('account_id -> accountId).as(lease *)
  } 
  
  def byProduct(accountId: Int): Option[Lease] = DB.withConnection {
    implicit c =>
      val res: List[Lease] = SQL("select * from leases where account = {account_id}").on('account_id -> accountId).as(lease *)
      res.headOption
  } 
  
}				 