import org.jsoup.Jsoup
import twitter4j.{Twitter, TwitterFactory}
import scalaz._
import Scalaz._
import collection.JavaConversions._

/**
  * Created by FScoward on 2016/01/21.
  */
object Main {

  val twitter = TwitterFactory.getSingleton

  def main(args: Array[String]): Unit = {

    val todays = Schedule.todaySchedule.toList
    todays.headOption.map(_.items.map(item => twitter.updateStatus(item.toString)))
    Affiliate.tweet
    NewInfo.getNewInfo.filter(_.isToday).foreach(newInfo => twitter.updateStatus(newInfo.toString))
  }

}
