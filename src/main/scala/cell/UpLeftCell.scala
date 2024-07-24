package cell

class UpLeftCell  extends AbstractCell {
  private val pivot: Array[Int] = Array(1,0,0,1)
  private val tipo = 8


  def getPivot:Array[Int] ={
    pivot
  }

  def getTipo: Int = {
    tipo
  }

}
