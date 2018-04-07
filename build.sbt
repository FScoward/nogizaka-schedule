name := "nogizaka-schedule"

version := "1.0"

scalaVersion := "2.12.5"

libraryDependencies ++= Seq(
  "org.jsoup" % "jsoup" % "1.8.3",
//  "org.scalaz" %% "scalaz-core" % "7.2.0",
  "org.twitter4j" % "twitter4j-core" % "4.0.4",
  "com.typesafe.scala-logging" %% "scala-logging" % "3.8.0",
  "ch.qos.logback" % "logback-classic" % "1.1.3",
  "joda-time" % "joda-time" % "2.9.1",
  "com.typesafe" % "config" % "1.3.0",
  "com.typesafe.play" % "play-ws_2.11" % "2.4.6",
  "rome" % "rome" % "1.0",
  "rome" % "rome-fetcher" % "1.0"

)