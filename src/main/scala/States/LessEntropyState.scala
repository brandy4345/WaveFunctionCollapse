package States

import controller.AlgoritmoController
import tiles.Tiles

import scala.util.Random

class LessEntropyState extends IState {
  private var tile: Tiles= _

  def update(controller: AlgoritmoController): Unit = {
    val seed = controller.getSeed
    controller.doOrderList()
    val minorList = controller.getMinorEntropy
    val rand = new Random(seed).between(0,minorList.length)
    tile = minorList(rand)
    changeState(controller)
  }

  def changeState(controller: AlgoritmoController): Unit = {
    controller.changeState(new ProcessState(tile))
  }
}
