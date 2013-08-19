// (C) Uri Wilensky. https://github.com/NetLogo/NetLogo

package org.nlogo.extensions.landscapes

import java.util.Locale.ENGLISH

import com.oat.domains.cfo.CFOProblem
import com.oat.domains.cfo.CFOProblem.MAX
import com.oat.domains.cfo.CFOProblem.MIN
import com.oat.domains.cfo.CFOProblem.X
import com.oat.domains.cfo.CFOProblem.Y

class MappedProblem(problemName: String,
  minWorldX: Double, maxWorldX: Double,
  minWorldY: Double, maxWorldY: Double) {

  val problem = Problems.get(problemName)
  val worldWidth: Double = maxWorldX - minWorldX
  val worldHeight: Double = maxWorldY - minWorldY

  val minMax = problem.getMinmax
  val problemWidth: Double = minMax(X)(MAX) - minMax(X)(MIN)
  val problemHeight: Double = minMax(Y)(MAX) - minMax(Y)(MIN)

  def apply(worldX: Int, worldY: Int): Double =
    problem.directFunctionEvaluation(map(worldX, worldY))

  def map(worldX: Int, worldY: Int): Array[Double] = {
    val problemX = minMax(X)(MIN) + (((worldX - minWorldX) / worldWidth) * problemWidth)
    val problemY = minMax(Y)(MIN) + (((worldY - minWorldY) / worldHeight) * problemHeight)
    Array(problemX, problemY)
  }
}

object Problems {
  private val problems: Map[String, CFOProblem] = List(
    new com.oat.domains.cfo.problems.dejong.TestFunctionF1,
    new com.oat.domains.cfo.problems.dejong.TestFunctionF2,
    new com.oat.domains.cfo.problems.dejong.TestFunctionF3,
    new com.oat.domains.cfo.problems.dejong.TestFunctionF4,
    new com.oat.domains.cfo.problems.dejong.TestFunctionF5,
    new com.oat.domains.cfo.problems.mahfoud.M5,
    new com.oat.domains.cfo.problems.mahfoud.M6,
    new com.oat.domains.cfo.problems.hendtlass.SquashedFrog,
    new com.oat.domains.cfo.problems.hendtlass.ThreePotHoles,
    new com.oat.domains.cfo.problems.timmis.F3,
    new com.oat.domains.cfo.problems.timmis.F4,
    new com.oat.domains.cfo.problems.timmis.F5,
    new com.oat.domains.cfo.problems.timmis.F6,
    new com.oat.domains.cfo.problems.timmis.F7,
    new com.oat.domains.cfo.problems.garrett.Euclidean,
    new com.oat.domains.cfo.problems.garrett.Exp,
    new com.oat.domains.cfo.problems.garrett.Ripples,
    new com.oat.domains.cfo.problems.garrett.Peaks,
    new com.oat.domains.cfo.problems.yao.Sphere,
    new com.oat.domains.cfo.problems.yao.SchwefelsProblem2_22,
    new com.oat.domains.cfo.problems.yao.SchwefelsProblem1_2,
    new com.oat.domains.cfo.problems.yao.SchwefelsProblem2_21,
    new com.oat.domains.cfo.problems.yao.GeneralizedRosenbrocksFunction,
    new com.oat.domains.cfo.problems.yao.StepFunction,
    new com.oat.domains.cfo.problems.yao.QuarticFunction,
    new com.oat.domains.cfo.problems.yao.GeneralizedSchwefelsProblem2_26,
    new com.oat.domains.cfo.problems.yao.GeneralizedRastriginsFunction,
    new com.oat.domains.cfo.problems.yao.AckleysFunction,
    new com.oat.domains.cfo.problems.yao.GeneralizedGriewankFunction,
    new com.oat.domains.cfo.problems.yao.GeneralizedPenalizedFunction1,
    new com.oat.domains.cfo.problems.yao.GeneralizedPenalizedFunction2,
    new com.oat.domains.cfo.problems.geatbx.DeJongF1,
    new com.oat.domains.cfo.problems.geatbx.AxisParalleHyper_EllipsoidFunction,
    new com.oat.domains.cfo.problems.geatbx.RotatedHyper_EllipsoidFunction,
    new com.oat.domains.cfo.problems.geatbx.MovedAxisParalleHyper_EllipsoidFunction,
    new com.oat.domains.cfo.problems.geatbx.RosenbrocksValley,
    new com.oat.domains.cfo.problems.geatbx.RastriginFunction,
    new com.oat.domains.cfo.problems.geatbx.SchwefelsFunction,
    new com.oat.domains.cfo.problems.geatbx.GriewangksFunction8,
    new com.oat.domains.cfo.problems.geatbx.SumOfDifferentPowerFunction,
    new com.oat.domains.cfo.problems.geatbx.AckleysPathFunction10,
    new com.oat.domains.cfo.problems.geatbx.LangermannFunction,
    new com.oat.domains.cfo.problems.geatbx.LangermannFunction2,
    new com.oat.domains.cfo.problems.geatbx.MichalewiczsFunction,
    new com.oat.domains.cfo.problems.geatbx.BraninssRcosFunction,
    new com.oat.domains.cfo.problems.geatbx.EasomsFunction,
    new com.oat.domains.cfo.problems.geatbx.GoldsteinPricesFunction,
    new com.oat.domains.cfo.problems.geatbx.SixHumpCamelBackFunction,
    new com.oat.domains.cfo.problems.optainet.Multi,
    new com.oat.domains.cfo.problems.optainet.Roots,
    new com.oat.domains.cfo.problems.optainet.SchaffersFunction,
    new com.oat.domains.cfo.problems.horn.FmmEasy,
    new com.oat.domains.cfo.problems.horn.ModifiedHornsFivePeaks,
    new com.oat.domains.cfo.problems.clonalg.G3,
    new com.oat.domains.cfo.problems.BohachevskyFunction,
    new com.oat.domains.cfo.problems.CPF1,
    new com.oat.domains.cfo.problems.CPF2,
    new com.oat.domains.cfo.problems.GeneralizedHimmelblausFunction,
    new com.oat.domains.cfo.problems.Hansens,
    new com.oat.domains.cfo.problems.ShubertFunction)
    .map(p ⇒ p.getName.toUpperCase(ENGLISH) -> p)(collection.breakOut)
  def names = problems.keys.toIndexedSeq.sorted
  def get(name: String) = {
    val problemName = name.toUpperCase(ENGLISH)
    problems.getOrElse(problemName,
      throw new IllegalArgumentException("Unknown problem name: " + problemName + "."))
  }
}
