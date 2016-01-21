import java.util.Date

import org.jsoup.Jsoup
import org.jsoup.nodes.Element
import org.jsoup.select.Elements
import twitter4j.TwitterFactory
import scalaz._
import Scalaz._
import collection.JavaConversions._

case class Schedule(date: String, items: List[Item])
case class Item(`type`: String, body: String) {
  override def toString = s"今日の乃木坂46\n: [${`type`}] $body".take(140)
}

/**
  * Created by FScoward on 2016/01/21.
  */
object Main {

  val nogizakaScheduleUrl = "http://www.nogizaka46.com/schedule/"

  def main(args: Array[String]): Unit = {
    val htmlDocument = Jsoup.connect(nogizakaScheduleUrl).get()
    val scheduleTableListEl = htmlDocument
      .select("div#scheduleTable")
      .select(".scheduleTableList")

    val todays = todaySchedule(scheduleTableListEl.iterator()).toList
    val twitter = TwitterFactory.getSingleton
    todays.headOption.map(_.items.map(item => twitter.updateStatus(item.toString)))
  }

  private def isToday(schedule: Schedule) = {
    schedule.date == ("%td" format new Date)
  }

  /**
    * 今日のスケジュールを取得する
    *
    * @param elements 今月のスケジュールの記載された HTML Elements
    * @return today schedule
    * */
  private def todaySchedule(elements: Iterator[Element]) = {
    elements.map(parseSchedule).filter(isToday)
  }

  /**
    * スケジュールリストからスケジュールをparseする
    *
    * @param element .scheduleTableList element
    * @return Schedule
    * */
  private def parseSchedule(element: Element): Schedule = {
    val date = element
      .select(".first-child")
      .select("span")
      .text()
    val items = element.select(".last-child").select("a")
    Schedule(date, parseItems(items).toList)
  }

  /**
    * 特定の一日のスケジュールのリストから予定の種別と内容を取り出す
    *
    * @param items schedule items
    * @return Sequence of Item
    * */
  private def parseItems(items: Elements): Seq[Item] = {
    items.map(item => {
      Item(item.className(), item.text)
    }).toSeq
  }











}
