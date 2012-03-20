package controllers

import play.api._
import play.api.mvc._
import org.nisshiee.mtghorde.model.NormalCard
import scalaz._
import Scalaz._
import org.nisshiee.mtghorde.model.Token

object Application extends Controller {

  def index = Action {
    Ok(views.html.index("Your new application is ready."))
  }

  def insertNormal(name: String) = Action {
    val id = NormalCard.create(name)
    Ok("new card id: " |+| id.shows)
  }

  def insertToken(name: String) = Action {
    val id = Token.create(name)
    Ok("new card id: " |+| id.shows)
  }

}
