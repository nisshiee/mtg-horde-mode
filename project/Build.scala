import sbt._
import Keys._
import PlayProject._

object ApplicationBuild extends Build {

    val appName         = "mtg-horde-mode"
    val appVersion      = "1.0.0-SNAPSHOT"

    val appDependencies = Seq(
      // Add your project dependencies here,
      "org.scalaz" %% "scalaz-core" % "6.0.4"
    )

    val main = PlayProject(appName, appVersion, appDependencies, mainLang = SCALA).settings(
      // Add your own project settings here      
    )

}
