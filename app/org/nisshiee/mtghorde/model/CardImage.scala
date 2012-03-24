package org.nisshiee.mtghorde.model

import play.api.db._
import play.api.Play.current
import scalaz._
import Scalaz._
import org.nisshiee.mtghorde.model.sql.CardImageSql._
import org.nisshiee.mtghorde.model.parser.CardImageParser._
import play.api.Logger

case class CardImage(id: Long, contentType: String, binary: Array[Byte])

object CardImage {

  sealed trait ReplaceResult
  case object Insert extends ReplaceResult
  case object Update extends ReplaceResult
  case object Error extends ReplaceResult

  def int2ReplaceResult: Int => ReplaceResult = {
    case 1 => Insert
    case 2 => Update
    case _ => Error
  }

  def replace(id: Long, contentType: String, binary: Array[Byte]) =
    DB.withConnection { implicit c =>
      (replaceSql
        .on(
          "id" -> id,
          "contentType" -> contentType,
          "imageBinary" -> binary)
        .executeUpdate()
        |> int2ReplaceResult)
    }

  def select(id: Long) = {
    Logger.debug("CardImage.select called")
    DB.withConnection { implicit c =>
      selectSql
        .on("id" -> id)
        .as(cardImageParser)
    }
  }
}
