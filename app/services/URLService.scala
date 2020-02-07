package services

import javax.inject.{Inject, Singleton}
import repositories.URLRepository
import utilities.Base62Encoder

@Singleton
class URLService @Inject()(urlRepository: URLRepository, base62Encoder: Base62Encoder) {

  def shorten(longURL: String): Option[String] = {
    val integerURL: Long = base62Encoder.encodeRawToBase10(longURL)
    val hashURL: String = base62Encoder.encodeBase10To62(integerURL)

    val repoResponse = urlRepository.saveURL(hashURL, longURL)
    if (repoResponse) Some(hashURL) else None
  }

  def getOriginal(shortURL: String): Option[String] = {
    println(shortURL)
    urlRepository.getLongURL(shortURL)
  }
}
