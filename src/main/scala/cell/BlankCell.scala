package cell

class BlankCell extends AbstractCell {
  private val pivot: Array[Int] = Array(0,0,0,0)
  private val tipo = 0
  

  def getPivot:Array[Int] ={
    pivot
  }
  
  def getTipo: Int = {
    tipo
  }
}
