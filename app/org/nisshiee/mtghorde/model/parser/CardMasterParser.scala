package org.nisshiee.mtghorde.model.parser

import anorm._
import SqlParser._
import org.nisshiee.mtghorde.model.NormalCard
import org.nisshiee.mtghorde.model.Token
import org.nisshiee.mtghorde.model.CardMaster

object CardMasterParser {
  val idParser = long("null.GENERATED_KEY").single

  val cardMasterRowParser: RowParser[CardMaster] =
    long("id") ~ int("card_type") ~ str("name") map {
      case id ~ 1 ~ name => NormalCard(id, name)
      case id ~ 2 ~ name => Token(id, name)
    }

  val cardMasterParser = cardMasterRowParser.singleOpt
}
