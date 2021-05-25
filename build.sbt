scalaVersion := "2.12.8"

enablePlugins(org.nlogo.build.NetLogoExtension)

scalacOptions ++= Seq("-deprecation", "-unchecked", "-Xlint", "-Xfatal-warnings", "-encoding", "UTF8")

libraryDependencies ++= Seq(
  "com.oat" % "optalgtoolkit" % "1.4" from
    "https://ccl.northwestern.edu/devel/optalgtoolkit-1.4.jar"
)

name := "landscapes"

version := "1.0.2"

netLogoExtName := "landscapes"

netLogoClassManager := "org.nlogo.extensions.landscapes.LandscapesExtension"

netLogoVersion := "6.2.0"

netLogoTarget := org.nlogo.build.NetLogoExtension.directoryTarget(baseDirectory.value)
