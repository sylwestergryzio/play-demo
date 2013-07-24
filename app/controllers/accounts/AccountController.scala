package controllers.accounts

import models.Account
import play.api.mvc._
import play.api._
import play.api.data.Form
import play.api.data.Forms._
import play.libs.WS
import play.libs.F.Promise

object AccountController extends Controller {

  val accountForm = Form[Account](
      mapping(
        "id" -> optional(number),
        "firstName" -> text,
        "lastName" -> nonEmptyText,
        "status" -> number
      )((id, fn, ln, s) => Account(id.getOrElse(0), fn, ln, s) )
        ((acc: Account) => Some(Option(acc.id), acc.firstName, acc.lastName, acc.status))
      // (Account.apply)(Account.unapply)
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

  def validateAccount(id: Int) = TODO

  def newAccount() = Action {
    implicit request =>
      val form = accountForm.fill(new Account(0, "", "", 1))
      Ok(views.html.accounts.create(form))
  }

  def createAccount() = Action {
    implicit request =>
      accountForm.bindFromRequest.fold (
        errors => BadRequest(views.html.accounts.create(errors)),
        account => {
          Account.create(account)
          Redirect(controllers.accounts.routes.AccountController.all())
        }
      )
  }

  /*def getLeases(accountId: Int) = Action {
    implicit request =>
      val getResponsePromise: Promise[WS.Response] = WS.url("http://localhost:9000/leases/account/" + accountId).get()
      val responsePromise = getResponsePromise.getWrappedPromise
      val resp = responsePromise.map(r => {
        if(r.getStatus == 200) {
          Ok(r.getBody)
        }
      })

      Ok("")
  } */
}