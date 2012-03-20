package org.nisshiee.mtghorde.model.parser

import anorm._
import SqlParser._
import scalaz._
import Scalaz._
import org.nisshiee.mtghorde.model.CardImage

object CardImageParser {
  implicit lazy val blobColumn = Column {
    case (data: Array[Byte], meta: MetaDataItem) => data.right[SqlRequestError]
  }

  def blob(columnName: String) = get[Array[Byte]](columnName)

  lazy val cardImageRowParser =
    long("id") ~ str("content_type") ~ blob("image_binary") map {
      case id ~ contentType ~ binary => CardImage(id, contentType, binary)
    }

  lazy val cardImageParser = cardImageRowParser.singleOpt
}
