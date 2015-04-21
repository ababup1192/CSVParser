package org.ababup1192

import scala.util.parsing.combinator.RegexParsers

object ParserSimulator {
  def main(args: Array[String]): Unit = {
    val res = NameParser.parse("Martin Odersky")
    println(res)
    println(res.map {
      case Name(first, Some(middle), last) => s"$first $middle $last"
      case Name(first, None, last) => s"$first $last"
    }.getOrElse("NoName"))
  }
}

case class Name(firstName: String, middleName: Option[String], lastName: String)

object NameParser extends RegexParsers {
  def name: Parser[String] = "[a-zA-Z]+".r

  def fullName: Parser[Name] = rep(name) ^^ {
    /* リストの長さが4以上のものは値を捨てつつミドルネームアリ判定 */
    case names@List(first, middle, last, _*) => Name(first, Some(middle), last)
    case List(first, last) => Name(first, None, last)
  }

  def parse(input: String) = parseAll(fullName, input)
}
