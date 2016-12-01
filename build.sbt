lazy val `geoscale` =
  project.in(file(".")).enablePlugins(AutomateHeaderPlugin, GitVersioning)

libraryDependencies ++= Vector(
  Library.scalaTest % "test",
  Library.scalaCheck % "test",
  Library.jts
)

initialCommands := """|import io.uport.geoscale._
                      |""".stripMargin
