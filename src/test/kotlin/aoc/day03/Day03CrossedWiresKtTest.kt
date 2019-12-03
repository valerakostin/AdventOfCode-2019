package aoc.day03

import aoc.utils.Utils
import org.junit.Assert.assertEquals
import org.junit.Test

internal class Day03CrossedWiresKtTest {
    @Test
    fun `Task1 wire path R8,U5,L5,D3 and wire path U7,R6,D4,L4 have closest distance 6`() {
        val wire1 = parseWire("R8,U5,L5,D3")
        val wire2 = parseWire("U7,R6,D4,L4")

        assertEquals(6, task1(wire1, wire2))
    }


    @Test
    fun `Task1 wire path R75,D30,R83,U83,L12,D49,R71,U7,L72 and wire path U62,R66,U55,R34,D71,R55,D58,R83 have closest distance 159`() {
        val wire1 = parseWire("R75,D30,R83,U83,L12,D49,R71,U7,L72")
        val wire2 = parseWire("U62,R66,U55,R34,D71,R55,D58,R83")

        assertEquals(159, task1(wire1, wire2))
    }


    @Test
    fun `Task1 wire path R98,U47,R26,D63,R33,U87,L62,D20,R33,U53,R51 and wire path U98,R91,D20,R16,D67,R40,U7,R15,U6,R7 have closest distance 135`() {
        val wire1 = parseWire("R98,U47,R26,D63,R33,U87,L62,D20,R33,U53,R51")
        val wire2 = parseWire("U98,R91,D20,R16,D67,R40,U7,R15,U6,R7")

        assertEquals(135, task1(wire1, wire2))
    }

    @Test
    fun `task 1 with puzzle input should return 303`() {
        val wires =
                Utils.linesFromResource("InputDay03.txt")
                        .map { IntervalParser.parseIntervals(it.split(",")) }.toList()
        assertEquals(303, task1(wires[0], wires[1]))
    }


    @Test
    fun `Task2 wire path R8,U5,L5,D3 and wire path U7,R6,D4,L4 should have 40 steps`() {
        val wire1 = parseWire("R8,U5,L5,D3")
        val wire2 = parseWire("U7,R6,D4,L4")

        assertEquals(30, task2(wire1, wire2))
    }


    @Test
    fun `Task2 wire path R75,D30,R83,U83,L12,D49,R71,U7,L72 and wire path U62,R66,U55,R34,D71,R55,D58,R83 should have 610 steps`() {
        val wire1 = parseWire("R75,D30,R83,U83,L12,D49,R71,U7,L72")
        val wire2 = parseWire("U62,R66,U55,R34,D71,R55,D58,R83")

        assertEquals(610, task2(wire1, wire2))
    }


    @Test
    fun `Task2 wire path R98,U47,R26,D63,R33,U87,L62,D20,R33,U53,R51 and wire path U98,R91,D20,R16,D67,R40,U7,R15,U6,R7 should have 410 steps`() {
        val wire1 = parseWire("R98,U47,R26,D63,R33,U87,L62,D20,R33,U53,R51")
        val wire2 = parseWire("U98,R91,D20,R16,D67,R40,U7,R15,U6,R7")

        assertEquals(410, task2(wire1, wire2))
    }

    @Test
    fun `task 2 with puzzle input should return 11222`() {
        val wires =
                Utils.linesFromResource("InputDay03.txt")
                        .map { IntervalParser.parseIntervals(it.split(",")) }.toList()
        assertEquals(11222, task2(wires[0], wires[1]))
    }


    private fun parseWire(rawData: String): List<Interval> {
        return IntervalParser.parseIntervals(rawData.split(",")).toList()
    }
}