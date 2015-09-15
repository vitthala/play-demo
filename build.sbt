name := """demo-cassandra"""

version := "1.0-SNAPSHOT"

scalaVersion := "2.11.6"

libraryDependencies ++= Seq(
  javaJdbc,
  cache,
  javaWs
)

resolvers ++= Seq(
  "Kundera" at "https://oss.sonatype.org/content/repositories/releases",
  "Riptano" at "http://mvn.riptano.com/content/repositories/public",
  "Kundera missing" at "http://kundera.googlecode.com/svn/maven2/maven-missing-resources",
  "Scale 7" at "https://github.com/s7/mvnrepo/raw/master"
)


libraryDependencies ++= Seq(
  javaJpa,
  "com.impetus.client" % "kundera-cassandra" % "2.5" // replace by your jpa implementation
)


// Play provides two styles of routers, one expects its actions to be injected, the
// other, legacy style, accesses its actions statically.
routesGenerator := InjectedRoutesGenerator

lazy val root = (project in file(".")).enablePlugins(PlayJava)
//  .aggregate(admin)
//  .dependsOn(admin)

//lazy val admin = project.in(file("modules/admin"))
