name := "scala-gorid-client"

version := "1.0"

libraryDependencies += "net.databinder.dispatch" %% "dispatch-core" % "0.11.2"

libraryDependencies += "com.typesafe" % "config" % "1.2.1"

libraryDependencies += "io.argonaut" %% "argonaut" % "6.0.4"

// fix logging error on start
libraryDependencies += "org.slf4j" % "slf4j-simple" % "1.7.5"
