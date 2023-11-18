import kotlin.math.pow
import kotlin.math.sqrt
import kotlin.random.Random

// Определение класса Point для представления точек в двумерном пространстве
class Point(val x: Int, val y: Int)

fun main() {
    // Генерация случайного количества точек
    val numOfPoints = Random.nextInt(3, 8)
    val points = mutableListOf<Point>()

    // Создание и добавление случайных точек в список
    repeat(numOfPoints) {
        val x = Random.nextInt(0, 10)
        val y = Random.nextInt(0, 10)
        val point = Point(x, y)
        points.add(point)
    }

    val distances = mutableListOf<Double>()

    // Вычисление расстояний между всеми парами точек
    for (i in 0 until points.size - 1) {
        for (j in i + 1 until points.size) {
            val distance = calculateDistance(points[i], points[j])
            distances.add(distance)
        }
    }

    // Нахождение минимального и максимального расстояния
    val minDistance = distances.minOrNull()
    val maxDistance = distances.maxOrNull()

    println("Минимальное расстояние: $minDistance")
    println("Максимальное расстояние: $maxDistance")

    // Вывод графического представления точек на плоскости
    printGraph(points)
}

// Функция calculateDistance для вычисления расстояния между двумя точками
fun calculateDistance(point1: Point, point2: Point): Double {
    val dx = point2.x - point1.x
    val dy = point2.y - point1.y
    return sqrt(dx.toDouble().pow(2) + dy.toDouble().pow(2))
}

// Функция printGraph для вывода графического представления точек на плоскости
fun printGraph(points: List<Point>) {
    // Создание матрицы символов для представления графика
    val graph = Array(10) { CharArray(10) { '.' } }

    // Установка символов для точек на графике
    for (i in 0 until points.size) {
        val point = points[i]
        val label = (i + 1).toString()
        graph[point.y][point.x] = label[0]
    }

    // Вывод графика на экран
    for (row in graph) {
        println(row.joinToString(" "))
    }
}

