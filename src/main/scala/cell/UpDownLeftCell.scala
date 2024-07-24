package cell

class UpDownLeftCell  extends AbstractCell {
  private val pivot: Array[Int] = Array(1,0,1,1)
  private val tipo = 3

  def getPivot: Array[Int] = {
    pivot
  }

  def getTipo: Int = {
    tipo
  }
}
