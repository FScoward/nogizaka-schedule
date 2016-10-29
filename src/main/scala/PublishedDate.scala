import scala.concurrent.duration.Duration
import org.joda.time._

/**
  * Created by Fumiyasu on 2016/10/30.
  */
case class PublishedDate(publishedTimeMills: Long) {
  private val now: DateTime = new DateTime(System.currentTimeMillis())
  private val nowHour: Int = now.getHourOfDay
  private val publishedDateTime: DateTime = new DateTime(publishedTimeMills)

  // 前回起動時よりも後のものか
  def isLaterThanLastTime: Boolean = {
    if (0 <= nowHour && nowHour < 9)
      publishedDateTime.isAfter(now.withTime(0, 0, 0, 0))
    else if (9 <= nowHour && nowHour < 18)
      publishedDateTime.isAfter(now.withTime(9, 0, 0, 0))
    else
      publishedDateTime.isAfter(now.withTime(18, 0, 0, 0))
  }

}