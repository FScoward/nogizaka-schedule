import com.google.inject.Guice
import modules.Modules
import service.{AffiliateService, OutputService}
import twitter4j.TwitterFactory

/**
  * Created by FScoward on 2016/01/21.
  */
object Main {

  val twitter = TwitterFactory.getSingleton

  def main(args: Array[String]): Unit = {
    val injector = Guice.createInjector(new Modules())

    import net.codingwell.scalaguice.InjectorExtensions._

    val outputService = injector.instance[OutputService]
    val schedule = new ScheduleService().todaySchedule
    outputService.showAll(schedule.flatMap(_.items))
//    NewInfo.tweet
//    Blog.tweet
    outputService.show(AffiliateService.getOne)
//    News.crawlLivedoorNews
//    News.googleNews
  }

}
