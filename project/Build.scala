import sbt._
import Keys._
import PlayProject._

object ApplicationBuild extends Build {

    val appName         = "play-demo"
    val appVersion      = "1.0-SNAPSHOT" 
     
    //http://mvnrepository.com/artifact/org.scalatest/scalatest_2.9.1/1.8
    val appDependencies = Seq(
      "org.scalatest" % "scalatest_2.9.1" % "1.8"
    )
    
    val main = PlayProject(appName, appVersion, appDependencies, mainLang = SCALA).settings(
      testOptions in Test := Nil      
    )

}
