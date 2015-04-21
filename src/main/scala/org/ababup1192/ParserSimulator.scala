package org.ababup1192

import scala.util.parsing.combinator.RegexParsers

object ParserSimulator {
  def main(args: Array[String]): Unit = {
    val res = LinesParser.parse(
      """name,age,place
        John,17,NewYork
        Mike,23,Soul
      """)
    println(res)
  }


  object LinesParser extends RegexParsers {
    def eol = opt('\r') <~ '\n'

    def line = ".*".r <~ eol

    def lines = rep(line)

    def parse(input: String): ParseResult[List[String]] = parseAll(lines, input)

  }

}