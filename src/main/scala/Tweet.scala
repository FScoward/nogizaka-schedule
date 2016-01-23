import twitter4j.TwitterFactory

/**
  * Created by Fumiyasu on 2016/01/23.
  */
object TweetService {
  val twitter = TwitterFactory.getSingleton
  val limit = 140
  val hashtag = "#乃木坂46"
  def tweet(target: {def toString(): String}) = twitter.updateStatus(s"${target.toString.take(limit - hashtag.length)} #乃木坂46")
}
