package utilities

class Base62Encoder {
  private val base10To62Dict = ((0 to 9) ++  ('a' to 'z') ++ ('A' to 'Z') ).mkString

  def encodeRawToBase10(raw: String): Long = raw.map(s => base10To62Dict.indexOf(s)).sum

  def encodeBase10To62(base10: Long): String = {
    @scala.annotation.tailrec
    def div(base10: Long, listResult: List[Int] = Nil): List[Int] = {
      base10 / 62 match {
        case rem if rem > 0 => div(rem, (base10 % 62).toInt :: listResult)
        case _ => base10.toInt :: listResult
      }
    }
    div(base10).map(x => base10To62Dict(x)).mkString
  }

  def decodeBase62To10(base62: String): Long = {
    base62.zip(base62.indices.reverse)
      .map { case (c, p) => base10To62Dict.indexOf(c) * scala.math.pow(62, p).toLong }
      .sum
  }
}
