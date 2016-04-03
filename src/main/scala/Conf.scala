import com.typesafe.config.ConfigFactory

/**
  * Created by Fumiyasu on 2016/04/04.
  */
object Conf {
  val config = ConfigFactory.load()
  def userAgent = config.getString("user_agent")
}
