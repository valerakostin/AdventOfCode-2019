package aoc.day03


import aoc.utils.Utils
import kotlin.math.abs
import kotlin.math.min

typealias Wire = List<Interval>

data class Point(val x: Int, val y: Int)
enum class Type { HORIZONTAL, VERTICAL }

object IntervalParser {
    fun parseIntervals(rawData: List<String>): List<Interval> {
        val intervals = mutableListOf<Interval>()

        for (rowString in rawData) {
            val str = rowString.trim()
            val start =
                    if (intervals.isEmpty())
                        Point(0, 0)
                    else
                        intervals.last().end

            val direction = str.substring(0, 1)
            val value = str.substring(1).toInt()

            when (direction) {
                "R" -> {
                    val end = Point(start.x + value, start.y)
                    intervals.add(Interval(start, end))
                }
                "L" -> {
                    val end = Point(start.x - value, start.y)
                    intervals.add(Interval(start, end))
                }
                "U" -> {
                    val end = Point(start.x, start.y + value)
                    intervals.add(Interval(start, end))
                }
                "D" -> {
                    val end = Point(start.x, start.y - value)
                    intervals.add(Interval(start, end))
                }
            }
        }
        return intervals
    }
}

fun Wire.distanceTo(interval: Interval): Int = this.takeWhile { it != interval }.sumBy { it.length() }

data class Interval(val start: Point, val end: Point) {

    private val type: Type = if (start.x == end.x) Type.VERTICAL else Type.HORIZONTAL

    fun length(): Int {
        return abs(this.end.x - this.start.x) + abs(this.end.y - this.start.y)
    }

    private fun xRange(): IntRange {
        return if (start.x < end.x)
            start.x..end.x
        else
            end.x..start.x
    }

    private fun yRange(): IntRange {
        return if (start.y < end.y)
            start.y..end.y
        else
            end.y..start.y
    }

    fun intersect(interval: Interval): Point? {
        if (type != interval.type) {
            if (type == Type.HORIZONTAL) {
                if (interval.start.x in xRange() && start.y in interval.yRange())
                    return Point(interval.start.x, start.y)
            } else if (interval.start.y in yRange() && start.x in interval.xRange())
                return Point(start.x, interval.start.y)
        }
        return null
    }

    fun distance(p: Point): Int = abs(start.x - p.x) + abs(start.y - p.y)
}

fun main() {
    val wires =
            Utils.linesFromResource("InputDay03.txt")
                    .map { IntervalParser.parseIntervals(it.split(",")) }.toList()
    val task1 = task1(wires[0], wires[1])
    val task2 = task2(wires[0], wires[1])

    println(
            """
              Day03
               Task1: $task1
               Task2: $task2
            """.trimIndent())
}

fun task1(wire1: Wire, wire2: Wire): Int {
    var minDistance = Integer.MAX_VALUE
    for (segment1 in wire1) {
        for (segment2 in wire2) {
            val intersectionPoint = segment1.intersect(segment2)
            intersectionPoint?.let {
                val currentDistance = abs(intersectionPoint.x) + abs(intersectionPoint.y)
                if (currentDistance > 0)
                    minDistance = min(minDistance, currentDistance)
            }
        }
    }
    return minDistance
}

fun task2(wire1: Wire, wire2: Wire): Int {

    var minDistance = Integer.MAX_VALUE

    for (interval1 in wire1) {
        for (interval2 in wire2) {
            val intersection = interval1.intersect(interval2)
            intersection?.let {
                val wire1Length = wire1.distanceTo(interval1)
                val wire2Length = wire2.distanceTo(interval2)

                val distToPoint1 = interval1.distance(intersection)
                val distToPoint2 = interval2.distance(intersection)

                val totalCurrentDistance = wire1Length + wire2Length + distToPoint1 + distToPoint2
                if (totalCurrentDistance > 0)
                    minDistance = min(minDistance, totalCurrentDistance)
            }
        }
    }

    return minDistance
}


