ThisBuild / scalaVersion := "2.12.6"
ThisBuild / organization := "CoinWatchdog (working title)"

lazy val coinwd = (project in file("."))
  .settings(
      name := "CoinWd",
      resolvers += "Akka Snapshots" at "https://repo.akka.io/snapshots/",
      libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.5" % Test,
      libraryDependencies += "com.typesafe.akka" % "akka-remote_2.12" % "2.5-20180903-232157",
      libraryDependencies += "com.typesafe.akka" % "akka-http-core_2.12" % "10.0.6+7-e2ba6752",
      libraryDependencies += "io.spray" %%  "spray-json" % "1.3.4",
  )
  

