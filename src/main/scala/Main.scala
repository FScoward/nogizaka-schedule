import twitter4j.TwitterFactory

/**
  * Created by FScoward on 2016/01/21.
  */
object Main {

  val twitter = TwitterFactory.getSingleton

  def main(args: Array[String]): Unit = {
    Schedule.tweet
    Affiliate.tweet
    NewInfo.tweet
    News.crawlLivedoorNews
//    News.googleNews
  }

}
