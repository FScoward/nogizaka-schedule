package models

case class Affiliate(title: String, link: String) extends Show {
  val text = s"【宣伝】$link\n$title"
}
