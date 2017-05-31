scalaVersion := "2.12.1"

enablePlugins(org.nlogo.build.NetLogoExtension)

scalacOptions ++= Seq("-deprecation", "-unchecked", "-Xlint", "-Xfatal-warnings", "-encoding", "UTF8")

libraryDependencies ++= Seq(
  "com.oat" % "optalgtoolkit" % "1.4" from
    "http://ccl.northwestern.edu/devel/optalgtoolkit-1.4.jar"
)

name := "landscapes"

netLogoExtName := "landscapes"

netLogoClassManager := "org.nlogo.extensions.landscapes.LandscapesExtension"

netLogoVersion := "6.0.1"

netLogoTarget := org.nlogo.build.NetLogoExtension.directoryTarget(baseDirectory.value)
