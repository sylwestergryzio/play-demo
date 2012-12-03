package controllers.accounts

import models.Account
import play.api.mvc._
import play.api._
import play.api.data.Form
import play.api.data.Forms._
import play.libs.WS

object AccountController extends Controller {

  val accountForm = Form[Account](
      mapping(
        "id" -> number,
        "firstName" -> text,
        "lastName" -> nonEmptyText,
        "status" -> number
      )(Account.apply)(Account.unapply)
  )
  
  def all = Action {
    implicit request =>
      Ok(views.html.accounts.list(accounts = Account.all))
  }
  
  def account(id: Int) = Action {
    implicit request =>
      Account.account(id) match {
        case Some(account) =>
          val form = accountForm.fill(account)
          Ok(views.html.accounts.edit(form))
        case None =>
          NotFound("Klient nie został znaleziony")
      }
  }
  
  def updateAccount = Action {
    implicit request =>
      accountForm.bindFromRequest.fold (
        errors => BadRequest(views.html.accounts.edit(errors)),
        account => {
          Account.update(account)
          Redirect(controllers.accounts.routes.AccountController.account(account.id))
        }
      )
  }

  def validateAccount(lastName: String) = TODO
}