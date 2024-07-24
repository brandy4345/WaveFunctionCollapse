package States

import controller.AlgoritmoController

trait IState {
  def update(controller: AlgoritmoController): Unit
  def changeState(controller: AlgoritmoController): Unit
}
