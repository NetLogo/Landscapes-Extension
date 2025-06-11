// (C) Uri Wilensky. https://github.com/NetLogo/NetLogo

package org.nlogo.extensions.landscapes

import org.nlogo.api.Argument
import org.nlogo.api.Command
import org.nlogo.api.Context
import org.nlogo.api.DefaultClassManager
import org.nlogo.api.ExtensionException
import org.nlogo.api.PrimitiveManager
import org.nlogo.api.Reporter
import org.nlogo.api.ScalaConversions.toLogoList
import org.nlogo.core.Syntax.ListType
import org.nlogo.core.Syntax.StringType
import org.nlogo.core.Syntax.commandSyntax
import org.nlogo.core.Syntax.reporterSyntax

class LandscapesExtension extends DefaultClassManager {
  override def load(primManager: PrimitiveManager): Unit = {
    primManager.addPrimitive("generate", new GeneratePrim())
    primManager.addPrimitive("list", new ListPrim())
  }
}

class ListPrim extends Reporter {
  override def getSyntax = reporterSyntax(ret = ListType)
  def report(args: Array[Argument], context: Context): AnyRef = toLogoList(Problems.names)
}

class GeneratePrim() extends Command {
  override def getSyntax = commandSyntax(List(StringType, StringType))
  override def perform(args: Array[Argument], context: Context): Unit = {
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
