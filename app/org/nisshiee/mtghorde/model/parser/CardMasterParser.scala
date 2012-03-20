package org.nisshiee.mtghorde.model.parser

import anorm.SqlParser._

object CardMasterParser {
  val idParser = long("null.GENERATED_KEY").single
}
