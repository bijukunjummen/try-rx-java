name := "try-rx-java"

version := "1.0"

organization := "org.bk"

scalaVersion := "2.11.7"

libraryDependencies += "io.reactivex" % "rxjava" % "1.0.11"

libraryDependencies += "io.reactivex" %% "rxscala" % "0.25.0"

libraryDependencies += "com.google.guava" % "guava" % "18.0"

libraryDependencies += "ch.qos.logback" % "logback-classic" % "1.1.2"

libraryDependencies += "ch.qos.logback" % "logback-core" % "1.1.2"

libraryDependencies += "io.projectreactor" % "reactor-core" % "2.5.0.M3"

libraryDependencies += "junit" % "junit" % "4.12" % "test"

libraryDependencies += "org.slf4j" % "slf4j-api" % "1.7.6"

libraryDependencies += "org.scalatest" % "scalatest_2.11" % "2.2.0" % "test"

javacOptions ++= Seq("-source", "1.8")

javaOptions += "-Xmx1G"
