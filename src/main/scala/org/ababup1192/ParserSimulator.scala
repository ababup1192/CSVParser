package org.ababup1192

object ParserSimulator {
  def main(args: Array[String]): Unit = {
    val res = CsvParser.parse(
      """"name","age","memo"
          "Andy","20","Skills - English,Chinese - Java,Scala"
          "Brian","22",""
      """)
    res.get.foreach {
      println(_)
    }
  }

}