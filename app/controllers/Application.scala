package controllers

import play.api._
import play.api.mvc._

object Application extends Controller {
  
  def index = Action {
    Redirect(controllers.accounts.routes.AccountController.all)
  }  
}