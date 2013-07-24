package controllers.leases

import play.api.mvc.{Action, AsyncResult, Controller}
import models.Lease
import scala.concurrent.{ExecutionContext, Future}
import ExecutionContext.Implicits.global

object LeaseController extends Controller {

  def getByAccount(accountId: Int) = Action {
    implicit request =>
      val futureLeases = Future { Lease.byAccount(accountId) }
      Async {
        futureLeases.map(l => Ok("Found leases: " + l.size))
      }
  }

}