// Импортируем необходимые классы и пакеты
import java.util.*
import javax.swing.JFrame
import javax.swing.JPanel

// Создаем data-класс Point для представления точек в двумерном пространстве
data class Point(val x: Double, val y: Double)

// Создаем класс Triangle для представления треугольника с тремя вершинами
class Triangle(val vertex1: Point, val vertex2: Point, val vertex3: Point) {
    // Функция contains проверяет, находится ли заданная точка внутри треугольника
    fun contains(point: Point): Boolean {
        // Вычисляем знак для каждой из трех диагоналей треугольника
        val d1 = sign(point, vertex1, vertex2)
        val d2 = sign(point, vertex2, vertex3)
        val d3 = sign(point, vertex3, vertex1)

        // Проверяем условие, что все знаки одного знака или равны нулю
        val hasNeg = (d1 < 0) && (d2 < 0) && (d3 < 0)
        val hasPos = (d1 > 0) && (d2 > 0) && (d3 > 0)

        return !(hasNeg && hasPos)
    }

    // Вспомогательная функция sign для вычисления знака
    private fun sign(p1: Point, p2: Point, p3: Point): Double {
        return (p1.x - p3.x) * (p2.y - p3.y) - (p2.x - p3.x) * (p1.y - p3.y)
    }
}

// Создаем класс TrianglePanel, наследующий от JPanel, для отображения треугольника и точки
class TrianglePanel(val triangle: Triangle, val point: Point) : JPanel() {
    override fun paintComponent(g: java.awt.Graphics) {
        super.paintComponent(g)

        val g2d = g as java.awt.Graphics2D

        val width = size.width
        val height = size.height

        // Отображение треугольника 
        val x1 = width * (triangle.vertex1.x / 10.0)
        val y1 = height - height * (triangle.vertex1.y / 10.0)
        val x2 = width * (triangle.vertex2.x / 10.0)
        val y2 = height - height * (triangle.vertex2.y / 10.0)
        val x3 = width * (triangle.vertex3.x / 10.0)
        val y3 = height - height * (triangle.vertex3.y / 10.0)

        g2d.drawLine(x1.toInt(), y1.toInt(), x2.toInt(), y2.toInt())
        g2d.drawLine(x2.toInt(), y2.toInt(), x3.toInt(), y3.toInt())
        g2d.drawLine(x3.toInt(), y3.toInt(), x1.toInt(), y1.toInt())

        // Отображение точки 
        val px = width * (point.x / 10.0)
        val py = height - height * (point.y / 10.0)
        g2d.fillOval(px.toInt() - 3, py.toInt() - 3, 6, 6)
    }
}

// Функция generateRandomPoint для генерации случайной точки
fun generateRandomPoint(): Point {
    val random = Random()
    val x = random.nextDouble() * 10
    val y = random.nextDouble() * 10
    return Point(x, y)
}

// Функция generateRandomTriangle для генерации случайного треугольника
fun generateRandomTriangle(): Triangle {
    val random = Random()
    val vertex1 = generateRandomPoint()
    val vertex2 = generateRandomPoint()
    val vertex3 = generateRandomPoint()
    return Triangle(vertex1, vertex2, vertex3)
}

fun main() {
    // Генерируем случайный треугольник и точку
    val triangle = generateRandomTriangle()
    val point = generateRandomPoint()

    // Создаем графическое окно с отображением треугольника и точки
    val frame = JFrame("Triangle and Point")
    frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
    frame.setSize(400, 400)
    frame.setLocationRelativeTo(null)

    val panel = TrianglePanel(triangle, point)
    frame.add(panel)

    frame.isVisible = true

    // Проверяем, находится ли заданная точка внутри треугольника и выводим результат
    val isInside = triangle.contains(point)

    if (isInside) {
        println("Точка находится внутри треугольника")
    } else {
        println("Точка находится вне треугольника")
    }
}
