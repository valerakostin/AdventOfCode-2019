package aoc.day10

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
}