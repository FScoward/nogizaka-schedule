import org.jsoup.Jsoup

/**
  * Created by Fumiyasu on 2016/01/24.
  */
object News {
  val url = "https://www.google.co.jp/search?q=%E4%B9%83%E6%9C%A8%E5%9D%8246&ie=utf-8&oe=utf-8&hl=ja#q=%E4%B9%83%E6%9C%A8%E5%9D%8246&lr=lang_ja&hl=ja&tbas=0&tbs=qdr:d,lr:lang_1ja&tbm=nws"

  def crawl = {
    val document = Jsoup.connect(url).get()
    val title = document.select("a.l.HId")
    println(title.html)
  }
}
