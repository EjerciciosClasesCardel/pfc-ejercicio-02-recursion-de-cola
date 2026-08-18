package taller

import scala.annotation.tailrec

class Ejercicio() {

  // El esqueleto ya es recursivo de cola y compila: lo que falta es acumular.
  // Tal como está, siempre devuelve 0 y las pruebas quedan en rojo.
  @tailrec
  final def sumOfSquares(n: Int, acc: Int = 0): Int = {
    if (n <= 0) acc
    else sumOfSquares(n - 1, acc) // Completar: falta sumar el cuadrado de n
  }

  @tailrec
  final def sumOfNumbers(n: Int, acc: Int = 0): Int = {
    if (n <= 0) acc
    else sumOfNumbers(n - 1, acc) // Completar: falta sumar n cuando es par
  }
}
