package repositories

trait URLRepository {
  def saveURL(base62URL: String, urlInfo: String): Boolean

  def getURLInfo(base62URL: String): Option[String]
}
