# Ejercicio 2 — Recursión de cola

Fundamentos de Programación Funcional y Concurrente
Escuela de Ingeniería de Sistemas y Computación, Universidad del Valle
Carlos Andrés Delgado Saavedra

Dos sumatorias que se pueden escribir de forma recursiva de dos maneras: una
que deja operaciones pendientes y otra que no. Este ejercicio pide la segunda.

## Qué es una recursión de cola

Una función es recursiva de cola cuando la llamada recursiva es lo último que
hace, y su resultado se devuelve tal cual, sin combinarlo con nada. Compare
las dos formas de escribir el factorial:

```scala
// Recursión lineal: al volver todavía hay que multiplicar por n.
def factorial(n: Int): Int =
  if (n == 0) 1 else n * factorial(n - 1)

// Recursión de cola: el producto se calcula antes de llamar y viaja
// en el acumulador. Al volver no queda nada pendiente.
@tailrec
final def factorial(n: Int, acc: Int = 1): Int =
  if (n == 0) acc else factorial(n - 1, n * acc)
```

La diferencia se nota en el espacio. La primera versión deja `n` operaciones
esperando y con una entrada grande agota la pila. La segunda corre en espacio
constante, porque el compilador la traduce a un salto.

La anotación `@tailrec` es la red de seguridad: si la llamada no queda en
posición de cola, el programa no compila. Úsela siempre; equivocarse en
silencio es peor que no compilar.

## Lo que hay que resolver

Todo va en `app/src/main/scala/taller/Ejercicio.scala`. Las dos funciones
llevan un acumulador con valor por defecto, de modo que quien las use pueda
llamarlas con un solo argumento.

### Punto 1: suma de cuadrados

Devuelve la suma de los cuadrados de los enteros entre 1 y `n`.

```scala
@tailrec
final def sumOfSquares(n: Int, acc: Int = 0): Int
```

| Llamada | Resultado | Por qué |
|---|---|---|
| `sumOfSquares(3)` | 14 | 1 + 4 + 9 |
| `sumOfSquares(5)` | 55 | 1 + 4 + 9 + 16 + 25 |
| `sumOfSquares(6)` | 91 | lo anterior más 36 |
| `sumOfSquares(-2)` | 0 | no hay ningún entero entre 1 y -2 |

### Punto 2: suma de los pares

Devuelve la suma de los números **pares** entre 1 y `n`. Los impares no
cuentan.

```scala
@tailrec
final def sumOfNumbers(n: Int, acc: Int = 0): Int
```

| Llamada | Resultado | Por qué |
|---|---|---|
| `sumOfNumbers(3)` | 2 | el único par hasta 3 es el 2 |
| `sumOfNumbers(5)` | 6 | 2 + 4 |
| `sumOfNumbers(6)` | 12 | 2 + 4 + 6 |
| `sumOfNumbers(-2)` | 0 | no hay pares en un intervalo vacío |

Fíjese en el último caso de cada punto: con `n` negativo el intervalo está
vacío y la respuesta es el neutro de la suma. Ese es el caso base, y conviene
escribirlo antes que el resto.

## Cómo está organizado el proyecto

```
app/src/main/scala/taller/
    App.scala          programa de arranque
    Ejercicio.scala    aquí van los dos puntos

app/src/test/scala/taller/
    AppSuite.scala        comprueba que el entorno quedó bien
    EjercicioTest1.scala  punto 1
    EjercicioTest2.scala  punto 2
```

Su código va en `main`. Las pruebas viven aparte y no se tocan.

## Cómo se ejecuta

```bash
./gradlew test    # corre las pruebas
```

Las pruebas arrancan en rojo y el trabajo es ponerlas en verde. El informe
completo queda en `app/build/reports/tests/test/index.html`.

## Cómo se trabaja

1. Haga fork de este repositorio.
2. En su fork, abra la pestaña **Actions** y habilítelas. GitHub las deja
   desactivadas en las copias hasta que el dueño lo confirme.
3. Clone, resuelva, haga commit y suba a `main`.
4. Verifique en **Actions** que la última ejecución quedó en verde.

## Restricciones

Este curso trabaja sin estado mutable: nada de `var`, `while`, `return` ni
variables que cambien. El resultado correcto por el camino equivocado no
cuenta como resultado correcto.
