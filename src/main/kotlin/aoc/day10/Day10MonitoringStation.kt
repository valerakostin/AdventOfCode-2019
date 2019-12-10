package aoc.day10

import aoc.utils.Utils
import kotlin.math.abs

//data class Vector(private val start: Point, val end: Point) {
//    private val x = end.x - start.x
//    private val y = end.y - start.y


//    fun angle(v: Vector): Double {
//        return ((this.x * v.x + this.y * v.y).toDouble()) / (sqrt((this.x * this.x + this.y * this.y).toDouble()) * sqrt((v.x * v.x + v.y * v.y).toDouble()))
//    }
//}

data class Point(val x: Int, val y: Int) {

    fun between(a: Point, b: Point): Boolean {
        if (this == a || this == b)
            return false


        val crossProduct = (this.y - a.y) * (b.x - a.x) - (this.x - a.x) * (b.y - a.y)
        if (abs(crossProduct) > 0)
            return false

        val dotProduct = (this.x - a.x) * (b.x - a.x) + (this.y - a.y) * (b.y - a.y)
        if (dotProduct < 0)
            return false

        val squared = (b.x - a.x) * (b.x - a.x) + (b.y - a.y) * (b.y - a.y)
        if (dotProduct > squared)
            return false
        return true
    }

    object PointParser {
        fun readPoints(linesFromResource: List<String>): List<Point> {
            val points = mutableListOf<Point>()
            for (i in linesFromResource.indices) {
                for (j in linesFromResource[i].indices) {
                    val c = linesFromResource[i][j]
                    if (c == '#')
                        points.add(Point(i, j))
                }
            }
            return points
        }
    }
}


fun main() {

    val points =
            Point.PointParser.readPoints(Utils.linesFromResource("InputDay10.txt"))
    val connections = getConnections(points)
    val entry = connections.maxBy { it.value.count() }
    val task1 = entry?.value?.size

    // entry?.let {
    //     val start = it.key
    //     val base = Vector(start, Point(start.x, start.y - 1))

    //       val angles = it.value.map { p -> Pair(p, Vector(start, p).angle(base)) }.sortedBy { pair -> pair.second }
//}

    val task2 = task2()

    println(
            """
              Day10
               Task1: $task1
               Task2: $task2
            """.trimIndent())
}

internal fun getConnections(points: List<Point>): Map<Point, List<Point>> {
    val map = mutableMapOf<Point, MutableList<Point>>()
    for (start in points) {
        map[start] = mutableListOf()
        for (end in points) {
            if (start != end) {
                if (points.none { it.between(start, end) })
                    map[start]?.add(end)
            }
        }
    }
    return map
}


fun task2() {}
