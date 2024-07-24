package cell

class RightDownLeftCell  extends AbstractCell {
  private val pivot: Array[Int] = Array(0,1,1,1)
  private val tipo = 2
  def getPivot: Array[Int] = {
    pivot
  }

  def getTipo: Int = {
    tipo
  }
  
}
