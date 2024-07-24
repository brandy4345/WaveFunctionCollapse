import controller.AlgoritmoController

class Accion {
  def run(): Unit = {
    val algorithm = new AlgoritmoController
    algorithm.start()
    algorithm.update()
    while(!algorithm.isEndOfLoop){
      algorithm.update()
    }
    println("Matrix colapsada:")
    algorithm.printMatrix()
    println("Entropy:")
    algorithm.printEntropy()
  }
}
