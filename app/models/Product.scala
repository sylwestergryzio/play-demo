package models

import anorm._
import anorm.SqlParser._
import play.api.db._
import play.api.Play.current

case class Product(id: Int, 
				   name: String, 
				   productType: String, 
				   price: Double)

object Product {
  val product = {
    get[Int]("id") ~
    get[String]("name") ~
    get[String]("product_type") ~ 
    get[Double]("price") map {
      case id~name~product_type~price => Product(id, name, product_type, price)
    }
  }
  
  def all: List[Product] = DB.withConnection {
    implicit c => 
      SQL("select * from products").as(product *)
  }
  
  def product(id: Int): Option[Product] = DB.withConnection {
    implicit c =>
      val res: List[Product] = SQL("select * from products where id = {id}").on('id -> id).as(product *)
      res.headOption
  } 
}