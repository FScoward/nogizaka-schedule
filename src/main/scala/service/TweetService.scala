package service

import com.typesafe.scalalogging.LazyLogging
import models.Show
import twitter4j.TwitterFactory

import scala.util.Try

/**
  * Created by Fumiyasu on 2016/01/23.
  */
class TweetService extends LazyLogging with OutputService {
  val twitter = TwitterFactory.getSingleton
  val limit = 140
  val hashtag = "#乃木坂46"

  def tweet(target: {def toString(): String}): Unit = {
    Try{
//      twitter.updateStatus(s"${target.toString.take(limit - hashtag.length)} $hashtag")
      logger.info(s"${target.toString.take(limit - hashtag.length)} $hashtag")
    }.recover{
      case e: Throwable => logger.error(s"$e")
    }
  }
//  def tweet(target: {def toString(): String}) = {
//    logger.info(s"${target.toString.take(limit - hashtag.length)} #乃木坂46")
//  }

  override def show(target: Show): Unit = {
   Try{
      logger.info(s"${target.text.take(limit - hashtag.length)} $hashtag")
     //      twitter.updateStatus(s"${target.toString.take(limit - hashtag.length)} $hashtag")
    }.recover{
      case e: Throwable => logger.error(s"$e")
    }
  }

  override def showAll(targetList: Seq[Show]): Unit = targetList.map(show)

}
