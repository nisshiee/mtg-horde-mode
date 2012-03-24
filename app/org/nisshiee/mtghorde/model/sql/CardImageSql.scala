package org.nisshiee.mtghorde.model.sql

import anorm._

object CardImageSql {
  val replaceSql = SQL("""
REPLACE INTO card_image
    (id  , content_type , image_binary)
  VALUES
    ({id}, {contentType}, {imageBinary})
;
""")

  val selectSql = SQL("""
SELECT id, content_type, image_binary
  FROM card_image
  WHERE
    id = {id}
;
""")
}
