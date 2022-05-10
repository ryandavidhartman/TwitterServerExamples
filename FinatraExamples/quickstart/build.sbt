lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "com.twitter",
      scalaVersion := "2.13.8",
      version      := "1.0"
    )),
    name := "quickstart",
    libraryDependencies ++= Seq(
      "com.twitter" %% "finatra-http-server" % "22.4.0",
      "ch.qos.logback" % "logback-classic" % "1.2.8",
    )
  )
