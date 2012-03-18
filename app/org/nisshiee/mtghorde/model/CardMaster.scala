package org.nisshiee.mtghorde.model

sealed trait CardMaster
case class NormalCard(id: Long, name: String)
case class Token(id: Long, name: String)
