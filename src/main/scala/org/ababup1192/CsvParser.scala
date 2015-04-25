package org.ababup1192

import scala.util.parsing.combinator.RegexParsers

abstract class Row

case class HeaderRow(cells: List[String]) extends Row {
  override def toString: String = {
    s"Header: $cells"
  }
}

case class DataRow(cells: List[String]) extends Row {
  override def toString: String = {
    s"Data: $cells"
  }
}

object CsvParser extends RegexParsers {
  def eol = opt('\r') <~ '\n'

  def cell = "\"" ~> "[^\"]*".r <~ "\""

  def row = repsep(cell, ",") <~ eol

  def headerRow = row ^^ { cells => new HeaderRow(cells) }

  def dataRow = row ^^ { cells => new DataRow(cells) }

  def all = headerRow ~ rep(dataRow) ^^ { res => res._1 :: res._2 }

  def parse(input: String): ParseResult[List[Row]] = parseAll(all, input)

}
