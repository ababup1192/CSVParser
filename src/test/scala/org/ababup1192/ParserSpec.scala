package org.ababup1192

import org.scalatest._

class ParserSpec extends FlatSpec with Matchers {
  "Martin Odersky" should "has FirstName and Last name" in {
    val parseResult = NameParser.parse("Martin Odersky")
    val name = parseResult.get
    name should be(Name("Martin", None, "Odersky"))
  }

 "John F Kennedy" should "has FirstName, Middle name and Last name" in {
    val parseResult = NameParser.parse("John F Kennedy")
    val name = parseResult.get
    name should be(Name("John", Some("F"), "Kennedy"))
  }

}

