name := """scala-url-shortener"""
organization := "me.ifkarsyah"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.13.1"

libraryDependencies += guice
libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "5.0.0" % Test
libraryDependencies ++= Seq(
  "net.debasishg" %% "redisclient" % "3.20"
)

// Adds additional packages into Twirl
//TwirlKeys.templateImports += "me.ifkarsyah.controllers._"

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "me.ifkarsyah.binders._"