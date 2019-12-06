package aoc.day06

import aoc.utils.Utils
import org.junit.Test
import kotlin.test.assertEquals

internal class Day06UniversalOrbitMapKtTest {

    @Test
    fun `D directly orbits C and indirectly orbits B and COM, a total of 3 orbits`() {
        val mapping = task1ExampleConnections()
        assertEquals(3, connectionCount(mapping["D"], mapping))
    }

    @Test
    fun `L directly orbits K and indirectly orbits J, E, D, C, B, and COM, a total of 7 orbits`() {
        val mapping = task1ExampleConnections()
        assertEquals(7, connectionCount(mapping["L"], mapping))
    }

    @Test
    fun `COM orbits nothing`() {
        val mapping = task1ExampleConnections()
        assertEquals(0, connectionCount(mapping["COM"], mapping))
    }

    @Test
    fun `The total number of direct and indirect orbits in this example is 42`() {
        val connections = task1ExampleConnections()
        assertEquals(42, task1(connections))
    }

    @Test
    fun `Task 1 the puzzle answer is 315757`() {
        val connections = puzzleInput()
        assertEquals(315757, task1(connections))
    }

    @Test
    fun `Task2 The minimum number of orbital transfers between SAN and YOU is 4`() {

        assertEquals(4, task2(task2ExampleConnections2(), "SAN", "YOU"))
    }

    @Test
    fun `Task 2 correct input is 481`() {
        val connections = puzzleInput()
        assertEquals(481, task2(connections, "SAN", "YOU"))
    }

    private fun task1ExampleConnections(): Map<String, String> {
        return mapOf(
                "B" to "COM",
                "C" to "B",
                "D" to "C",
                "E" to "D",
                "F" to "E",
                "G" to "B",
                "H" to "G",
                "I" to "D",
                "J" to "E",
                "K" to "J",
                "L" to "K")
    }

    private fun task2ExampleConnections2(): Map<String, String> {
        return mapOf(
                "B" to "COM",
                "C" to "B",
                "D" to "C",
                "E" to "D",
                "F" to "E",
                "G" to "B",
                "H" to "G",
                "I" to "D",
                "J" to "E",
                "K" to "J",
                "L" to "K",
                "YOU" to "K",
                "SAN" to "I")
    }

    private fun puzzleInput(): Map<String, String> {
        return Utils.linesFromResource("InputDay06.txt").map { it.split(")") }
                .map { it[1] to it[0] }
                .toMap()
    }
}