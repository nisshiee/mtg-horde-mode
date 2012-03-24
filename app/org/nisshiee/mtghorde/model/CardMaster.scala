package org.nisshiee.mtghorde.model

import anorm._
import play.api.db._
import play.api.Play.current

import org.nisshiee.mtghorde.model.sql.CardMasterSql._
import org.nisshiee.mtghorde.model.parser.CardMasterParser._

sealed trait CardMaster {
  def id: Long
  def name: String
}
case class NormalCard(id: Long, name: String) extends CardMaster
case class Token(id: Long, name: String) extends CardMaster

object CardMaster {
  private[model] def insert(name: String, sql: SimpleSql[Row]) =
    DB.withConnection { implicit c => sql on ("name" -> name) executeInsert idParser }

  def select(id: Long) =
    DB.withConnection { implicit c => selectSql on ("id" -> id) as cardMasterParser }
}

object NormalCard {
  def create(name: String) = CardMaster.insert(name, insertNormalCardSql)
}

object Token {
  def create(name: String) = CardMaster.insert(name, insertTokenSql)
}
