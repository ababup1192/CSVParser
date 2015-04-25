package org.ababup1192

import org.scalatest._

class ParserSpec extends FlatSpec with Matchers{
  "CSV parser" should "return Row List" in {
    val parseResult = CsvParser.parse(
      """name,age,place
        John,17,NewYork
        Mike,23,Soul
      """)
    val lines = parseResult.get
    lines should be(
      List(HeaderRow(List("name", "age", "place")), DataRow(List("John", "17", "NewYork")), DataRow(List("Mike", "23", "Soul"))))
  }

}

