package utilities

object Config {
  val PLAY_HOST = "localhost:9000"
  val REDIS_CONN_HOST = "localhost"
  val REDIS_CONN_PORT = 6379
  val REDIS_PREFIX_URL = "url:"
  val BUSINESS_EXPIRE_TIME: Long = 3600L * 7 * 14
}
