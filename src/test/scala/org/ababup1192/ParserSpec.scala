package org.ababup1192

import org.ababup1192.ParserSimulator.LinesParser
import org.scalatest._

class ParserSpec extends FlatSpec with Matchers {
  "Lines parser" should "return lines List" in {
    val parseResult = LinesParser.parse(
      """name,age,place
        John,17,NewYork
        Mike,23,Soul
      """)
    val lines = parseResult.get
    lines should be(List("name,age,place", "John,17,NewYork", "Mike,23,Soul"))
  }

}

