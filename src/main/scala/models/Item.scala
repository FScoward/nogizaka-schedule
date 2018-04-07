package models

case class Item(`type`: String, body: String) extends Show {
  val text = s"【今日の乃木坂46】 [${`type`}]\n$body"
}
