import java.util.Date

import org.jsoup.Jsoup
import org.jsoup.nodes.Element
import org.jsoup.select.Elements
import collection.JavaConversions._

/**
  * Created by Fumiyasu on 2016/01/23.
  */
case class Schedule(date: String, items: List[Item])
case class Item(`type`: String, body: String) {
  override def toString = s"【今日の乃木坂46】 [${`type`}]\n$body"
}

object Schedule {

  val nogizakaScheduleUrl = "http://www.nogizaka46.com/schedule/"

  def tweet = {
    todaySchedule.map(TweetService.tweet)
  }

  private def isToday(schedule: Schedule) = {
    schedule.date == ("%td" format new Date)
  }

  /**
    * 今日のスケジュールを取得する
    *
    * @return today schedule
    * */
  def todaySchedule = {
    val htmlDocument = Jsoup.connect(nogizakaScheduleUrl).get()
    val scheduleTableListEl = htmlDocument
      .select("div#scheduleTable")
      .select(".scheduleTableList")

    scheduleTableListEl.map(parseSchedule).filter(isToday)
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


  /**
    * TODO
    * - リプライもらったら返す
    *   <p>@Fanimeation 今日のテレビ</p>
    *   ときたら今日のテレビの情報を返す
    * */



}
