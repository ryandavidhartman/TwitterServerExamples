name := "SimpleServer"

version := "0.1"

scalaVersion := "2.13.6"

libraryDependencies += "com.twitter" %% "twitter-server" % "21.6.0"

assemblyMergeStrategy in assembly := {
  case PathList("META-INF", xs @ _*) => MergeStrategy.discard
  case x => MergeStrategy.first
}