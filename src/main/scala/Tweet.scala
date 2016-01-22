import twitter4j.TwitterFactory

/**
  * Created by Fumiyasu on 2016/01/23.
  */
object TweetService {
  val twitter = TwitterFactory.getSingleton
  def tweet(target: {def toString(): String}) = twitter.updateStatus(s"${target.toString} #乃木坂46".take(140))
}
