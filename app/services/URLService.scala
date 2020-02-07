package services

import java.text.SimpleDateFormat

import javax.inject.{Inject, Singleton}
import play.api.libs.json.{JsValue, Json}
import repositories.RedisURLRepository
import utilities.{Base62Encoder, Config}

@Singleton
class URLService @Inject()(ur: RedisURLRepository, be: Base62Encoder) {

  /**
   *
   * @param longURL ex: "https://blog.softwaremill.com/is-your-scala-object-always-a-singleton-cb3fd24a2fd9"
   * @return
   */
  def shorten(longURL: String): Option[JsValue] = {
    val integerURL: Long = be.encodeRawToBase10(longURL)
    val shortURL: String = be.encodeBase10To62(integerURL)

    val currentTimestamp = System.currentTimeMillis / 1000
    val urlInfo = Json.obj(
      "shortURL" -> shortURL,
      "createdAt" -> currentTimestamp,
      "expiredAt" -> (currentTimestamp + Config.BUSINESS_EXPIRE_TIME)
    )

    val repoResponse = ur.saveURL(shortURL, Json.stringify(urlInfo))
    if (repoResponse) Some(jsonTsToJsonDate(urlInfo)) else None
  }

  private def jsonTsToJsonDate(jsValue: JsValue): JsValue = {
    Json.obj(
      "shortURL" -> (Config.PLAY_HOST + "/go/" + (jsValue \ "shortURL").as[String]),
      "createdAt" -> tsToDate((jsValue \ "createdAt").as[Long]),
      "expiredAt" -> tsToDate((jsValue \ "expiredAt").as[Long])
    )
  }

  private def tsToDate(ts: Long): String = {
    val df = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss zzzz")
    df.format(ts * 1000)
  }

  /**
   *
   * @param shortURL if (url == "http://localhost:9000/go/nI"), base62URL is "nI"
   * @return
   */
  def getOriginal(shortURL: String): Option[JsValue] = {
    ur.getURLInfo(shortURL) match {
      case None => Some(Json.obj(
        "error" -> "Your URL is not in our database"
      ))
      case Some(jsonString) =>
        val jsValue = Json.parse(jsonString)
        Some(jsonTsToJsonDate(jsValue))
    }
  }
}
