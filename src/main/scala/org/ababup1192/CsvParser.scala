package org.ababup1192

import scala.util.parsing.combinator.RegexParsers

abstract class Row

case class HeaderRow(cells: List[String]) extends Row

case class DataRow(cells: List[String]) extends Row

object CsvParser extends RegexParsers {
  def eol = opt('\r') <~ '\n'

  def line = ".*".r <~ eol

  def headerRow = line ^^ { row => new HeaderRow(row.split(",").toList) }

  def dataRow = line ^^ { row => new DataRow(row.split(",").toList) }

  def all = headerRow ~ rep(dataRow)

  def parse(input: String): ParseResult[HeaderRow ~ List[DataRow]] = parseAll(all, input)

}
