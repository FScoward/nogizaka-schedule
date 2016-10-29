import java.net.URL

import com.sun.syndication.feed.synd.SyndEntryImpl
import com.sun.syndication.fetcher.impl.{HashMapFeedInfoCache, HttpURLFeedFetcher}
import com.typesafe.scalalogging.LazyLogging

/**
  * Created by Fumiyasu on 2016/10/30.
  */
object Blog extends LazyLogging {

  val nogizakaBlogUrl = "http://blog.nogizaka46.com/atom.xml"
  val url = new URL(nogizakaBlogUrl)
  val now = System.currentTimeMillis()

  def tweet = {
    import scala.collection.JavaConverters._

    val feedFetcherCache = HashMapFeedInfoCache.getInstance()
    val feedFetcher = new HttpURLFeedFetcher(feedFetcherCache)
    val feed = feedFetcher.retrieveFeed(url)

    feed.getEntries.asInstanceOf[java.util.List[SyndEntryImpl]].asScala
      .filter(syndFeed => PublishedDate(syndFeed.getPublishedDate.getTime).isLaterThanLastTime)
      .map(syndFeed => {
        val text = s"${syndFeed.getAuthor}\n${syndFeed.getLink}\n${syndFeed.getTitle}"
        TweetService.tweet(text)
    })

  }

}
