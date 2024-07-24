package tiles

import cell.{BlankCell, DownLeftCell, ICell, RightDownCell, RightDownLeftCell, UpDownLeftCell, UpLeftCell, UpRightCell, UpRightDownCell, UpRightLeftCell}
import controller.AlgoritmoController

import scala.collection.mutable
import scala.collection.mutable.Set
import scala.collection.mutable.ListBuffer
import scala.util.Random

class Tiles {
  // u = Up; r = Right; d = Down; l = Left

  private var uNeighbor: Option[Tiles] = None
  private var rNeighbor: Option[Tiles] = None
  private var dNeighbor: Option[Tiles] = None
  private var lNeighbor: Option[Tiles] = None

  private var collapsed = false
  private var cell: Option[ICell] = None 
  private var cells: ListBuffer[ICell] =
    ListBuffer(
      new BlankCell,
      new RightDownLeftCell,
      new UpDownLeftCell,
      new UpRightDownCell,
      new UpRightLeftCell,
      new UpRightCell,
      new RightDownCell,
      new DownLeftCell,
      new UpLeftCell
    )
  private var entropy = cells.length
  private var lastEntropy = entropy

  def getCell: Int = {
    if(cell.isDefined){
      cell.get.getTipo
    }else {
      -1
    }
  }

  def getCollapsed: Boolean = {
    collapsed
  }
  private def collapse(controller: AlgoritmoController): Unit = {
    if(entropy==1){
      cell = Some(cells.head)
      collapsed = true
      controller.removeTile(this)
    }
  }
  def randomCollapse(seed: Int): Unit = {
    val rand = new Random(seed).between(0,entropy)
    cell = Some(cells(rand))
    cells = ListBuffer(cell.get)
    entropy = 1
    collapsed = true
  }

  def getPivot(num: Int): ListBuffer[Int] = {
    val temp = ListBuffer[Int]()
    for (cell <- cells){
      temp += cell.getPivot(num)
    }
    temp
  }

  def notifyNeighbors(controller:AlgoritmoController): Unit = {
    if(lastEntropy!= entropy){
      lastEntropy = entropy
        //(tipo: 0 o 1 , dir: 0=up,1=right,2=down,3=left)
        if(uNeighbor.isDefined){
          if (!uNeighbor.get.getCollapsed){
            uNeighbor.get.receiveNotification(controller,getPivot(0),0)
          }else {
            uNeighbor = None
          }
        }
        if(rNeighbor.isDefined){
          if (!rNeighbor.get.getCollapsed) {
            rNeighbor.get.receiveNotification(controller,getPivot(1),1)
          } else {
            rNeighbor = None
          }
        }
        if (dNeighbor.isDefined) {
          if (!dNeighbor.get.getCollapsed) {
            dNeighbor.get.receiveNotification(controller, getPivot(2), 2)
          } else {
            dNeighbor = None
          }
        }
        if(lNeighbor.isDefined){
          if (!lNeighbor.get.getCollapsed) {
            lNeighbor.get.receiveNotification(controller, getPivot(3),3)
          } else {
            lNeighbor = None
          }
      }
    }
  }
  def receiveNotification(controller:AlgoritmoController,tipo:ListBuffer[Int], dir: Int): Unit = {
    removeCells(tipo,dir)
    collapse(controller)
    notifyNeighbors(controller)
  }

  //(tipo: 0 o 1 , dir: 0=up,1=right,2=down,3=left)
  private def removeCells(tipo:ListBuffer[Int], dir: Int):Unit = {
    val temp = mutable.Set[ICell]()

    for(i<- tipo){
      for (cel<-cells) {
        val tmp =cel.getPivot((dir+2)%4)==i
        if(tmp){
          temp += cel
        }
      }
    }
    cells = ListBuffer(temp.toSeq: _*)
    reduceEntropy()
  }
  def getEntropy: Int = {
    entropy
  }
  private def reduceEntropy(): Unit ={
    entropy= cells.length
  }
  def addUpNeighbor(tile:Tiles):Unit = {
    uNeighbor = Some(tile)
  }

  def addRightNeighbor(tile: Tiles): Unit = {
    rNeighbor = Some(tile)
  }

  def addDownNeighbor(tile: Tiles): Unit = {
    dNeighbor = Some(tile)
  }

  def addLeftNeighbor(tile: Tiles): Unit = {
    lNeighbor = Some(tile)
  }


}
