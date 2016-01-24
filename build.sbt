name := "nogizaka-schedule"

version := "1.0"

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  "org.jsoup" % "jsoup" % "1.8.3",
  "org.scalaz" %% "scalaz-core" % "7.2.0",
  "org.twitter4j" % "twitter4j-core" % "4.0.4",
  "com.typesafe.scala-logging" %% "scala-logging" % "3.1.0",
  "ch.qos.logback" % "logback-classic" % "1.1.3"
)