package repositories

import com.redis.RedisClient

class URLRepository {
  val dbConnection = new RedisClient("localhost", 6379)

  def getLongURL(hashURL: String): Option[String] =  {
    dbConnection.get(hashURL)
  }

  def saveURL(hashURL: String, originalURL: String): Boolean = {
    dbConnection.set(hashURL, Map("originalURL" -> originalURL))
  }
}

