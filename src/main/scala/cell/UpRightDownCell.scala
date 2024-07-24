package cell

class UpRightDownCell  extends AbstractCell {
  private val pivot: Array[Int] = Array(1,1,1,0)
  private val tipo = 1
  def getPivot: Array[Int] = {
    pivot
  }

  def getTipo: Int = {
    tipo
  }
  
}
