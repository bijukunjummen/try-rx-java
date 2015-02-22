name := "try-rx-java"

version := "1.0"

organization := "org.bk"

scalaVersion := "2.11.5"

libraryDependencies += "io.reactivex" % "rxjava" % "1.0.5"

libraryDependencies += "io.reactivex" %% "rxscala" % "0.23.1"

libraryDependencies += "ch.qos.logback" % "logback-classic" % "1.1.2"

libraryDependencies += "ch.qos.logback" % "logback-core" % "1.1.2"

libraryDependencies += "junit" % "junit" % "4.12" % "test"

libraryDependencies += "org.slf4j" % "slf4j-api" % "1.6.1"

libraryDependencies += "org.scalatest" % "scalatest_2.11" % "2.2.0" % "test"

javacOptions ++= Seq("-source", "1.8")

javaOptions += "-Xmx1G"
