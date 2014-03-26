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
    var min = Double.MaxValue
    var max = Double.MinValue
    val results: Map[Patch, Double] =
      world.patches.agents.asScala.collect {
        case p: Patch ⇒
          val v = mappedProblem(p.pxcor, p.pycor)
          if (v < min) min = v
          if (v > max) max = v
          (p, v)
      }(collection.breakOut)
    val normalizedResults = results.mapValues(r ⇒ (r - min) / (max - min))
    val finalResults =
      if (mappedProblem.problem.isMinimization)
        normalizedResults.mapValues(1.0 - _)
      else normalizedResults
    for ((p, v) ← finalResults) p.setVariable(variableIndex, Double.box(v))
  }
}
