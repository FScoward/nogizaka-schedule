package util

import com.typesafe.config.ConfigFactory
import com.typesafe.scalalogging.LazyLogging
import play.api.libs.ws.ning.NingWSClient

import scala.concurrent.Await
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration

case class UrlShortnerResult(kind: String, id: String, longUrl: String)

/**
  * Created by Fumiyasu on 2016/01/24.
  */
object GoogleUrlShortner extends LazyLogging {

  def shortner(targetUrl: String): UrlShortnerResult = {
    val ws = NingWSClient()
    val conf = ConfigFactory.load
    val urlShortnerKey = conf.getString("api_key.url_shortner")

    val url = "https://www.googleapis.com/urlshortener/v1/url"
    val result = ws.url(url)
      .withHeaders(("Content-Type", "application/json"))
      .withQueryString(("key", urlShortnerKey))
      .post(s"""{"longUrl": "$targetUrl"}""")
      .map(response => {
        logger.info(s"${response.body}")
        val json = response.json
        UrlShortnerResult((json \ "kind").as[String], (json \ "id").as[String], (json \ "longUrl").as[String])
      })
    val ret = Await.result(result, Duration.Inf)
    ws.close()
    ret
  }
}
