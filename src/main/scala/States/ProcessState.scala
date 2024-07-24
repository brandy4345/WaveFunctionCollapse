package States
import controller.AlgoritmoController
import tiles.Tiles

class ProcessState(tile: Tiles) extends IState {
  def update(controller: AlgoritmoController): Unit = {
    val seed = controller.getSeed
    controller.removeTile(tile)
    tile.randomCollapse(seed)
    tile.notifyNeighbors(controller)
    changeState(controller)
  }

  def changeState(controller: AlgoritmoController): Unit = {
    controller.changeState(new LessEntropyState)
  }
}
