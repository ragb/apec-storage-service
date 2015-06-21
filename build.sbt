name := "storage-service"

organization := "com.ruiandrebatista"

version := "0.1"

scalaVersion  := "2.11.6"

scalacOptions := Seq("-unchecked", "-deprecation", "-encoding", "utf8")

libraryDependencies ++= {
	val akkaV = "2.3.11"
	val akkaStreamV = "1.0-RC3"
	val slickV = "3.0.0"
	Seq("com.typesafe.akka" %% "akka-actor" % akkaV,
    "com.typesafe.akka" %% "akka-stream-experimental"             % akkaStreamV,
    "com.typesafe.akka" %% "akka-http-core-experimental"          % akkaStreamV,
    "com.typesafe.akka" %% "akka-http-experimental"         % akkaStreamV,
    "com.typesafe.akka" %% "akka-http-spray-json-experimental" % akkaStreamV,
  "com.typesafe.slick" %% "slick" % slickV, 
    "com.typesafe.akka" %% "akka-http-testkit-experimental" % akkaStreamV,
"org.scalatest" %% "scalatest" % "2.2.4" % "test",
      "org.postgresql" % "postgresql" % "9.4-1201-jdbc41",
"com.zaxxer" % "HikariCP" % "2.3.7",
"ch.qos.logback" % "logback-classic" % "1.1.3" % "runtime")

}

scalacOptions in Test ++= Seq ( "-Yrangepos")


Revolver.settings

enablePlugins (JavaAppPackaging, DockerPlugin ) 

dockerBaseImage := "java:8"
