// (C) Uri Wilensky. https://github.com/NetLogo/NetLogo

package org.nlogo.extensions.landscapes

import org.nlogo.api.Patch
import org.nlogo.api.World

object Landscapes {

  def generate(problemName: String, patchVariableName: String, world: World): Unit = {

    import scala.jdk.CollectionConverters.IterableHasAsScala

    val variableName = patchVariableName.toUpperCase(java.util.Locale.ENGLISH)
    val variableIndex = world.program.patchesOwn.indexOf(variableName)
    if (variableIndex == -1)
      throw new IllegalArgumentException(s"Unknown patch variable name: $variableName.")

    val mappedProblem = new MappedProblem(problemName,
      world.minPxcor, world.maxPxcor,
      world.minPycor, world.maxPycor)

    var min = Double.MaxValue
    var max = Double.MinValue
    val results: Map[Patch, Double] =
      world.patches.agents.asScala.collect {
        case p: Patch =>
          val v = mappedProblem(p.pxcor, p.pycor)
          if (v < min) min = v
          if (v > max) max = v
          (p, v)
      }.toMap

    val isMin = mappedProblem.problem.isMinimization
    for ((p, v) <- results) {
      val v1 = (v - min) / (max - min) // normalize
      val v2 = if (isMin) 1.0 - v1 else v1 // invert if needed
      p.setVariable(variableIndex, Double.box(v2))
    }

  }

}
