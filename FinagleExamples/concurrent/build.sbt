lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "com.twitter",
      scalaVersion := "2.13.8",
      version      := "1.0"
    )),
    name := "concurrent",
    libraryDependencies ++= Seq(
      "com.twitter" %% "finagle-http" % "22.2.0"
    )
  )
