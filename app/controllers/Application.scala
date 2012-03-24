package controllers

import play.api._
import play.api.mvc._
import org.nisshiee.mtghorde.model.NormalCard
import scalaz._
import Scalaz._
import org.nisshiee.mtghorde.model.Token
import scalax.io.Resource
import org.nisshiee.mtghorde.model.CardImage
import org.nisshiee.mtghorde.model.CardImage

object Application extends Controller {

  def index = Action { implicit req =>
    Ok(views.html.index("Your new application is ready." |+| session.get("test").getOrElse("nocookie"))).withSession("test" -> "testValue")
  }

  def insertNormal(name: String) = Action {
    val id = NormalCard.create(name)
    Ok("new card id: " |+| id.shows)
  }

  def insertToken(name: String) = Action {
    val id = Token.create(name)
    Ok("new card id: " |+| id.shows)
  }

  def replaceImage = Action(parse.multipartFormData) { req =>
    val result = for {
      file <- req.body.file("image")
      idStr <- req.body.dataParts.get("id") >>= { _.headOption }
      id <- idStr.parseLong.toOption
      contentType <- file.contentType
    } yield {
      val binary = Resource.fromFile(file.ref.file).byteArray
      CardImage.replace(id, contentType, binary) match {
        case CardImage.Insert => Ok("inserted")
        case CardImage.Update => Ok("updated")
        case CardImage.Error => BadRequest("error")
      }
    }

    result | BadRequest("bad")
  }

  def getImage(id: Long) = Action {
    CardImage.select(id) map { cardImage =>
      Ok(cardImage.binary)
        .as(cardImage.contentType)
        .withHeaders("Cache-Control" -> "public, max-age=3600")
    } getOrElse {
      Redirect(routes.Application.index)
    }
  }

}
