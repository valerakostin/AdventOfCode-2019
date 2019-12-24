package aoc.day24

import aoc.utils.Utils
import org.junit.Test
import kotlin.test.assertEquals

internal class Day24PlanetOfDiscordKtTest {
    @Test
    fun `input should return 2129920`() {
        val str = """
            ....#
            #..#.
            #..##
            ..#..
            #....
            """.trimIndent()
        val initialState = str.split("\n").parseState()

        val task1 = task1(initialState)
        assertEquals(2129920, task1)
    }

    @Test
    fun `task 1 should return 18370591`() {
        val lines =
                Utils.linesFromResource("InputDay24.txt")
        val initialState = lines.parseState()

        val task1 = task1(initialState)
        assertEquals(18370591, task1)
    }
}