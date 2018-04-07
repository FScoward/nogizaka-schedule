import java.util.Date

import com.typesafe.scalalogging.LazyLogging
import models.{Item, Schedule}
import org.jsoup.nodes.Element
import org.jsoup.select.Elements

import collection.JavaConverters._

class ScheduleService extends LazyLogging {

//  val nogizakaScheduleUrl = "http://www.nogizaka46.com/schedule/"
  val nogizakaScheduleUrl = "http://www.nogizaka46.com/schedule/"

  private def isToday(schedule: Schedule) = {
    schedule.date == ("%te" format new Date)
  }

  /**
    * 今日のスケジュールを取得する
    *
    * @return today schedule
    * */
  def todaySchedule = {
    val htmlDocument = JsoupClient.get(nogizakaScheduleUrl)
    val scheduleTableListEl = htmlDocument
      .select("div#scheduleTable")
      .select(".scheduleTableList")

    scheduleTableListEl.asScala.map(parseSchedule).filter(isToday)
  }

  /**
    * スケジュールリストからスケジュールをparseする
    *
    * @param element .scheduleTableList element
    * @return models.Schedule
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
    * @return Sequence of models.Item
    * */
  private def parseItems(items: Elements): Seq[Item] = {
    items.asScala.map(item => {
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
