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
//    Schedule.tweet
//    Affiliate.tweet
//    NewInfo.tweet
    News.crawl
  }

}
