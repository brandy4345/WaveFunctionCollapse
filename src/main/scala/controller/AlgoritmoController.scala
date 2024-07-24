package controller

import States.{IState, RandomState}
import tiles.Tiles

import scala.collection.mutable.ListBuffer
import scala.util.Random

class AlgoritmoController {
  private val tamano = 30
  //(new Random().nextFloat()*100000000).toInt
  private val seed = 10
  private var state: IState = new RandomState()
  private val orderList: ListBuffer[Tiles] = ListBuffer[Tiles]()

  private val matrix: Array[Array[Tiles]] = Array.ofDim[Tiles](tamano, tamano)

  def start(): Unit = {
    for (i <- 0 until tamano; j <- 0 until tamano) {
      matrix(i)(j) = new Tiles
      orderList += matrix(i)(j)
    }
    for (i <- 0 until tamano; j <- 0 until tamano) {
      if (i < tamano - 1) {
        matrix(i)(j).addRightNeighbor(matrix(i + 1)(j))
      }
      if (i > 0) {
        matrix(i)(j).addLeftNeighbor(matrix(i - 1)(j))
      }
      if (j < tamano - 1) {
        matrix(i)(j).addDownNeighbor(matrix(i)(j + 1))
      }
      if (j > 0) {
        matrix(i)(j).addUpNeighbor(matrix(i)(j - 1))
      }
    }
  }
  def removeOrderList(tile:Tiles): Unit = {
    orderList-=tile
  }
  def getMinorEntropy: ListBuffer[Tiles] = {
    val minor = orderList.head.getEntropy
    val minorList = ListBuffer[Tiles]()
    var i = 0
    var isMinor = true
    while (i<orderList.length & isMinor){
      if(orderList(i).getEntropy>minor){
        isMinor = false
      }
      else {
        minorList+= orderList(i)
      }

      i+=1
    }
    minorList

  }

  def getTamano: Int = {
    tamano
  }
  def changeState(newState: IState):Unit = {
    state = newState
  }

  def removeTile(tile:Tiles): Unit = {
    orderList-=tile
  }
  def doOrderList(): Unit = {
    orderList.sortBy(_.getEntropy)
  }


  def update(): Unit = {
    state.update(this)
  }

  def getMatrix: Array[Array[Tiles]] = {
    matrix
  }
  def getSeed: Int = {
    seed
  }
  
  def isEndOfLoop: Boolean = {
    orderList.isEmpty
  }
  def printMatrix(): Unit = {
    for (i <- 0 until tamano) {
      for (j <- 0 until tamano) {
        print(s"${matrix(j)(i).getCell} ")
      }
      print("\n")
    }
  }
  def printEntropy():Unit = {
    for (i <- 0 until tamano) {
      for (j <- 0 until tamano) {
        print(s"${matrix(j)(i).getEntropy} ")
      }
      print("\n")
    }
  }

  def vaciar(): Unit = {
    orderList-=orderList.head
  }
}
