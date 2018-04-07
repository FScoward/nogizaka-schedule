package modules

import com.google.inject.AbstractModule
import net.codingwell.scalaguice.ScalaModule
import service.{OutputService, TweetService}

class Modules extends AbstractModule with ScalaModule {
  override def configure(): Unit = {
    bind[OutputService].to[TweetService]
  }
}
