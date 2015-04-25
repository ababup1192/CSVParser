package org.ababup1192

object ParserSimulator {
  def main(args: Array[String]): Unit = {
    val res = CsvParser.parse(
      """name,age,place
        John,17,NewYork
        Mike,23,Soul
      """)
    res.get.foreach {
      println(_)
    }
  }

}