name := "duplo"

organization := "com.github.perspectivet"

version := "0.0.1-SNAPSHOT"

scalaVersion := "2.10.0-RC2"

//scalaVersion := "2.10.0-RC3"

resolvers ++= Seq(
	  "Local Maven Repository" at "file://"+Path.userHome.absolutePath+"/.m2/repository",
	  "repo.codahale.com" at "http://repo.codahale.com",
	  "bigdata.releases" at "http://www.systap.com/maven/releases",
	  "bigdata.snapshots" at "http://www.systap.com/maven/snapshots",
	  "nxparser-repo" at "http://nxparser.googlecode.com/svn/repository",
	  "openrdf.releases" at "http://repo.aduna-software.org/maven2/releases"
)

libraryDependencies := Seq(
  "org.specs2" % "specs2_2.10.0-RC2" % "1.12.2",
//  "org.specs2" %% "specs2" % "1.12.2" % "test",
  "org.clapper" % "grizzled-slf4j_2.10" % "1.0.1",
//  "commons-logging" % "commons-logging-api" % "1.1",
  "org.apache.httpcomponents" % "httpclient" % "4.2.2",
  "org.w3" % "banana-sesame_2.10" % "x14-SNAPSHOT",
  "org.openrdf.sesame" % "sesame-repository-http" % "2.6.10",
  "org.openrdf.sesame" % "sesame-repository-sparql" % "2.6.10",
  "org.openrdf.sesame" % "sesame-repository-sail" % "2.6.10",
  "org.openrdf.sesame" % "sesame-repository" % "2.6.10",
  "org.openrdf.sesame" % "sesame-repository-manager" % "2.6.10",
  "org.openrdf.sesame" % "sesame-repository-api" % "2.6.10"
)

initialCommands := "import com.github.perspectivet.bigdata.rest._"
