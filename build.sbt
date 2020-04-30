import Dependencies._

name := "calendar-kata"

inThisBuild(
  List(
    version := "1",
    scalaVersion := "2.12.10"
  )
)

libraryDependencies ++= Seq(
  scala_test
)