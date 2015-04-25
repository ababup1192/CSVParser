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
  override val whiteSpace = "[ \t]+".r

  def eol = opt('\r') <~ '\n'

  def notQuotedCell = "[^\"\r\n,]*".r

  def quotedCell = "\"" ~> "[^\"]*".r <~ "\""

  def row = repsep(quotedCell | notQuotedCell, ",")

  def rows = repsep(row, eol) ^^ {
    case cellsList => {
      cellsList match {
        case Nil => Nil
        case hCells :: dCellsList =>
          val hRow = new HeaderRow(hCells)
          val dRows = dCellsList filter {
            // remove empty line
            case l => l.size != 1 || !l.head.trim.isEmpty
          } map {
            case dr => new DataRow(dr)
          }
          hRow :: dRows
      }
    }
  }

  def parse(input: String): ParseResult[List[Row]] = parseAll(rows, input)

}
