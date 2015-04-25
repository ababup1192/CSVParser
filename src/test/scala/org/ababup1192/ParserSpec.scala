package org.ababup1192

import org.scalatest._

class ParserSpec extends FlatSpec with Matchers {
  "CSV parser" should "return Row List" in {
    val parseResult = CsvParser.parse(
      """"name","age","memo"
          "Andy","20","Skills - English,Chinese - Java,Scala"
          "Brian","22",""
      """)
    val lines = parseResult.get
    lines should be(
      List(HeaderRow(List("name", "age", "memo")),
        DataRow(List("Andy", "20", "Skills - English,Chinese - Java,Scala")),
        DataRow(List("Brian", "22", ""))))
  }

}

