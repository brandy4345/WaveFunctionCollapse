package cell

class UpRightLeftCell extends ICell {
  private val pivot: Array[Int] = Array(1, 1, 0, 1)
  private val tipo = 4

  def getPivot: Array[Int] = {
    pivot
  }

  def getTipo: Int = {
    tipo
  }
}
