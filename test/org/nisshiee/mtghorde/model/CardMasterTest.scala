package org.nisshiee.mtghorde.model

import org.specs2._
import play.api.test._
import Helpers._

class CardMasterTest extends Specification {
  def is =
    """The test of CardMaster model""" ^
      p ^
      """create normal card""" ! e01 ^
      end

  def e01 = running(FakeApplication()) {
    NormalCard.create("test card")
  } must be_>(0l)

}

