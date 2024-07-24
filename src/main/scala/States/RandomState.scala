package States
import scala.util.Random
import controller.AlgoritmoController
import tiles.Tiles

class RandomState extends IState {
  private var i = -1
  private var j = -1
  private var matrix: Array[Array[Tiles]] = _
  
  def update(controller: AlgoritmoController):Unit ={
    val tamano = controller.getTamano
    matrix= controller.getMatrix
    val seed = controller.getSeed
    var selecting = true
    while (selecting) {
      i = new Random(seed).between(0, tamano)
      j = new Random(seed).between(0, tamano)
      if (!matrix(i)(j).getCollapsed) {
        selecting = false
      }
    }
    changeState(controller)
  }
  def changeState(controller: AlgoritmoController): Unit ={
    controller.changeState(new ProcessState(matrix(i)(j)))
  }

}
