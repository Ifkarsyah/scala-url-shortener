package models

import play.api.libs.json.{Json, OFormat}

case class URL(shortURL: String, originalURL: String)

object URL {
  implicit val format: OFormat[URL] = Json.format[URL]
}
