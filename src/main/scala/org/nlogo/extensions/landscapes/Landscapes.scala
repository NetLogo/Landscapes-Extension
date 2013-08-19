// (C) Uri Wilensky. https://github.com/NetLogo/NetLogo

package org.nlogo.extensions.landscapes

import scala.collection.JavaConverters._

import org.nlogo.api.Patch
import org.nlogo.api.World

object Landscapes {
  def generate(problemName: String, patchVariableName: String, world: World) {
    val variableName = patchVariableName.toUpperCase(java.util.Locale.ENGLISH)
    val variableIndex = world.program.patchesOwn.indexOf(variableName)
    if (variableIndex == -1) throw new IllegalArgumentException("Unknown patch variable name: " + variableName + ".")
    val mappedProblem = new MappedProblem(problemName,
      world.minPxcor, world.maxPxcor,
      world.minPycor, world.maxPycor)
    val patches = world.patches.agents.asScala.collect { case p: Patch ⇒ p }
    val results: Map[Patch, Double] = patches.map(p ⇒ p -> mappedProblem(p.pxcor, p.pycor))(collection.breakOut)
    val min = results.values.min
    val max = results.values.max
    val normalizedResults = results.mapValues(r ⇒ (r - min) / (max - min))
    val finalResults =
      if (mappedProblem.problem.isMinimization)
        normalizedResults.mapValues(1.0 - _)
      else normalizedResults
    patches.foreach(p ⇒ p.setVariable(variableIndex, Double.box(finalResults(p))))
  }
}
