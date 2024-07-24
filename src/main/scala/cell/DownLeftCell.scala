package cell

class DownLeftCell  extends AbstractCell {
  private val pivot: Array[Int] = Array(0,0,1,1)
  private val tipo = 7


  def getPivot:Array[Int] ={
    pivot
  }

  def getTipo: Int = {
    tipo
  }

}
