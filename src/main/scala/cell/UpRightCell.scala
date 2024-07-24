package cell

class UpRightCell extends AbstractCell {
  private val pivot: Array[Int] = Array(1,1,0,0)
  private val tipo = 5


  def getPivot:Array[Int] ={
    pivot
  }

  def getTipo: Int = {
    tipo
  }

}
