import com.typesafe.scalalogging.{LazyLogging, Logger}
import org.slf4j.LoggerFactory
import twitter4j.TwitterFactory

import scala.util.Try

/**
  * Created by Fumiyasu on 2016/01/23.
  */
object TweetService extends LazyLogging {
  val twitter = TwitterFactory.getSingleton
  val limit = 140
  val hashtag = "#乃木坂46"

  def tweet(target: {def toString(): String}): Unit = {
    Try{
      twitter.updateStatus(s"${target.toString.take(limit - hashtag.length)} #乃木坂46")
    }.recover{
      case e: Throwable => logger.error(s"$e")
    }
  }
//  def tweet(target: {def toString(): String}) = {
//    logger.info(s"${target.toString.take(limit - hashtag.length)} #乃木坂46")
//  }
}
