// (C) Uri Wilensky. https://github.com/NetLogo/NetLogo

package org.nlogo.extensions.landscapes

import org.nlogo.api.Argument
import org.nlogo.api.Context
import org.nlogo.api.DefaultClassManager
import org.nlogo.api.DefaultCommand
import org.nlogo.api.DefaultReporter
import org.nlogo.api.ExtensionException
import org.nlogo.api.PrimitiveManager
import org.nlogo.api.ScalaConversions.toLogoList
import org.nlogo.api.Syntax._

class LandscapesExtension extends DefaultClassManager {
  override def load(primManager: PrimitiveManager) {
    primManager.addPrimitive("generate", new GeneratePrim())
    primManager.addPrimitive("list", new ListPrim())
  }
}

class ListPrim extends DefaultReporter {
  override def getSyntax = reporterSyntax(ListType)
  def report(args: Array[Argument], context: Context): AnyRef = toLogoList(Problems.names)
}

class GeneratePrim() extends DefaultCommand {
  override def getSyntax = commandSyntax(Array(StringType, StringType))
  override def perform(args: Array[Argument], context: Context) {
    try {
      val problemName = args(0).getString
      val variableName = args(1).getString
      val world = context.getAgent.world
      Landscapes.generate(problemName, variableName, world)
    } catch {
      case e: Exception => throw new ExtensionException(e)
    }
  }
}
