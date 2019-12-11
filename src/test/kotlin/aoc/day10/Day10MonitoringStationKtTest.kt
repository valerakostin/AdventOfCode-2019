package aoc.day10

import aoc.utils.Utils
import org.junit.Test
import kotlin.test.assertEquals

internal class Day10MonitoringStationKtTest {
    /*
    .7..7
    .....
    67775
    ....7
    ...87
     */
    @Test
    fun `The best location for a new monitoring station on this map is the highlighted asteroid at 3,4 because it can detect 8 asteroids`() {
        val points = listOf(
                Point(1, 0), Point(4, 0),
                Point(0, 2), Point(1, 2), Point(2, 2), Point(3, 2), Point(4, 2),
                Point(4, 3),
                Point(3, 4), Point(4, 4)
        )

        val connections = getConnections(points)
        assertEquals(7, connections[Point(1, 0)]?.size)
        assertEquals(7, connections[Point(4, 0)]?.size)
        assertEquals(6, connections[Point(0, 2)]?.size)
        assertEquals(7, connections[Point(1, 2)]?.size)
        assertEquals(7, connections[Point(2, 2)]?.size)
        assertEquals(7, connections[Point(3, 2)]?.size)
        assertEquals(5, connections[Point(4, 2)]?.size)
        assertEquals(7, connections[Point(4, 3)]?.size)
        assertEquals(8, connections[Point(3, 4)]?.size)
        assertEquals(7, connections[Point(4, 4)]?.size)

    }

    @Test
    fun `test task 1 should return 260`() {
        val pair = task1()
        val task1 = pair.second.size
        assertEquals(260, task1)
    }

    @Test
    fun `test task 2 should return 608`() {
        val pair = task1()
        val task2 = task2(199, pair)
        assertEquals(608, task2)
    }


    @Test
    fun `test asteroid vaporization order`() {
        val points =
                Point.PointParser.readPoints(Utils.linesFromResource("InputDay10_test.txt"))
        val connections = getConnections(points)
        val input = connections.maxBy { it.value.count() }!!.toPair()
        assertEquals(Point(11, 12), getAsteroidAt(input, 0))
        assertEquals(Point(12, 1), getAsteroidAt(input, 1))
        assertEquals(Point(12, 2), getAsteroidAt(input, 2))
        assertEquals(Point(12, 8), getAsteroidAt(input, 9))
        assertEquals(Point(16, 0), getAsteroidAt(input, 19))
        assertEquals(Point(16, 9), getAsteroidAt(input, 49))
        assertEquals(Point(10, 16), getAsteroidAt(input, 99))
        assertEquals(Point(9, 6), getAsteroidAt(input, 198))
        assertEquals(Point(8, 2), getAsteroidAt(input, 199))
        assertEquals(Point(10, 9), getAsteroidAt(input, 200))
    }
}