package repositories


import com.redis.RedisClient
import javax.inject.Singleton
import utilities.Config

@Singleton
class RedisURLRepository extends URLRepository {

  val dbConnection = new RedisClient(Config.REDIS_CONN_HOST, Config.REDIS_CONN_PORT)

  override def saveURL(base62URL: String, urlInfo: String): Boolean = {
    val key = Config.REDIS_PREFIX_URL + base62URL
    dbConnection.set(key, urlInfo)
  }

  override def getURLInfo(base62URL: String): Option[String] = {
    dbConnection.get(Config.REDIS_PREFIX_URL + base62URL)
  }
}

