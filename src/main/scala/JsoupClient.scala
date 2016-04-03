import org.jsoup.Jsoup

/**
  * Created by Fumiyasu on 2016/04/04.
  */
object JsoupClient {

  def get(url: String) = {
    Jsoup.connect(url).userAgent(Conf.userAgent).get()
  }

}
