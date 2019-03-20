lazy val HashRoute = project

lazy val JsonHashRoute = project.dependsOn(HashRoute)

ThisBuild / organization := "com.thoughtworks.binding"

dynverSeparator in ThisBuild := "-"

skip in publish := true

enablePlugins(ScalaUnidocPlugin)
