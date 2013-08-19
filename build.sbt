scalaVersion := "2.9.2"

scalacOptions ++= Seq("-deprecation", "-unchecked", "-Xfatal-warnings")

libraryDependencies ++= Seq(
  "org.nlogo" % "NetLogoLite" % "5.0.4" from
    "http://ccl.northwestern.edu/netlogo/5.0.4/NetLogoLite.jar",
  "com.oat" % "optalgtoolkit" % "1.4" from
    "http://ccl.northwestern.edu/devel/optalgtoolkit-1.4.jar"
)

name := "landscapes"

NetLogoExtension.settings

NetLogoExtension.classManager := "org.nlogo.extensions.landscapes.LandscapesExtension"
