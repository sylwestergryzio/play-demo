package models

import anorm._
import anorm.SqlParser._
import play.api.db._
import play.api.Play.current

case class Account(id: Int,
				   firstName: String, 
				   lastName: String,
				   status: Int = 0) {
  
  def statusName =
    status match {
      case 0 => "Złoty"
      case 1 => "Srebrny"
      case 2 => "Bronzowy"
    }
  
}

object Account {
  
  val account = {
    get[Int]("id") ~
    get[String]("first_name") ~
    get[String]("last_name") ~ 
    get[Int]("status") map {
      case id~first_name~last_name~status => Account(id, first_name, last_name, status)
    }
  }
  
  def all: List[Account] = DB.withConnection {
    implicit c => 
      SQL("select * from accounts").as(account *)
  }
  
  def account(id: Int): Option[Account] = DB.withConnection {
    implicit c =>
      val res: List[Account] = SQL("select * from accounts where id = {id}").on('id -> id).as(account *)
      res.headOption
  } 
  
  def update(account: Account) = DB.withConnection {
    implicit c =>
      val res = SQL(
          """
    		  update accounts set first_name = {fn}, last_name = {ln}, status = {s} 
    		  where id = {id}
          """).on('fn -> account.firstName, 'ln -> account.lastName, 's -> account.status, 'id -> account.id).executeUpdate()
  }
}
				  