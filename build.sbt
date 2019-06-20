lazy val HashRoute = project

lazy val JsonHashRoute = project.dependsOn(HashRoute)

ThisBuild / organization := "com.thoughtworks.binding"

skip in publish := true

enablePlugins(ScalaUnidocPlugin)
