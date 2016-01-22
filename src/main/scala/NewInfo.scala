import java.util.Date

import org.jsoup.Jsoup
import org.jsoup.nodes.Element
import scalaz._
import Scalaz._
import collection.JavaConversions._

case class NewInfo(date: String, title: String, summary: String) {
  def isToday = ("%tY.%<tm.%<td" format new Date) == date
  override def toString = s"【今日の新着】\n$title\n$summary".take(140)
}

/**
  * Created by Fumiyasu on 2016/01/22.
  */
object NewInfo {
  val newInfoUrl = "http://www.nogizaka46.com/news/"

  def getNewInfo = {
    val htmlDocument = Jsoup.connect(newInfoUrl).get()
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
