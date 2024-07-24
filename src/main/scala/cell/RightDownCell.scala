package cell

class RightDownCell  extends AbstractCell {
  private val pivot: Array[Int] = Array(0,1,1,0)
  private val tipo = 6


  def getPivot:Array[Int] ={
    pivot
  }

  def getTipo: Int = {
    tipo
  }

}
