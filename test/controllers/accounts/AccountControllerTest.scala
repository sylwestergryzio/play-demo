package controllers.accounts

import org.scalatest._
import org.scalatest.matchers.ShouldMatchers
import play.api.test.FakeApplication
import play.api.test.Helpers._
import play.api.test.FakeRequest
import play.api.test.TestBrowser

class AccountControllerTest 
  extends FunSuite 
  with ShouldMatchers {

  test("Should return a list of accounts") {
    running(FakeApplication()) {
      val result = route(FakeRequest(GET, "/accounts"))
      result should not be (None)
      status(result.get) should be(OK)
      contentAsString(result.get) should include("John").and(include("Doe"))
    }
  }

  test("Should get a list of all accounts from remote server") {
    val browser = TestBrowser.default(Some("http://localhost:9000/"))
    browser.goTo("http://localhost:9000/accounts")
    browser.await()
    val source = browser.pageSource
    source should include("John")
  }
}