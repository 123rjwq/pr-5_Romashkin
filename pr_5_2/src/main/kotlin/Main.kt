import kotlin.math.sqrt
import kotlin.random.Random

// Определение класса Point для представления точек в двумерном пространстве
class Point(val x: Int, val y: Int)

fun main() {
    // Создание двух случайных точек
    val point1 = Point(Random.nextInt(0, 10), Random.nextInt(0, 10))
    val point2 = Point(Random.nextInt(0, 10), Random.nextInt(0, 10))

    // Вычисление расстояния между точками
    val distance = calculateDistance(point1, point2)
    println("Расстояние между точками: $distance")

    // Вывод графического представления точек
    printGraph(point1, point2)
}

// Функция calculateDistance для вычисления расстояния между двуми точками
fun calculateDistance(point1: Point, point2: Point): Double {
    val xDiff = point2.x - point1.x
    val yDiff = point2.y - point1.y
    return sqrt((xDiff * xDiff + yDiff * yDiff).toDouble())
}

// Функция printGraph для вывода графического представления двух точек на координатной сетке
fun printGraph(point1: Point, point2: Point) {
    // Определение максимальных значений по осям x и y
    val maxX = maxOf(point1.x, point2.x)
    val maxY = maxOf(point1.y, point2.y)

    // Создание матрицы символов для представления графика
    val graph = Array(maxY + 1) { CharArray(maxX + 1) { '.' } }

    // Установка символов для точек на графике
    graph[point1.y][point1.x] = '1'
    graph[point2.y][point2.x] = '2'

    // Вывод графика на экран
    for (i in graph.indices) {
        for (j in graph[i].indices) {
            print(graph[i][j])
        }
        println()
    }
}
