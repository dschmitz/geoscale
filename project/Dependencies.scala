import sbt._

object Version {
  final val Scala      = "2.12.0"
  final val ScalaTest  = "3.0.1"
  final val ScalaCheck = "1.13.4"
  final val Jts        = "1.13"
}

object Library {
  val scalaTest  = "org.scalatest"       %% "scalatest"  % Version.ScalaTest
  val scalaCheck = "org.scalacheck"      %% "scalacheck" % Version.ScalaCheck
  val jts        = "com.vividsolutions"  %  "jts"        % Version.Jts
}
