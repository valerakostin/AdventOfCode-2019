package aoc.day10

import aoc.utils.Utils
import kotlin.math.abs
import kotlin.math.atan2

data class Vector(private val start: Point, val end: Point) {
    private val x = end.x - start.x
    private val y = end.y - start.y

    fun angle(v: Vector): Double {
        val dot = this.x * v.x + this.y * v.y
        val det = this.x * v.y - this.y * v.x
        return atan2(det.toDouble(), dot.toDouble())
    }
}

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
                        points.add(Point(j, i))
                }
            }
            return points
        }
    }
}


fun main() {

    val pair = task1()
    val task1 = pair.second.size

    val task2 = task2(199, pair)

    println(
            """
              Day10
               Task1: $task1
               Task2: $task2
            """.trimIndent())
}

internal fun task1(): Pair<Point, List<Point>> {
    val points =
            Point.PointParser.readPoints(Utils.linesFromResource("InputDay10.txt"))
    val connections = getConnections(points)
    return connections.maxBy { it.value.count() }!!.toPair()
}

fun task2(i: Int, input: Pair<Point, List<Point>>): Int {
    val asteroidPos = getAsteroidAt(input, i)
    return asteroidPos.x * 100 + asteroidPos.y
}

internal fun getAsteroidAt(input: Pair<Point, List<Point>>, i: Int): Point {
    val angles = getSortedAngles(input)
    return angles[i].first
}

internal fun getSortedAngles(input: Pair<Point, List<Point>>): List<Pair<Point, Double>> {
    val start = input.first
    val base = Vector(start, Point(start.x, start.y - 1))
    return input.second
            .map { p -> Pair(p, Math.toDegrees(Vector(p, start).angle(base))) }
            .sortedBy { pair -> pair.second }.reversed()
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

