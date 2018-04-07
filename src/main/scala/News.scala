import java.text.SimpleDateFormat
import java.util.Date

import com.typesafe.scalalogging.LazyLogging
import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat
import org.jsoup.Jsoup
import org.jsoup.nodes.Element
import play.api.libs.ws.ning.NingWSClient
import util.GoogleUrlShortner
import collection.JavaConversions._
import scala.concurrent.Await
import scala.concurrent.duration.Duration

case class News(date: DateTime, title: String, link: String) {
  def isToday = new DateTime().toLocalDate.isEqual(date.toLocalDate)
  override def toString = s"$title ($link)"
}

/**
  * Created by Fumiyasu on 2016/01/24.
  */
object News extends LazyLogging {

  def googleNews = {
    val googleNews = "https://www.google.co.jp/search?q=%E4%B9%83%E6%9C%A8%E5%9D%8246&ie=utf-8&oe=utf-8&hl=ja#q=%E4%B9%83%E6%9C%A8%E5%9D%8246&hl=ja&tbm=nws&tbs=qdr:d"
    val ws = NingWSClient()
    val f = ws.url(googleNews).get()
    val result = Await.result(f, Duration.Inf)
    ws.close()
    logger.info(s"${result.body}")
  }


  def crawlLivedoorNews() = {
    val livedoorNews = "http://news.livedoor.com/%E4%B9%83%E6%9C%A8%E5%9D%8246/topics/keyword/24004/"
    val document = JsoupClient.get(livedoorNews)
    val articleList = document.select(".articleList")
//    articleList.map(news).filter(_.isToday).map(TweetService.tweet)
  }

  def news(article: Element) = {
    val date = convertDate(article.select(".articleListDate").attr("datetime"))
    val title = article.select("h3.articleListTtl").text()
    val link = article.select("a").attr("href")

    val news = News(date, title, GoogleUrlShortner.shortner(link).id)
    logger.info(s"date: $date, title: $title, link: $link, isToday: ${news.isToday}")
    news
  }

  def convertDate(date: String): DateTime = {
    DateTimeFormat.forPattern("yyyy-mm-dd HH:mm:ss").parseDateTime(date)
  }


}
