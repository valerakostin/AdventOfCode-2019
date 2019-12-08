package aoc.day05

import aoc.utils.Computer
import org.junit.Test
import kotlin.test.assertEquals

internal class Day05SunnyWithAChanceOfAsteroidsTest {
    @Test
    fun `answer for task 1 is 9025675`() {
        val program = Computer.ProgramReader.readProgram("InputDay05.txt")
        assertEquals(9025675, task1(program))
    }

    @Test
    fun `answer for task 2 is 11981754`() {
        val program = Computer.ProgramReader.readProgram("InputDay05.txt")
        assertEquals(11981754, task2(program))

    }
}