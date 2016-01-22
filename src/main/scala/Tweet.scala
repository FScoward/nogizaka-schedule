import twitter4j.TwitterFactory

/**
  * Created by Fumiyasu on 2016/01/23.
  */
object TweetService {
  val twitter = TwitterFactory.getSingleton
  def tweet(target: {def toString(): String}) = twitter.updateStatus(target.toString)
}
