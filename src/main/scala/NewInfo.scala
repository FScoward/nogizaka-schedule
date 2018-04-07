import java.util.Date

import com.typesafe.scalalogging.LazyLogging
import org.jsoup.Jsoup
import org.jsoup.nodes.Element
import collection.JavaConversions._

case class NewInfo(date: String, title: String, summary: String) {
  def isToday = ("%tY.%<tm.%<td" format new Date) == date
  override def toString = s"【今日の新着】\n$title\n$summary"
}

/**
  * Created by Fumiyasu on 2016/01/22.
  */
object NewInfo extends LazyLogging {
  val newInfoUrl = "http://www.nogizaka46.com/news/"

  def tweet = {
    logger.info(s"==================NewInfo START")
//    getNewInfo.filter(_.isToday).map(TweetService.tweet)
  }

  def getNewInfo = {
    val htmlDocument = JsoupClient.get(newInfoUrl)
    val elements = htmlDocument.select("li.clearfix.noborder").iterator()
    elements.map(extractNewInfo).toList
  }

  def extractNewInfo(element: Element) = {
    val date = element.select(".date").text()
    val title = element.select(".title").text()
    val summary = element.select(".summary").text()
    NewInfo(date, title, summary)
  }


}
