import scala.util.Random

/**
  * Created by Fumiyasu on 2016/01/23.
  */
case class Affiliate(title: String, link: String) {
  override def toString = s"【宣伝】$link\n$title"
}

object Affiliate {
  val affiliates = List(
    Affiliate("ALL MV COLLECTION〜あの時の彼女たち〜(完全生産限定盤) [Blu-ray]", "http://amzn.to/1JkH2hA"),
    Affiliate("生田絵梨花1st写真集 『転調』 単行本（ソフトカバー）", "http://amzn.to/1KsRgaN"),
    Affiliate("NOGIBINGO! 5 Blu-ray BOX", "http://amzn.to/1Nppxrm"),
    Affiliate("悲しみの忘れ方 Documentary of 乃木坂46 Blu-ray コンプリートBOX(4枚組)(完全限定生産)", "http://amzn.to/1NppKL9"),
    Affiliate("初森ベマーズ Blu-ray SPECIAL BOX(ロゴ入りオリジナルトートバック付)(先着予約購入特典:メンバーメッセージ入り生写真12枚セット)[Blu-ray]", "http://amzn.to/23jaISM"),
    Affiliate("乃木坂46 生駒里奈ファースト写真集『君の足跡』", "http://amzn.to/1QDKHtY")
  )

  def getOne = affiliates(Random.nextInt(affiliates.length - 1))

  def tweet = TweetService.tweet(getOne)
//    affiliates.map(TweetService.tweet)

}
