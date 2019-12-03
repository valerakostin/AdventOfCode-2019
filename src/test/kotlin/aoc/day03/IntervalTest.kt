package aoc.day03

import org.junit.Test
import kotlin.test.assertEquals

internal class IntervalTest {
    @Test
    fun `test length of interval 0,0 - 0,5 is 5`() {
        assertEquals(5, Interval(Point(0, 0), Point(0, 5)).length())
    }

    @Test
    fun `test intersection point (0,0)-(5,0) and (4,4)-(4,-4)`() {
        val interval1 = Interval(Point(0, 0), Point(5, 0))
        val interval2 = Interval(Point(4, 4), Point(4, -4))
        val point = interval1.intersect(interval2)

        assertEquals(Point(4, 0), point)
    }

    @Test
    fun `test intersection point (4,4)-(4,-4) and (0,0)-(5,0)`() {
        val interval1 = Interval(Point(4, 4), Point(4, -4))
        val interval2 = Interval(Point(0, 0), Point(5, 0))
        val point = interval1.intersect(interval2)

        assertEquals(Point(4, 0), point)
    }
}