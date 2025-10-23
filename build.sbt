import org.nlogo.build.NetLogoExtension

enablePlugins(NetLogoExtension)

name       := "landscapes"
version    := "1.0.3"
isSnapshot := true

scalaVersion           := "3.7.0"
Compile / scalaSource  := baseDirectory.value / "src" / "main"
Test / scalaSource     := baseDirectory.value / "src" / "test"
scalacOptions          ++= Seq("-deprecation", "-unchecked", "-Xfatal-warnings", "-encoding", "us-ascii",
                               "-release", "17", "-Wunused:linted")

libraryDependencies ++= Seq(
  "com.oat" % "optalgtoolkit" % "1.4" from "https://ccl.northwestern.edu/devel/optalgtoolkit-1.4.jar"
)

netLogoExtName      := "landscapes"
netLogoClassManager := "org.nlogo.extensions.landscapes.LandscapesExtension"
netLogoVersion      := "7.0.1"
