package org.nisshiee.mtghorde.model

import anorm._
import play.api.db._
import play.api.Play.current

import org.nisshiee.mtghorde.model.sql.CardMasterSql._
import org.nisshiee.mtghorde.model.parser.CardMasterParser._

sealed trait CardMaster
case class NormalCard(id: Long, name: String)
case class Token(id: Long, name: String)

object CardMaster {
  private[model] def create(name: String, sql: SimpleSql[Row]) =
    DB.withConnection { implicit c => sql on ("name" -> name) executeInsert idParser }
}

object NormalCard {
  def create(name: String) = CardMaster.create(name, insertNormalCardSql)
}

object Token {
  def create(name: String) = CardMaster.create(name, insertTokenSql)
}
