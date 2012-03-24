package org.nisshiee.mtghorde.model.sql

import anorm._

object CardMasterSql {
  private val insertCardMasterSql = SQL("""
INSERT INTO card_master
    (name  , card_type)
  VALUES
    ({name}, {card_type})
;
""")

  val insertNormalCardSql = insertCardMasterSql.on("card_type" -> 1)

  val insertTokenSql = insertCardMasterSql.on("card_type" -> 2)
  
  val selectSql = SQL("""
SELECT
    id, card_type, name
  FROM
    card_master
  WHERE
    id = {id}
;
""")
}
